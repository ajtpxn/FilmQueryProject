package com.skilldistillery.filmquery.database;

import java.util.ArrayList;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public interface DatabaseAccessor {
	public Film getFilmById(int filmId);

	public Actor getActorById(int actorId);

	public ArrayList<Actor> getActorsByFilmId(int filmId);
	
	public ArrayList<Film> getFilmBySearch(String searchString);
	
	public String getLanguage(int filmId);
	
}
