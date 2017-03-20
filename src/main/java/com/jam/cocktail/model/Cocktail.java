package com.jam.cocktail.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Cocktail {
	
	private Integer idDrink;
	private String strDrink;
	private String strDrinkThumb;
	private String strCategory;
	private String strAlcoholic;
	private String strGlass;
	private String strInstructions;
	private List<Ingredient> ingredients;
	private Date dateModified;
	
	public Cocktail() {
		super();
		
	}

	public Cocktail(int idDrink, String strDrink, String strDrinkThumb, String strCategory, String strAlcoholic,
			String strGlass, String strInstructions, List<Ingredient> ingredients, String dateModified) {
		super();
		this.idDrink = new Integer(idDrink);
		this.strDrink = strDrink;
		this.strDrinkThumb = strDrinkThumb;
		this.strCategory = strCategory;
		this.strAlcoholic = strAlcoholic;
		this.strGlass = strGlass;
		this.strInstructions = strInstructions;
		this.ingredients = ingredients;
		//Tue Oct 11 09:54:47 EDT 2016
		//this.dateModified = new Date(dateModified);
		SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        try {
            this.dateModified = formatter.parse(dateModified);
        } catch (ParseException e) {
            this.dateModified = new Date();
        	//e.printStackTrace();
        }
	}
	
	public Integer getIdDrink() {
		return idDrink;
	}
	public void setIdDrink(Integer idDrink) {
		this.idDrink = idDrink;
	}
	public String getStrDrink() {
		return strDrink;
	}
	public void setStrDrink(String strDrink) {
		this.strDrink = strDrink;
	}
	public String getStrDrinkThumb() {
		return strDrinkThumb;
	}
	public void setStrDrinkThumb(String strDrinkThumb) {
		this.strDrinkThumb = strDrinkThumb;
	}
	public String getStrCategory() {
		return strCategory;
	}
	public void setStrCategory(String strCategory) {
		this.strCategory = strCategory;
	}
	public String getStrAlcoholic() {
		return strAlcoholic;
	}
	public void setStrAlcoholic(String strAlcoholic) {
		this.strAlcoholic = strAlcoholic;
	}
	public String getStrGlass() {
		return strGlass;
	}
	public void setStrGlass(String strGlass) {
		this.strGlass = strGlass;
	}
	public String getStrInstructions() {
		return strInstructions;
	}
	public void setStrInstructions(String strInstructions) {
		this.strInstructions = strInstructions;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public Date getDateModified() {
		return dateModified;
	}
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	
	public void printnl() {
		System.out.println("idDrink: " + idDrink + ", strDrink: " + strDrink);
		System.out.println("------------");
		int i = 1;
		for (Ingredient ingredient : ingredients) {
			System.out.println("\tIngredients " + i++ + ": " + ingredient.toString());
		}
				
		System.out.println("############");
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof Cocktail))
            return false;
        Cocktail cocktail = (Cocktail) obj;
        return cocktail.getIdDrink().intValue() == this.getIdDrink().intValue();
    }
	
	@Override
	public String toString() {
		
		String strIngredients = "ingredients.clear();\n";
		for (Ingredient ingredient : ingredients) {
			strIngredients += "ingredients.add(new Ingredient(\"" + ingredient.getStrIngredient() + "\", \"" + ingredient.getStrMeasure() + "\", \"" + ingredient.getStrIngredientThumb() + "\", \"" + ingredient.getStrIngredientThumbSmall() + "\", \"" + ingredient.getStrIngredientThumbMedium() + "\"));\n";
		}
		
		String str = strIngredients;
		str += "cocktails.add(new Cocktail(" + idDrink + ", \"" + strDrink + "\", \"" + strDrinkThumb + "\", \"" + strCategory + "\", \"" + strAlcoholic + "\", \"" + strGlass + "\", \"" + strInstructions + "\", ingredients , \"" + dateModified + "\"));";
		
/*		str += "idDrink: " + idDrink + ", strDrink: " + strDrink;
		str += ", strDrinkThumb: " + strDrinkThumb + ", strCategory: " + strCategory;
		str += ", strAlcoholic: " + strAlcoholic + ", strGlass: " + strGlass + ", strInstructions: " + strInstructions;
		int i = 1;
		for (Ingredient ingredient : ingredients) {
			str += ", ingredient" + i++ + ": " + ingredient.toString();
		}
		
		str += ", dateModified" + dateModified; 
*/		return str;
	}
}