import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class LivroBaseDeDados {
    private ArrayList<Livro> livros = new ArrayList<Livro>();

    public void AdicionarLivro(Livro livro){ //Create
        livros.add(livro);
        System.out.println("Livro adicionado");
    }

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

    public void EditarLivro(Livro livro){ //Update
        for(Livro l : livros){
            if(l.getID() == livro.getID()){
                System.out.println("Livro editado");
            }
        }
        System.out.println("Livro não encontrado");
    }
    public void ExcluirLivro(String titulo){ //Delete
        for(Livro l : livros){
            if(l.getTitulo().equals(titulo)){
                livros.remove(l);
                System.out.println("Livro excluído");
                return;
            }
        }
        System.out.println("Livro não encontrado");
    }
}
