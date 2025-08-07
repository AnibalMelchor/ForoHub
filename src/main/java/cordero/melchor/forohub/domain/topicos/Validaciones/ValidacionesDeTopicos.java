package cordero.melchor.forohub.domain.topicos.Validaciones;

import cordero.melchor.forohub.domain.topicos.DatosRegistroTopico;

public interface ValidacionesDeTopicos {
    void validarExistenciaUsuarioYCurso(DatosRegistroTopico datos);
    void validarMismoMensajeYTitulo(DatosRegistroTopico datos);
    void validarExistenciaTopico(Long id);
}
