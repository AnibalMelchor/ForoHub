package cordero.melchor.forohub.domain.usuario.ValidacionDeUsuarios;

import cordero.melchor.forohub.domain.usuario.DatosActualizacionUsuario;
import cordero.melchor.forohub.domain.usuario.DatosRegistroUsuario;
import cordero.melchor.forohub.domain.usuario.Usuario;

public interface ValidacionDeUsuarios {
    void validarExistenciaPorId(Long id);
    void validarCorreoExistente(DatosActualizacionUsuario datos, Long id);
    void validarCorreoNuevo(DatosRegistroUsuario datos);
    void validarDesactivarUsuario(Long id);

}
