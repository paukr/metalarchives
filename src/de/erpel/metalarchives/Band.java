package de.erpel.metalarchives;

public class Band {
	private final long id;
	private boolean summary;
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
	
	Band(long id) {
		this.id = id;
		this.summary = false;
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
	
	public long getId() {
		return this.id;
	}
	
	/**
	 * if true: only {@link Band#getId()}, {@link Band#getName()},
	 * {@link Band#getCountry()}, {@link Band#getGenre()} give results
	 */
	public boolean isSummary() {
		return this.summary;
	}
	
	public void setSummary(boolean summary) {
		this.summary = summary;
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s, %s", this.name, this.country, this.genre);
	}
	
	@Override
	public int hashCode() {
		return Long.hashCode(this.id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Band) {
			return ((Band) obj).id == this.id;
		}
		return false;
	}
}
