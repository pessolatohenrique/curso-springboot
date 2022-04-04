package br.com.company.cursospring.controllers;

import br.com.company.cursospring.models.Curso;
import br.com.company.cursospring.models.Topico;
import br.com.company.cursospring.models.dto.TopicoDetalheDto;
import br.com.company.cursospring.models.dto.TopicoDto;
import br.com.company.cursospring.models.forms.TopicoForm;
import br.com.company.cursospring.repository.CursoRepository;
import br.com.company.cursospring.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    // ?page=0&size=5&sort=id,desc
    @GetMapping
    @Cacheable("topicos")
    public Page<TopicoDto> lista(@RequestParam(required = false) String cursoNome,
                                 @PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pagination) {
        if (cursoNome == null) {
            Page<Topico> topicos = repository.findAll(pagination);
            return TopicoDto.converter(topicos);
        }

        Page<Topico> topicos = repository.findByCursoNome(cursoNome, pagination);

        return TopicoDto.converter(topicos);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "topicos", allEntries = true)
    public ResponseEntity<TopicoDto> cadastra(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(form, cursoRepository);
        repository.save(topico);

        URI uri = uriBuilder.path("/topicos").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    @Transactional
    public TopicoDetalheDto visualiza(@PathVariable Long id) {
        Topico topico = repository.getById(id);
        return new TopicoDetalheDto(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "topicos", allEntries = true)
    public ResponseEntity<TopicoDto> atualiza(@PathVariable Long id, @RequestBody @Valid TopicoForm form) {
        Topico topico = form.atualiza(id, repository);
        return ResponseEntity.ok(new TopicoDto(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "topicos", allEntries = true)
    public ResponseEntity<?> deleta(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
