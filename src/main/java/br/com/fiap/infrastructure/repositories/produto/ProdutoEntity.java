package br.com.fiap.infrastructure.repositories.produto;

import br.com.fiap.core.enums.ProdutoCategoriaEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class ProdutoEntity {

    @Id
    private String id;

    private String nome;

    private String descricao;

    private BigDecimal valor;

    @Field("categoria")
    private ProdutoCategoriaEnum categoria;

    private LocalDateTime dataInclusao = LocalDateTime.now();
}
