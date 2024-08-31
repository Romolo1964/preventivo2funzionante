package it.preventivo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
@SpringBootApplication
public class Preventivo2Application {
	static ConfigurableApplicationContext context;
	//static ApplicationContext context;

	public static void main(String[] args) {
		context =SpringApplication.run(Preventivo2Application.class, args);
		Preventivo2Application app=new Preventivo2Application();
		// app.stampaBean();

		System.out.println();
		System.out.println("Questi sono i beans creati in automatico da SPRING  ");
		System.out.println("----------------------------------------------------");
		// Visualizza i nomi dei bean nel contesto
        String[] beanNames = context.getBeanDefinitionNames();

        for (String beanName : beanNames) {
        	System.out.println("----------------------------------------------------");
            System.out.println(beanName);
        }
	}

	private void stampaBean() {


	}



}
