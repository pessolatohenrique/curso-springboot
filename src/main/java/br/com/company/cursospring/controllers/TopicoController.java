package br.com.company.cursospring.controllers;

import br.com.company.cursospring.models.Curso;
import br.com.company.cursospring.models.Topico;
import br.com.company.cursospring.models.dto.TopicoDto;
import br.com.company.cursospring.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @RequestMapping("/topicos")
    public List<TopicoDto> lista(String cursoNome) {
        if (cursoNome == null) {
            List<Topico> topicos = repository.findAll();
            System.out.println(cursoNome);
            return TopicoDto.converter(topicos);
        }

        List<Topico> topicos = repository.findByCursoNome(cursoNome);

        return TopicoDto.converter(topicos);

    }
}
