package com.jam.cocktail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jam.cocktail.dao.CocktailDAO;
import com.jam.cocktail.model.Cocktail;

@RestController
@RequestMapping("/cocktail")
public class CocktailRestController {

	
	@Autowired
	private CocktailDAO cocktailDAO;

	
	//@GetMapping("/cocktails")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Cocktail> getCocktails() {
		return cocktailDAO.list();
	}

	//@GetMapping("/cocktails/{id}")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cocktail> getCocktail(@PathVariable("id") Integer id) {

		Cocktail cocktail = cocktailDAO.get(id);
		if (cocktail == null) {
			return new ResponseEntity<Cocktail>(new Cocktail(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cocktail>(cocktail, HttpStatus.OK);
	}

	//@PostMapping(value = "/cocktails")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Cocktail> createCocktail(@RequestBody Cocktail cocktail) {

		cocktailDAO.create(cocktail);

		return new ResponseEntity<Cocktail>(cocktail, HttpStatus.OK);
	}

	//@DeleteMapping("/{id}")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCocktail(@PathVariable Integer id) {

		if (null == cocktailDAO.delete(id)) {
			return new ResponseEntity<String>("No Cocktail found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<String>(id + "", HttpStatus.OK);

	}

	//@PutMapping("/cocktails/{id}")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Cocktail> updateCocktail(@PathVariable Integer id, @RequestBody Cocktail cocktail) {

		cocktail = cocktailDAO.update(id, cocktail);

		if (null == cocktail) {
			return new ResponseEntity<Cocktail>(new Cocktail(), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cocktail>(cocktail, HttpStatus.OK);
	}

}