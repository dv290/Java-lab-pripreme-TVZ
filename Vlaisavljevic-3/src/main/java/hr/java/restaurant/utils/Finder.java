package hr.java.restaurant.utils;

import hr.java.restaurant.model.*;

import java.math.BigDecimal;

/**
 * Util klasa u kojoj se pronalaze metode za specifične stavke koje se traže u poljima,
 * kao što su npr. dostavljač s najviše dostava, kuhar s najvišom plaćom, itd...
 */

public class Finder {

    public static void dostavljacNajviseDostava(Deliverer[] deliverers) {
        int maxDostave = 0;
        Deliverer[] topDostavljac = new Deliverer[deliverers.length];
        int brojTopDostavljaca = 0;

        for (Deliverer deliverer : deliverers) {
            if (deliverer.getBrojDostava() > maxDostave) {
                maxDostave = deliverer.getBrojDostava();
                brojTopDostavljaca = 0;
                topDostavljac[brojTopDostavljaca++] = deliverer;
            } else if (deliverer.getBrojDostava() == maxDostave) {
                topDostavljac[brojTopDostavljaca++] = deliverer;
            }
        }

        System.out.println("Dostavljaci s najvise dostava (" + maxDostave + "):");
        for (int i = 0; i < brojTopDostavljaca; i++) {
            Deliverer dostavljac = topDostavljac[i];
            System.out.println(dostavljac.getFirstName() + " " + dostavljac.getLastName() + ", Placa: " + dostavljac.getContract().getSalary());
        }
    }



    public static void restoranNajskupljaNarudzba(Order[] orders) {
        BigDecimal highestPrice = BigDecimal.ZERO;
        Restaurant[] highestRestaurants = new Restaurant[orders.length];
        int restaurantCount = 0;

        for (Order order : orders) {
            BigDecimal currentPrice = order.getTotalPrice();
            Restaurant restaurant = order.getRestaurant();

            if (currentPrice.compareTo(highestPrice) > 0) {
                highestPrice = currentPrice;
                restaurantCount = 0;
                highestRestaurants[restaurantCount++] = restaurant;
            } else if (currentPrice.compareTo(highestPrice) == 0) {
                highestRestaurants[restaurantCount++] = restaurant;
            }
        }

        System.out.println("Restorani s najskupljom narudzbom (cijena: " + highestPrice + "):");
        for (int i = 0; i < restaurantCount; i++) {
            System.out.println("Restoran: " + highestRestaurants[i].getName());
        }
    }



    private static BigDecimal getSalary(Person employee) {
        return employee.getContract().getSalary();
    }

    public static void findHighestPaidEmployee(Person[] employees) {
        Person highestPaid = employees[0];
        BigDecimal highestSalary = getSalary(highestPaid);

        for (Person employee : employees) {
            if (employee != null) {
                BigDecimal salary = getSalary(employee);
                if (salary.compareTo(highestSalary) > 0) {
                    highestPaid = employee;
                    highestSalary = salary;
                }
            }
        }
        System.out.println("Najplaceniji radnik je: "+highestPaid.getFirstName()+
                " "+highestPaid.getLastName()+"sa placom od: "+highestPaid.getContract().getSalary());
    }



    public static void findLongestWorkingEmployee(Person[] employees) {
        Person longestWorkingEmployee = employees[0];
        long longestDuration = Long.MIN_VALUE;

        for (Person employee : employees) {
            long duration = employee.getContract().getEndDate().toEpochDay() - employee.getContract().getStartDate().toEpochDay();

            if (duration > longestDuration || (duration == longestDuration && employee.getContract()
                    .getStartDate().isBefore(longestWorkingEmployee.getContract().getStartDate()))) {
                longestWorkingEmployee = employee;
                longestDuration = duration;
            }
        }

        System.out.println("Zaposlenik koji najduže radi je: " + longestWorkingEmployee.getFirstName() + " " +
                longestWorkingEmployee.getLastName());
    }



    public static void printMealWithMinMaxCalories(Meal[] newMeals) {
        Meal maxCalorieMeal = newMeals[0];
        Meal minCalorieMeal = newMeals[0];

        for (Meal meal : newMeals) {
            for(int i = 0; i < meal.getIngredient().length; i++) {
                if (meal.getTotalKcal().compareTo( maxCalorieMeal.getTotalKcal())>0)
                    maxCalorieMeal = meal;

                if (meal.getTotalKcal().compareTo(minCalorieMeal.getTotalKcal())<0)
                    minCalorieMeal = meal;
            }
        }
        System.out.println("Jelo sa najviše kalorija: ");
        System.out.println(maxCalorieMeal.getName());

        System.out.println("Jelo sa najmanje kalorija: ");
        System.out.println(minCalorieMeal.getName());
        System.out.println(minCalorieMeal.getCategory().getName());
        System.out.println(minCalorieMeal.getPrice());
    }
}
