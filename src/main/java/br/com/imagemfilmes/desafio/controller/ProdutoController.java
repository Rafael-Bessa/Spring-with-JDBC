package br.com.imagemfilmes.desafio.controller;

import br.com.imagemfilmes.desafio.entity.Produto;
import br.com.imagemfilmes.desafio.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @RequestMapping("/listar")
    public List<Produto> listar() throws Exception {
        return produtoService.getProdutos();
    }

    @RequestMapping("/listar/preco")
    public List<Produto> listarOrdenadoPorPreco() throws Exception {
        return produtoService.getProdutosSortedByPrice();
    }

}
