package features.cadastro.livro.presentation;

import features.cadastro.livro.datasource.LivroDataBase;
import features.cadastro.livro.model.Livro;
import features.cadastro.presentation.BaseView;

import java.util.List;

public class LivroControllerImpl implements LivroController {
    private BaseView livroView;
    private final LivroDataBase livroDataBase;

    public LivroControllerImpl(LivroDataBase livroDataBase) {
        this.livroDataBase = livroDataBase;
    }

    @Override
    public void setView(BaseView view) {
        this.livroView = view;
    }

    @Override
    public void adicionaLivro(Object[] dados){
        String titulo = dados[0].toString();
        String categoria = dados[1].toString();
        String autor = dados[2].toString();
        String ISBN = dados[3].toString();
        String prazo = dados[4].toString();
        Boolean disponivel = Boolean.parseBoolean(dados[5].toString());

        if(titulo.isEmpty() || categoria.isEmpty() || autor.isEmpty() || ISBN.isEmpty() || prazo.isEmpty()) {
            System.out.println("Erro ao Adicionar Livro");
            livroView.mostrarMensagemDeErro("Erro ao Adicionar Livro");
        }else{
            livroDataBase.inserirLivro(titulo, categoria, autor, ISBN, prazo, disponivel);
        }
    }

    @Override
    public Livro buscarLivroPorID(int livroID){
        return  livroDataBase.buscaLivroPorID(livroID);
    }

    @Override
    public void buscarLivro(Object[] dados){
        String titulo = dados[0].toString();
        String categoria = dados[1].toString();
        String autor = dados[2].toString();
        String ISBN = dados[3].toString();

        //Se o campo estiver preenchido, pesquisamos por este dado, se não, tentamos o próximo
        if(!titulo.isEmpty()) {
            livroDataBase.buscarLivroPorTitulo(titulo);
        } else if (!categoria.isEmpty()) {
            livroDataBase.buscarLivroPorCategoria(categoria);
        } else if (!autor.isEmpty()) {
            livroDataBase.buscarLivroPorAutor(autor);
        } else if (!ISBN.isEmpty()) {
            livroDataBase.buscarLivroPorISBN(ISBN);
        }else{
            livroView.mostrarMensagemDeErro("Livro não encontrado...");
            System.out.println("Livro não encontrado");
        }
    }

    @Override
    public void editarLivro(int livroId, Object[] dados){
        String titulo = dados[0].toString();
        String categoria = dados[1].toString();
        String autor = dados[2].toString();
        String ISBN = dados[3].toString();
        String prazo = dados[4].toString();
        Boolean disponivel = Boolean.parseBoolean(dados[5].toString());

        if(titulo.isEmpty() || categoria.isEmpty() || autor.isEmpty() || ISBN.isEmpty() || prazo.isEmpty()) {
            System.out.println("Erro ao Editar Livro");
            livroView.mostrarMensagemDeErro("Erro ao Editar Livro");
        }
        livroDataBase.editarLivro(livroId, titulo, categoria, autor, ISBN, prazo, disponivel); //Atualiza informações de um certo livro por ID

    }

    @Override
    public void excluirLivro(int livroId){
        if(livroId > -1) {
            livroDataBase.excluirLivro(livroId);
        }else{
            System.out.println("Livro não encontrado");
            livroView.mostrarMensagemDeErro("Livro não encontrado");
        }
    }

    @Override
    public List<Livro> getLivros() {
        return livroDataBase.getLivros();
    }
}
