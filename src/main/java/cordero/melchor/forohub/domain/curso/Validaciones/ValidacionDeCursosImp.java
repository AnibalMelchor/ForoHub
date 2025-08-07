package cordero.melchor.forohub.domain.curso.Validaciones;

import cordero.melchor.forohub.domain.curso.CursoRepository;
import cordero.melchor.forohub.domain.curso.DatosRegistroCurso;
import cordero.melchor.forohub.infra.exceptions.RecursoNoEncontradoException;
import cordero.melchor.forohub.infra.exceptions.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionDeCursosImp implements ValidacionDeCursos {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public void validar(DatosRegistroCurso datos) {
        if(cursoRepository.existsByNombre(datos.nombre())){
            throw new ValidacionException("No puedes registrar un curso que ya existe con este nombre");
        }
    }

    @Override
    public void validarExistenciaPorId(Long id) {
        var curso = cursoRepository.findById(id);
        if (!curso.isPresent()) {
            throw new RecursoNoEncontradoException("No puedes desactivar un curso que no existe con ese id informado");
        }
        if (!curso.get().getActivo()) {
            throw new ValidacionException("Este curso ya está desactivado");
        }

    }
}
