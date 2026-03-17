package projetojava.mvc.View;

import projetojava.mvc.Exception.ValidacaoException;
import projetojava.mvc.ConfigBD.ConnectionBD;
import projetojava.mvc.Controller.ProdutoController;
import projetojava.mvc.Model.Produto;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProdutoView {

    private ProdutoController produtoController;
    private Scanner scanner;

    public ProdutoView() {
        this.produtoController = new ProdutoController();
        this.scanner = new Scanner(System.in);
    }

    public void iniciarAplicacao(boolean conexaoEstabelecida) {
        if(conexaoEstabelecida) {
            System.out.println("Olá! Seja bem-vindo ao nosso sistema de gerenciamento de produtos!");
            exibirMenu();
        }
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

            try {
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
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite apenas números.");
                scanner.nextLine();
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
            Integer id = 0;
            String nome = "";
            String categoria = "";
            Integer quantidade = 0;

            try {
                System.out.println("Digite o ID do produto:");
                id = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Digite o nome do produto:");
                nome = scanner.nextLine();

                System.out.println("Digite a categoria do produto:");
                categoria = scanner.nextLine();

                System.out.println("Digite a quantidade do produto:");
                quantidade = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite apenas números.");
                scanner.nextLine();
                adicionarProduto();
            }

            Produto produto = new Produto(id, nome, categoria, quantidade);

            produtoController.salvarProduto(produto);
            System.out.println("Produto cadastrado com sucesso!");
        } catch(ValidacaoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void exibirMenuPesquisa() {
        try {
            Integer opcaoPesquisada = 0;

            try {
                System.out.println("Escolha uma das opções para pesquisarmos: \n 1 - Pesquisar por Nome \n 2 - Pesquisar por Categoria \n 3 - Pesquisar por ID:");
                opcaoPesquisada = scanner.nextInt();
                scanner.nextLine();

                String nome = "";
                String categoria = "";
                Integer id = 0;

                if (opcaoPesquisada == 1) {
                    System.out.println("Informe o nome do produto:");
                    nome = scanner.nextLine();

                    Produto produto = produtoController.pesquisarProdutoPorNome(nome);

                    System.out.println("Produto encontrado! Exibindo produto...");
                    System.out.println(produto);
                } else if (opcaoPesquisada == 2) {
                    System.out.println("Digite o nome da categoria desejada: ");
                    categoria = scanner.nextLine();

                    List<Produto> produtos = produtoController.pesquisarProdutosPorCategoria(categoria);

                    System.out.println("Categoria encontrada! Exibindo produtos cadastrados com essa categoria...");
                    for(Produto produto : produtos) {
                        System.out.println(produto);
                    }
                } else if (opcaoPesquisada == 3) {
                    System.out.println("Informe o ID do produto:");
                    id = scanner.nextInt();

                    Produto produto = produtoController.pesquisarProdutoPorId(id);

                    System.out.println("Produto encontrado! Exibindo produto...");
                    System.out.println(produto);
                } else {
                    System.out.println("Opção inválida. Favor informar uma opção válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite apenas números.");
                scanner.nextLine();
                exibirMenuPesquisa();
            }
        } catch(RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void exibirMenuAtualizacao() {
        try {
            Integer id = 0;
            Integer opcaoPesquisada = 0;

            try {
                System.out.println("Primeiro, Informe o ID do produto que você deseja alterar:");
                id = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Escolha uma das opções para a gente prosseguir: \n 1 - Atualizar Nome do Produto \n 2 - Atualizar Quantidade do Produto:");
                opcaoPesquisada = scanner.nextInt();
                scanner.nextLine();

                String nome = "";
                Integer opcao = 0;
                Integer quantidade = 0;

                if(opcaoPesquisada == 1) {
                    System.out.println("Informe o novo nome que você deseja colocar no produto:");
                    nome = scanner.nextLine();

                    produtoController.atualizarNomeProduto(id, nome);

                    System.out.println("Alteração do nome do produto realizada com sucesso!");
                } else if(opcaoPesquisada == 2) {
                    System.out.println("Escolha uma das opções: \n 1 - Aumentar quantidade \n 2 - Reduzir quantidade:");
                    opcao = scanner.nextInt();

                    if (opcao == 1) {
                        System.out.println("Digite a quantidade que você deseja adicionar ao produto:");
                        quantidade = scanner.nextInt();

                        produtoController.atualizarQuantidadeProduto(1, id, quantidade);
                        System.out.println("Aumento da quantidade realizado com sucesso!");
                    } else if (opcao == 2) {
                        System.out.println("Digite a quantidade que você deseja retirar do produto:");
                        quantidade = scanner.nextInt();

                        produtoController.atualizarQuantidadeProduto(2, id, quantidade);
                        System.out.println("Redução da quantidade realizada com sucesso!");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite apenas números.");
                scanner.nextLine();
                exibirMenuAtualizacao();
            }
        } catch(RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void opcaoExcluirProduto() {
        try {
            Integer id = 0;

            try {
                System.out.println("Informe o ID do produto que você deseja excluir:");
                id = scanner.nextInt();
                scanner.nextLine();

                produtoController.excluirProduto(id);
                System.out.println("Produto excluído com sucesso!");
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite apenas números.");
                scanner.nextLine();
                opcaoExcluirProduto();
            }
        } catch(RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public boolean estabelecerConexaoBD() {
        try {
            ConnectionBD.getConnection();
            return true;
        } catch(SQLException e) {
            System.out.println("Erro: Falha ao estabelecer conexão com o banco de dados - " + e.getMessage());
            recarregarConexaoBD();
            return false;
        }
    }

    public void recarregarConexaoBD() {
        System.out.println("Deseja tentar estabelecer a conexão novamente? Escolha uma das opções:\n"
        + " - Digite 1 para realizar a tentativa \n"
        + " - Digite 2 para encerrar a aplicação");

        try {
            Integer opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                estabelecerConexaoBD();
            } else if (opcao == 2) {
                System.out.println("Encerrando a aplicação...");
            } else {
                System.out.println("Por favor, digite uma opção válida.");
                recarregarConexaoBD();
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Digite apenas números.");
            scanner.nextLine();
            recarregarConexaoBD();
        }
    }

}
