package cordero.melchor.forohub.domain.curso.Validaciones;

import cordero.melchor.forohub.domain.curso.DatosRegistroCurso;

public interface ValidacionDeCursos {
    void validar(DatosRegistroCurso datos);
    void validarExistenciaPorId(Long id);
}
