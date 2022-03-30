package certantPrueba.vtv.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import certantPrueba.vtv.validator.PatenteVehiculo;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "vehiculo")
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
// property = "patente")
public class Vehiculo {

    @Id
    @Column(name = "patente")
    @NotEmpty(message = "La patente es requerida")
    @PatenteVehiculo
    private String patente;
    @Column(name = "color")
    @NotEmpty(message = "El color es requerido")
    private String color;
    @Column(name = "year")
    @NotEmpty(message = "El año es requerido")
    private String year;
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE })
    @JoinColumn(name = "id_modelo")
    // @JsonBackReference
    private Modelo modelo;
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.REFRESH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "dni")
    // @JsonBackReference
    @JsonIgnoreProperties(value = { "applications", "hibernateLazyInitializer" })
    private Cliente cliente;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nro_oblea")
    private Oblea oblea;
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    //@JsonManagedReference
    @JsonIgnore
    private List<Inspeccion> inspecciones = new ArrayList<>();

    public Vehiculo() {
    }

    public List<Inspeccion> getInspecciones() {
        return inspecciones;
    }

    public void setInspecciones(List<Inspeccion> inspecciones) {
        this.inspecciones = inspecciones;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Oblea getOblea() {
        return oblea;
    }

    public void setOblea(Oblea oblea) {
        this.oblea = oblea;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

}
