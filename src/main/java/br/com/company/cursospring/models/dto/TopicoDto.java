package br.com.company.cursospring.models.dto;

import br.com.company.cursospring.models.Topico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/*
* Um DTO é criado com o objetivo de permitir a manipulação de um objeto, não manipulando diretamente via entidade de
* negócio (JPA)
* Além disso, é possível escolher quais os campos serão devolvidos
* */
public class TopicoDto {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao = LocalDateTime.now();

    public TopicoDto(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public static List<TopicoDto> converter(List<Topico> topicos) {
        return topicos.stream().map(TopicoDto::new).collect(Collectors.toList());
    }
}
