package com.skilldistillery.filmquery.app;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private void launch() {
		Scanner input = new Scanner(System.in);
		startUserInterface(input);
		input.close();
	}

	private void startUserInterface(Scanner input) {
		System.out.print("Welcome! ");
		boolean run = true;
		while (run) {
			System.out.println("Please choose from the following menu:");
			System.out.println("1. Look up film by id.");
			System.out.println("2. Search for film by keyword.");
			System.out.println("3. Exit.\n");
			String choice = input.nextLine();
			boolean choiceValidated = inputCheckerInt(choice, 3);
			if (choiceValidated) {
				switch (choice) {
				case "1":
					filmById(input);
					break;
				case "2":
					searchForFilm(input);
					break;
				case "3":
					System.out.println("Exiting.");
					run = false;
					break;
					
				default:
					System.out.println("\nInvalid input. Please try again.\n");
					break;
				}
			}
			else {
				continue;
			}
		}

	}

	private void filmById(Scanner input) {
		boolean run = true;
		while (run) {
			System.out.print("\nInput id (1 - 1000): ");
			String choice = input.nextLine();
			boolean choiceValidated = inputCheckerInt(choice, 1000);
			if (choiceValidated) {
				int inputInt = Integer.parseInt(choice);
				Film film = db.getFilmById(inputInt);
				if (film == null) {
					run = tryAgainChoices(input);
				}
				else {
					System.out.println(film.toString());
					run = false;
				}
			}
			else {
				continue;
			}
		}
	}

	private void searchForFilm(Scanner input) {
		boolean run = true;
		while (run) {
			System.out.print("\nInput search: ");
			String inputString = input.nextLine();
			String searchString = "%" + inputString + "%";
			ArrayList<Film> filmList = db.getFilmBySearch(searchString);
			if (filmList.size() == 0) {
				run = tryAgainChoices(input);
			} 
			else {
				System.out.println(searchOuput(filmList));
				System.out.println("Search complete.\n");
				run = false;
			}
		}
	}
	
	private String searchOuput(ArrayList<Film> list) {
		StringBuilder sb = new StringBuilder();
		for (Film film : list) {
			sb.append(film.toString());
		}
		return sb.toString();
	}
	
	private boolean tryAgainChoices(Scanner input) {
		System.out.println("\nSearch complete. No films found. Try again?");
		System.out.print("\n(Y)es or (N)o: ");
		String tryAgain = input.nextLine();
		switch (tryAgain) {
		case "y":
			break;
		case "Y":
			break;
		case "yes":
			break;
		case "Yes":
			break;
		case "n":
			System.out.println();
			return false;
		case "N":
			System.out.println();
			return false;
		case "no":
			System.out.println();
			return false;
		case "No":
			System.out.println();
			return false;
		default:
			System.out.println();
			return false;
		}
		return true;
	}
	
	private boolean inputCheckerInt(String input, int range) {
		if (!input.isEmpty()) {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher patternMatch = pattern.matcher(input);
			boolean inputMatchesPattern = patternMatch.matches();
			if (inputMatchesPattern) {
				int inputParsedToInteger = Integer.parseInt(input);
				if (inputParsedToInteger <= range && inputParsedToInteger >= 1) {
					return true;
				} 
				else {
					System.out.println("\nInvalid input. Please try again.\n"); 
					return false;
				}
			} 
			else {
				System.out.println("\nInvalid input. Please try again.\n");
				return false;
			}
		}

		else {
			System.out.println("\nInvalid input. Please try again.\n");
			return false;
		}
	}
	
	
}
