package br.com.fiap.infrastructure.repositories.produto;

import br.com.fiap.core.entities.Produto;
import br.com.fiap.core.enums.ProdutoCategoriaEnum;
import br.com.fiap.core.gateways.ProdutoRepositoryGateway;
import br.com.fiap.infrastructure.controllers.converters.ProdutoConverter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoRepositoryGatewayImpl implements ProdutoRepositoryGateway {

    private final ProdutoRepository produtoRepository;

    public ProdutoRepositoryGatewayImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto salvar(Produto produto) {
        ProdutoEntity produtoEntity = produtoRepository.save(ProdutoConverter.converterProdutoToEntity(produto));

        return ProdutoConverter.converterEntityToProduto(produtoEntity);
    }

    @Override
    public List<Produto> getProdutosPorCategoria(ProdutoCategoriaEnum categoria) {
        List<ProdutoEntity> produtosList = produtoRepository.findByCategoria(categoria);

        if (!produtosList.isEmpty()) {
            return ProdutoConverter.converterListaEntityToProduto(produtosList);
        }

        return List.of();
    }

    @Override
    public Produto getProdutoById(String id) {
        return produtoRepository.findById(id)
                .map(ProdutoConverter::converterEntityToProduto).orElse(null);
    }

    @Override
    public Produto excluir(Produto produto) {
      return produtoRepository.findById(produto.getId())
              .map(produtoEntity -> {
                  produtoRepository.delete(produtoEntity);
                  return ProdutoConverter.converterEntityToProduto(produtoEntity);
              }).orElse(null);
    }
}
