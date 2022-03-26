package br.com.company.cursospring.models.dto;

import br.com.company.cursospring.models.StatusTopico;
import br.com.company.cursospring.models.Topico;
import br.com.company.cursospring.models.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoDetalheDto {
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private StatusTopico status;
    private UsuarioDto autor;
    private List<RespostaDto> respostas = new ArrayList<RespostaDto>();

    public TopicoDetalheDto(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
        this.status = topico.getStatus();
        this.autor = new UsuarioDto(topico.getAutor());
        this.respostas = topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList());
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

    public StatusTopico getStatus() {
        return status;
    }

    public UsuarioDto getAutor() {
        return autor;
    }

    public List<RespostaDto> getRespostas() {
        return respostas;
    }
}
