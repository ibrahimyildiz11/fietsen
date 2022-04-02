package be.vdab.fietsen.domain;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "cursussen")
@DiscriminatorColumn(name = "soort")
public abstract class Cursus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;

    public Cursus(String naam) {

        this.naam = naam;
    }
    protected Cursus(){};

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
