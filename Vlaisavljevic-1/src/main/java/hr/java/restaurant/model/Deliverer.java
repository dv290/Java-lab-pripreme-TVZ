package hr.java.restaurant.model;

public class Deliverer extends Person {
    private Contract contract;
    private Bonus bonus;
    private int brojDostava;

    public Deliverer(DelivererBuilder builder) {
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

    public int getBrojDostava() {
        return brojDostava;
    }

    public static class DelivererBuilder {
        private String firstName;
        private String lastName;
        private Contract contract;
        private Bonus bonus;

        public Deliverer.DelivererBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Deliverer.DelivererBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Deliverer.DelivererBuilder setContract(Contract contract) {
            this.contract = contract;
            return this;
        }

        public Deliverer.DelivererBuilder setBonus(Bonus bonus) {
            this.bonus = bonus;
            return this;
        }

        public Deliverer build() {
            return new Deliverer(this);
        }
    }
}
