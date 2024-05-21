package com.keduit.SangWook;

public class Genre {
	protected int genre_id;
	protected String genreName;
	
	protected Genre(String genreName) {
		this.genreName = genreName;
	}
	
	protected Genre(int genre_id) {
		this.genre_id = genre_id;
	}
	
	protected int getGenre_id() {
		return genre_id;
	}
	protected void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}
	protected String getGenreName() {
		return genreName;
	}
	protected void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	@Override
	public String toString() {
		return "Genre [genre_id=" + genre_id + ", genreName=" + genreName + "]";
	}
}
