package hr.java.restaurant.model;

public class Waiter extends Person {
    private Contract contract;
    private Bonus bonus;

    private Waiter(WaiterBuilder builder) {
        super(builder.firstName, builder.lastName);
        this.contract = builder.contract;
        this.bonus = builder.bonus;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public static class WaiterBuilder {
        private String firstName;
        private String lastName;
        private Contract contract;
        private Bonus bonus;

        public Waiter.WaiterBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Waiter.WaiterBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Waiter.WaiterBuilder setContract(Contract contract) {
            this.contract = contract;
            return this;
        }

        public Waiter.WaiterBuilder setBonus(Bonus bonus) {
            this.bonus = bonus;
            return this;
        }

        public Waiter build() {
            return new Waiter(this);
        }
    }
}
