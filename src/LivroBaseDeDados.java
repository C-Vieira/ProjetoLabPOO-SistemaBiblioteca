import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class LivroBaseDeDados {

    /* Classe que define uma base de dados, contendo uma lista de livros
       Contém métodos de busca de variados tipos */

    private ArrayList<Livro> livros = new ArrayList<Livro>();

    //Adiciona um objeto livro na lista
    public void AdicionarLivro(Livro livro){ //Create
        livros.add(livro);
        System.out.println("Livro adicionado");
    }

    /* Retorna um modelo de tabela contendo todos os livros cadastrados
       Cada livro é representado por uma linha na tabela,
       que por sua vez é um vetor de objetos contendo os dados para cada campo */
    public DefaultTableModel MostrarTodosOsLivros(DefaultTableModel model){
        Object[] resultado = new Object[7]; //Vetor de objetos (tamanho 7, um para cada campo) para representar uma linha na tabela
        for(Livro l : livros){
            resultado[0] = l.getID();
            resultado[1] = l.getTitulo();
            resultado[2] = l.getCategoria();
            resultado[3] = l.getAutor();
            resultado[4] = l.getISBN();
            resultado[5] = l.getPrazoDeEntrega();
            resultado[6] = l.isDisponivel();
            model.addRow(resultado); //Adiciona uma linha na tabela
        }
        return model;
    }

    /* Os métodos de busca abaixo se comportam como o método acima
       Com a adição de uma condição para filtragem dos resultados */
    public DefaultTableModel BuscarLivrosPorTitulo(String titulo, DefaultTableModel model){ //Read
        Object[] resultado = new Object[7];
        for(Livro l : livros){
            if(l.getTitulo().equals(titulo)){
                System.out.println("Livro encontrado");
                resultado[0] = l.getID();
                resultado[1] = l.getTitulo();
                resultado[2] = l.getCategoria();
                resultado[3] = l.getAutor();
                resultado[4] = l.getISBN();
                resultado[5] = l.getPrazoDeEntrega();
                resultado[6] = l.isDisponivel();
                model.addRow(resultado);
            }
        }
        return model;
    }

    public DefaultTableModel BuscarLivrosPorCategoria(String categoria, DefaultTableModel model){
        Object[] resultado = new Object[7];
        for(Livro l : livros){
            if(l.getCategoria().equals(categoria)){
                System.out.println("Livro encontrado");
                resultado[0] = l.getID();
                resultado[1] = l.getTitulo();
                resultado[2] = l.getCategoria();
                resultado[3] = l.getAutor();
                resultado[4] = l.getISBN();
                resultado[5] = l.getPrazoDeEntrega();
                resultado[6] = l.isDisponivel();
                model.addRow(resultado);
            }
        }
        return model;
    }

    public DefaultTableModel BuscarLivrosPorAutor(String autor, DefaultTableModel model){
        Object[] resultado = new Object[7];
        for(Livro l : livros){
            if(l.getAutor().equals(autor)){
                System.out.println("Livro encontrado");
                resultado[0] = l.getID();
                resultado[1] = l.getTitulo();
                resultado[2] = l.getCategoria();
                resultado[3] = l.getAutor();
                resultado[4] = l.getISBN();
                resultado[5] = l.getPrazoDeEntrega();
                resultado[6] = l.isDisponivel();
                model.addRow(resultado);
            }
        }
        return model;
    }

    public DefaultTableModel BuscarLivrosPorISBN(String isbn, DefaultTableModel model){
        Object[] resultado = new Object[7];
        for(Livro l : livros){
            if(l.getISBN().equals(isbn)){
                System.out.println("Livro encontrado");
                resultado[0] = l.getID();
                resultado[1] = l.getTitulo();
                resultado[2] = l.getCategoria();
                resultado[3] = l.getAutor();
                resultado[4] = l.getISBN();
                resultado[5] = l.getPrazoDeEntrega();
                resultado[6] = l.isDisponivel();
                model.addRow(resultado);
            }
        }
        return model;
    }

    //Busca por ID e atualiza todos os campos de um livro correspondente
    public void EditarLivro(int id, String titulo, String categoria, String autor, String isbn, int prazo, boolean disponibilidade){ //Update
        for(Livro l : livros){
            if(l.getID() == id){
                l.setTitulo(titulo);
                l.setCategoria(categoria);
                l.setAutor(autor);
                l.setISBN(isbn);
                l.setPrazoDeEntrega(prazo);
                l.setDisponibilidade(disponibilidade);
                System.out.println("Livro editado");
            }
        }
        System.out.println("Livro não encontrado");
    }

    //Busca por ID e remove o livro correspondente da lista
    public void ExcluirLivro(int id){ //Delete
        for(Livro l : livros){
            if(l.getID() == id){
                livros.remove(l);
                System.out.println("Livro excluído");
                return;
            }
        }
        System.out.println("Livro não encontrado");
    }
}
