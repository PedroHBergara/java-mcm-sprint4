package br.com.fiap.api.controller;

import br.com.fiap.api.dto.FilialRequest;
import br.com.fiap.api.dto.FilialResponse;
import br.com.fiap.api.service.FilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller Web responsável pelas páginas de gerenciamento de Filiais.
 *
 * Adaptado para trabalhar com DTOs (FilialRequest/FilialResponse) e os métodos
 * específicos do FilialService (create, update, delete).
 *
 * Rotas deste controller: /filiais (páginas HTML)
 * API REST continua em: /api/filiais (JSON)
 *
 * @author Pedro Henrique Bergara e Henrique Izzi
 */
@Controller
@RequestMapping("/filiais")
public class FilialWebController {

    @Autowired
    private FilialService filialService;

    /**
     * Lista todas as filiais na página web.
     *
     * @param model Modelo para passar dados para a view
     * @return Nome da view Thymeleaf (filiais/list.html)
     */
    @GetMapping
    public String listar(Model model) {
        try {
            // Busca todas as filiais sem paginação para a página web
            Page<FilialResponse> filiaisPage = filialService.findAll(Pageable.unpaged());
            model.addAttribute("filiais", filiaisPage.getContent());
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao carregar filiais: " + e.getMessage());
        }
        return "filiais/list";
    }

    /**
     * Exibe o formulário para criar uma nova filial.
     *
     * @param model Modelo para passar dados para a view
     * @return Nome da view Thymeleaf (filiais/form.html)
     */
    @GetMapping("/novo")
    public String novo(Model model) {
        // Cria um objeto vazio para o formulário
        model.addAttribute("filial", new FilialRequest(null, null, null));
        model.addAttribute("isEdit", false); // ADICIONAR ESTA LINHA
        return "filiais/form";
    }

    /**
     * Processa o formulário de criação de uma nova filial.
     *
     * @param nome Nome da filial
     * @param pais País da filial
     * @param logradouro Logradouro da filial
     * @param redirectAttributes Atributos para mensagens flash
     * @return Redirecionamento para a listagem
     */
    @PostMapping("/novo")
    public String criar(@RequestParam String nome,
                        @RequestParam String pais,
                        @RequestParam String logradouro,
                        RedirectAttributes redirectAttributes) {
        try {
            FilialRequest request = new FilialRequest(nome, pais, logradouro);
            filialService.create(request);
            redirectAttributes.addFlashAttribute("message", "Filial cadastrada com sucesso!");
            return "redirect:/filiais";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao cadastrar filial: " + e.getMessage());
            return "redirect:/filiais/novo";
        }
    }

    /**
     * Exibe o formulário para editar uma filial existente.
     *
     * @param id ID da filial a ser editada
     * @param model Modelo para passar dados para a view
     * @param redirectAttributes Atributos para mensagens flash
     * @return Nome da view Thymeleaf (filiais/form.html) ou redirecionamento
     */
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            FilialResponse filial = filialService.findById(id);
            // Converte FilialResponse para um objeto que o formulário possa usar
            model.addAttribute("filial", filial);
            model.addAttribute("isEdit", true);
            return "filiais/form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Filial não encontrada!");
            return "redirect:/filiais";
        }
    }

    /**
     * Processa o formulário de edição de uma filial.
     *
     * @param id ID da filial a ser atualizada
     * @param nome Nome da filial
     * @param pais País da filial
     * @param logradouro Logradouro da filial
     * @param redirectAttributes Atributos para mensagens flash
     * @return Redirecionamento para a listagem
     */
    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Long id,
                            @RequestParam String nome,
                            @RequestParam String pais,
                            @RequestParam String logradouro,
                            RedirectAttributes redirectAttributes) {
        try {
            FilialRequest request = new FilialRequest(nome, pais, logradouro);
            filialService.update(id, request);
            redirectAttributes.addFlashAttribute("message", "Filial atualizada com sucesso!");
            return "redirect:/filiais";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao atualizar filial: " + e.getMessage());
            return "redirect:/filiais/editar/" + id;
        }
    }

    /**
     * Exclui uma filial.
     *
     * @param id ID da filial a ser excluída
     * @param redirectAttributes Atributos para mensagens flash
     * @return Redirecionamento para a listagem
     */
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            filialService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Filial excluída com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao excluir filial: " + e.getMessage());
        }
        return "redirect:/filiais";
    }
}
