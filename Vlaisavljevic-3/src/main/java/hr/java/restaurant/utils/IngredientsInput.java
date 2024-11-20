package hr.java.restaurant.utils;

import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.restaurant.model.Category;
import hr.java.restaurant.model.Ingredient;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Pomoćna klasa za unos sastojaka u sustav. Omogućava korisniku unos naziva sastojka, odabir kategorije, unos kalorija
 * i opisa pripreme. Također osigurava da ne postoji duplikat naziva sastojka u sustavu.
 */

public class IngredientsInput {

    /**
     * Omogućava unos svih podataka za više sastojaka, uključujući ime, kategoriju, kalorije i pripremu.
     * Provodi provjeru duplikata imena sastojka i traži ponovni unos u slučaju duplikata.
     *
     * @param ingredients polje sastojaka u koji se dodaju novi uneseni sastojci
     * @param scanner {@link Scanner} objekt koji se koristi za unos podataka
     * @param categories polje kategorija iz kojeg korisnik bira kategoriju sastojka
     */

    public static void ingredientsInput(Ingredient[] ingredients, Scanner scanner, Category[] categories) {
        for(int i = 0; i < ingredients.length; i++) {
            String ingredientName;

            while(true) {
                try {
                    System.out.print("Unesi ime "+(i+1)+". sastojka: ");
                    ingredientName = scanner.nextLine();
                    
                    checkForDuplicate(ingredients, ingredientName);
                    break;
                }
                catch (DuplicateEntityException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.println("Odaberi kategoriju "+(i+1)+". sastojka: ");
            for(int j = 0; j < categories.length; j++)
                System.out.println((j+1)+". "+ categories[j].getName());

            int odabranaKategorija = ingredientCategorySelect(scanner, categories);

            System.out.print("\nUnesi broj kalorija "+(i+1)+". sastojka: ");
            BigDecimal ingredientKcal = BigDecimalInput.inputBigDecimal(scanner);

            System.out.print("Unesi korake pripreme "+(i+1)+". sastojka: ");
            String ingredientPreparationMethod = scanner.nextLine();
            System.out.print("\n");

            ingredients[i] = new Ingredient(ingredientName, categories[odabranaKategorija-1], ingredientKcal, ingredientPreparationMethod);
        }
    }

    /**
     * Omogućava korisniku odabir kategorije za sastojak.
     * Ako korisnik unese pogrešan broj, unos će se ponoviti dok se ne unese valjani broj.
     *
     * @param scanner {@link Scanner} objekt koji se koristi za unos podataka
     * @param categories polje kategorija iz kojeg korisnik bira kategoriju
     * @return odabranu kategoriju kao indeks unutar polja kategorija
     */

    private static int ingredientCategorySelect(Scanner scanner, Category[] categories) {
        int odabranaKategorija;
        do {
            System.out.print("> ");
            odabranaKategorija = IntInput.inputInt(scanner);
            if(odabranaKategorija < 1 || odabranaKategorija > categories.length )
                System.out.println(Messages.INVALID_INPUT_MESSAGE);

        } while(odabranaKategorija < 1 || odabranaKategorija > categories.length);
        return odabranaKategorija;
    }

    private static void checkForDuplicate(Ingredient[] ingredients, String ingredientName) throws DuplicateEntityException {
        for(Ingredient ingredient : ingredients)
            if(ingredient != null && ingredient.getName().equalsIgnoreCase(ingredientName))
                throw new DuplicateEntityException("Naziv '"+ ingredientName+"' već postoji! Unesite drugi naziv. ");
    }
}
