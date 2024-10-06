package com.project.moviehub.controller;

import com.project.moviehub.model.Filme;
import com.project.moviehub.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller; // Importando Controller
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // Mude de @RestController para @Controller
@RequestMapping("/api/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public List<Filme> listar() {
        return filmeService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarPorId(@PathVariable Long id) {
        Filme filme = filmeService.buscarPorId(id);
        return filme != null ? ResponseEntity.ok(filme) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Filme> criar(@RequestBody Filme filme) {
        return ResponseEntity.ok(filmeService.salvar(filme));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        filmeService.deletar(id);
    }

    // Método para listar filmes e renderizar a página
    @GetMapping("/page")
    public String listarFilmes(Model model) {
        List<Filme> filmes = filmeService.listarTodos();
        model.addAttribute("filmes", filmes);
        return "index"; // Nome do arquivo HTML sem a extensão
    }

}
