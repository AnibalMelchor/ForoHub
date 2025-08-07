package cordero.melchor.forohub.controller;

import cordero.melchor.forohub.domain.curso.*;
import cordero.melchor.forohub.domain.curso.Validaciones.ValidacionDeCursos;
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
@RequestMapping("/cursos")

public class CursoController {

    @Autowired
    private ServicioDeCursos  servicioDeCursos;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroCurso datos , UriComponentsBuilder uriBuilder) {
        var curso = servicioDeCursos.registrar(datos);
        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleCurso(curso));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaCurso>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion){
        var page = servicioDeCursos.listar(paginacion);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        servicioDeCursos.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
