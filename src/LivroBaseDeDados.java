import java.util.ArrayList;

public class LivroBaseDeDados {
    private ArrayList<Livro> livros = new ArrayList<Livro>();

    public void AdicionarLivro(Livro livro){ //Create
        livros.add(livro);
        System.out.println("Livro adicionado");
    }

    public void BuscarLivro(Livro livro){ //Read
        for(Livro l : livros){
            if(l.getID() == livro.getID()){
                System.out.println("Livro encontrado");
            }
        }
        System.out.println("Livro não encontrado");
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
