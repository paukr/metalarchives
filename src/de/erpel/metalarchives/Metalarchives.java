package de.erpel.metalarchives;

import java.io.IOException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Metalarchives {
	private final static Logger LOGGER = LoggerFactory
		.getLogger(Metalarchives.class);
		
	public Collection<Band> searchBand(String search) throws IOException, InterruptedException {
		return BandSearch.search(search);
	}
	
	public Band getBand(long id) throws IOException {
		return BandSearch.getBand(id);
	}
}
