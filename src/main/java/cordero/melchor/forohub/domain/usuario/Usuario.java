package cordero.melchor.forohub.domain.usuario;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "usuarios")
@Entity(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correoElectronico;
    private String contrasena;
    private Boolean activo;


    public Usuario(DatosRegistroUsuario datos) {
        this.id = null;
        this.nombre = datos.nombre();
        this.correoElectronico = datos.correoElectronico();
        this.contrasena = datos.contrasena();
        this.activo = true;
    }

    public void actualizarInformaciones(@Valid DatosActualizacionUsuario datos) {
        if (datos.nombre() != null) {
            this.nombre =  datos.nombre();
        }

        if (datos.email() != null) {
            this.correoElectronico = datos.email();
        }
        if (datos.contrasena() != null) {
            this.contrasena = datos.contrasena();
        }
    }
    public void eliminar() {
        this.activo = false;
    }
}
