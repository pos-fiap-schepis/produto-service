package br.com.fiap.infrastructure.repositories.produto;


import br.com.fiap.core.entities.Produto;
import br.com.fiap.core.enums.ProdutoCategoriaEnum;
import br.com.fiap.infrastructure.controllers.converters.ProdutoConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProdutoRepositoryGatewayImplTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoRepositoryGatewayImpl produtoRepositoryGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvar() {
        Produto produto = new Produto.Builder()
                .nome("Produto Teste")
                .descricao("Descricao Teste")
                .valor(BigDecimal.valueOf(100))
                .categoria(ProdutoCategoriaEnum.L)
                .build();

        ProdutoEntity produtoEntity = ProdutoConverter.converterProdutoToEntity(produto);
        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(produtoEntity);

        Produto result = produtoRepositoryGateway.salvar(produto);

        assertNotNull(result);
        assertEquals(produto.getNome(), result.getNome());
    }

    @Test
    void testGetProdutosPorCategoria() {
        ProdutoCategoriaEnum categoria = ProdutoCategoriaEnum.L;
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setCategoria(categoria);
        when(produtoRepository.findByCategoria(categoria)).thenReturn(List.of(produtoEntity));

        List<Produto> result = produtoRepositoryGateway.getProdutosPorCategoria(categoria);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(categoria, result.get(0).getCategoria());
    }

    @Test
    void testGetProdutoById() {
        String id = "1";
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(id);
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoEntity));

        Produto result = produtoRepositoryGateway.getProdutoById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void testExcluir() {
        String id = "1";
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(id);
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoEntity));

        Produto result = produtoRepositoryGateway.excluir(new Produto.Builder().id(id).build());

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(produtoRepository, times(1)).delete(produtoEntity);
    }

    @Test
    void testGetProdutosPorCategoriaEmpty() {
        ProdutoCategoriaEnum categoria = ProdutoCategoriaEnum.L;
        when(produtoRepository.findByCategoria(categoria)).thenReturn(List.of());

        List<Produto> result = produtoRepositoryGateway.getProdutosPorCategoria(categoria);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}