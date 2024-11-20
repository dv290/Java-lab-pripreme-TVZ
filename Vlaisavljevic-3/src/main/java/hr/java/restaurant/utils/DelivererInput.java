package hr.java.restaurant.utils;

import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.restaurant.model.Bonus;
import hr.java.restaurant.model.Contract;
import hr.java.restaurant.model.Deliverer;

import java.math.BigDecimal;
import java.util.Scanner;

import static hr.java.restaurant.utils.ContractInput.contractInput;

public class DelivererInput {
    public static void delivererInput(Deliverer[] deliverers, Scanner scanner) {
        for(int i = 0; i < deliverers.length; i++) {

            String delivererFirstName, delivererLastName;
            while (true) {
                try {
                    System.out.print("Unesi ime "+(i+1)+". dostavljaca: ");
                    delivererFirstName = scanner.nextLine();

                    System.out.print("Unesi prezime "+(i+1)+". dostavljaca: ");
                    delivererLastName = scanner.nextLine();

                    checkForDuplicate(deliverers, delivererFirstName, delivererLastName);

                    break;
                }
                catch (DuplicateEntityException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.print("UGOVOR "+(i+1)+". DOSTAVLJACA: ");
            Contract delivererContract = contractInput(scanner);

            System.out.println("Unesi bonus za dostavljaca: ");
            BigDecimal bonusAmount = BigDecimalInput.inputBigDecimal(scanner);
            Bonus delivererBonus = new Bonus(bonusAmount);
            System.out.print("\n");

            deliverers[i] = new Deliverer.DelivererBuilder()
                    .setFirstName(delivererFirstName)
                    .setLastName(delivererLastName)
                    .setContract(delivererContract)
                    .setBonus(delivererBonus)
                    .build();
        }
    }

    private static void checkForDuplicate(Deliverer[] deliverers, String delivererFirstName, String delivererLastName) throws DuplicateEntityException {
        for(Deliverer deliverer : deliverers)
            if(deliverer != null && deliverer.getFirstName().equalsIgnoreCase(delivererFirstName) && deliverer.getLastName().equalsIgnoreCase(delivererLastName))
                throw new DuplicateEntityException("Dostavljač s imenom \"" + delivererFirstName + " " + delivererLastName + "\" već postoji!");
    }
}
