package br.com.company.cursospring.controllers;

import br.com.company.cursospring.models.Curso;
import br.com.company.cursospring.models.Topico;
import br.com.company.cursospring.models.dto.TopicoDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicoController {
    @RequestMapping("/topicos")
    public List<TopicoDto> lista() {
        Curso c1 = new Curso("Java 1", "programação");
        Topico t1 = new Topico("Duvida qualquer", "estou com dúvida", c1);
        List<Topico> topicos = Arrays.asList(t1, t1, t1);

        return TopicoDto.converter(topicos);
    }
}
