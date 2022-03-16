package br.com.company.cursospring.repository;

import br.com.company.cursospring.models.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
