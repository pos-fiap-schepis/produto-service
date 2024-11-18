package br.com.fiap.infrastructure;

import br.com.fiap.core.gateways.ProdutoRepositoryGateway;
import br.com.fiap.core.gateways.ProdutoServiceGateway;
import br.com.fiap.core.usecases.ProdutoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ProdutoServiceGateway produtoServiceImpl(ProdutoRepositoryGateway todoARepositoryPort) {
        return new ProdutoUseCase(todoARepositoryPort);
    }
}
