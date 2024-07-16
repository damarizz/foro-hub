package com.alura.forohub.controller;

import com.alura.forohub.dominio.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private iTopicoRepository repo;

    @PostMapping
    public ResponseEntity crearTopico(@RequestBody @Valid DatosCreateTopico datos) {
        Optional<Topico> existentePorTitulo = repo.findByTitulo(datos.titulo());
        if (existentePorTitulo.isPresent()) {
            return ResponseEntity.badRequest().body("Título ya existe, ingresa otro");
        }

        Optional<Topico> existentePorMensaje = repo.findByMensaje(datos.mensaje());
        if (existentePorMensaje.isPresent()) {
            return ResponseEntity.badRequest().body("Mensaje ya existe, ingresa otro");
        }

        Topico topico = repo.save(new Topico(datos));
        return ResponseEntity.ok(new DatosCreateTopico(topico.getTitulo(), topico.getMensaje(), topico.getAutor(), topico.getCurso()));
    }

    @GetMapping
    public ResponseEntity<Page<DatosOutputTopico>> listarTopicos(@PageableDefault(size = 5) Pageable pag) {
        return ResponseEntity.ok(repo.findAllByOrderByFechaCreacionDesc(pag).map(topico -> new DatosOutputTopico(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosOutputTopico> verTopico(@PathVariable Long id) {
        return repo.findById(id)
                .map(topico -> ResponseEntity.ok(new DatosOutputTopico(
                        topico.getTitulo(),
                        topico.getMensaje(),
                        topico.getFechaCreacion(),
                        topico.getStatus(),
                        topico.getAutor(),
                        topico.getCurso())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosUpdateTopico datos, @PathVariable Long id) {
        Topico topico = repo.findById(id).orElse(null);
        if (topico == null) {
            return ResponseEntity.notFound().build();
        }

        topico.actualizarTopico(datos);

        return ResponseEntity.ok(new DatosOutputTopico(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getAutor(), topico.getCurso()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarTopico(@PathVariable Long id) {
        Optional<Topico> topico = repo.findById(id);
        if (topico.isPresent()) {
            repo.deleteById(id);
            return ResponseEntity.ok().body("Tópico eliminado con éxito");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
