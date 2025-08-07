package cordero.melchor.forohub.domain.curso;

import cordero.melchor.forohub.domain.curso.Validaciones.ValidacionDeCursos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioDeCursos {
    @Autowired
    private CursoRepository repository;
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private List<ValidacionDeCursos> validacionDeCursos;

    public Curso registrar(DatosRegistroCurso datos){
        validacionDeCursos.forEach(validacionDeCurso -> {validacionDeCurso.validar(datos);});
        var curso = new Curso(datos);
        repository.save(curso);
        return curso;
    }
    public Page listar(Pageable paginacion){
       return repository.findAll(paginacion).map(DatosListaCurso::new);
    }
    public void eliminar (Long id){
        validacionDeCursos.forEach(validacionDeCurso -> {
            validacionDeCurso.validarExistenciaPorId(id);
        });
        var curso = cursoRepository.findById(id);
        curso.get().eliminar();
    }
}
