package hr.java.restaurant.model;

import java.math.BigDecimal;

public non-sealed class Grill extends Meal implements Meat{

    public Grill(String name,Category category,Ingredient[] ingredient ,BigDecimal price) {
        super(name, category, ingredient, price);
    }

    @Override
    public boolean isGrilled() {
        return true;
    }

    @Override
    public String getMeatType(){
        return "Tip mesa: ";
    }
}
