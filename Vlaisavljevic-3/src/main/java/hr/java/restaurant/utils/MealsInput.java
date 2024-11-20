package hr.java.restaurant.utils;

import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.restaurant.model.Category;
import hr.java.restaurant.model.Ingredient;
import hr.java.restaurant.model.Meal;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Pomoćna klasa za unos jela u sustav. Omogućava korisniku unos naziva jela, odabir kategorije, cijene jela,
 * te dodavanje sastojaka. Također osigurava da ne postoji duplikat naziva jela u sustavu.
 */

public class MealsInput {

    /**
     * Omogućava unos svih podataka za više jela, uključujući ime, kategoriju, cijenu i sastojke.
     * Provodi provjeru duplikata imena jela i traži ponovni unos u slučaju duplikata.
     *
     * @param meals polje jela u koji se dodaju novi uneseni obroci
     * @param scanner {@link Scanner} objekt koji se koristi za unos podataka
     * @param categories polje kategorija iz kojeg korisnik bira kategoriju jela
     * @param ingredients polje sastojaka koji se mogu dodati u jelo
     */

    public static void mealsInput(Meal[] meals, Scanner scanner, Category[] categories, Ingredient[] ingredients) {
        for(int i = 0; i < meals.length; i++) {
            String mealName;
            while(true) {
                try {
                    System.out.print("Unesi ime "+(i+1)+". jela: ");
                    mealName = scanner.nextLine();
                    checkForDuplicate(meals, mealName);
                    break;
                }
                catch (DuplicateEntityException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.println("Odaberi kategoriju "+(i+1)+". jela: ");
            for(int j = 0; j < categories.length; j++)
                System.out.println((j+1)+". "+ categories[j].getName());

            int odabranaKategorija = mealCategorySelect(scanner, categories);

            System.out.print("Unesi cijenu "+(i+1)+". jela: ");
            BigDecimal mealPrice = BigDecimalInput.inputPrice(scanner);
            System.out.print("\n");

            meals[i] = new Meal(mealName, categories[odabranaKategorija-1],ingredients,mealPrice);
        }
    }

    /**
     * Omogućava korisniku odabir kategorije za jelo.
     * Ako korisnik unese pogrešan broj, unos će se ponoviti dok se ne unese valjani broj.
     *
     * @param scanner {@link Scanner} objekt koji se koristi za unos podataka
     * @param categories polje kategorija iz kojeg korisnik bira kategoriju
     * @return odabranu kategoriju kao indeks unutar polja kategorija
     */

    private static int mealCategorySelect(Scanner scanner, Category[] categories) {
        int odabranaKategorija;
        do {
            System.out.print("> ");
            odabranaKategorija = IntInput.inputInt(scanner);
            if(odabranaKategorija < 1 || odabranaKategorija > categories.length )
                System.out.println("Pogresan unos! Pokusajte ponovno!");

        } while(odabranaKategorija < 1 || odabranaKategorija > categories.length);
        return odabranaKategorija;
    }


    /**
     * Provodi provjeru postoji li već jelo sa zadanim nazivom.
     * Ako postoji, baca iznimku {@link DuplicateEntityException}.
     *
     * @param meals polje jela koji su već uneseni
     * @param mealName ime jela koji se pokušava unijeti
     * @throws DuplicateEntityException ako postoji već jelo s istim imenom
     */

    private static void checkForDuplicate (Meal[] meals, String mealName) throws DuplicateEntityException {
        for (Meal meal : meals)
            if(meal.getName().equalsIgnoreCase(mealName))
                throw new DuplicateEntityException("Naziv '"+mealName+"' već postoji! Unesite drugi naziv. ");
    }
}
