package cordero.melchor.forohub.domain.topicos;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizacionTopico(
    @NotBlank String mensaje
) {
}
