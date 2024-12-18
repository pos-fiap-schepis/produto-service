package br.com.fiap.core.gateways;

import br.com.fiap.core.entities.Produto;

import java.util.List;

public interface ProdutoServiceGateway {

    Produto salvar(Produto produto);

    List<Produto> getProdutosPorCategoria(String categoria);

    Produto getProdutoById(String id);

    Produto excluir(String id);

    Produto obterPorId(String id);
}
