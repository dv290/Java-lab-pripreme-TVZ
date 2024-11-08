package hr.java.restaurant.model;

public sealed interface Meat permits Grill{
    boolean isGrilled();
    String getMeatType();
}
