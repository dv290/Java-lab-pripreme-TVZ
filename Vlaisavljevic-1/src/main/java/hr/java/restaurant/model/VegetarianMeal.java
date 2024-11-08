package hr.java.restaurant.model;

import java.math.BigDecimal;

public non-sealed class VegetarianMeal extends Meal implements Vegetarian {

    public VegetarianMeal(String name,Category category,Ingredient[] ingredient ,BigDecimal price) {
        super(name, category, ingredient, price);
    }

    @Override
    public boolean isPlantBased() {
        return true;
    }

    @Override
    public String getVegetarianIngredients() {
        return "Popis vegeterijanskih sastojaka: ";
    }
}
