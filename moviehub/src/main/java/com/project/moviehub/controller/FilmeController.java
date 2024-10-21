package com.project.moviehub.controller;

import com.project.moviehub.model.Filme;
import com.project.moviehub.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller; // Importando Controller
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // Indica a classe como controlador
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired //Injeção de dependencias
    private FilmeService filmeService;

    @GetMapping("/") // Mapeia a rota raiz "/"
    public String listarFilmes(Model model) {
        List<Filme> filmes = filmeService.listarTodos(); // Busca todos os filmes no serviço
        model.addAttribute("filmes", filmes); // Adiciona os filmes ao modelo para o HTML
        return "index"; // Retorna o nome da página HTML (index.html)
    }

    @GetMapping("/adicionar") // Mapeamento para a rota de adicionar
    public String mostrarFormularioAdicionarFilme(Model model) {
        model.addAttribute("filme", new Filme()); // Adiciona um novo objeto Filme ao modelo
        return "add_movie"; // Retorna o nome do arquivo HTML sem a extensão
    }

    @PostMapping("/")
    public String adicionarFilme(@ModelAttribute Filme filme) {
        filmeService.salvar(filme); // Salva o filme usando o serviço
        return "redirect:/filmes/";
    }

    @GetMapping("/{id}")
    public String buscarFilmePorId(@PathVariable Long id, Model model) {
        Filme filme = filmeService.buscarPorId(id);
        if (filme != null) {
            model.addAttribute("filme", filme); // Adiciona o filme ao modelo
            return "detalhes_filme"; // Página de detalhes do filme
        } else {
            return "filme_nao_encontrado"; // Página caso o filme não exista
        }
    }

    // Deletar um filme por ID
    @DeleteMapping("/{id}")
    public String deletarFilme(@PathVariable Long id) {
        filmeService.deletar(id); // Chama o serviço para deletar o filme
        return "redirect:/filmes/"; // Redireciona para a página inicial após deletar
    }

}
