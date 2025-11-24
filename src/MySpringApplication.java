import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import difexamples.ClientComponent;
import difexamples.SingletonComponent;
import difexamples.TransientComponent;

@SpringBootApplication(
        scanBasePackages = {
                "difexamples",
                "controllers"
        }
)
public class MySpringApplication {

    public static void main(String[] args) {

        // Pornim contextul DI
        ApplicationContext context =
                SpringApplication.run(MySpringApplication.class, args);

        // 1. Testăm bean-ul PROTOTYPE
        TransientComponent transientBean =
                context.getBean(TransientComponent.class);
        transientBean.operation();

        transientBean = context.getBean(TransientComponent.class);
        transientBean.operation();

        // 2. Testăm bean-ul SINGLETON
        SingletonComponent singletonBean =
                context.getBean(SingletonComponent.class);
        singletonBean.operation();

        singletonBean = context.getBean(SingletonComponent.class);
        singletonBean.operation();

        // 3. Testăm ClientComponent
        ClientComponent c = context.getBean(ClientComponent.class);
        c.operation();

        // 4. Obținem același bean după nume
        c = (ClientComponent) context.getBean("clientComponent");
        c.operation();
    }
}
