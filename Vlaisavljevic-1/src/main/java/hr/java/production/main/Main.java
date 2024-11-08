package hr.java.production.main;

import hr.java.restaurant.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    private static final int BROJ_CATEGORY = 3;
    private static final int BROJ_INGREDIENTS = 2;
    private static final int BROJ_MEAL = 3;
    private static final int BROJ_CHEF = 3;
    private static final int BROJ_WAITER = 3;
    private static final int BROJ_DELIVERER = 3;
    private static final int BROJ_RESTAURANT = 3;
    private static final int BROJ_ORDER = 3;
    private static final int BROJ_LAB2_MEALS = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Category[] categories = new Category[BROJ_CATEGORY]; //3
        Ingredient[] ingredients = new Ingredient[BROJ_INGREDIENTS]; //5
        Meal[] meals = new Meal[BROJ_MEAL]; //3
        Chef[] chefs = new Chef[BROJ_CHEF]; //3
        Waiter[] waiters = new Waiter[BROJ_WAITER]; //3
        Deliverer[] deliverers = new Deliverer[BROJ_DELIVERER]; //3
        Restaurant[] restaurants = new Restaurant[BROJ_RESTAURANT]; //3
        Order[] orders = new Order[BROJ_ORDER]; //3

        Person[] employees = new Person[BROJ_CHEF + BROJ_WAITER + BROJ_DELIVERER]; //9
        Meal[] newMeals = new Meal[BROJ_LAB2_MEALS]; //3

        categoriesInput(categories, scanner);
        ingredientsInput(ingredients, scanner, categories);
        mealsInput(meals, scanner, categories, ingredients);
        chefsInput(chefs, scanner);
        waitersInput(waiters, scanner);
        delivererInput(deliverers, scanner);
        restaurantInput(restaurants, scanner, meals, chefs, waiters, deliverers);
        orderInput(orders, restaurants, scanner, deliverers, meals);
        dostavljacNajviseDostava(deliverers);
        restoranNajskupljaNarudzba(orders);

        findHighestPaidEmployee(employees);
        findLongestWorkingEmployee(employees);
        printMealWithMinMaxCalories(newMeals);


        scanner.close();
    }

    private static Address addressInput(Scanner scanner) {
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

    private static void categoriesInput(Category[] categories, Scanner scanner) {
        for(int i = 0; i < categories.length; i++) {
            System.out.print("Unesi naziv "+(i+1)+". kategorije: ");
            String categoryName = scanner.nextLine();

            System.out.print("Unesi opis "+(i+1)+". kategorije: ");
            String categoryDescription = scanner.nextLine();
            System.out.print("\n");

            categories[i] = new Category.CategoryBuilder()
                    .setName(categoryName)
                    .setDescription(categoryDescription)
                    .build();
        }
    }

    private static void ingredientsInput(Ingredient[] ingredients, Scanner scanner, Category[] categories) {
        for(int i = 0; i < ingredients.length; i++) {
            System.out.print("Unesi ime "+(i+1)+". sastojka: ");
            String ingredientName = scanner.nextLine();

            System.out.println("Odaberi kategoriju "+(i+1)+". sastojka: ");
            for(int j = 0; j < categories.length; j++)
                System.out.println((j+1)+". "+ categories[j].getName());

            int odabranaKategorija;
            do {
                System.out.print("> ");
                odabranaKategorija = scanner.nextInt();
                scanner.nextLine();
                if(odabranaKategorija < 1 || odabranaKategorija > categories.length )
                    System.out.println("Pogresan unos! Pokusajte ponovo!");

            } while(odabranaKategorija < 1 || odabranaKategorija > categories.length);

            System.out.print("\nUnesi broj kalorija "+(i+1)+". sastojka: ");
            BigDecimal ingredient_kcal = scanner.nextBigDecimal();
            scanner.nextLine();

            System.out.print("Unesi korake pripreme "+(i+1)+". sastojka: ");
            String ingredientPreparationMethod = scanner.nextLine();
            System.out.print("\n");

            ingredients[i] = new Ingredient(ingredientName, categories[odabranaKategorija-1], ingredient_kcal, ingredientPreparationMethod);
        }
    }

    private static void mealsInput(Meal[] meals, Scanner scanner, Category[] categories, Ingredient[] ingredients) {
        for(int i = 0; i < meals.length; i++) {
            System.out.print("Unesi ime "+(i+1)+". jela: ");
            String mealName = scanner.nextLine();

            System.out.println("Odaberi kategoriju "+(i+1)+". jela: ");
            for(int j = 0; j < categories.length; j++)
                System.out.println((j+1)+". "+ categories[j].getName());

            int odabranaKategorija = mealCategorySelect(scanner, categories);

            System.out.print("Unesi cijenu "+(i+1)+". jela: ");
            BigDecimal mealPrice = scanner.nextBigDecimal();
            scanner.nextLine();
            System.out.print("\n");

            meals[i] = new Meal(mealName, categories[odabranaKategorija-1],ingredients,mealPrice);
        }
    }

    private static int mealCategorySelect(Scanner scanner, Category[] categories) {
        int odabir;
        do {
            System.out.print("> ");
            odabir = scanner.nextInt();
            scanner.nextLine();
            if(odabir < 1 || odabir > categories.length )
                System.out.println("Pogresan unos! Pokusajte ponovno!");

        } while(odabir < 1 || odabir > categories.length);
        return odabir;
    }

    private static Contract.ContractType contractTypeInput(Scanner scanner) {
        Contract.ContractType selectedContractType = null;

        int odabir;
        do {
            System.out.print("> ");
            odabir = scanner.nextInt();
            scanner.nextLine();

            switch (odabir) {
                case 1 -> selectedContractType = Contract.ContractType.FULL_TIME;
                case 2 -> selectedContractType = Contract.ContractType.PART_TIME;
                default -> System.out.println("Pogresan unos! Pokusajte ponovno!");
            }

        }
        while (selectedContractType == null);

        return selectedContractType;
    }

    private static Contract contractInput(Scanner scanner) {
        System.out.print("Unesi placu: ");
        BigDecimal salary = scanner.nextBigDecimal();
        scanner.nextLine();

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

    private static void chefsInput(Chef[] chefs, Scanner scanner) {
        for(int i = 0; i< chefs.length; i++) {
            System.out.print("Unesi ime "+(i+1)+". kuhara: ");
            String chefFirstName = scanner.nextLine();

            System.out.print("Unesi prezime "+(i+1)+". kuhara: ");
            String chefLastName = scanner.nextLine();

            System.out.print("UGOVOR "+(i+1)+". KUHARA: ");
            Contract chefContract = contractInput(scanner);

            System.out.println("Unesi bonus za kuhara: ");
            BigDecimal bonusAmount = scanner.nextBigDecimal();
            Bonus chefBonus = new Bonus(bonusAmount);
            scanner.nextLine();
            System.out.print("\n");

            chefs[i] = new Chef.ChefBuilder()
                    .setFirstName(chefFirstName)
                    .setLastName(chefLastName)
                    .setContract(chefContract)
                    .setBonus(chefBonus)
                    .build();
        }
    }

    private static void waitersInput(Waiter[] waiters, Scanner scanner) {
        for(int i = 0; i < waiters.length; i++) {
            System.out.print("Unesi ime "+(i+1)+". konobara: ");
            String waiterFirstName = scanner.nextLine();

            System.out.print("Unesi prezime "+(i+1)+". konobara: ");
            String waiterLastName = scanner.nextLine();

            System.out.print("UGOVOR "+(i+1)+". KONOBARA: ");
            Contract waiterContract = contractInput(scanner);

            System.out.println("Unesi bonus za konobara: ");
            BigDecimal bonusAmount = scanner.nextBigDecimal();
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

    private static void delivererInput(Deliverer[] deliverers, Scanner scanner) {
        for(int i = 0; i < deliverers.length; i++) {
            System.out.print("Unesi ime "+(i+1)+". dostavljaca: ");
            String delivererFirstName = scanner.nextLine();

            System.out.print("Unesi prezime "+(i+1)+". dostavljaca: ");
            String delivererLastName = scanner.nextLine();

            System.out.print("UGOVOR "+(i+1)+". DOSTAVLJACA: ");
            Contract delivererContract = contractInput(scanner);

            System.out.println("Unesi bonus za dostavljaca: ");
            BigDecimal bonusAmount = scanner.nextBigDecimal();
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


    private static void restaurantInput(Restaurant[] restaurants, Scanner scanner, Meal[] meals, Chef[] chefs, Waiter[] waiters, Deliverer[] deliverers) {
        for(int i = 0; i< restaurants.length; i++) {
            System.out.print("Unesi ime "+(i+1)+". restorana: ");
            String restaurantName = scanner.nextLine();
            Address restaurantAddress = addressInput(scanner);
            System.out.print("\n");

            restaurants[i] = new Restaurant(restaurantName, restaurantAddress, meals, chefs, waiters, deliverers);
        }
    }

    private static void orderInput(Order[] orders, Restaurant[] restaurants, Scanner scanner, Deliverer[] deliverers, Meal[] meals) {
        for(int i = 0; i < orders.length; i++) {
            System.out.println("Odaberi restoran: ");
            for(int j = 0; j < restaurants.length; j++) {
                System.out.println((j+1)+". "+ restaurants[j].getName());
            }

            int odabraniRestoran;
            do {
                System.out.print("> ");
                odabraniRestoran = scanner.nextInt();
                scanner.nextLine();
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
                odabraniDostavljac = scanner.nextInt();
                scanner.nextLine();
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
