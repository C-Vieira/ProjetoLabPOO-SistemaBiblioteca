package features.emprestimo.datasource;

public interface EmprestimoPublisher {
    void subscribe(EmprestimoListener listener);
    void unsubscribe(EmprestimoListener listener);
}
