package certantPrueba.vtv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "inspector")
public class Inspector extends Persona {

    @Column(name = "legajo")
    private int legajo;

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public void generaLegajo(int leg) {
        this.legajo = leg + 1;
    }

}
