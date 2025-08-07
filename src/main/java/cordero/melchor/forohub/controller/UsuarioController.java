package cordero.melchor.forohub.controller;

import cordero.melchor.forohub.domain.usuario.*;
import cordero.melchor.forohub.domain.usuario.ValidacionDeUsuarios.ValidacionDeUsuarios;
import cordero.melchor.forohub.infra.exceptions.RecursoNoEncontradoException;
import cordero.melchor.forohub.infra.exceptions.ValidacionException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    @Autowired
    private ServicioDeUsuarios servicioDeUsuarios;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroUsuario datos , UriComponentsBuilder uriBuilder) {
        var usuario = servicioDeUsuarios.registrar(datos);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleUsuario(usuario));
    }
    @GetMapping
    public ResponseEntity<Page<DatosListaUsuario>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion){
        var page = servicioDeUsuarios.listar(paginacion);
        return ResponseEntity.ok(page);
    }
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionUsuario datos, @PathVariable Long id){
        var DatosDetalleUsuario = servicioDeUsuarios.actualizar(datos, id);
        return ResponseEntity.ok(DatosDetalleUsuario);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){
        servicioDeUsuarios.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
