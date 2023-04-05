package br.com.alura.alura.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinguagemController {
    
    private List<Linguagem> linguagens = 
    List.of(
        new Linguagem("Java","https://logodownload.org/wp-content/uploads/2017/04/java-logo.png","1")
    );

    @GetMapping("/linguagens")
    public List<Linguagem> obterLinguagems() {
        return linguagens;
    }

}
