import javax.swing.*;

public class LivroController {
    private final TelaCadastroLivro livroView;
    private final LivroDAO livroDAO;

    public LivroController(TelaCadastroLivro livroView, LivroDAO livroDAO) {
        this.livroView = livroView;
        this.livroDAO = livroDAO;
    }

    public void adicionaLivro(Object[] dados){
        String titulo = dados[0].toString();
        String categoria = dados[1].toString();
        String autor = dados[2].toString();
        String ISBN = dados[3].toString();
        String prazo = dados[4].toString();
        Boolean disponivel = Boolean.parseBoolean(dados[5].toString());

        if(titulo.isEmpty() || categoria.isEmpty() || autor.isEmpty() || ISBN.isEmpty() || prazo.isEmpty()) {
            System.out.println("Erro ao Adicionar Livro");
        }else{
            livroDAO.inserirLivro(titulo, categoria, autor, ISBN, prazo, disponivel);
        }
    }

    public void buscarLivro(Object[] dados){
        String titulo = dados[0].toString();
        String categoria = dados[1].toString();
        String autor = dados[2].toString();
        String ISBN = dados[3].toString();

        //Se o campo estiver preenchido, pesquisamos por este dado, se não, tentamos o próximo
        if(!titulo.isEmpty()) {
            livroDAO.buscarLivroPorTitulo(titulo);
        } else if (!categoria.isEmpty()) {
            livroDAO.buscarLivroPorCategoria(categoria);
        } else if (!autor.isEmpty()) {
            livroDAO.buscarLivroPorAutor(autor);
        } else if (!ISBN.isEmpty()) {
            livroDAO.buscarLivroPorISBN(ISBN);
        }else{
            JOptionPane.showMessageDialog(livroView, "Livro não encontrado...", "Erro", JOptionPane.ERROR_MESSAGE);
            System.out.println("Livro não encontrado");
        }
    }

    public void editarLivro(int livroId, Object[] dados){
        String titulo = dados[0].toString();
        String categoria = dados[1].toString();
        String autor = dados[2].toString();
        String ISBN = dados[3].toString();
        String prazo = dados[4].toString();
        Boolean disponivel = Boolean.parseBoolean(dados[5].toString());

        if(titulo.isEmpty() || categoria.isEmpty() || autor.isEmpty() || ISBN.isEmpty() || prazo.isEmpty()) {
            System.out.println("Erro ao Editar Livro");
        }
        livroDAO.editarLivro(livroId, titulo, categoria, autor, ISBN, prazo, disponivel); //Atualiza informações de um certo livro por ID

    }

    public void excluirLivro(int livroId, Object[] dados){
        if(livroId > -1) {
            livroDAO.excluirLivro(livroId);
        }else{
            System.out.println("Livro não encontrado");
        }
    }
}
