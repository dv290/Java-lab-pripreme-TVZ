package hr.java.restaurant.utils;

import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.restaurant.model.*;

import java.util.Scanner;

import static hr.java.restaurant.utils.AddressInput.addressInput;

/**
 * Pomoćna klasa za unos podataka vezanih za restorane u sustav.
 * Omogućuje korisnicima unos imena restorana, adrese te selekciju jela, kuhara, konobara i dostavljača za svaki restoran.
 */

public class RestaurantsInput {

    /**
     * Omogućava korisnicima unos podataka za nove restorane, uključujući ime restorana, adresu te selekciju jela,
     * kuhara, konobara i dostavljača za svaki restoran. Na temelju tih podataka kreira nove objekte Restorana.
     *
     * @param restaurants polje restorana u koji se dodaju novi restorani
     * @param scanner {@link Scanner} objekt koji se koristi za unos podataka
     * @param meals polje jela koja se nude u restoranu
     * @param chefs polje kuhara koji rade u restoranu
     * @param waiters polje konobara koji rade u restoranu
     * @param deliverers polje dostavljača koji isporučuju jela iz restorana
     */

    public static void restaurantInput(Restaurant[] restaurants, Scanner scanner, Meal[] meals, Chef[] chefs, Waiter[] waiters, Deliverer[] deliverers) {
        for(int i = 0; i< restaurants.length; i++) {

            String restaurantName;
            while(true) {
                try {
                    System.out.print("Unesi ime "+(i+1)+". restorana: ");
                    restaurantName = scanner.nextLine();

                    checkForDuplicate(restaurants, restaurantName);
                    break;
                }
                catch (DuplicateEntityException e) {
                    System.out.println(e.getMessage());
                }
            }

            Address restaurantAddress = addressInput(scanner);
            System.out.print("\n");

            restaurants[i] = new Restaurant(restaurantName, restaurantAddress, meals, chefs, waiters, deliverers);
        }
    }

    /**
     * Provjerava postoji li već restoran s unesenim imenom.
     *
     * @param restaurants polje restorana u kojem se traži dupliciranje imena
     * @param restaurantName ime restorana koje se provjerava za dupliciranje
     * @throws DuplicateEntityException ako restoran s unesenim imenom već postoji
     */

    private static void checkForDuplicate(Restaurant[] restaurants, String restaurantName) throws DuplicateEntityException {
        for(Restaurant restaurant : restaurants)
            if(restaurant.getName().equalsIgnoreCase(restaurantName))
                throw new DuplicateEntityException("Naziv '" + restaurantName + "' već postoji! Unesite drugi naziv.");
    }
}
