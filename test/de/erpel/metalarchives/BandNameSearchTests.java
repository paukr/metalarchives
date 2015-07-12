package de.erpel.metalarchives;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collection;

import org.junit.Test;

public class BandNameSearchTests {
	
	@Test
	public void acdc() throws IOException, InterruptedException {
		Collection<Band> bands = BandNameSearch.search("acdc");
		assertEquals("expecting 0", 0, bands.size());
	}
	
	@Test
	public void slayer() throws IOException, InterruptedException {
		Collection<Band> bands = BandNameSearch.search("slayer");
		assertEquals("expecting 7", 7, bands.size());
	}
	
	@Test
	public void taake() throws IOException, InterruptedException {
		Collection<Band> bands = BandNameSearch.search("taake");
		assertEquals("expecting 1", 1, bands.size());
	}
}
