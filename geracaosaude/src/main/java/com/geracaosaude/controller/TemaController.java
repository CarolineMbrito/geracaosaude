package com.geracaosaude.controller;

import com.geracaosaude.model.Tema;
import com.geracaosaude.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/temas")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class TemaController {
    @Autowired
    TemaRepository temaRepository;

    @GetMapping
    public ResponseEntity<List<Tema>> getAll() { return ResponseEntity.ok(temaRepository.findAll());}

    @GetMapping("{id}")
    public ResponseEntity<Tema> getById(@PathVariable Long id){
        return temaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Tema>> getByName(@PathVariable String nome){
        return ResponseEntity.ok(temaRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @GetMapping("/descricao/{descricao}")
    public ResponseEntity<List<Tema>> getByDescricao(@PathVariable String descricao){
        return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
    }

    @PostMapping
    public ResponseEntity<Tema> post(@RequestBody @Valid Tema tema){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(temaRepository.save(tema));
    }
    @PutMapping
    public ResponseEntity<Tema> put(@RequestBody @Valid Tema tema){
        return temaRepository.findById(tema.getId())
                .map(response -> ResponseEntity.status(HttpStatus.CREATED)
                .body(temaRepository.save(tema)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        Optional<Tema> tema = temaRepository.findById(id);
            if(tema.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        temaRepository.deleteById(id);
    }


}
