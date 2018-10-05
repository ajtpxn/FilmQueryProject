package com.skilldistillery.filmquery.database;

import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	@Override
	public Film getFilmById(int filmId) {
		return null;
	}

	@Override
	public Actor getActorById(int actorId) {
		return null;
	}

	@Override
	public List<Actor> getActorsByFilmId(int filmId) {
		return null;
	}

}
