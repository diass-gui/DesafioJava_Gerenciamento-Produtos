package projetojava.mvc;

import projetojava.mvc.View.ProdutoView;

public class Main {
    public static void main(String[] args) {

       ProdutoView produtoView = new ProdutoView();
       produtoView.iniciarAplicacao(produtoView.estabelecerConexaoBD());

    }
}
