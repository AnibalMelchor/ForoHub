package cordero.melchor.forohub.domain.topicos.Validaciones;

import cordero.melchor.forohub.domain.curso.CursoRepository;
import cordero.melchor.forohub.domain.topicos.DatosRegistroTopico;
import cordero.melchor.forohub.domain.topicos.TopicoRepository;
import cordero.melchor.forohub.domain.usuario.UsuarioRepository;
import cordero.melchor.forohub.infra.exceptions.RecursoNoEncontradoException;
import cordero.melchor.forohub.infra.exceptions.ValidacionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionDeTopicosImpl implements ValidacionesDeTopicos{

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public void validarExistenciaUsuarioYCurso(DatosRegistroTopico datos) {
        if(!cursoRepository.existsById(datos.curso())){
            throw new RecursoNoEncontradoException("No existe un curso con el id informado");
        }
        if (!usuarioRepository.existsById(datos.usuario())){
            throw new RecursoNoEncontradoException("No existe un usuario con el id informado");
        }
    }

    @Override
    public void validarMismoMensajeYTitulo(DatosRegistroTopico datos) {
        if(topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())){
            throw new ValidacionException("No puedes registrar un topico con el mismo titulo/mensaje");
        }
    }

    @Override
    public void validarExistenciaTopico(Long id) {
        if(!topicoRepository.existsById(id)){
            throw new RecursoNoEncontradoException("No existe un topico con el id informado");
        }
    }
}
