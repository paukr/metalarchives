package de.erpel.metalarchives;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collection;

import org.junit.Test;

public class BandNameSearchTests {
	
	@Test
	public void acdc() throws IOException, InterruptedException {
		Collection<Band> bands = BandSearch.search("acdc");
		assertEquals("expecting 0", 0, bands.size());
	}
	
	@Test
	public void slayer() throws IOException, InterruptedException {
		Collection<Band> bands = BandSearch.search("slayer");
		assertEquals(7, bands.size());
	}
	
	@Test
	public void taake() throws IOException, InterruptedException {
		Collection<Band> bands = BandSearch.search("taake");
		assertEquals(1, bands.size());
	}
	
	@Test
	public void war() throws IOException, InterruptedException {
		Collection<Band> bands = BandSearch.search("war");
		assertTrue(bands.size() > 260);
	}
	
	
	@Test
	public void slayerId() throws IOException, InterruptedException {
		Band band = BandSearch.getBand(72);
		assertEquals(72, band.getId());
		assertEquals("Slayer", band.getName());
	}
}
