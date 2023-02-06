package br.com.imagemfilmes.desafio.dao;

import br.com.imagemfilmes.desafio.entity.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends DAO{
    public ProdutoDAO(Connection connection) {
        super(connection);
    }

    public List<Produto> findAll() throws SQLException {
        try (PreparedStatement psmt = getConnection().prepareStatement("SELECT * FROM produto")) {
            try (ResultSet rs = psmt.executeQuery()) {
                final List<Produto> produtos = new ArrayList<>();
                while (rs.next()) {
                    final Produto produto = new Produto()
                            .setRegistro(rs.getInt("registro"))
                            .setDescricao(rs.getString("descricao"))
                            .setValorUnitario(rs.getBigDecimal("valor_unitario"));
                    produtos.add(produto);
                }
                return produtos;
            }
        }
    }
    public List<Produto> findAllSortedByPrice() throws SQLException {
        try (PreparedStatement psmt = getConnection().prepareStatement("SELECT * FROM produto ORDER BY valor_unitario")){
            try (ResultSet rs = psmt.executeQuery()){
                final List<Produto> produtos = new ArrayList<>();
                while (rs.next()) {
                    final Produto produto = new Produto()
                            .setRegistro(rs.getInt("registro"))
                            .setDescricao(rs.getString("descricao"))
                            .setValorUnitario(rs.getBigDecimal("valor_unitario"));
                    produtos.add(produto);
                }
                return produtos;
            }
        }
    }


}
