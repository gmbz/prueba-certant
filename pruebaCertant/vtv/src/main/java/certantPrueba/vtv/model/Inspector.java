package certantPrueba.vtv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "inspector")
public class Inspector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "legajo")
    private int legajo;
    @Column(name = "nombre")
    @NotEmpty
    private String nombre;
    @Column(name = "apellido")
    @NotEmpty
    private String apellido;
    @Column(name = "email")
    @NotEmpty
    private String email;
    @Column(name = "telefono")
    @NotEmpty
    private String telefono;

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Inspector [apellido=" + apellido + ", email=" + email + ", legajo=" + legajo + ", nombre=" + nombre
                + ", telefono=" + telefono + "]";
    }

}
