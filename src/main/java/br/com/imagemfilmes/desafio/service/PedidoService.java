package br.com.imagemfilmes.desafio.service;

import br.com.imagemfilmes.desafio.dao.PedidoDAO;
import br.com.imagemfilmes.desafio.dto.RelatorioDTO;
import br.com.imagemfilmes.desafio.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    DataSource dataSource;

    public List<Pedido> findAll() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            return new PedidoDAO(connection).findAll();
        }
    }

    public List<Pedido> findAllByClienteId(long id) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            return new PedidoDAO(connection).findAllByIdCliente(id);
        }
    }

    public List<RelatorioDTO> findPedidosSortedByPrice() throws Exception{
        try (Connection connection = dataSource.getConnection()) {
            return new PedidoDAO(connection).findAllPessoaSortedByTotalPrice();
        }
    }


}
