package br.com.company.cursospring.models.forms;

import br.com.company.cursospring.models.Curso;
import br.com.company.cursospring.models.Topico;
import br.com.company.cursospring.repository.CursoRepository;
import br.com.company.cursospring.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TopicoForm {
    @NotBlank @NotNull
    private String titulo;
    @NotBlank @NotNull
    private String mensagem;
    @NotBlank @NotNull
    private String cursoNome;

    public Topico converter(TopicoForm form, CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(cursoNome);
        return new Topico(titulo, mensagem, curso);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCursoNome() {
        return cursoNome;
    }

    public void setCursoNome(String cursoNome) {
        this.cursoNome = cursoNome;
    }
}
