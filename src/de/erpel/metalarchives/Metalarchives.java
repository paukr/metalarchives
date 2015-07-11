package de.erpel.metalarchives;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Metalarchives {
	private final static Logger LOGGER = LoggerFactory
		.getLogger(Metalarchives.class);
		
	public Collection<Band> searchBand(String search) {
		try {
			BandNameSearch bs = new BandNameSearch(search);
			return bs.getBands();
		} catch (IOException e) {
			LOGGER.error("band search failed", e);
		}
		return new ArrayList<Band>(0);
	}
}
