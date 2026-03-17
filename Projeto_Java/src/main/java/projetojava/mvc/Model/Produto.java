package projetojava.mvc.Model;

public class Produto {

    private Integer id;
    private String nome;
    private String categoria;
    private Integer quantidade;

    public Produto() {
        this.id = 0;
        this.nome = "";
        this.categoria = "";
        this.quantidade = 0;
    }

    public Produto(Integer id, String nome, String categoria, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", categoria='" + categoria + '\'' +
                ", quantidade=" + quantidade +
                '}';
    }
}
