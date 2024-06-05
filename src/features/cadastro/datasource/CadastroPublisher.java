package features.cadastro.datasource;

public interface CadastroPublisher {
    void subscribe(CadastroListener listener);
    void unsubscribe(CadastroListener listener);
}
