
 # Desafio em Java: Gerenciamento de Produtos!

Repositório criado para desenvolver um desafio/projeto sugerido durante o meu estágio, onde eu optei por criar um sistema de gerenciamento de produtos, integrado a um banco de dados.

Os requisitos principais para a criação e desenvolvimento deste projeto são: 

 * 1. A utilização apenas de **Java**, sem a utilização de bibliotecas/frameworks como o Spring Boot, por exemplo. 
 * 2. Além da utilização do **JDBC (Java Database Connectivity)** integrado a algum banco de dados de minha escolha, para realizar a persistência dos dados, neste caso eu defini o **MySQL** como o Banco de Dados para ser utilizado.


## Instalação/Configuração do Ambiente

Para realizar a execução do projeto, é necessário a instalação de algumas ferramentas na máquina que será executada a aplicação, sendo elas:

 * **IntelliJ** - IDE utilizada para o desenvolvimento do projeto;
 * **Java/JDK 17/21/25 LTS** - Kit de Desenvolvimento Java para que você consiga executar todo o ambiente Java sem maiores problemas;
 * **MySQL Server/Workbench** - SGBD utilizado para realizar a persistência dos dados.

Após isso, é necessário o clone do projeto na máquina que será executada, através do comando abaixo: 

```bash
git clone https://github.com/diass-gui/Desafio_Java.git
```
Em seguida, com a instalação das ferramentas e do projeto na máquina, abra o projeto pelo IntelliJ, e copie o script SQL que está no arquivo **Database.sql** dentro da pasta **/ConfigBD/**, e em seguida execute-o no MySQL para a criação da Base de Dados e da tabela correspondente.

Após realizar a criação da base de dados e da tabela no MySQL, configure o arquivo **ConnectionBD** conforme orientação abaixo:

```java

public class ConnectionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/base_dados"; // Troque o campo 'base_dados' pelo nome da base de dados que você criou.
    private static final String USER = "root"; // Coloque o nome do seu usuário MySQL no lugar do 'root'.
    private static final String PASSWORD = "senha"; // Por último, coloque a senha do seu usuário MySQL no lugar de 'senha'.

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

```

## Agradecimentos

Agradeço primeiro ao arquiteto que me desafiou com a ideia de um projeto somente com Java e JDBC, plantando a semente para oque seria esta aplicação, podendo assim me oferecer mais oportunidades de desenvolvimento tanto acadêmico quanto profissional.

