package br.com.fiap.infrastructure.controllers.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriarProdutoCommand {

    private String nome;
    private String descricao;
    private BigDecimal valor;
    private String categoria;
}
