package hr.java.restaurant.model;

public class Address extends Entity {
    private String street;
    private String houseNumber;
    private String city;
    private String postalCode;
    public static Long idCounter = 1L;

    // Private constructor that takes a Builder instance
    private Address(AddressBuilder builder) {
        super(idCounter);
        this.street = builder.street;
        this.houseNumber = builder.houseNumber;
        this.city = builder.city;
        this.postalCode = builder.postalCode;
    }

    // Getters
    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    // Static inner Builder class
    public static class AddressBuilder {
        private String street;
        private String houseNumber;
        private String city;
        private String postalCode;

        // Setter methods for the builder, returning the builder instance
        public AddressBuilder setStreet(String street) {
            this.street = street;
            return this;
        }

        public AddressBuilder setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public AddressBuilder setCity(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        // Build method to create an Address instance
        public Address build() {
            return new Address(this);
        }
    }
}
