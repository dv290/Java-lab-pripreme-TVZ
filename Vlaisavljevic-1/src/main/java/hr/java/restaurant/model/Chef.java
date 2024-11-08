package hr.java.restaurant.model;

public class Chef extends Person {
    public static class ChefBuilder {
        private String firstName;
        private String lastName;
        private Contract contract;
        private Bonus bonus;

        public ChefBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public ChefBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ChefBuilder setContract(Contract contract) {
            this.contract = contract;
            return this;
        }

        public ChefBuilder setBonus(Bonus bonus) {
            this.bonus = bonus;
            return this;
        }

        public Chef build() {
            return new Chef(this);
        }
    }

    private final Contract contract;
    private final Bonus bonus;

    private Chef(ChefBuilder builder) {
        super(builder.firstName,builder.lastName);
        this.contract = builder.contract;
        this.bonus = builder.bonus;
    }

    public Contract getContract() {
        return contract;
    }

}
