package br.com.company.cursospring.models.dto;

public class ErroFormDto {
    private String campo;
    private String mensagem;

    public ErroFormDto(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }
}
