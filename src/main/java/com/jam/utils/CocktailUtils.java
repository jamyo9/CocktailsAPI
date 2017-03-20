package com.jam.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jam.cocktail.model.Cocktail;
import com.jam.cocktail.model.Ingredient;

public class CocktailUtils {
	
	// CALL API
	
	public static String callAPI(String urlStr, String method, String propertyAccept) {
		String cocktailsJson = "";
		try {
			URL url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(method);
			conn.setRequestProperty("Accept", propertyAccept);

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			String output;
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			while ((output = br.readLine()) != null) {
				cocktailsJson+=output;
			}
			conn.disconnect();
			
		} catch (MalformedURLException e) {
			System.out.println("ERROR -> CocktailUtils.callAPI -> MalformedURL");
		} catch (IOException e) {
			System.out.println("ERROR -> CocktailUtils.callAPI -> IO");
		}
		return cocktailsJson;
	}
	
	
	
	// PARSE COCKTAIL

	public static Cocktail getFullCocktail(Integer idDrink) {
		return parseFullCocktailDetail(callAPI("http://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=" + idDrink, "GET", "application/json"));
	}
	
	private static Cocktail parseFullCocktailDetail(String jsonStr) {
		Cocktail cocktailTmp = new Cocktail();
		try {
			JSONObject obj = new JSONObject(jsonStr.replaceAll("null", "\"\""));
			JSONArray arr = obj.getJSONArray("drinks");
			
			cocktailTmp.setIdDrink(arr.getJSONObject(0).getInt("idDrink"));
			cocktailTmp.setStrDrink(arr.getJSONObject(0).getString("strDrink"));
			cocktailTmp.setStrDrinkThumb(arr.getJSONObject(0).getString("strDrinkThumb"));
			cocktailTmp.setStrCategory(arr.getJSONObject(0).getString("strCategory"));
			cocktailTmp.setStrAlcoholic(arr.getJSONObject(0).getString("strAlcoholic"));
			cocktailTmp.setStrGlass(arr.getJSONObject(0).getString("strGlass"));
			cocktailTmp.setStrInstructions(arr.getJSONObject(0).getString("strInstructions"));
			
			List<Ingredient> ingredientsList = new ArrayList<Ingredient>();
//			List<String> measuresList = new ArrayList<String>();
			for (int i = 1; i<=15 ; i++) {
				String strIngredient = arr.getJSONObject(0).getString("strIngredient" + i);
				String measure = arr.getJSONObject(0).getString("strMeasure" + i);
				if (!strIngredient.equals("") && !strIngredient.equals(" ") && !strIngredient.equals("\n")) {
					Ingredient ingredient = new Ingredient();
					ingredient.setStrIngredient(strIngredient);
					if (!measure.equals("") && !measure.equals(" ") && !measure.equals("\n")) {
						ingredient.setStrMeasure(measure);
					}
					
					ingredient.setStrIngredientThumb("http://www.thecocktaildb.com/images/ingredients/" + strIngredient + ".png");
					ingredient.setStrIngredientThumbSmall("http://www.thecocktaildb.com/images/ingredients/" + strIngredient + "-Small.png");
					ingredient.setStrIngredientThumbMedium("http://www.thecocktaildb.com/images/ingredients/" + strIngredient + "-Medium.png");
					
					ingredientsList.add(ingredient);
				}
			}
			cocktailTmp.setIngredients(ingredientsList);
//			cocktailTmp.setMeasures(measuresList);
			
			String dateStr = arr.getJSONObject(0).getString("dateModified");
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			try {
				cocktailTmp.setDateModified(formatter.parse(dateStr));
			} catch (ParseException e) {
				cocktailTmp.setDateModified(new Date());
			}
		} catch (JSONException e) {
			System.out.println("ERROR -> CocktailUtils.parseFullCocktailDetail");
		}
	    return cocktailTmp;
	}
	
	public static List<Cocktail> parseCocktails(String jsonStr) {
		List<Cocktail> cocktailsList = new ArrayList<Cocktail>();
		try {
			JSONObject obj = new JSONObject(jsonStr);
			JSONArray arr = obj.getJSONArray("drinks");
			for (int i = 0; i < arr.length(); i++) {
		    	Cocktail cocktailTmp = new Cocktail();
				cocktailTmp.setIdDrink(arr.getJSONObject(i).getInt("idDrink"));
				cocktailTmp.setStrDrink(arr.getJSONObject(i).getString("strDrink"));
				cocktailTmp.setStrDrinkThumb(arr.getJSONObject(i).getString("strDrinkThumb"));
		    	cocktailsList.add(cocktailTmp);
			}
		} catch (JSONException e) {
			System.out.println("ERROR -> CocktailUtils.parseCocktails");
		}
		return cocktailsList;
	}
	
	public static List<String> parseStrings(String jsonStr, String jsonName) {
		List<String> list = new ArrayList<String>();
		try {
			JSONObject obj = new JSONObject(jsonStr);
			JSONArray arr = obj.getJSONArray("drinks");
			for (int i = 0; i < arr.length(); i++) {
				String retStr = arr.getJSONObject(i).getString(jsonName);
				if (!retStr.equals("") && !retStr.equals(" ") && !retStr.equals("\n")) {
					list.add(retStr);
				}
			}
		} catch (JSONException e) {
			System.out.println("ERROR -> CocktailUtils.parseStrings");
		}
		return list;
	}
	
}
