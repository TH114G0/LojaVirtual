# Loja Virtual

## Descrição

A Loja Virtual é um sistema de gerenciamento de produtos desenvolvido em Java, projetado para facilitar o controle e a organização de um inventário de produtos. O sistema permite realizar operações essenciais como adicionar, listar, deletar e consultar produtos de forma eficiente. Utiliza um pool de conexões com C3P0 para otimizar a interação com o banco de dados.

## Funcionalidades

- **Adicionar Produtos**: Insira novos produtos no sistema com informações como nome e descrição.
- **Listar Produtos**: Visualize todos os produtos cadastrados, oferecendo uma visão geral do estoque disponível.
- **Deletar Produtos por ID**: Remova produtos específicos utilizando seu identificador único, garantindo um controle preciso do inventário.
- **Selecionar Produto Específico**: Consulte e visualize os detalhes de um produto específico baseado em seu ID.

## Boas Práticas Implementadas

- **JavaDoc**: Documentação detalhada das classes e métodos, facilitando a manutenção e a compreensão do código.
- **Tratamento de Exceções**: Manejo adequado de erros para assegurar que o sistema seja robusto e confiável.
- **Encapsulamento**: Protege e gerencia o acesso aos dados e métodos dos produtos, promovendo a integridade do sistema.
- **Organização com Pacotes**: Estruturação do código em pacotes distintos para uma melhor organização e modularidade.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação utilizada para implementar a lógica do sistema.
- **MySQL**: Sistema de gerenciamento de banco de dados utilizado para armazenar e manipular as informações dos produtos.
- **C3P0**: Biblioteca de pooling de conexões JDBC utilizada para otimizar o gerenciamento de conexões com o banco de dados.

## Como Executar

1. **Configurar o Banco de Dados**:
    - Configure o MySQL e crie um banco de dados chamado `loja_virtual`.
    - Verifique se a tabela `produtos` foi criada com os seguintes campos:

      ```sql
      CREATE TABLE produtos (
          id INT PRIMARY KEY,
          nome VARCHAR(255),
          descricao VARCHAR(255)
      );
      ```

    - Adicione alguns produtos para teste:

      ```sql
      INSERT INTO produtos (id, nome, descricao) VALUES (2, 'Mouse', 'Mouse sem fio');
      INSERT INTO produtos (id, nome, descricao) VALUES (3, 'Playstation', 'Playstation 5');
      INSERT INTO produtos (id, nome, descricao) VALUES (5, 'Garrafa', 'Garrafa Plástica');
      ```

2. **Compilar e Executar o Projeto**:
    - Utilize um IDE como IntelliJ IDEA ou Eclipse para abrir e executar o projeto.
    - Alternativamente, compile e execute o projeto via linha de comando:

      ```bash
      javac -cp .:mysql-connector-java-x.x.x.jar:c3p0-x.x.x.jar Main.java
      java -cp .:mysql-connector-java-x.x.x.jar:c3p0-x.x.x.jar Main
      ```

3. **Teste as Funcionalidades**:
    - Utilize a interface fornecida pelo sistema para adicionar, listar, deletar e consultar produtos.

## Contribuição

Contribuições são bem-vindas! Para contribuir, siga as diretrizes padrão para pull requests e issues.

---
