package cordero.melchor.forohub.domain.topicos;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
    Long id,
    String titulo,
    String mensaje,
    LocalDateTime fechaCreacion,
    Status status,
    String curso,
    String usuario,
    int respuestas

) {
    public DatosDetalleTopico(Topicos topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getStatus(),topico.getCurso().getNombre(), topico.getUsuario().getNombre(), topico.getRespuestas());
    }
}
