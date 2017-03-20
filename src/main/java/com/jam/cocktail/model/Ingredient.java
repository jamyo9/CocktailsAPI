package com.jam.cocktail.model;

public class Ingredient {
	
	private String strIngredient;
	private String strMeasure;
	private String strIngredientThumb;
	private String strIngredientThumbSmall;
	private String strIngredientThumbMedium;
	
	public Ingredient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ingredient(String strIngredient, String strMeasure, String strIngredientThumb,
			String strIngredientThumbSmall, String strIngredientThumbMedium) {
		super();
		this.strIngredient = strIngredient;
		this.strMeasure = strMeasure;
		this.strIngredientThumb = strIngredientThumb;
		this.strIngredientThumbSmall = strIngredientThumbSmall;
		this.strIngredientThumbMedium = strIngredientThumbMedium;
	}
	
	public String getStrIngredient() {
		return strIngredient;
	}
	public void setStrIngredient(String strIngredient) {
		this.strIngredient = strIngredient;
	}
	public String getStrMeasure() {
		return strMeasure;
	}
	public void setStrMeasure(String strMeasure) {
		this.strMeasure = strMeasure;
	}
	public String getStrIngredientThumb() {
		return strIngredientThumb;
	}
	public void setStrIngredientThumb(String strIngredientThumb) {
		this.strIngredientThumb = strIngredientThumb;
	}
	public String getStrIngredientThumbSmall() {
		return strIngredientThumbSmall;
	}
	public void setStrIngredientThumbSmall(String strIngredientThumbSmall) {
		this.strIngredientThumbSmall = strIngredientThumbSmall;
	}
	public String getStrIngredientThumbMedium() {
		return strIngredientThumbMedium;
	}
	public void setStrIngredientThumbMedium(String strIngredientThumbMedium) {
		this.strIngredientThumbMedium = strIngredientThumbMedium;
	}
	
	@Override
	public String toString() {
		return "[" + strMeasure + " " + strIngredient +
				", strIngredientThumb: " + strIngredientThumb +
				", strIngredientThumbSmall: " + strIngredientThumbSmall +
				", strIngredientThumbMedium: " + strIngredientThumbMedium + "]";
	}
}
