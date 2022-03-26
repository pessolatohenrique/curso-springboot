package br.com.company.cursospring.models.forms;

import br.com.company.cursospring.models.Curso;
import br.com.company.cursospring.models.Topico;
import br.com.company.cursospring.repository.CursoRepository;
import br.com.company.cursospring.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TopicoForm {
    @NotEmpty @NotNull
    private String titulo;
    @NotEmpty @NotNull
    private String mensagem;
    @NotBlank @NotNull
    private String cursoNome;

    public Topico converter(TopicoForm form, CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(cursoNome);
        return new Topico(titulo, mensagem, curso);
    }

    public Topico atualiza(Long id, TopicoRepository repository) {
        Topico topico = repository.getById(id);
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);

        return topico;
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
