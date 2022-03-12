package certantPrueba.vtv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medicion")
public class Medicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_medicion")
    private int nro_medicion;
    @Column(name = "suspencion")
    private String suspencion;
    @Column(name = "tren_delantero")
    private String tren_delantero;
    @Column(name = "frenos")
    private String frenos;
    @Column(name = "contaminacion")
    private String contaminacion;

    public int getNro_medicion() {
        return nro_medicion;
    }

    public void setNro_medicion(int nro_medicion) {
        this.nro_medicion = nro_medicion;
    }

    public String getSuspencion() {
        return suspencion;
    }

    public void setSuspencion(String suspencion) {
        this.suspencion = suspencion;
    }

    public String getTren_delantero() {
        return tren_delantero;
    }

    public void setTren_delantero(String tren_delantero) {
        this.tren_delantero = tren_delantero;
    }

    public String getFrenos() {
        return frenos;
    }

    public void setFrenos(String frenos) {
        this.frenos = frenos;
    }

    public String getContaminacion() {
        return contaminacion;
    }

    public void setContaminacion(String contaminacion) {
        this.contaminacion = contaminacion;
    }

    @Override
    public String toString() {
        return "Medicion [contaminacion=" + contaminacion + ", frenos=" + frenos + ", nro_medicion=" + nro_medicion
                + ", suspencion=" + suspencion + ", tren_delantero=" + tren_delantero + "]";
    }

}
