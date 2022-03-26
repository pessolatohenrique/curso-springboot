package br.com.company.cursospring.controllers;

import br.com.company.cursospring.models.Curso;
import br.com.company.cursospring.models.Topico;
import br.com.company.cursospring.models.dto.TopicoDto;
import br.com.company.cursospring.models.forms.TopicoForm;
import br.com.company.cursospring.repository.CursoRepository;
import br.com.company.cursospring.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public List<TopicoDto> lista(String cursoNome) {
        if (cursoNome == null) {
            List<Topico> topicos = repository.findAll();
            System.out.println(cursoNome);
            return TopicoDto.converter(topicos);
        }

        List<Topico> topicos = repository.findByCursoNome(cursoNome);

        return TopicoDto.converter(topicos);
    }

    @PostMapping
    public ResponseEntity<TopicoDto> cadastra(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(form, cursoRepository);
        System.out.println(topico);
        repository.save(topico);

        URI uri = uriBuilder.path("/topicos").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }
}
