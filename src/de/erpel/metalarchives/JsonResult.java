package de.erpel.metalarchives;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

class JsonResult {
	private String error;
	@SuppressWarnings("unused")
	private int iTotalRecords;
	@SuppressWarnings("unused")
	private int iTotalDisplayRecords;
	@SuppressWarnings("unused")
	private String sEcho;
	private String[][] aaData;
	
	public boolean hasError() {
		return this.error == null || this.error.trim().length() == 0;
	}
	
	public String getError() {
		return this.error;
	}
	
	public List<Band> getBands() {
		List<Band> bands = new ArrayList<>();
		for (String[] data : this.aaData) {
			Element anchor = Jsoup.parse(data[0]).select("a").first();
			Band band = Utils.createBandFromAnchor(anchor);
			band.setSummary(true);
			band.setGenre(data[1]);
			band.setCountry(data[2]);
			bands.add(band);
		}
		return bands;
	}
}
