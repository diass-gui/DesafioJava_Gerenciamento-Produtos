package projetojava.mvc.view;

import projetojava.mvc.Exception.FalhaConexaoException;
import projetojava.mvc.Exception.ValidacaoException;
import projetojava.mvc.configBD.ConnectionBD;
import projetojava.mvc.controller.ProdutoController;
import projetojava.mvc.model.Produto;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProdutoView {

    private ProdutoController produtoController;
    private Scanner scanner;

    public ProdutoView() {
        this.produtoController = new ProdutoController();
        this.scanner = new Scanner(System.in);
    }

    public void iniciarAplicacao() {
        System.out.println("Seja bem vindo ao nosso sistema de gerenciamento de produtos!");
        exibirMenu();
    }

    public void exibirMenu() {
        Integer opcao = 0;

        while (opcao != 6) {
            System.out.println("Escolha uma das opções para prosseguirmos: \n" +
                        "1 - Exibir Produtos \n" +
                        "2 - Pesquisar Produtos \n" +
                        "3 - Adicionar Produto \n" +
                        "4 - Atualizar Produto \n" +
                        "5 - Remover Produto \n" +
                        "6 - Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();

                if (opcao == 1) {
                    exibirProdutos();
                } else if (opcao == 2) {
                    exibirMenuPesquisa();
                } else if (opcao == 3) {
                    adicionarProduto();
                } else if (opcao == 4) {
                    exibirMenuAtualizacao();
                } else if (opcao == 5) {
                    opcaoExcluirProduto();
                } else if (opcao == 6) {
                    System.out.println("Encerrando a aplicação...");
                } else {
                    System.out.println("Opção inválida. Favor digitar uma opção válida.");
                }
            }
        }

    public void exibirProdutos() {
        List<Produto> produtos = produtoController.listarProdutos();

        for(Produto produto : produtos) {
            System.out.println(produto);
        }
    }

    public void adicionarProduto() {
        try {
            System.out.println("Digite o ID do produto:");
            Integer id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Digite o nome do produto:");
            String nome = scanner.nextLine();

            System.out.println("Digite a categoria do produto:");
            String categoria = scanner.nextLine();

            System.out.println("Digite a quantidade do produto:");
            Integer quantidade = scanner.nextInt();

            Produto produto = new Produto(id, nome, categoria, quantidade);

            produtoController.salvarProduto(produto);

            System.out.println("Produto cadastrado com sucesso!");

        } catch(ValidacaoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void exibirMenuPesquisa() {
        try {
            System.out.println("Escolha uma das opções para pesquisarmos: \n 1 - Pesquisar por Nome \n 2 - Pesquisar por Categoria \n 3 - Pesquisar por ID:");
            Integer opcaoPesquisada = scanner.nextInt();
            scanner.nextLine();

            if (opcaoPesquisada == 1) {
                System.out.println("Informe o nome do produto:");
                String nome = scanner.nextLine();

                Produto produto = produtoController.pesquisarProdutoPorNome(nome);

                System.out.println("Produto encontrado!");
                System.out.println(produto);
            } else if (opcaoPesquisada == 2) {
                System.out.println("Digite o nome da categoria desejada: ");
                String categoria = scanner.nextLine();

                List<Produto> produtos = produtoController.pesquisarProdutosPorCategoria(categoria);

                System.out.println("Categoria encontrada! Exibindo produtos cadastradados com essa categoria...");

                for(Produto produto : produtos) {
                    System.out.println(produto);
                }
            } else if (opcaoPesquisada == 3) {
                System.out.println("Informe o ID do produto:");
                Integer id = scanner.nextInt();

                Produto produto = produtoController.pesquisarProdutoPorId(id);

                System.out.println("Produto encontrado!");

                System.out.println(produto);
            } else {
                System.out.println("Opção inválida. Favor informar uma opção válida.");
            }
        } catch(RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void exibirMenuAtualizacao() {
        try {
            System.out.println("Primeiro, Informe o ID do produto que você deseja alterar:");
            Integer id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Escolha uma das opções para a gente prosseguir: \n 1 - Atualizar Nome do Produto \n 2 - Atualizar Quantidade do Produto:");
            Integer opcaoPesquisada = scanner.nextInt();
            scanner.nextLine();

            if(opcaoPesquisada == 1) {
                System.out.println("Informe o novo nome que você deseja colocar no produto:");
                String nome = scanner.nextLine();

                produtoController.atualizarNomeProduto(id, nome);
            } else if(opcaoPesquisada == 2) {
                System.out.println("Escolha uma das opções: \n 1 - Aumentar quantidade \n 2 - Reduzir quantidade:");
                Integer opcao = scanner.nextInt();

                if (opcao == 1) {
                    System.out.println("Digite a quantidade que você deseja adicionar ao produto:");
                    Integer quantidade = scanner.nextInt();

                    produtoController.atualizarQuantidadeProduto(1, id, quantidade);
                    System.out.println("Aumento de quantidade realizado com sucesso!");
                } else if (opcao == 2) {
                    System.out.println("Digite a quantidade que você deseja retirar do produto:");
                    Integer quantidade = scanner.nextInt();

                    produtoController.atualizarQuantidadeProduto(2, id, quantidade);
                    System.out.println("Redução de quantidade realizada com sucesso!");
                }
            }
        } catch(RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void opcaoExcluirProduto() {
        try {
            System.out.println("Informe o ID do produto que você deseja excluir:");
            Integer id = scanner.nextInt();
            scanner.nextLine();

            produtoController.excluirProduto(id);
            System.out.println("Produto excluído com sucesso!");
        } catch(RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void estabelecerConexaoBD() {
        try {
            ConnectionBD.getConnection();
        } catch(SQLException e) {
            throw new FalhaConexaoException("Falha ao estabelecer conexão com o banco de dados." + e.getMessage());
        }
    }
}
