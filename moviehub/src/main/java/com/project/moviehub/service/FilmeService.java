package com.project.moviehub.service;

import com.project.moviehub.model.Filme;
import com.project.moviehub.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }

    public Filme buscarPorId(Long id) {
        return filmeRepository.findById(id).orElse(null);
    }

    public Filme salvar(Filme filme) {
        return filmeRepository.save(filme);
    }

    public void removeFilme(Long id) {
        filmeRepository.deleteById(id); // Se você estiver usando JPA
    }

}
