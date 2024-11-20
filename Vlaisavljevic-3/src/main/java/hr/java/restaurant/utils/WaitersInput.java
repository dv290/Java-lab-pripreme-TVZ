package hr.java.restaurant.utils;

import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.restaurant.model.Bonus;
import hr.java.restaurant.model.Contract;
import hr.java.restaurant.model.Waiter;

import java.math.BigDecimal;
import java.util.Scanner;

import static hr.java.restaurant.utils.ContractInput.contractInput;

/**
 * Pomoćna klasa za unos podataka vezanih za konobare u sustav.
 * Omogućuje unos podataka za konobare, uključujući ime, prezime, ugovor, bonus i provjeru dupliciranja konobara.
 */

public class WaitersInput {

    /**
     * Omogućava korisnicima unos podataka za nove konobare, uključujući ime, prezime, ugovor i bonus.
     * Na temelju tih podataka kreira nove objekte konobara.
     *
     * @param waiters polje konobara u koji se dodaju novi konobari
     * @param scanner {@link Scanner} objekt koji se koristi za unos podataka
     */

    public static void waitersInput(Waiter[] waiters, Scanner scanner) {
        for(int i = 0; i < waiters.length; i++) {

            String waiterFirstName, waiterLastName;
            while(true) {
                try {
                    System.out.print("Unesi ime "+(i+1)+". konobara: ");
                    waiterFirstName = scanner.nextLine();

                    System.out.print("Unesi prezime "+(i+1)+". konobara: ");
                    waiterLastName = scanner.nextLine();

                    checkForDuplicate(waiters, waiterFirstName, waiterLastName);
                    break;
                }
                catch (DuplicateEntityException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.print("UGOVOR "+(i+1)+". KONOBARA: ");
            Contract waiterContract = contractInput(scanner);

            System.out.println("Unesi bonus za konobara: ");
            BigDecimal bonusAmount = BigDecimalInput.inputBigDecimal(scanner);
            Bonus waiterBonus = new Bonus(bonusAmount);
            System.out.print("\n");

            waiters[i] = new Waiter.WaiterBuilder()
                    .setFirstName(waiterFirstName)
                    .setLastName(waiterLastName)
                    .setContract(waiterContract)
                    .setBonus(waiterBonus)
                    .build();
        }
    }

    private static void checkForDuplicate(Waiter[] waiters, String waiterFirstName, String waiterLastName) throws DuplicateEntityException {
        for(Waiter waiter : waiters)
            if(waiter != null && waiter.getFirstName().equalsIgnoreCase(waiterFirstName) && waiter.getLastName().equalsIgnoreCase(waiterLastName))
                throw new DuplicateEntityException("Konobar s imenom \"" + waiterFirstName + " " + waiterLastName + "\" već postoji! ");
    }
}
