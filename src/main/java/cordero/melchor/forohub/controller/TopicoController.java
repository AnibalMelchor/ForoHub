package cordero.melchor.forohub.controller;

import cordero.melchor.forohub.domain.topicos.*;
import cordero.melchor.forohub.infra.exceptions.RecursoNoEncontradoException;
import cordero.melchor.forohub.infra.exceptions.ValidacionException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private ServicioDeTopicos servicioDeTopicos;

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriBuilder){
        var DatosDetalleTopico = servicioDeTopicos.registrar(datos);
        return ResponseEntity.ok().body(DatosDetalleTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listar(@PageableDefault(size = 10, sort = {"fechaCreacion"} ) Pageable paginacion){
        var page = topicoRepository.findAll(paginacion).map(DatosListaTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id){
        var DatosDetalleTopico = servicioDeTopicos.detallar(id);
        return ResponseEntity.ok(DatosDetalleTopico);
    }
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionTopico datos, @PathVariable Long id){
        var DatosDetalleTopico = servicioDeTopicos.actualizar(datos, id);
        return ResponseEntity.ok(DatosDetalleTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id){
        servicioDeTopicos.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
