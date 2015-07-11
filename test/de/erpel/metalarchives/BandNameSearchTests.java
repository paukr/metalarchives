package de.erpel.metalarchives;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class BandNameSearchTests {
	
	@Test
	public void acdc() throws IOException {
		BandNameSearch bs = new BandNameSearch("acdc");
		assertEquals("expecting 0", 0, bs.getBands().size());
	}
	
	@Test
	public void slayer() throws IOException {
		BandNameSearch bs = new BandNameSearch("slayer");
		assertEquals("expecting 7", 7, bs.getBands().size());
	}
	
	@Test
	public void taake() throws IOException {
		BandNameSearch bs = new BandNameSearch("taake");
		assertEquals("expecting 1", 1, bs.getBands().size());
	}
}
