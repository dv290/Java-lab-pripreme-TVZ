package hr.java.restaurant.utils;

import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.restaurant.model.Category;

import java.util.Scanner;

/**
 * Pomoćna klasa za unos kategorija. Ova klasa omogućava unos naziva i opisa kategorije.
 * Također provodi provjeru da li uneseni naziv već postoji u postojećim kategorijama.
 */

public class CategoryInput {

    /**
     * Unosi naziv i opis kategorije za svaki element u polju kategorija.
     * Provodi provjeru dupliciranja naziva i omogućava korisniku da ponovi unos ukoliko je naziv već zauzet.
     *
     * @param categories polje kategorija u koje se pohranjuju unesene kategorije
     * @param scanner {@link Scanner} objekt koji se koristi za unos podataka
     */

    public static void categoriesInput(Category[] categories, Scanner scanner) {
        for (int i = 0; i < categories.length; i++) {
            String categoryName;
            while (true) {
                try {
                    System.out.print("Unesi naziv " + (i + 1) + ". kategorije: ");
                    categoryName = scanner.nextLine();

                    checkForDuplicate(categories, categoryName);
                    break;
                } catch (DuplicateEntityException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.print("Unesi opis " + (i + 1) + ". kategorije: ");
            String categoryDescription = scanner.nextLine();
            System.out.print("\n");

            categories[i] = new Category.CategoryBuilder()
                    .setName(categoryName)
                    .setDescription(categoryDescription)
                    .build();
        }
    }

    /**
     * Provodi provjeru dupliciranja naziva kategorije unutar postojećeg polja kategorija.
     * Ako naziv već postoji, baca {@link DuplicateEntityException}.
     *
     * @param categories polje kategorija u kojem se provodi provjera
     * @param categoryName naziv kategorije koji se provjerava
     * @throws DuplicateEntityException ako naziv kategorije već postoji
     */

    private static void checkForDuplicate(Category[] categories, String categoryName) throws DuplicateEntityException {
        for (Category category : categories) {
            if (category != null && category.getName().equalsIgnoreCase(categoryName)) {
                throw new DuplicateEntityException("Naziv '" + categoryName + "' već postoji! Unesite drugi naziv.");
            }
        }
    }
}