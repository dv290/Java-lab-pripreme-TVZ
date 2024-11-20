package hr.java.restaurant.utils;

import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.restaurant.model.Bonus;
import hr.java.restaurant.model.Chef;
import hr.java.restaurant.model.Contract;

import java.math.BigDecimal;
import java.util.Scanner;

import static hr.java.restaurant.utils.ContractInput.contractInput;


/**
 * Pomoćna klasa za unos podataka o kuharima. Ova klasa omogućava unos imena, prezimena, ugovora i bonusa za kuhara.
 * Također provodi provjeru dupliciranja imena i prezimena kuhara.
 */

public class ChefsInput {

    /**
     * Unosi podatke o kuharima u polje kuhara, uključujući ime, prezime, ugovor i bonus.
     * Provodi provjeru dupliciranja imena i prezimena kuhara i omogućava korisniku da ponovi unos u slučaju duplikata.
     *
     * @param chefs polje kuhara u koje se pohranjuju uneseni podaci
     * @param scanner {@link Scanner} objekt koji se koristi za unos podataka
     */

    public static void chefsInput(Chef[] chefs, Scanner scanner) {
        for(int i = 0; i< chefs.length; i++) {

            String chefFirstName, chefLastName;
            while (true) {
                try {
                    System.out.print("Unesi ime " + (i + 1) + ". kuhara: ");
                    chefFirstName = scanner.nextLine();

                    System.out.print("Unesi prezime " + (i + 1) + ". kuhara: ");
                    chefLastName = scanner.nextLine();

                    checkForDuplicate(chefs, chefFirstName, chefLastName);
                    break;
                }
                catch (DuplicateEntityException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.print("UGOVOR "+(i+1)+". KUHARA: ");
            Contract chefContract = contractInput(scanner);

            System.out.println("Unesi bonus za kuhara: ");
            BigDecimal bonusAmount = BigDecimalInput.inputBigDecimal(scanner);
            Bonus chefBonus = new Bonus(bonusAmount);
            System.out.print("\n");

            chefs[i] = new Chef.ChefBuilder()
                    .setFirstName(chefFirstName)
                    .setLastName(chefLastName)
                    .setContract(chefContract)
                    .setBonus(chefBonus)
                    .build();
        }
    }

    /**
     * Provodi provjeru dupliciranja imena i prezimena kuhara unutar postojećeg polja kuhara.
     * Ako kuhara s istim imenom i prezimenom već postoji, baca {@link DuplicateEntityException}.
     *
     * @param chefs polje kuhara u kojem se provodi provjera
     * @param chefFirstName ime kuhara koje se provjerava
     * @param chefLastName prezime kuhara koje se provjerava
     * @throws DuplicateEntityException ako kuhar s istim imenom i prezimenom već postoji
     */

    private static void checkForDuplicate(Chef[] chefs, String chefFirstName, String chefLastName) throws DuplicateEntityException {
        for (Chef chef : chefs) {
            if (chef != null && chef.getFirstName().equalsIgnoreCase(chefFirstName) && chef.getLastName().equalsIgnoreCase(chefLastName)) {
                throw new DuplicateEntityException("Kuhar s imenom \"" + chefFirstName + " " + chefLastName + "\" već postoji! ");
            }
        }
    }
}
