package infrastructure;

import features.cadastro.livro.model.Livro;
import features.cadastro.user.model.Usuario;
import features.emprestimo.model.Emprestimo;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DatabaseManager {

    private static SessionFactory sessionFactory;

    public static SessionFactory getDatabaseSessionFactory() {
        if (sessionFactory == null) {
            createSessionFactory();
        }

        return sessionFactory;
    }

    private static void createSessionFactory() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(Livro.class)
                    .addAnnotatedClass(Usuario.class)
                    .addAnnotatedClass(Emprestimo.class)
                    .buildMetadata()
                    .buildSessionFactory();

        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we
            // had trouble building the SessionFactory so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}

