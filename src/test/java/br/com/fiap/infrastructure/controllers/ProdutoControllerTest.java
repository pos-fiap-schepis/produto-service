package br.com.fiap.infrastructure.controllers;

import br.com.fiap.core.entities.Produto;
import br.com.fiap.core.enums.ProdutoCategoriaEnum;
import br.com.fiap.core.gateways.ProdutoServiceGateway;
import br.com.fiap.infrastructure.controllers.commands.AlterarProdutoCommand;
import br.com.fiap.infrastructure.controllers.commands.CriarProdutoCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Collections;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProdutoServiceGateway produtoServiceGateway;

    @Test
    public void salvarProdutoComSucesso() throws Exception {
        CriarProdutoCommand command = new CriarProdutoCommand();
        command.setNome("Produto Teste");
        command.setDescricao("Descricao Teste");
        command.setValor(BigDecimal.valueOf(100));
        command.setCategoria(ProdutoCategoriaEnum.L.getDescricao());

        Produto produto = new Produto.Builder()
                .id("1")
                .nome("Produto Teste")
                .descricao("Descricao Teste")
                .valor(BigDecimal.valueOf(100))
                .categoria(ProdutoCategoriaEnum.L)
                .build();

        Mockito.when(produtoServiceGateway.salvar(Mockito.any(Produto.class))).thenReturn(produto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/produtos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome")
                        .value("Produto Teste"));
    }

    @Test
    public void alterarProdutoComSucesso() throws Exception {
        AlterarProdutoCommand command = new AlterarProdutoCommand();
        command.setNome("Produto Alterado");
        command.setDescricao("Descricao Alterada");
        command.setValor(BigDecimal.valueOf(150));
        command.setCategoria(ProdutoCategoriaEnum.A.getDescricao());

        Produto produto = new Produto.Builder()
                .id("1")
                .nome("Produto Alterado")
                .descricao("Descricao Alterada")
                .valor(BigDecimal.valueOf(150))
                .categoria(ProdutoCategoriaEnum.A)
                .build();

        Mockito.when(produtoServiceGateway.salvar(Mockito.any(Produto.class))).thenReturn(produto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/produtos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome")
                        .value("Produto Alterado"));
    }

    @Test
    public void excluirProdutoComSucesso() throws Exception {
        String id = "1";

        Produto produto = new Produto.Builder()
                .id("1")
                .nome("Produto Teste")
                .descricao("Descricao Teste")
                .valor(BigDecimal.valueOf(100))
                .categoria(ProdutoCategoriaEnum.L)
                .build();

        Mockito.when(produtoServiceGateway.excluir(id)).thenReturn(produto);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/produtos/{id}", id)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                        .value("1"));
    }

    @Test
    public void getProdutosPorCategoriaComSucesso() throws Exception {
        String categoria = "Lanche";

        Produto produto = new Produto.Builder()
                .id("1")
                .nome("Produto Teste")
                .descricao("Descricao Teste")
                .valor(BigDecimal.valueOf(100))
                .categoria(ProdutoCategoriaEnum.L)
                .build();

        Mockito.when(produtoServiceGateway.getProdutosPorCategoria(categoria)).thenReturn(Collections.singletonList(produto));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/produtos/{categoria}", categoria)
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome")
                        .value("Produto Teste"));
    }
}