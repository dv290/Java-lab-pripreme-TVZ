package hr.java.restaurant.model;

import java.math.BigDecimal;

public non-sealed class VeganMeal extends Meal implements Vegan{

    public VeganMeal(String name,Category category,Ingredient[] ingredient ,BigDecimal price) {
        super(name, category,ingredient,price);
    }

    @Override
    public boolean isDairyFree() {
        return true;
    }

    @Override
    public String getVeganIngredients() {
        return "Popis veganskih sastojaka: ";
    }
}
