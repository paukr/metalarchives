package de.erpel.metalarchives;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

class BandNameSearch {
	private static String BAND_SEARCH = "http://www.metal-archives.com/search?searchString=%s&type=band_name";
	private static String BAND_READ_MORE = "http://www.metal-archives.com/band/read-more/id/%d";
	private static String BAND = "http://www.metal-archives.com/bands/id/%d";
	private static Logger LOGGER = LoggerFactory
		.getLogger(BandNameSearch.class);
		
	private List<Band> bands = new ArrayList<>();
	
	public BandNameSearch(String search) throws IOException {
		LOGGER.debug("band search: {}", search);
		WebClient client = null;
		HtmlPage page = null;
		try {
			client = new WebClient();
			page = client.getPage(String.format(BAND_SEARCH, search));
		} finally {
			if (client != null) {
				client.close();
			}
		}
		parseDoc(Jsoup.parse(page.asXml()));
	}
	
	private void parseDoc(Document searchResult) throws IOException {
		Elements searchResultsInfo = searchResult
			.select("div#searchResults_info");
		if (searchResultsInfo.size() > 0) {
			String info = searchResultsInfo.first().text();
			int start = info.lastIndexOf("of") + 2;
			int end = info.lastIndexOf("entries");
			int count = Integer.valueOf(info.substring(start, end));
			LOGGER.debug("{} results found", count);
			if (count > 0) {
				Element table = searchResult.select("div#searchResults")
					.first();
				parseSearchResults(table);
			}
		} else if (searchResult.select("div#band_info").size() > 0) {
			LOGGER.debug("single result found");
			parseBand(searchResult);
		} else {
			throw new IllegalStateException("unknown search result");
		}
	}
	
	private void parseSearchResults(Element table) {
		LOGGER.debug("parsing table");
		for (Element row : table.select("tr")) {
			LOGGER.debug("parsing row: {}", row.outerHtml());
			Elements data = row.select("td");
			Band band = parseBandName(data.get(0).child(0), true);
			band.setGenre(data.get(1).text());
			band.setCountry(data.get(2).text());
			this.bands.add(band);
		}
	}
	
	private void parseBand(Document docBand) throws IOException {
		LOGGER.debug("parsing band");
		Elements bandInfo = docBand.select("div#band_info");
		Band band = parseBandName(bandInfo.select("h1#band_name")
			.first()
			.child(0), false);
			
		Element bandStats = bandInfo.select("div#band_stats").first();
		parseFloatLeft(bandStats.select("dl.float_left").first(), band);
		parseFloatRight(bandStats.select("dl.float_right").first(), band);
		parseClear(bandStats.select("dl.clear").first(), band);
		
		LOGGER.debug("parsing description");
		Document docDescription = Jsoup
			.connect(String.format(BAND_READ_MORE, band.getId())).get();
		band.setDescription(docDescription.text());
		this.bands.add(band);
	}
	
	private static Band parseBandName(Element anchor, boolean summary) {
		String url = anchor.attr("href");
		int id = Integer.valueOf(url.substring(url.lastIndexOf("/")));
		Band band = new Band(id, summary);
		band.setName(anchor.text());
		return band;
	}
	
	private static void parseFloatLeft(Element floatLeft, Band band) {
		LOGGER.debug("parsing left");
		Elements dds = floatLeft.select("dd");
		
		Element country = dds.get(0);
		String url = country.attr("href");
		band.setCountryShort(url.substring(url.lastIndexOf("/")));
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
	
	public Collection<Band> getBands() {
		return this.bands;
	}
}
