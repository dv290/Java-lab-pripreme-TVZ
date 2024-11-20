package hr.java.restaurant.utils;

import hr.java.restaurant.model.Contract;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Pomoćna klasa za unos podataka o ugovoru. Ova klasa omogućava unos plaće, vrste zaposlenja te datuma početka i kraja rada.
 */

public class ContractInput {

    /**
     * Unosi podatke o ugovoru, uključujući plaću, vrstu zaposlenja, datum početka i kraja rada.
     * Koristi pomoćne metode za unos vrsta zaposlenja i validaciju unosa.
     *
     * @param scanner {@link Scanner} objekt koji se koristi za unos podataka
     * @return novostvoreni {@link Contract} objekt temeljen na unesenim podacima
     */

    public static Contract contractInput(Scanner scanner) {
        System.out.print("Unesi placu: ");
        BigDecimal salary = BigDecimalInput.inputSalary(scanner);

        System.out.println("Odaberi vrstu zaposljavanja:\n1. Full time\n2. Part time");
        Contract.ContractType contractType = contractTypeInput(scanner);

        System.out.print("Unesi pocetak rada: ");
        String startDateInput = scanner.nextLine();
        LocalDate startDate = LocalDate.parse(startDateInput, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        System.out.print("Unesi kraj rada: ");
        String endDateInput = scanner.nextLine();
        LocalDate endDate = LocalDate.parse(endDateInput, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        return new Contract(salary, startDate, endDate, contractType);
    }

    /**
     * Omogućava unos vrste zaposlenja, gdje korisnik može odabrati između full-time i part-time zaposlenja.
     * Provodi provjeru unosa i omogućava ponovni unos u slučaju pogreške.
     *
     * @param scanner {@link Scanner} objekt koji se koristi za unos podataka
     * @return odabranu vrstu zaposlenja kao {@link Contract.ContractType}
     */

    private static Contract.ContractType contractTypeInput(Scanner scanner) {
        Contract.ContractType selectedContractType = null;

        int odabir;
        do {
            System.out.print("> ");
            odabir = IntInput.inputInt(scanner);

            switch (odabir) {
                case 1 -> selectedContractType = Contract.ContractType.FULL_TIME;
                case 2 -> selectedContractType = Contract.ContractType.PART_TIME;
                default -> System.out.println("Pogresan unos! Pokusajte ponovno!");
            }

        }
        while (selectedContractType == null);

        return selectedContractType;
    }
}
