package cordero.melchor.forohub.domain.topicos;

import cordero.melchor.forohub.domain.curso.CursoRepository;
import cordero.melchor.forohub.domain.topicos.Validaciones.ValidacionesDeTopicos;
import cordero.melchor.forohub.domain.usuario.DatosListaUsuario;
import cordero.melchor.forohub.domain.usuario.UsuarioRepository;
import cordero.melchor.forohub.infra.exceptions.RecursoNoEncontradoException;
import cordero.melchor.forohub.infra.exceptions.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;

@Service
public class ServicioDeTopicos {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<ValidacionesDeTopicos> validacionesDeTopicos;


    public DatosDetalleTopico registrar(DatosRegistroTopico datos){
        validacionesDeTopicos.forEach(validacionesDeTopico -> {
            validacionesDeTopico.validarExistenciaUsuarioYCurso(datos);
            validacionesDeTopico.validarMismoMensajeYTitulo(datos);
        });
        var curso = cursoRepository.findById(datos.curso()).get();
        var usuario = usuarioRepository.findById(datos.usuario()).get();
        var topico = new Topicos(datos, usuario, curso);
        topicoRepository.save(topico);
        return new DatosDetalleTopico(topico);
    }
    public DatosDetalleTopico detallar(Long id) {
        validacionesDeTopicos.forEach(v -> v.validarExistenciaTopico(id));
        var topico = topicoRepository.findById(id).get();
        return new DatosDetalleTopico(topico);
    }
    public DatosDetalleTopico actualizar(DatosActualizacionTopico datos, Long id){
        validacionesDeTopicos.forEach(v -> v.validarExistenciaTopico(id));
        var topico = topicoRepository.findById(id);
        topico.get().actualizarInformaciones(datos);
        return new DatosDetalleTopico(topico.get());
    }
    public void eliminar(Long id){
        validacionesDeTopicos.forEach(v -> v.validarExistenciaTopico(id));
        var topico = topicoRepository.findById(id);
        topicoRepository.deleteById(topico.get().getId());
    }
}
