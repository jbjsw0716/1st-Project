package com.keduit.SangWook;

public class Movie {
	protected int movie_num;
	protected int title;
	protected int genre_id;
	protected String runningTime;
	protected int country_id;
	protected String releaseDate;
	protected int director_id;
	protected int rating_id;
	protected int additionalinfo_id;
	
	protected Movie(int title, int genre_id, String runningTime, int country_id,
			String releaseDate, int director_id, int rating_id, int additionalinfo_id) {
		this.title = title;
		this.genre_id = genre_id;
		this.runningTime = runningTime;
		this.country_id = country_id;
		this.releaseDate = releaseDate;
		this.director_id = director_id;
		this.rating_id = rating_id;
		this.additionalinfo_id = additionalinfo_id;
	}
	
	protected Movie(int movie_num) {
		this.movie_num = movie_num;
	}

	protected int getMovie_num() {
		return movie_num;
	}

	protected void setMovie_num(int movie_num) {
		this.movie_num = movie_num;
	}

	protected int getTitle() {
		return title;
	}

	protected void setTitle(int title) {
		this.title = title;
	}

	protected int getGenre_id() {
		return genre_id;
	}

	protected void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}

	protected String getRunningTime() {
		return runningTime;
	}

	protected void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}

	protected int getCountry_id() {
		return country_id;
	}

	protected void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	protected String getReleaseDate() {
		return releaseDate;
	}

	protected void setReleasedate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	protected int getDirector_id() {
		return director_id;
	}

	protected void setDirector_id(int director_id) {
		this.director_id = director_id;
	}
	
	public int getRating_id() {
		return rating_id;
	}

	public void setRating_id(int rating_id) {
		this.rating_id = rating_id;
	}

	protected int getAdditionalinfo_id() {
		return additionalinfo_id;
	}

	protected void setAdditionalinfo_id(int additionalinfo_id) {
		this.additionalinfo_id = additionalinfo_id;
	}

	@Override
	public String toString() {
		return "Movie [movie_num=" + movie_num + ", title=" + title + ", genre_id=" + genre_id + ", runningTime="
				+ runningTime + ", country_id=" + country_id + ", releaseDate=" + releaseDate + ", director_id="
				+ director_id + ", rating_id=" + rating_id + ", additionalinfo_id=" + additionalinfo_id + "]";
	}
}
