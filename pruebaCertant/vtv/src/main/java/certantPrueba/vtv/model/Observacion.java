package certantPrueba.vtv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "observacion")
public class Observacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_observacion")
    private int nro_observacion;
    @Column(name = "luces")
    private String luces;
    @Column(name = "patente")
    private String patente;
    @Column(name = "espejos")
    private String espejos;
    @Column(name = "chasis")
    private String chasis;
    @Column(name = "vidrios_seguridad")
    private String vidrios_seguridad;
    @Column(name = "emergencia")
    private String emergencia;

    public int getNro_observacion() {
        return nro_observacion;
    }

    public void setNro_observacion(int nro_observacion) {
        this.nro_observacion = nro_observacion;
    }

    public String getLuces() {
        return luces;
    }

    public void setLuces(String luces) {
        this.luces = luces;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getEspejos() {
        return espejos;
    }

    public void setEspejos(String espejos) {
        this.espejos = espejos;
    }

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public String getVidrios_seguridad() {
        return vidrios_seguridad;
    }

    public void setVidrios_seguridad(String vidrios_seguridad) {
        this.vidrios_seguridad = vidrios_seguridad;
    }

    public String getEmergencia() {
        return emergencia;
    }

    public void setEmergencia(String emergencia) {
        this.emergencia = emergencia;
    }

    @Override
    public String toString() {
        return "Observacion [chasis=" + chasis + ", emergencia=" + emergencia + ", espejos=" + espejos + ", luces="
                + luces + ", nro_observacion=" + nro_observacion + ", patente=" + patente + ", vidrios_seguridad="
                + vidrios_seguridad + "]";
    }

}