package cordero.melchor.forohub.domain.topicos;

import cordero.melchor.forohub.domain.curso.Curso;
import cordero.melchor.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensaje;

    @JoinColumn(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JoinColumn(name = "usuario_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @JoinColumn(name = "curso_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Curso curso;

    private int respuestas;

    public Topicos(DatosRegistroTopico datos, Usuario usuario, Curso curso) {
        this.id = null;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = Status.NO_RESPONDIDO;
        this.usuario = usuario;
        this.curso = curso;
        this.respuestas = 0;
    }
    public void actualizarInformaciones(@Valid DatosActualizacionTopico datos) {
        if(datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
    }
}
