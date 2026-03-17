package projetojava.mvc.Service;

import projetojava.mvc.Exception.ProdutoNaoEncontradoException;
import projetojava.mvc.Exception.ValidacaoBancoException;
import projetojava.mvc.Exception.ValidacaoException;
import projetojava.mvc.Model.Produto;
import projetojava.mvc.Repository.ProdutoRepository;
import java.util.List;

public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService() {
        this.produtoRepository = new ProdutoRepository();
    }

    public void adicionarProduto(Produto produto) throws RuntimeException {
        if(produto.getId() == null || produto.getId() < 0) {
            throw new ValidacaoException("Identificação do produto inválida. Considere inserir uma identificação válida.");
        }
        if(produto.getNome() == null || produto.getNome().isBlank()) {
            throw new ValidacaoException("Nome do produto inválido. Por favor, informe corretamente o nome do produto.");
        }
        if(produto.getNome().length() < 4) {
            throw new ValidacaoException("Nome declarado de forma inválida, minímo 4 letras.");
        }
        if(produto.getCategoria() == null || produto.getCategoria().isBlank()) {
            throw new ValidacaoException("Categoria do produto inválida. Considere declarar uma categoria válida.");
        }
        if(produto.getQuantidade() == null || produto.getQuantidade() < 0) {
            throw new ValidacaoException("Quantidade do produto vazia/inválida. Considere informar um valor válido.");
        }

        Produto produtoID = produtoRepository.buscarPorId(produto.getId());

        if(produtoID != null) {
            throw new ValidacaoBancoException("Produto já existente no sistema. Caso queira atualizar, procure a seção de atualização de produtos.");
        }

        produtoRepository.inserirProduto(produto);
    }

    public List<Produto> listarProdutos() throws RuntimeException {
        List<Produto> listaProdutos = produtoRepository.buscarProdutos();

        if(listaProdutos.isEmpty()) {
            throw new ProdutoNaoEncontradoException("Não há produtos cadastrados no sistema.");
        }

        return listaProdutos;
    }

    public Produto pesquisarProdutoPorNome(String nome) {
        if(nome == null || nome.isBlank()) {
            throw new ValidacaoException("Nome do produto vazio/inválido. Favor preencher o campo corretamente.");
        }

        Produto produtoPesquisado = produtoRepository.buscarProdutoPorNome(nome);

        if(produtoPesquisado == null) {
            throw new ProdutoNaoEncontradoException("Não há nenhum produto cadastrado com este nome.");
        }

        return produtoPesquisado;
    }

    public List<Produto> listarProdutosPorCategoria(String categoria) {
        if(categoria == null || categoria.isBlank()) {
            throw new ValidacaoException("Categoria não informada/inválida. Favor confirmar os dados e inseri-los corretamente.");
        }

        List<Produto> listaProdutosPorCategoria = produtoRepository.buscarProdutosPorCategoria(categoria);

        if(listaProdutosPorCategoria.isEmpty()) {
            throw new ProdutoNaoEncontradoException("Não há produtos cadastrados com essa categoria.");
        }

        return listaProdutosPorCategoria;
    }

    public Produto pesquisarProdutoPorId(Integer id) throws RuntimeException {
        if(id == null) {
            throw new ValidacaoException("Identificação inválida/vazia. Favor informar uma identificação válida.");
        }

        Produto produtoPesquisado = produtoRepository.buscarPorId(id);

        if(produtoPesquisado == null) {
            throw new ProdutoNaoEncontradoException("Não há nenhum produto cadastrado com essa identificação.");
        }

        return produtoPesquisado;
    }

    public void atualizarNome(Integer id, String nome) throws RuntimeException {
        if(id == null) {
            throw new ValidacaoException("Identificação inválida/vazia. Favor informar uma identificação válido.");
        }
        if(nome == null || nome.isBlank()) {
            throw new ValidacaoException("Nome inválido. Favor informar um nome válido.");
        }

        Produto produtoPesquisado = produtoRepository.buscarPorId(id);

        if(produtoPesquisado == null) {
            throw new ProdutoNaoEncontradoException("Não há como alterar o nome, produto não encontrado. Favor validar se o produto existe.");
        }

        produtoRepository.atualizarNome(id, nome);
    }

    public void aumentarQuantidade(Integer id, Integer qtd) {
        if(id == null || qtd == null) {
            throw new ValidacaoException("ID/Quantidade não informados. Favor informar corretamente os dados.");
        }
        if(qtd <= 0) {
            throw new ValidacaoException("Quantidade inválida. Favor informar uma quantidade válida.");
        }

        Produto produtoPesquisado = produtoRepository.buscarPorId(id);

        if(produtoPesquisado == null) {
            throw new ProdutoNaoEncontradoException("Não há como alterar a quantidade, produto não encontrado. Favor validar se o produto existe.");
        }

        Integer qtdAtual = produtoPesquisado.getQuantidade();
        produtoRepository.atualizarQuantidade(id, (qtdAtual + qtd));
    }

    public void reduzirQuantidade(Integer id, Integer qtd) {
        if(id == null || qtd == null) {
            throw new ValidacaoException("ID/Quantidade não informados. Favor informar corretamente os dados.");
        }
        if(qtd <= 0) {
            throw new ValidacaoException("Quantidade inválida. Favor informar um valor válido.");
        }

        Produto produtoPesquisado = produtoRepository.buscarPorId(id);

        if(produtoPesquisado == null) {
            throw new ProdutoNaoEncontradoException("Não há como alterar a quantidade, produto não encontrado. Favor validar se o produto existe.");
        }
        if(produtoPesquisado.getQuantidade() < qtd) {
            throw new ValidacaoBancoException("Quantidade para retirada maior que o estoque disponível. Neste caso, considere remover o produto.");
        }

        Integer qtdAtual = produtoPesquisado.getQuantidade();
        produtoRepository.atualizarQuantidade(id, (qtdAtual - qtd));
    }

    public void removerProduto(Integer id) {
        if(id == null) {
            throw new ValidacaoException("ID não informado. Favor informar um ID corretamente.");
        }

        Produto produto = produtoRepository.buscarPorId(id);

        if(produto == null) {
            throw new ProdutoNaoEncontradoException("Não há produtos cadastrados com essa identificação. Favor verificar se o produto existe.");
        }

        produtoRepository.deletarProduto(id);
    }
}
