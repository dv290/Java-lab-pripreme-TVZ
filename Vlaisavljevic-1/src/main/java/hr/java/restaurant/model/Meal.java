package hr.java.restaurant.model;

import java.math.BigDecimal;

public class Meal extends Entity{
    private String name;
    private Category category;
    private Ingredient[] ingredient;
    private BigDecimal price;
    public static Long idCounter=1L;


    public Meal(String name, Category category,Ingredient[] ingredient ,BigDecimal price) {
        super(idCounter);
        this.name = name;
        this.category = category;
        this.ingredient = ingredient;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Ingredient[] getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient[] ingredient) {
        this.ingredient = ingredient;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalKcal() {
        BigDecimal totalKcal = new BigDecimal("0");
        for(Ingredient ing : ingredient)
            totalKcal = totalKcal.add(ing.getKcal());
        return totalKcal;
    }
}
