package com.experiment.di;

import com.experiment.di.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ╔══════════════════════════════════════════════════════════════════╗
 * ║  TASK 5 – Load Spring Container                                  ║
 * ║  TASK 6 – Retrieve beans and display values                      ║
 * ╚══════════════════════════════════════════════════════════════════╝
 */
@SpringBootApplication
public class DIApplication {

    public static void main(String[] args) {
        // ── TASK 5: Load Spring Boot container (Annotation context) ─────────
        ApplicationContext bootContext = SpringApplication.run(DIApplication.class, args);
        System.out.println("\n" + "═".repeat(60));
        System.out.println("  Spring Boot ApplicationContext loaded!");
        System.out.println("  Total beans: " + bootContext.getBeanDefinitionCount());
        System.out.println("═".repeat(60));
    }

    /**
     * CommandLineRunner executes once the Spring context is ready.
     *
     * TASK 5a – Load XML container (ClassPathXmlApplicationContext)
     * TASK 5b – Boot annotation context is already loaded above
     * TASK 6  – Retrieve each bean and call display()
     */
    @Bean
    public CommandLineRunner demonstrateDI(ApplicationContext annotationContext) {
        return args -> {

            // ══════════════════════════════════════════════════════
            //  SECTION A — XML-Based Configuration
            //  TASK 5a: Load the XML ApplicationContext
            // ══════════════════════════════════════════════════════
            System.out.println("\n" + "═".repeat(60));
            System.out.println("  SECTION A: XML Configuration");
            System.out.println("  Loading ClassPathXmlApplicationContext...");
            System.out.println("═".repeat(60));

            // TASK 5a: ClassPathXmlApplicationContext loads applicationContext.xml
            // from src/main/resources and creates all beans defined there.
            try (ClassPathXmlApplicationContext xmlContext =
                         new ClassPathXmlApplicationContext("applicationContext.xml")) {

                // ── TASK 6: Retrieve XML beans and display ─────────────────

                System.out.println("\n>>> Bean 1: Constructor Injection (XML)");
                Student s1 = (Student) xmlContext.getBean("studentConstructor");
                s1.display();

                System.out.println("\n>>> Bean 2: Setter Injection (XML)");
                Student s2 = (Student) xmlContext.getBean("studentSetter");
                s2.display();

                System.out.println("\n>>> Bean 3: Mixed Injection (XML)");
                Student s3 = (Student) xmlContext.getBean("studentMixed");
                s3.display();

                System.out.println("\n  [XML context] Singleton check:");
                System.out.println("  studentConstructor is singleton: " +
                        xmlContext.isSingleton("studentConstructor"));
                System.out.println("  studentSetter is singleton: " +
                        xmlContext.isSingleton("studentSetter"));
            }
            // XML context is closed here (try-with-resources)

            // ══════════════════════════════════════════════════════
            //  SECTION B — Annotation-Based Configuration
            //  TASK 5b: Spring Boot context is already loaded
            // ══════════════════════════════════════════════════════
            System.out.println("\n" + "═".repeat(60));
            System.out.println("  SECTION B: Annotation (@Configuration) Configuration");
            System.out.println("═".repeat(60));

            // ── TASK 6: Retrieve Annotation beans and display ──────────────

            System.out.println("\n>>> Bean 4: Constructor Injection (@Bean)");
            Student s4 = annotationContext.getBean("studentAnnotationConstructor", Student.class);
            s4.display();

            System.out.println("\n>>> Bean 5: Setter Injection (@Bean)");
            Student s5 = annotationContext.getBean("studentAnnotationSetter", Student.class);
            s5.display();

            System.out.println("\n>>> Bean 6: Mixed Injection (@Bean)");
            Student s6 = annotationContext.getBean("studentAnnotationMixed", Student.class);
            s6.display();

            // ── Summary ────────────────────────────────────────────────────
            System.out.println("\n" + "═".repeat(60));
            System.out.println("  SUMMARY — Dependency Injection Comparison");
            System.out.println("═".repeat(60));
            System.out.printf("  %-12s %-16s %-10s%n", "Bean ID", "Injection Type", "Source");
            System.out.println("  " + "─".repeat(42));
            System.out.printf("  %-12s %-16s %-10s%n", "Bean 1",  "Constructor",   "XML");
            System.out.printf("  %-12s %-16s %-10s%n", "Bean 2",  "Setter",        "XML");
            System.out.printf("  %-12s %-16s %-10s%n", "Bean 3",  "Mixed",         "XML");
            System.out.printf("  %-12s %-16s %-10s%n", "Bean 4",  "Constructor",   "Annotation");
            System.out.printf("  %-12s %-16s %-10s%n", "Bean 5",  "Setter",        "Annotation");
            System.out.printf("  %-12s %-16s %-10s%n", "Bean 6",  "Mixed",         "Annotation");
            System.out.println("═".repeat(60) + "\n");
        };
    }
}
