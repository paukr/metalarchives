package de.erpel.metalarchives;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

class BandNameSearch {
	private static String BAND_SEARCH = "http://www.metal-archives.com/search/ajax-band-search/?field=name&query=%s";
	private static String BAND_READ_MORE = "http://www.metal-archives.com/band/read-more/id/%d";
	private static String BAND = "http://www.metal-archives.com/bands/id/%d";
	private static Logger LOGGER = LoggerFactory
		.getLogger(BandNameSearch.class);
		
	public static List<Band> search(String search) throws IOException, InterruptedException {
		LOGGER.debug("band search: {}", search);
		search = search.replaceAll("\\s", "+");
		String json = Jsoup.connect(String.format(BAND_SEARCH, search))
			.ignoreContentType(true)
			.execute()
			.body();
			
		JsonResult result = new Gson().fromJson(json, JsonResult.class);
		List<Band> bands = result.getBands();
		if (bands.size() == 1) {
			Band band = getBand(bands.get(0).getId());
			bands.clear();
			bands.add(band);
		}
		return bands;
	}
	
	public static Band getBand(int id) throws IOException {
		Document bandDocument = Jsoup.connect(String.format(BAND, id)).get();
		return parseBand(bandDocument);
	}
	
	private static Band parseBand(Document searchResult) throws IOException {
		LOGGER.debug("parsing band");
		Elements bandInfo = searchResult.select("div#band_info");
		Element anchor = bandInfo.select("h1.band_name > a").first();
		Band band = Utils.createBandFromAnchor(anchor);
		
		Element bandStats = bandInfo.select("div#band_stats").first();
		parseFloatLeft(bandStats.select("dl.float_left").first(), band);
		parseFloatRight(bandStats.select("dl.float_right").first(), band);
		parseClear(bandStats.select("dl.clear").first(), band);
		
		LOGGER.debug("parsing description");
		Document docDescription = Jsoup
			.connect(String.format(BAND_READ_MORE, band.getId())).get();
		band.setDescription(docDescription.body().text());
		return band;
	}
	
	private static void parseFloatLeft(Element floatLeft, Band band) {
		LOGGER.debug("parsing left");
		Elements dds = floatLeft.select("dd");
		
		Element country = dds.get(0).select("a").first();
		String url = country.attr("href");
		band.setCountryShort(url.substring(url.lastIndexOf("/") + 1));
		band.setCountry(country.text());
		
		band.setLocation(dds.get(1).text());
		band.setStatus(dds.get(2).text());
		band.setFormedIn(Integer.valueOf(dds.get(3).text()));
	}
	
	private static void parseFloatRight(Element floatRight, Band band) {
		LOGGER.debug("parsing right");
		Elements dds = floatRight.select("dd");
		
		band.setGenre(dds.get(0).text());
		band.setLyricalThemes(dds.get(1).text());
		band.setLabel(dds.get(2).child(0).text());
	}
	
	private static void parseClear(Element clear, Band band) {
		LOGGER.debug("parsing bottom");
		Elements dds = clear.select("dd");
		
		band.setYearsActive(dds.get(0).text());
	}
}
