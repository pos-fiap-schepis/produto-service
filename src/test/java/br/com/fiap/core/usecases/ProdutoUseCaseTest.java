package br.com.fiap.core.usecases;

import br.com.fiap.core.entities.Produto;
import br.com.fiap.core.enums.ProdutoCategoriaEnum;
import br.com.fiap.core.exceptions.BusinessException;
import br.com.fiap.core.gateways.ProdutoRepositoryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProdutoUseCaseTest {

    private ProdutoRepositoryGateway produtoRepositoryGateway;
    private ProdutoUseCase produtoUseCase;

    @BeforeEach
    void setUp() {
        produtoRepositoryGateway = mock(ProdutoRepositoryGateway.class);
        produtoUseCase = new ProdutoUseCase(produtoRepositoryGateway);
    }

    @Test
    void salvarProdutoComSucesso() {
        Produto produto = new Produto.Builder()
                .nome("Produto Teste")
                .descricao("Descricao Teste")
                .valor(BigDecimal.valueOf(100))
                .categoria(ProdutoCategoriaEnum.L)
                .build();

        when(produtoRepositoryGateway.salvar(any(Produto.class))).thenReturn(produto);

        Produto result = produtoUseCase.salvar(produto);

        assertNotNull(result);
        assertEquals("Produto Teste", result.getNome());
    }

    @Test
    void salvarProdutoComParametrosInvalidos() {
        Produto produto = new Produto.Builder()
                .nome(null)
                .descricao("Descricao Teste")
                .valor(BigDecimal.valueOf(100))
                .categoria(ProdutoCategoriaEnum.L)
                .build();

        assertThrows(BusinessException.class, () -> produtoUseCase.salvar(produto));
    }

    @Test
    void alterarProdutoComSucesso() {
        Produto produto = new Produto.Builder()
                .id("1")
                .nome("Produto Alterado")
                .descricao("Descricao Alterada")
                .valor(BigDecimal.valueOf(150))
                .categoria(ProdutoCategoriaEnum.A)
                .build();

        when(produtoRepositoryGateway.getProdutoById("1")).thenReturn(produto);
        when(produtoRepositoryGateway.salvar(any(Produto.class))).thenReturn(produto);

        Produto result = produtoUseCase.salvar(produto);

        assertNotNull(result);
        assertEquals("Produto Alterado", result.getNome());
    }

    @Test
    void alterarProdutoNaoExistente() {
        Produto produto = new Produto.Builder()
                .id("1")
                .nome("Produto Alterado")
                .descricao("Descricao Alterada")
                .valor(BigDecimal.valueOf(150))
                .categoria(ProdutoCategoriaEnum.A)
                .build();

        when(produtoRepositoryGateway.getProdutoById("1")).thenReturn(null);

        assertThrows(BusinessException.class, () -> produtoUseCase.salvar(produto));
    }

    @Test
    void excluirProdutoComSucesso() {
        Produto produto = new Produto.Builder()
                .id("1")
                .nome("Produto Teste")
                .descricao("Descricao Teste")
                .valor(BigDecimal.valueOf(100))
                .categoria(ProdutoCategoriaEnum.L)
                .build();

        when(produtoRepositoryGateway.getProdutoById("1")).thenReturn(produto);
        when(produtoRepositoryGateway.excluir(produto)).thenReturn(produto);

        Produto result = produtoUseCase.excluir("1");

        assertNotNull(result);
        assertEquals("Produto Teste", result.getNome());
    }

    void excluirProdutoNaoExistente() {
        when(produtoRepositoryGateway.getProdutoById("1")).thenReturn(null);

        assertThrows(BusinessException.class, () -> produtoUseCase.excluir("1"));
    }

    @Test
    void getProdutosPorCategoriaComSucesso() {
        Produto produto = new Produto.Builder()
                .id("1")
                .nome("Produto Teste")
                .descricao("Descricao Teste")
                .valor(BigDecimal.valueOf(100))
                .categoria(ProdutoCategoriaEnum.L)
                .build();

        when(produtoRepositoryGateway.getProdutosPorCategoria(ProdutoCategoriaEnum.L))
                .thenReturn(Collections.singletonList(produto));

        List<Produto> result = produtoUseCase.getProdutosPorCategoria("L");

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Produto Teste", result.get(0).getNome());
    }

    @Test
    void getProdutosPorCategoriaNaoExistente() {
        when(produtoRepositoryGateway.getProdutosPorCategoria(ProdutoCategoriaEnum.L))
                .thenReturn(Collections.emptyList());

        assertThrows(BusinessException.class, () -> produtoUseCase.getProdutosPorCategoria("L"));
    }

    @Test
    void getProdutoByIdComSucesso() {
        Produto produto = new Produto.Builder()
                .id("1")
                .nome("Produto Teste")
                .descricao("Descricao Teste")
                .valor(BigDecimal.valueOf(100))
                .categoria(ProdutoCategoriaEnum.L)
                .build();

        when(produtoRepositoryGateway.getProdutoById("1")).thenReturn(produto);

        Produto result = produtoUseCase.getProdutoById("1");

        assertNotNull(result);
        assertEquals("Produto Teste", result.getNome());
    }

    @Test
    void getProdutoByIdNaoExistente() {
        when(produtoRepositoryGateway.getProdutoById("1")).thenReturn(null);

        Produto result = produtoUseCase.getProdutoById("1");

        assertNull(result);
    }

}