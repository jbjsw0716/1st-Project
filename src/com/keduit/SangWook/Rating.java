package com.keduit.SangWook;

public class Rating {
	protected int rating_id;
	protected String viewingRating;
	
	protected Rating(int rating_id) {
		this.rating_id = rating_id;
	}
	
	protected Rating(String viewingRating) {
		this.viewingRating = viewingRating;
	}

	protected int getRating_id() {
		return rating_id;
	}

	protected void setRating_id(int rating_id) {
		this.rating_id = rating_id;
	}

	protected String getViewingRating() {
		return viewingRating;
	}

	protected void setViewingRating(String viewingRating) {
		this.viewingRating = viewingRating;
	}

	@Override
	public String toString() {
		return "Rating [rating_id=" + rating_id + ", viewingRating=" + viewingRating + "]";
	}
}
