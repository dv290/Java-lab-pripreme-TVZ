package hr.java.restaurant.model;

public sealed interface Vegetarian permits VegetarianMeal {
    boolean isPlantBased();
    String getVegetarianIngredients();
}
