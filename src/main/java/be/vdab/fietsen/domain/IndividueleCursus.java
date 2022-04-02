package be.vdab.fietsen.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("I")
public class IndividueleCursus extends Cursus{
    private int duurtijd;

    public IndividueleCursus(String naam, int duurtijd) {
        super(naam);
        this.duurtijd = duurtijd;
    }

    public IndividueleCursus(int duurtijd) {
        this.duurtijd = duurtijd;
    }
    protected IndividueleCursus(){};

    public int getDuurtijd() {
        return duurtijd;
    }
}
