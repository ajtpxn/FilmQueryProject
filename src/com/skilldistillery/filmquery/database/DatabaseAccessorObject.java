package com.skilldistillery.filmquery.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}
	}

	@Override
	public Film getFilmById(int filmId) {
		Film output = null;
		try {
			String user = "student";
			String pass = "student";
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqltxt;
			sqltxt = "SELECT * FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				List<Actor> cast = new ArrayList<>();
				cast = getActorsByFilmId(filmId);
				output = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features, cast);
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.err.println(e);
		}

		return output;
	}

	@Override
	public Actor getActorById(int actorId) {
		Actor output = null;
		try {
			String user = "student";
			String pass = "student";
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqltxt;
			sqltxt = "SELECT * FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				actorId = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				output = new Actor(actorId, firstName, lastName);
				System.out.println(output);
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.err.println(e);
		}

		return output;
	}

	@Override
	public ArrayList<Actor> getActorsByFilmId(int filmId) {
		ArrayList<Actor> output = new ArrayList<>();
		Actor actorOutput = null;
		try {
			String user = "student";
			String pass = "student";
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqltxt;
			sqltxt = "SELECT film.*, actor.* FROM film JOIN film_actor ON film_actor.film_id = film.id JOIN actor ON film_actor.actor_id = actor.id WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int actorId = rs.getInt("actor.id");
				String firstName = rs.getString("actor.first_name");
				String lastName = rs.getString("actor.last_name");
				actorOutput = new Actor(actorId, firstName, lastName);
				output.add(actorOutput);
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.err.println(e);
		}

		return output;
	}

	@Override
	public ArrayList<Film> getFilmBySearch(String searchString) {
		ArrayList<Film> output = new ArrayList<>();
		Film film = null;
		try {
			String user = "student";
			String pass = "student";
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqltxt;
			sqltxt = "SELECT * FROM film WHERE CONCAT(id,'',title,'',description,'',release_year,'',language_id,'',rental_duration,'',rental_rate,'',length,'',replacement_cost,'',rating,'',special_features) LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setString(1, searchString);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int filmId = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				short releaseYear = rs.getShort("release_year");
				int langId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");
				ArrayList<Actor> cast = new ArrayList<>();
				cast = getActorsByFilmId(filmId);
				film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features, cast);
				output.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println(e);
		}
			return output;
	}
	
	public String getLanguage(int filmId) {
		String output = null;
		try {
			String user = "student";
			String pass = "student";
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sqltxt;
			sqltxt = "SELECT language.name FROM film JOIN language ON film.language_id = language.id WHERE film.id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				output = rs.getString("name");
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println(e);
		}



		return output;
	}

	
}
