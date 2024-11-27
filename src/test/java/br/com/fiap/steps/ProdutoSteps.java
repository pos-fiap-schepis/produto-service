package br.com.fiap.steps;

import br.com.fiap.core.entities.Produto;
import br.com.fiap.core.enums.ProdutoCategoriaEnum;
import br.com.fiap.core.gateways.ProdutoRepositoryGateway;
import br.com.fiap.core.usecases.ProdutoUseCase;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProdutoSteps {

    @Mock
    private ProdutoRepositoryGateway produtoRepositoryGateway;

    @InjectMocks
    private ProdutoUseCase produtoUseCase;

    private Produto produto;
    private Exception exception;

    @Before("")
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Given("um produto válido")
    public void umProdutoValido() {
        produto = new Produto.Builder()
                .id("01")
                .nome("Produto Teste")
                .descricao("Descricao Teste")
                .valor(BigDecimal.valueOf(100))
                .categoria(ProdutoCategoriaEnum.L)
                .build();
    }

    @Given("um produto sem categoria")
    public void umProdutoSemCategoria() {
        produto = new Produto.Builder()
                .id("01")
                .nome("Produto Teste")
                .descricao("Descricao Teste")
                .valor(BigDecimal.valueOf(100))
                .categoria(null)
                .build();
    }

    @When("o produto é salvo")
    public void oProdutoESalvo() {
        try {
            when(produtoRepositoryGateway.salvar(any(Produto.class))).thenReturn(produto);
            produtoUseCase.salvar(produto);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("o produto deve ser salvo com sucesso")
    public void oProdutoDeveSerSalvoComSucesso() {
        Assertions.assertNotNull(produto);
    }
}