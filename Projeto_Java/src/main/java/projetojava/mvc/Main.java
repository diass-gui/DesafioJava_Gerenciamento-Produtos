package projetojava.mvc;

import projetojava.mvc.view.ProdutoView;

public class Main {
    public static void main(String[] args) {
       ProdutoView produtoView = new ProdutoView();

       produtoView.estabelecerConexaoBD();

       produtoView.iniciarAplicacao();
    }
}
