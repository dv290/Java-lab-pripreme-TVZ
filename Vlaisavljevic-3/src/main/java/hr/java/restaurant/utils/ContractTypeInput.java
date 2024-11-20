package hr.java.restaurant.utils;

import hr.java.restaurant.model.Contract;

import java.util.Scanner;

/**
 * Pomoćna klasa za unos vrste zaposlenja. Omogućava korisniku odabir između dvije vrste zaposlenja: full-time i part-time.
 * Ova klasa koristi metodu za unos broja i osigurava ispravan unos kroz ponovni unos u slučaju pogreške.
 */

public class ContractTypeInput {

    /**
     * Omogućava unos vrste zaposlenja, gdje korisnik bira između full-time i part-time opcija.
     * U slučaju pogrešnog unosa, korisniku je omogućeno ponavljanje unosa dok ne odabere ispravnu opciju.
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
