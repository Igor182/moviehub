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

    // Método para editar um filme existente
    public Filme editarFilme(Long id, Filme filmeAtualizado) {
        Optional<Filme> filmeOpt = filmeRepository.findById(id);
        if (filmeOpt.isPresent()) {
            Filme filme = filmeOpt.get();
            filme.setTitulo(filmeAtualizado.getTitulo());
            filme.setGenero(filmeAtualizado.getGenero());
            filme.setDiretor(filmeAtualizado.getDiretor());
            filme.setDescricao(filmeAtualizado.getDescricao());
            filme.setDataLancamento(filmeAtualizado.getDataLancamento());
            filme.setImagemUrl(filmeAtualizado.getImagemUrl());
            return filmeRepository.save(filme); // Atualiza no banco de dados
        } else {
            throw new RuntimeException("Filme não encontrado!");  // Caso o filme não exista
        }
    }

    public void removeFilme(Long id) {
        filmeRepository.deleteById(id); // Remove o filme com o ID especificado
    }


}
