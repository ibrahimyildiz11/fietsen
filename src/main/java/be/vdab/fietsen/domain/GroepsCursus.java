package be.vdab.fietsen.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("G")
public class GroepsCursus extends Cursus{
    private LocalDate van;
    private LocalDate tot;

    public GroepsCursus(String naam, LocalDate van, LocalDate tot) {
        super(naam);
        this.van = van;
        this.tot = tot;
    }

    public GroepsCursus(LocalDate van, LocalDate tot) {
        this.van = van;
        this.tot = tot;
    }
    protected GroepsCursus() {};

    public LocalDate getVan() {
        return van;
    }

    public LocalDate getTot() {
        return tot;
    }
}
