package br.com.fiap.infrastructure.controllers;

import br.com.fiap.core.entities.Produto;
import br.com.fiap.infrastructure.exceptions.ViolacaoDominioExcecao;
import br.com.fiap.core.gateways.ProdutoServiceGateway;
import br.com.fiap.infrastructure.controllers.commands.AlterarProdutoCommand;
import br.com.fiap.infrastructure.controllers.commands.CriarProdutoCommand;
import br.com.fiap.infrastructure.controllers.converters.ProdutoConverter;
import br.com.fiap.infrastructure.controllers.presenters.ProdutoPresenter;
import br.com.fiap.infrastructure.dtos.ProdutoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoServiceGateway produtoServiceGateway;

    public ProdutoController(ProdutoServiceGateway produtoServiceGateway) {
        this.produtoServiceGateway = produtoServiceGateway;
    }

    @Operation(summary = "Salvar um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto cadastrado com sucesso", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Produto.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Erro de validação no cadastro do produto", content = @Content)})
    @PostMapping
    public ProdutoDto salvar(@RequestBody CriarProdutoCommand command) {
        return ProdutoPresenter.converterProdutoToDto(produtoServiceGateway.salvar(ProdutoConverter.converterCommandToProduto(command)));
    }

    @Operation(summary = "Alterar um produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto alterado com sucesso", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Produto.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Erro de validação na alteração do produto", content = @Content)})
    @PutMapping
    public ProdutoDto alterar(@RequestBody AlterarProdutoCommand command) {
        return ProdutoPresenter.converterProdutoToDto(produtoServiceGateway.salvar(ProdutoConverter.converterAlterarCommandToProduto(command)));
    }

    @Operation(summary = "Excluir um produto pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Excluído com sucesso", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Produto.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Erro de negócio na exclusão.", content = @Content)})
    @DeleteMapping("/{id}")
    public ProdutoDto excluir(@PathVariable("id") String id) {
        return ProdutoPresenter.converterProdutoToDto(produtoServiceGateway.excluir(id));
    }

    @Operation(summary = "Obter um produto pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Buscado com sucesso", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Produto.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Erro de negócio na busca.", content = @Content)})
    @GetMapping("/{id}/produto")
    public ProdutoDto obterPorId(@PathVariable("id") String id) {
        Produto produto = produtoServiceGateway.obterPorId(id);

        if (produto == null) {
            throw new ViolacaoDominioExcecao("Produto não encontrado na base!");
        }

        return ProdutoPresenter.converterProdutoToDto(produto);
    }

    @GetMapping("/{id}/teste")
    public String teste(@PathVariable("id") String id) {
       return "Weiller";
    }

    @Operation(summary = "Retornar uma lista de produtos pela categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornou uma lista", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Produto.class))}),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos", content = @Content),
            @ApiResponse(responseCode = "412", description = "Categoria não encontrada", content = @Content)})
    @GetMapping("/{categoria}")
    public List<ProdutoDto> getProdutosPorCategoria(@PathVariable("categoria") String categoria) {
        return ProdutoConverter.converterListaProdutoToProdutoDto(produtoServiceGateway.getProdutosPorCategoria(categoria));
    }

}
