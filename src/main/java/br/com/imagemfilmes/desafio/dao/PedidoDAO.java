package br.com.imagemfilmes.desafio.dao;

import br.com.imagemfilmes.desafio.dto.RelatorioDTO;
import br.com.imagemfilmes.desafio.entity.Pedido;
import br.com.imagemfilmes.desafio.entity.PedidoItem;
import br.com.imagemfilmes.desafio.entity.Pessoa;
import br.com.imagemfilmes.desafio.entity.Produto;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PedidoDAO extends DAO {

    public PedidoDAO(Connection connection) {
        super(connection);
    }

    private Pessoa findPessoaById(long id) throws SQLException {
        try (PreparedStatement psmt = getConnection().prepareStatement("SELECT * FROM pessoa WHERE pessoa_id = ?")) {
            psmt.setLong(1, id);
            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    final Pessoa pessoa = new Pessoa()
                            .setId(rs.getLong("pessoa_id"))
                            .setNome(rs.getString("nome"))
                            .setCpf(rs.getString("cpf"));
                    return pessoa;
                }
                return null;
            }
        }
    }

    private Produto findProdutoByRegistro(int id) throws SQLException {
        try (PreparedStatement psmt = getConnection().prepareStatement("SELECT * FROM produto WHERE registro = ?")) {
            psmt.setInt(1, id);
            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    final Produto produto = new Produto()
                            .setRegistro(rs.getInt("registro"))
                            .setDescricao(rs.getString("descricao"))
                            .setValorUnitario(rs.getBigDecimal("valor_unitario"));
                    return produto;
                }
                return null;
            }
        }
    }

    private Pedido findPedidoById(long id) throws SQLException {
        try (PreparedStatement psmt = getConnection().prepareStatement("SELECT * FROM pedido WHERE pedido_id = ?")) {
            psmt.setLong(1, id);
            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    final Pedido pedido = new Pedido()
                            .setId(rs.getLong("pedido_id"))
                            .setPessoa(findPessoaById(rs.getLong("pessoa_id")));

                    return pedido;
                }
                return null;
            }
        }
    }
    private List<PedidoItem> findAllItensByPedidoId(Long id) throws SQLException {
        try (PreparedStatement psmt = getConnection().prepareStatement("SELECT * FROM pedido_item WHERE pedido_id = ?")) {
            psmt.setLong(1, id);
            try (ResultSet rs = psmt.executeQuery()) {
                final List<PedidoItem> pedidos = new ArrayList<>();
                while (rs.next()) {
                    final PedidoItem itens = new PedidoItem()
                            .setId(rs.getLong("pedido_item_id"))
                            .setProduto(findProdutoByRegistro(rs.getInt("produto_registro")))
                            .setQuantidade(rs.getInt("quantidade"));
                    pedidos.add(itens);
                }
                return pedidos;
            }
        }
    }
    public List<Pedido> findAll() throws SQLException {
        try (PreparedStatement psmt = getConnection().prepareStatement("SELECT * FROM pedido")) {
            try (ResultSet rs = psmt.executeQuery()) {
                final List<Pedido> pedidos = new ArrayList<>();
                while (rs.next()) {
                    final Pedido pedido = new Pedido()
                            .setId(rs.getLong("pedido_id"))
                            .setPessoa(findPessoaById(rs.getLong("pessoa_id")))
                            .setItens(findAllItensByPedidoId(rs.getLong("pedido_id")));
                    pedidos.add(pedido);
                }
                return pedidos;
            }
        }
    }
        public List<PedidoItem> findAllItens() throws SQLException {
            try (PreparedStatement psmt = getConnection().prepareStatement("SELECT * FROM pedido_item")) {
                try (ResultSet rs = psmt.executeQuery()) {
                    final List<PedidoItem> pedidos = new ArrayList<>();
                    while (rs.next()) {
                        final PedidoItem pedido = new PedidoItem()
                                .setId(rs.getLong("pedido_item_id"))
                                .setProduto(findProdutoByRegistro(rs.getInt("produto_registro")))
                                .setQuantidade(rs.getInt("quantidade"))
                                .setPedido(findPedidoById(rs.getLong("pedido_id")));
                        pedidos.add(pedido);
                    }
                    return pedidos;
                }
            }
        }

    public List<Pedido> findAllByIdCliente(Long id) throws SQLException {
        try (PreparedStatement psmt = getConnection().prepareStatement("SELECT * FROM pedido WHERE pessoa_id = ?")) {
            psmt.setLong(1, id);
            try (ResultSet rs = psmt.executeQuery()) {
                final List<Pedido> pedidos = new ArrayList<>();
                while (rs.next()) {
                    final Pedido pedido = new Pedido()
                            .setId(rs.getLong("pedido_id"))
                            .setPessoa(findPessoaById(rs.getLong("pessoa_id")))
                            .setItens(findAllItensByPedidoId(rs.getLong("pedido_id")));
                    pedidos.add(pedido);
                }
                return pedidos;
            }
        }
    }

    public List<RelatorioDTO> findAllPessoaSortedByTotalPrice() throws SQLException {

        List<PedidoItem> pedidos = findAllItens();

        final List<RelatorioDTO> relatorio = new ArrayList<>();

        final Map<String, BigDecimal> mapa = new HashMap<>();

        for (PedidoItem pedido: pedidos) {
            if(mapa.containsKey(pedido.getPedido().getPessoa().getNome())){
                mapa.put(pedido.getPedido().getPessoa().getNome(),
                        mapa.get(pedido.getPedido().getPessoa().getNome())
                        .add(new BigDecimal(pedido.getQuantidade())
                        .multiply(pedido.getProduto().getValorUnitario())));
            }
            else{
                mapa.put(pedido.getPedido().getPessoa().getNome(),
                        new BigDecimal(pedido.getQuantidade()).multiply(pedido.getProduto().getValorUnitario()));
            }
        }
//Ordem decrescente
        mapa.forEach((s, bigDecimal) -> relatorio.add(new RelatorioDTO(bigDecimal, s)));
        relatorio.sort(Comparator.comparing(RelatorioDTO::getSaldoTotal).reversed());

        return relatorio;
    }
}

