package cordero.melchor.forohub.domain.topicos;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topicos, Long> {

    boolean existsByTituloAndMensaje(@NotBlank String titulo, @NotBlank String mensaje);
}
