package com.skilldistillery.filmquery.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		boolean run = true;
		while (run) {
			System.out.println("1. Look up film by id.");
			System.out.println("2. Search for file.");
			System.out.println("3. Exit.");
			System.out.print("Your choice: ");
			String choice = input.nextLine();

			switch (choice) {
			case "1":
				filmById(input);
				break;
			case "2":
				searchForFilm(input);
				break;
			case "3":
				run = false;
				break;

			default:
				System.out.println("\nInvalid input. Please try again.\n");
				break;
			}
		}

	}

	private void filmById(Scanner input) {
		boolean run = true;
		while (run) {
			System.out.print("\nInput id: ");
			String choice = input.nextLine();
			int inputInt = Integer.parseInt(choice);
			Film film = db.getFilmById(inputInt);
			if (film == null) {
				System.out.println("\nNo such film. Try again?");
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
					run = false;
					break;
				case "N":
					System.out.println();
					run = false;
					break;
				case "no":
					System.out.println();
					run = false;
					break;
				case "No":
					System.out.println();
					run = false;
					break;
				default:
					System.out.println();
					run = false;
					break;
				}
			}
			else {
				System.out.println(film.toString());
				run = false;
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
				System.out.println("\nNo such film. Try again?");
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
					run = false;
					break;
				case "N":
					System.out.println();
					run = false;
					break;
				case "no":
					System.out.println();
					run = false;
					break;
				case "No":
					System.out.println();
					run = false;
					break;
				default:
					System.out.println();
					run = false;
					break;
				}
			} else {
				System.out.println(shortOuput(filmList));
				run = false;
			}
		}
	}
	
	private String shortOuput(ArrayList<Film> list) {
		StringBuilder sb = new StringBuilder();
		for (Film film : list) {
			sb.append(film.toString());
		}
		return sb.toString();
	}
}
