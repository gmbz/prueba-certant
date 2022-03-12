package certantPrueba.vtv.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "oblea")
public class Oblea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_oblea")
    private int nro_oblea;
    @Column(name = "fecha_vencimiento")
    private LocalDateTime fecha_vencimiento;

    public int getNro_oblea() {
        return nro_oblea;
    }

    public void setNro_oblea(int nro_oblea) {
        this.nro_oblea = nro_oblea;
    }

    public LocalDateTime getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(LocalDateTime fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    @Override
    public String toString() {
        return "Oblea [fecha_vencimiento=" + fecha_vencimiento + ", nro_oblea=" + nro_oblea + "]";
    }

}
