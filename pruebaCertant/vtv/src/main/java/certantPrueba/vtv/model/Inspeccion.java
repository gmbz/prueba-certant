package certantPrueba.vtv.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inspeccion")
public class Inspeccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_inspeccion")
    private int nro_inspeccion;
    @Column(name = "fecha")
    private LocalDateTime fecha;
    @Column(name = "debe_pagar")
    private boolean debe_pagar;
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "legajo")
    private Inspector inspector;
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "id_estado")
    private EstadoInspeccion estado;
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "patente")
    private Vehiculo vehiculo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nro_observacion")
    private Observacion observacion;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nro_medicion")
    private Medicion medicion;

    public boolean isDebe_pagar() {
        return debe_pagar;
    }

    public void setDebe_pagar(boolean debe_pagar) {
        this.debe_pagar = debe_pagar;
    }

    public int getNro_inspeccion() {
        return nro_inspeccion;
    }

    public void setNro_inspeccion(int nro_inspeccion) {
        this.nro_inspeccion = nro_inspeccion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Inspector getInspector() {
        return inspector;
    }

    public void setInspector(Inspector inspector) {
        this.inspector = inspector;
    }

    public EstadoInspeccion getEstado() {
        return estado;
    }

    public void setEstado(EstadoInspeccion estado) {
        this.estado = estado;
    }

    public Observacion getObservacion() {
        return observacion;
    }

    public void setObservacion(Observacion observacion) {
        this.observacion = observacion;
    }

    public Medicion getMedicion() {
        return medicion;
    }

    public void setMedicion(Medicion medicion) {
        this.medicion = medicion;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public String toString() {
        return "Inspeccion [debe_pagar=" + debe_pagar + ", estado=" + estado + ", fecha=" + fecha + ", inspector="
                + inspector + ", medicion=" + medicion + ", nro_inspeccion=" + nro_inspeccion + ", observacion="
                + observacion + ", vehiculo=" + vehiculo + "]";
    }

}
