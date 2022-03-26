package br.com.company.cursospring.models.dto;

import br.com.company.cursospring.models.Resposta;

public class RespostaDto {
    private Long id;
    private String mensagem;
    private String autorNome;

    public RespostaDto(Resposta resposta) {
        this.id = resposta.getId();
        this.mensagem = resposta.getMensagem();
        this.autorNome = resposta.getAutor().getNome();
    }

    public Long getId() {
        return id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getAutorNome() {
        return autorNome;
    }
}
