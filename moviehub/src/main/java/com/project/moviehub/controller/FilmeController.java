package com.project.moviehub.controller;

import com.project.moviehub.model.Filme;
import com.project.moviehub.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping("/")
    public String listarFilmes(Model model) {
        List<Filme> filmes = filmeService.listarTodos();
        model.addAttribute("filmes", filmes);
        return "index"; // Página principal que lista os filmes
    }

    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionarFilme(Model model) {
        model.addAttribute("filme", new Filme());
        return "add_movie"; // Página para adicionar novo filme
    }

    @PostMapping("/")
    public String adicionarFilme(@ModelAttribute Filme filme) {
        filmeService.salvar(filme); // Salva o filme
        return "redirect:/filmes/"; // Redireciona para a lista de filmes
    }

    @GetMapping("/{id}")
    public String buscarFilmePorId(@PathVariable Long id, Model model) {
        Filme filme = filmeService.buscarPorId(id);
        if (filme != null) {
            model.addAttribute("filme", filme);
            return "detalhes_filme"; // Página de detalhes do filme
        } else {
            return "filme_nao_encontrado"; // Página caso o filme não exista
        }
    }

    @DeleteMapping("/remover/{id}")  // Ajustei o caminho para /remover/{id}
    public String removerFilme(@PathVariable Long id) {
        filmeService.removeFilme(id); // Chama o serviço para remover o filme
        return "redirect:/filmes/"; // Após a remoção, redireciona para a lista de filmes
    }
}
