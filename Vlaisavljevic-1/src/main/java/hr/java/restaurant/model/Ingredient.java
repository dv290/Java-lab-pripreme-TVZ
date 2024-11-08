package hr.java.restaurant.model;

import java.math.BigDecimal;

public class Ingredient extends Entity {
    private String name;
    private Category category;
    private BigDecimal kcal;
    private String preparationMethod;
    public static Long idCounter=1L;

    public Ingredient(String name, Category category, BigDecimal kcal, String preparationMethod) {
        super(idCounter);
        this.name = name;
        this.category = category;
        this.kcal = kcal;
        this.preparationMethod = preparationMethod;
        idCounter++;
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

    public BigDecimal getKcal() {
        return kcal;
    }

    public void setKcal(BigDecimal kcal) {
        this.kcal = kcal;
    }

    public String getPreparationMethod() {
        return preparationMethod;
    }

    public void setPreparationMethod(String preparationMethod) {
        this.preparationMethod = preparationMethod;
    }
}
