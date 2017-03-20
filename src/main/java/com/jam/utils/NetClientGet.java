package com.jam.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jam.cocktail.model.Cocktail;

public class NetClientGet {
	
	public static List<String> categoriesList = new ArrayList<String>();
	public static List<String> glassesList = new ArrayList<String>();
	public static List<String> ingredientsList = new ArrayList<String>();
	public static List<String> alcoholicFiltersList = new ArrayList<String>();
	
	public static List<Cocktail> cocktailsList = new ArrayList<Cocktail>();
	
	public static void main(String[] args) {
		
		List<Cocktail> cocktailsListTmp = new ArrayList<Cocktail>();
		String cocktailsJson = "";

		// Get categories
		cocktailsJson = CocktailUtils.callAPI("http://www.thecocktaildb.com/api/json/v1/1/list.php?c=list", "GET", "application/json");
		categoriesList.addAll(CocktailUtils.parseStrings(cocktailsJson.replaceAll("null", "\"\""), "strCategory"));

		// Get glass types
		cocktailsJson = CocktailUtils.callAPI("http://www.thecocktaildb.com/api/json/v1/1/list.php?g=list", "GET", "application/json");
		categoriesList.addAll(CocktailUtils.parseStrings(cocktailsJson.replaceAll("null", "\"\""), "strGlass"));

		// Get ingredients
		cocktailsJson = CocktailUtils.callAPI("http://www.thecocktaildb.com/api/json/v1/1/list.php?i=list", "GET", "application/json");
		ingredientsList.addAll(CocktailUtils.parseStrings(cocktailsJson.replaceAll("null", "\"\""), "strIngredient1"));
		
		// Get alcoholic types
		cocktailsJson = CocktailUtils.callAPI("http://www.thecocktaildb.com/api/json/v1/1/list.php?a=list", "GET", "application/json");
		alcoholicFiltersList.addAll(CocktailUtils.parseStrings(cocktailsJson.replaceAll("null", "\"\""), "strAlcoholic"));
		
		
		
		// Get cocktails by name
		String[] letters = {"", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "ñ", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		for (String letter : letters) {
			cocktailsJson = CocktailUtils.callAPI("http://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + letter, "GET", "application/json");
			cocktailsListTmp.addAll(CocktailUtils.parseCocktails(cocktailsJson.replaceAll("null", "\"\"")));
				
		}
		
		// Get cocktails by category
		for (String category : categoriesList) {
			cocktailsJson = CocktailUtils.callAPI("http://www.thecocktaildb.com/api/json/v1/1/filter.php?c=" + category.replace(" ", "_"), "GET", "application/json");
			cocktailsListTmp.addAll(CocktailUtils.parseCocktails(cocktailsJson.replaceAll("null", "\"\"")));
		}
		
		// Get cocktails by glass type
		for (String glass : glassesList) {
			cocktailsJson = CocktailUtils.callAPI("http://www.thecocktaildb.com/api/json/v1/1/filter.php?g=" + glass.replace(" ", "_"), "GET", "application/json");
			cocktailsListTmp.addAll(CocktailUtils.parseCocktails(cocktailsJson.replaceAll("null", "\"\"")));
		}
		
		// Get cocktails by ingredient
		for (String ingredient : ingredientsList) {
			cocktailsJson = CocktailUtils.callAPI("http://www.thecocktaildb.com/api/json/v1/1/filter.php?i=" + ingredient.replace(" ", "_"), "GET", "application/json");
			cocktailsListTmp.addAll(CocktailUtils.parseCocktails(cocktailsJson.replaceAll("null", "\"\"")));
		}
		
		// Get cocktails by alcoholic types
		for (String alcoholFilter : alcoholicFiltersList) {
			cocktailsJson = CocktailUtils.callAPI("http://www.thecocktaildb.com/api/json/v1/1/filter.php?a=" + alcoholFilter.replace(" ", "_"), "GET", "application/json");
			cocktailsListTmp.addAll(CocktailUtils.parseCocktails(cocktailsJson.replaceAll("null", "\"\"")));
		}
		
		// Get Detailed Cocktails
//		int i = 1;
		for (Cocktail cocktailTmp : cocktailsListTmp) {
			Boolean repeated = false;
			for(Cocktail cocktailTmp2 : cocktailsList) {
				if (cocktailTmp.equals(cocktailTmp2)) {
					repeated = true;
					break;
				}
			}
			if (!repeated) {
				Cocktail cocktail = CocktailUtils.getFullCocktail(cocktailTmp.getIdDrink());
				cocktailsList.add(cocktail);
//				System.out.print(i++ + ": ");
//				cocktail.printnl();
			}
		}
		
		Collections.sort(cocktailsList, new Comparator<Cocktail>() {
            public int compare(Cocktail c1, Cocktail c2) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return c1.getIdDrink() < c2.getIdDrink() ? -1 : (c1.getIdDrink() > c2.getIdDrink() ) ? 1 : 0;
            }
        });
		
		// Write cocktails to file
		try {
			PrintWriter writer = new PrintWriter("cocktails.txt", "UTF-8");
			for (Cocktail cocktailTmp : cocktailsList) {
				writer.println(cocktailTmp.toString());
			}
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		System.out.println("DONE!!!!");
	}
	
}