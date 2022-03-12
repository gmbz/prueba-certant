package certantPrueba.vtv.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {

    @Id
    @Column(name = "patente")
    @NotEmpty
    private String patente;
    @Column(name = "marca")
    @NotEmpty
    private String marca;
    @Column(name = "modelo")
    @NotEmpty
    private String modelo;
    @Column(name = "color")
    @NotEmpty
    private String color;
    @Column(name = "year")
    @NotEmpty
    private String year;
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nro_oblea")
    private Oblea oblea;
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private List<Inspeccion> inspecciones = new ArrayList<>();

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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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

    @Override
    public String toString() {
        return "Vehiculo [cliente=" + cliente + ", color=" + color + ", marca=" + marca + ", modelo=" + modelo
                + ", oblea=" + oblea + ", patente=" + patente + ", year=" + year + "]";
    }

}
