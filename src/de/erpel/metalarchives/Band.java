package de.erpel.metalarchives;

public class Band {
	private final int id;
	private final boolean summary;
	private String name;
	private String country;//TODO Country
	private String countryShort;
	private String location;
	private String status;
	private int formedIn;
	private String yearsActive;
	private String genre;
	private String lyricalThemes;
	private String label;//TODO Label
	private String description;
	//private ? logo;
	//private Collection<?> photos;
	//releases
	//members
	//similar
	//links
	
	Band(int id, boolean summary) {
		this.id = id;
		this.summary = summary;
	}
	
	public String getName() {
		return this.name;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	void setCountry(String country) {
		this.country = country;
	}
	
	public String getCountryShort() {
		return this.countryShort;
	}
	
	void setCountryShort(String countryShort) {
		this.countryShort = countryShort;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	void setLocation(String location) {
		this.location = location;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	void setStatus(String status) {
		this.status = status;
	}
	
	public int getFormedIn() {
		return this.formedIn;
	}
	
	void setFormedIn(int formedIn) {
		this.formedIn = formedIn;
	}
	
	public String getYearsActive() {
		return this.yearsActive;
	}
	
	void setYearsActive(String yearsActive) {
		this.yearsActive = yearsActive;
	}
	
	public String getGenre() {
		return this.genre;
	}
	
	void setGenre(String genre) {
		this.genre = genre;
	}
	
	public String getLyricalThemes() {
		return this.lyricalThemes;
	}
	
	void setLyricalThemes(String lyricalThemes) {
		this.lyricalThemes = lyricalThemes;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	void setLabel(String label) {
		this.label = label;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	void setDescription(String description) {
		this.description = description;
	}
	
	public int getId() {
		return this.id;
	}
	
	public boolean isSummary() {
		return this.summary;
	}
}
