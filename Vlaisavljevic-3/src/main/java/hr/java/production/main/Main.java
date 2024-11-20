package hr.java.production.main;

import hr.java.restaurant.model.*;
import hr.java.restaurant.utils.*;

import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
    public static final int MINIMALNA_PLACA = 1000;


    public static final Logger log = LoggerFactory.getLogger(Main.class);
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

        categoryInput(categories, scanner);
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

    private static void categoryInput(Category[] categories, Scanner scanner) {
        CategoryInput.categoriesInput(categories, scanner);
    }

    private static void ingredientsInput(Ingredient[] ingredients, Scanner scanner, Category[] categories) {
        IngredientsInput.ingredientsInput(ingredients, scanner, categories);
    }

    private static void mealsInput(Meal[] meals, Scanner scanner, Category[] categories, Ingredient[] ingredients) {
        MealsInput.mealsInput(meals, scanner, categories, ingredients);
    }

    private static void chefsInput(Chef[] chefs, Scanner scanner) {
        ChefsInput.chefsInput(chefs, scanner);
    }

    private static void waitersInput(Waiter[] waiters, Scanner scanner) {
        WaitersInput.waitersInput(waiters,scanner);
    }

    private static void delivererInput(Deliverer[] deliverers, Scanner scanner) {
        DelivererInput.delivererInput(deliverers,scanner);
    }

    private static void restaurantInput(Restaurant[] restaurants, Scanner scanner, Meal[] meals, Chef[] chefs, Waiter[] waiters, Deliverer[] deliverers) {
        RestaurantsInput.restaurantInput(restaurants,scanner,meals,chefs,waiters,deliverers);
    }

    private static void orderInput(Order[] orders, Restaurant[] restaurants, Scanner scanner, Deliverer[] deliverers, Meal[] meals) {
        OrderInput.orderInput(orders,restaurants,scanner,deliverers,meals);
    }

    private static void dostavljacNajviseDostava(Deliverer[] deliverers) {
        Finder.dostavljacNajviseDostava(deliverers);
    }

    private static void restoranNajskupljaNarudzba(Order[] orders) {
        Finder.restoranNajskupljaNarudzba(orders);
    }

    private static void findHighestPaidEmployee(Person[] employees) {
        Finder.findHighestPaidEmployee(employees);
    }

    private static void findLongestWorkingEmployee(Person[] employees) {
        Finder.findLongestWorkingEmployee(employees);
    }

    private static void printMealWithMinMaxCalories(Meal[] newMeals) {
        Finder.printMealWithMinMaxCalories(newMeals);
    }
}