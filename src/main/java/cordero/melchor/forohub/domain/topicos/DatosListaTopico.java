package cordero.melchor.forohub.domain.topicos;

import java.time.LocalDateTime;

public record DatosListaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Status status,
        String usuario,
        String curso
) {
    public DatosListaTopico(Topicos topico) {
        this(topico.getId(),topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                topico.getStatus(),topico.getUsuario().getNombre(),topico.getCurso().getNombre());
    }
}
