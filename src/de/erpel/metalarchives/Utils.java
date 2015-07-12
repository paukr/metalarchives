package de.erpel.metalarchives;

import org.jsoup.nodes.Element;

class Utils {
	public static Band createBandFromAnchor(Element anchor) {
		String url = anchor.attr("href");
		long id = Long.valueOf(url.substring(url.lastIndexOf("/") + 1));
		Band band = new Band(id);
		band.setName(anchor.text());
		return band;
	}
}
