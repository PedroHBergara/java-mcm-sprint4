package br.com.fiap.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller responsável pela página inicial da aplicação web.
 *
 * Este controller gerencia apenas a rota principal ("/") e não interfere
 * com os endpoints REST da API.
 *
 * @author Pedro Henrique Bergara e Henrique Izzi
 */
@Controller
public class HomeController {

    /**
     * Exibe a página inicial com o menu de opções.
     *
     * @return Nome da view Thymeleaf (index.html)
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
