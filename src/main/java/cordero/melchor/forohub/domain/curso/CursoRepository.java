package cordero.melchor.forohub.domain.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface CursoRepository extends CrudRepository<Curso, Long> {
    Page<Curso> findAll(Pageable paginacion);

    boolean existsByNombre(String nombre);
}
