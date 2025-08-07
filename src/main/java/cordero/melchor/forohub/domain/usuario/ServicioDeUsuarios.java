package cordero.melchor.forohub.domain.usuario;

import cordero.melchor.forohub.domain.usuario.ValidacionDeUsuarios.ValidacionDeUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioDeUsuarios {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<ValidacionDeUsuarios> validacionDeUsuarios;

    public Usuario registrar(DatosRegistroUsuario datos){
        validacionDeUsuarios.forEach(validacionDeCurso -> {validacionDeCurso.validarCorreoNuevo(datos);});
        var usuario = new Usuario(datos);
        repository.save(usuario);
        return usuario;
    }
    public Page listar(Pageable paginacion){
        return repository.findAll(paginacion).map(DatosListaUsuario::new);
    }

    public DatosDetalleUsuario actualizar(DatosActualizacionUsuario datos, Long id){
        validacionDeUsuarios.forEach(validacionDeUsuario -> {
            validacionDeUsuario.validarExistenciaPorId(id);
            validacionDeUsuario.validarCorreoExistente(datos, id);
        });
        var usuario = usuarioRepository.findById(id);
        usuario.get().actualizarInformaciones(datos);
        return new DatosDetalleUsuario(usuario.get());
    }
    public void eliminar(Long id){
        validacionDeUsuarios.forEach(validacionDeCurso -> {validacionDeCurso.validarDesactivarUsuario(id);});
        var usuario = usuarioRepository.findById(id);
        usuario.get().eliminar();
    }
}
