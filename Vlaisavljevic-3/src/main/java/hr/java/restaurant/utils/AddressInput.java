package hr.java.restaurant.utils;

import hr.java.restaurant.model.Address;

import java.util.Scanner;

/**
 * Pomoćna klasa za unos podataka o adresi. Ova klasa omogućava unos ulice, kućnog broja, grada i poštanskog broja.
 * Nakon unosa, metoda vraća objekt {@link Address} sa unesenim podacima.
 */
public class AddressInput {
    /**
     * Zatraži od korisnika unos podataka za kreiranje objekta {@link Address}.
     * Korisnik unosi ulicu, kućni broj, grad i poštanski broj.
     *
     * @param scanner {@link Scanner} objekt koji se koristi za unos podataka
     * @return novi objekt {@link Address} sa unesenim podacima
     */
    public static Address addressInput(Scanner scanner) {
        System.out.println("Ulica: ");
        String street = scanner.nextLine();

        System.out.println("Kucni broj: ");
        String houseNumber = scanner.nextLine();

        System.out.println("Grad: ");
        String city = scanner.nextLine();

        System.out.println("Postanski broj: ");
        String postalCode = scanner.nextLine();

        return new Address.AddressBuilder()
                .setStreet(street)
                .setHouseNumber(houseNumber)
                .setCity(city)
                .setPostalCode(postalCode)
                .build();
    }
}
