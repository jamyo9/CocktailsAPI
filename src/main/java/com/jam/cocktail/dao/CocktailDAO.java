package com.jam.cocktail.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jam.cocktail.model.Cocktail;

@Component
public class CocktailDAO {

	// Dummy database. Initialize with some dummy values.
	

	/**
	 * Returns list of cocktails from dummy database.
	 * 
	 * @return list of cocktails
	 */
	public List<Cocktail> list() {
		return CocktailList.cocktails;
	}

	/**
	 * Return cocktail object for given id from dummy database. If cocktail is
	 * not found for id, returns null.
	 * 
	 * @param id
	 *            cocktail id
	 * @return cocktail object for given id
	 */
	public Cocktail get(Integer id) {

		for (Cocktail c : CocktailList.cocktails) {
			if (c.getIdDrink().intValue() == id.intValue()) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Create new cocktail in dummy database. Updates the id and insert new
	 * cocktail in list.
	 * 
	 * @param cocktail
	 *            Cocktail object
	 * @return cocktail object with updated id
	 */
	public Cocktail create(Cocktail cocktail) {
		cocktail.setIdDrink(0);
		CocktailList.cocktails.add(cocktail);
		return cocktail;
	}

	/**
	 * Delete the cocktail object from dummy database. If cocktail not found for
	 * given id, returns null.
	 * 
	 * @param id
	 *            the cocktail id
	 * @return id of deleted cocktail object
	 */
	public Integer delete(Integer id) {

		for (Cocktail c : CocktailList.cocktails) {
			if (c.getIdDrink().intValue() == id.intValue()) {
				CocktailList.cocktails.remove(c);
				return id;
			}
		}

		return null;
	}

	/**
	 * Update the cocktail object for given id in dummy database. If cocktail
	 * not exists, returns null
	 * 
	 * @param id
	 * @param cocktail
	 * @return cocktail object with id
	 */
	public Cocktail update(Integer id, Cocktail cocktail) {

		for (Cocktail c : CocktailList.cocktails) {
			if (c.getIdDrink().intValue() == id.intValue()) {
				cocktail.setIdDrink(c.getIdDrink());
				CocktailList.cocktails.remove(c);
				CocktailList.cocktails.add(cocktail);
				return cocktail;
			}
		}

		return null;
	}

}