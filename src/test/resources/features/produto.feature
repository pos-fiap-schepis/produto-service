Feature: Criação de Produto

  Scenario: Criar um produto com sucesso
    Given um produto válido
    When o produto é salvo
    Then o produto deve ser salvo com sucesso
