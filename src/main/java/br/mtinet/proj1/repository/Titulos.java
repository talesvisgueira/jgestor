package br.mtinet.proj1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mtinet.proj1.model.Titulo;

public interface Titulos extends JpaRepository<Titulo,Long> {

}
