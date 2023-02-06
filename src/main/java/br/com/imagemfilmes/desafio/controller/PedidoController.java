package br.com.imagemfilmes.desafio.controller;

import br.com.imagemfilmes.desafio.dto.RelatorioDTO;
import br.com.imagemfilmes.desafio.entity.Pedido;
import br.com.imagemfilmes.desafio.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @RequestMapping("/listar")
    public List<Pedido> listar() throws Exception {
        return pedidoService.findAll();
    }

    @RequestMapping("/listar/{idCliente}")
    public List<Pedido> listarByCliente(@PathVariable("idCliente") long idCliente) throws Exception {
        return pedidoService.findAllByClienteId(idCliente);
    }

    @RequestMapping("/listar/total")
    public List<RelatorioDTO> listarOrdenadoPorValorTotal() throws Exception {
        return pedidoService.findPedidosSortedByPrice();
    }

}
