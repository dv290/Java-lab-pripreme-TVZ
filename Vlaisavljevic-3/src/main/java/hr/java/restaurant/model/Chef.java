package hr.java.restaurant.model;

/**
 * Klasa koja predstavlja kuhara u sustavu.
 * Chef klasa nasljeđuje klasu {@link Person} i proširuje je dodatnim informacijama specifičnim za kuhara, kao što su ugovor ({@link Contract}) i bonus ({@link Bonus}).</p>
 *
 * Ova klasa koristi Builder obrazac za konstrukciju objekta kuhara, omogućujući postavljanje svih potrebnih parametara
 * kao što su ime, prezime, ugovor i bonus kroz {@link ChefBuilder} klasu.</p>
 *
 * <p>Chef objekti su imutabilni jer se svi parametri postavljaju u konstruktoru putem buildera i ne mogu se mijenjati nakon
 * što su jednom inicijalizirani.
 *
 * @see Person
 * @see Contract
 * @see Bonus
 */


public class Chef extends Person {
    /**
     * Builder pattern za konstrukciju objekta Chef.
     * Ova unutarnja klasa omogućuje postavljanje svih atributa kuhara (ime, prezime, ugovor, bonus) i izgradnju objekta
     * tipa {@link Chef} bez potrebe za velikim brojem konstruktora.
     */
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
