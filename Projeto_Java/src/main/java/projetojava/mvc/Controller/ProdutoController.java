package projetojava.mvc.Controller;

import projetojava.mvc.Model.Produto;
import projetojava.mvc.Service.ProdutoService;
import java.util.List;

public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController() {
        this.produtoService = new ProdutoService();
    }

    public void salvarProduto(Produto produto) {
        produtoService.adicionarProduto(produto);
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        return produtos;
    }

    public List<Produto> pesquisarProdutosPorCategoria(String categoria) {
        List<Produto> produtos = produtoService.listarProdutosPorCategoria(categoria);
        return produtos;
    }

    public Produto pesquisarProdutoPorNome(String nome) {
        Produto produto = produtoService.pesquisarProdutoPorNome(nome);
        return produto;
    }

    public Produto pesquisarProdutoPorId(Integer id) {
        Produto produto = produtoService.pesquisarProdutoPorId(id);
        return produto;
    }

    public void atualizarNomeProduto(Integer id, String nome) {
        produtoService.atualizarNome(id, nome);
    }

    public void atualizarQuantidadeProduto(Integer opcao, Integer id, Integer quantidade) {
        if(opcao == 1){
            produtoService.aumentarQuantidade(id, quantidade);
        } else if(opcao == 2) {
            produtoService.reduzirQuantidade(id, quantidade);
        }
    }

    public void excluirProduto(Integer id) {
        produtoService.removerProduto(id);
    }
}