package hr.java.restaurant.utils;

import hr.java.restaurant.model.Deliverer;
import hr.java.restaurant.model.Meal;
import hr.java.restaurant.model.Order;
import hr.java.restaurant.model.Restaurant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Pomoćna klasa za unos podataka vezanih za narudžbe u sustav.
 * Omogućuje korisnicima unos restorana, dostavljača, te datuma i vremena isporuke za novu narudžbu.
 */

public class OrderInput {

    /**
     * Omogućava unos svih podataka potrebnih za narudžbu, uključujući odabir restorana, dostavljača,
     * datum i vrijeme isporuke. Na temelju tih podataka kreira novu narudžbu.
     *
     * @param orders polje narudžbi u koji se dodaju nove narudžbe
     * @param restaurants polje restorana iz kojeg korisnik bira restoran
     * @param scanner {@link Scanner} objekt koji se koristi za unos podataka
     * @param deliverers polje dostavljača iz kojeg korisnik bira dostavljača
     * @param meals polje jela koja se mogu dodati u narudžbu
     */

    public static void orderInput(Order[] orders, Restaurant[] restaurants, Scanner scanner, Deliverer[] deliverers, Meal[] meals) {
        for(int i = 0; i < orders.length; i++) {
            System.out.println("Odaberi restoran: ");
            for(int j = 0; j < restaurants.length; j++) {
                System.out.println((j+1)+". "+ restaurants[j].getName());
            }

            int odabraniRestoran;
            do {
                System.out.print("> ");
                odabraniRestoran = IntInput.inputInt(scanner);
                if(odabraniRestoran < 1 || odabraniRestoran > restaurants.length )
                    System.out.println("Pogresan unos! Pokusajte ponovo!");

            } while(odabraniRestoran < 1 || odabraniRestoran > restaurants.length);

            System.out.println("Odaberi dostavljaca: ");
            for(int j = 0; j < deliverers.length; j++) {
                System.out.println((j+1)+". "+ deliverers[j].getFirstName()+" "+ deliverers[j].getLastName());
            }

            int odabraniDostavljac;
            do {
                System.out.print("> ");
                odabraniDostavljac = IntInput.inputInt(scanner);
                if(odabraniDostavljac < 1 || odabraniDostavljac > deliverers.length )
                    System.out.println("Pogresan unos! Pokusajte ponovo!");

            } while(odabraniDostavljac < 1 || odabraniDostavljac > deliverers.length);

            System.out.print("Unesite datum i vrijeme isporuke (dd.MM.yyyy. HH:mm): ");
            String dateInput = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
            LocalDateTime deliveryDateAndTime = LocalDateTime.parse(dateInput, formatter);

            orders[i] = new Order(restaurants[odabraniRestoran-1], meals,
                    deliverers[odabraniDostavljac-1], deliveryDateAndTime);
        }
    }
}
