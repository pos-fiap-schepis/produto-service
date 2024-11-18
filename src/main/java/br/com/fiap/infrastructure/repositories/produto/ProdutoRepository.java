package br.com.fiap.infrastructure.repositories.produto;

import br.com.fiap.core.enums.ProdutoCategoriaEnum;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProdutoRepository extends MongoRepository<ProdutoEntity, String> {

    List<ProdutoEntity> findByCategoria(ProdutoCategoriaEnum categoria);
}
