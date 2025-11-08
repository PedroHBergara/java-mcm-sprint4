package br.com.fiap.api.controller;

import br.com.fiap.api.dto.FilialResponse;
import br.com.fiap.api.dto.PatioRequest;
import br.com.fiap.api.dto.PatioResponse;
import br.com.fiap.api.service.PatioService;
import br.com.fiap.api.service.FilialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller Web responsável pelas páginas de gerenciamento de Pátios.
 *
 * Adaptado para trabalhar com DTOs (PatioRequest/PatioResponse) e os métodos
 * específicos do PatioService (create, update, delete).
 *
 * Rotas deste controller: /patios (páginas HTML)
 * API REST continua em: /api/patios (JSON)
 *
 * @author Pedro Henrique Bergara e Henrique Izzi
 */
@Controller
@RequestMapping("/patios")
public class PatioWebController {

    @Autowired
    private PatioService patioService;

    @Autowired
    private FilialService filialService;

    /**
     * Lista todos os pátios na página web.
     *
     * @param model Modelo para passar dados para a view
     * @return Nome da view Thymeleaf (patios/list.html)
     */
    @GetMapping
    public String listar(Model model) {
        try {
            // Busca todos os pátios sem paginação para a página web
            Page<PatioResponse> patiosPage = patioService.findAll(Pageable.unpaged());
            model.addAttribute("patios", patiosPage.getContent());
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao carregar pátios: " + e.getMessage());
        }
        return "patios/list";
    }

    /**
     * Exibe o formulário para criar um novo pátio.
     *
     * @param model Modelo para passar dados para a view
     * @return Nome da view Thymeleaf (patios/form.html)
     */
    @GetMapping("/novo")
    public String novo(Model model) {
        try {
            // Cria um objeto vazio para o formulário
            model.addAttribute("patio", new PatioRequest(null, null, null));
            model.addAttribute("isEdit", false); // ← ADICIONADO
            // Busca todas as filiais para o select
            Page<FilialResponse> filiaisPage = filialService.findAll(Pageable.unpaged());
            model.addAttribute("filiais", filiaisPage.getContent());
            return "patios/form";
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao carregar formulário: " + e.getMessage());
            return "redirect:/patios";
        }
    }

    /**
     * Processa o formulário de criação de um novo pátio.
     *
     * @param qtdMotos Quantidade de motos
     * @param numPatio Número do pátio
     * @param filialId ID da filial selecionada
     * @param redirectAttributes Atributos para mensagens flash
     * @return Redirecionamento para a listagem
     */
    @PostMapping("/novo")
    public String criar(@RequestParam Integer qtdMotos,
                        @RequestParam Integer numPatio,
                        @RequestParam Long filialId,
                        RedirectAttributes redirectAttributes) {
        try {
            PatioRequest request = new PatioRequest(qtdMotos, numPatio, filialId);
            patioService.create(request);
            redirectAttributes.addFlashAttribute("message", "Pátio cadastrado com sucesso!");
            return "redirect:/patios";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao cadastrar pátio: " + e.getMessage());
            return "redirect:/patios/novo";
        }
    }

    /**
     * Exibe o formulário para editar um pátio existente.
     *
     * @param id ID do pátio a ser editado
     * @param model Modelo para passar dados para a view
     * @param redirectAttributes Atributos para mensagens flash
     * @return Nome da view Thymeleaf (patios/form.html) ou redirecionamento
     */
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            PatioResponse patio = patioService.findById(id);
            model.addAttribute("patio", patio);
            model.addAttribute("isEdit", true); // ← JÁ EXISTE

            // Busca todas as filiais para o select
            Page<FilialResponse> filiaisPage = filialService.findAll(Pageable.unpaged());
            model.addAttribute("filiais", filiaisPage.getContent());

            // Buscar informações da filial para exibir na seção de informações
            if (patio.filialId() != null) {
                try {
                    FilialResponse filialInfo = filialService.findById(patio.filialId());
                    model.addAttribute("filialInfo", filialInfo); // ← ADICIONADO
                } catch (Exception e) {
                    // Se não encontrar a filial, apenas não adiciona o filialInfo
                }
            }

            return "patios/form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Pátio não encontrado!");
            return "redirect:/patios";
        }
    }

    /**
     * Processa o formulário de edição de um pátio.
     *
     * @param id ID do pátio a ser atualizado
     * @param qtdMotos Quantidade de motos
     * @param numPatio Número do pátio
     * @param filialId ID da filial selecionada
     * @param redirectAttributes Atributos para mensagens flash
     * @return Redirecionamento para a listagem
     */
    @PostMapping("/editar/{id}")
    public String atualizar(@PathVariable Long id,
                            @RequestParam Integer qtdMotos,
                            @RequestParam Integer numPatio,
                            @RequestParam Long filialId,
                            RedirectAttributes redirectAttributes) {
        try {
            PatioRequest request = new PatioRequest(qtdMotos, numPatio, filialId);
            patioService.update(id, request);
            redirectAttributes.addFlashAttribute("message", "Pátio atualizado com sucesso!");
            return "redirect:/patios";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao atualizar pátio: " + e.getMessage());
            return "redirect:/patios/editar/" + id;
        }
    }

    /**
     * Exclui um pátio.
     *
     * @param id ID do pátio a ser excluído
     * @param redirectAttributes Atributos para mensagens flash
     * @return Redirecionamento para a listagem
     */
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            patioService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Pátio excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao excluir pátio: " + e.getMessage());
        }
        return "redirect:/patios";
    }
}