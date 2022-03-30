package certantPrueba.vtv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import certantPrueba.vtv.validator.DniPersona;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persona")
public class Persona {
    @Id
    @Column(name = "dni")
    @NotEmpty(message = "El dni es requerido")
    @DniPersona
    private String dni;
    @Column(name = "nombre")
    @NotEmpty(message = "El nombre es requerido")
    private String nombre;
    @Column(name = "apellido")
    @NotEmpty(message = "El apellido es requerido")
    private String apellido;
    @Column(name = "email")
    @NotEmpty(message = "El email es requerido")
    @Email
    private String email;
    @Column(name = "telefono")
    @NotEmpty(message = "El telefono es requerido")
    private String telefono;

    public Persona() {

    }

    public Persona(@NotEmpty(message = "El dni es requerido") String dni,
            @NotEmpty(message = "El nombre es requerido") String nombre,
            @NotEmpty(message = "El apellido es requerido") String apellido,
            @NotEmpty(message = "El email es requerido") String email,
            @NotEmpty(message = "El telefono es requerido") String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

}
