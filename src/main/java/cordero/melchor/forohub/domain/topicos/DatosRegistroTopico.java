package cordero.melchor.forohub.domain.topicos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull Long usuario,
        @NotNull Long curso
) {
}
