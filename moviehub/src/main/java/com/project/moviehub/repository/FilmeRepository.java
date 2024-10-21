package com.project.moviehub.repository;

import com.project.moviehub.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Interação do model com o banco de dados, especificado com o <Filme, Long>
public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
