package com.project.moviehub.service;

import com.project.moviehub.model.Filme;
import com.project.moviehub.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    public List<Filme> listarTodos() {
        return filmeRepository.findAll();
    }

    public Filme buscarPorId(Long id) {
        Optional<Filme> filmeOpt = filmeRepository.findById(id);
        return filmeOpt.orElse(null); // Retorna o filme se encontrado, ou null
    }

    public Filme salvar(Filme filme) {
        return filmeRepository.save(filme); // Salva ou atualiza o filme
    }

    public void removeFilme(Long id) {
        filmeRepository.deleteById(id); // Remove o filme com o ID especificado
    }
}
