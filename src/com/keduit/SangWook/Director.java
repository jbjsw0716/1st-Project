package com.keduit.SangWook;

public class Director {
	protected int director_id;
	protected String name;
	protected String movie_title;
	protected int nothing; //nothing 변수는 의미 없는 변수(name을 넣어주기 위해 필요)
	
	protected Director(String name, String movie_title) {
		this.name = name;
		this.movie_title = movie_title;
	}
	
	//nothing 변수는 의미 없는 변수(name을 넣어주기 위해 필요)
	protected Director(int nothing, String name) {
		this.nothing = nothing;
		this.name = name;
	}
	
	protected Director(String movietitle) {
		this.movie_title = movietitle;
	}
	
	protected Director(int director_id) {
		this.director_id = director_id;
	}
	
	protected Director(String name, int director_id) {
		this.director_id = director_id;
		this.name = name;
	}
	
	protected int getDirector_id() {
		return director_id;
	}
	protected void setDirector_id(int director_id) {
		this.director_id = director_id;
	}
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected String getMovie_title() {
		return movie_title;
	}
	protected void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}
	
	@Override
	public String toString() {
		return "Director [director_id=" + director_id + ", name=" + name + ", movie_title=" + movie_title + "]";
	}
}
