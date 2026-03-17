package com.experiment.di.config;

import com.experiment.di.model.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ╔══════════════════════════════════════════════════════════════════╗
 * ║  TASK 4b – Bean configuration using Annotation (@Configuration) ║
 * ╚══════════════════════════════════════════════════════════════════╝
 *
 * @Configuration
 * ──────────────
 * Marks this class as a Spring bean-definition source.
 * Replaces the XML applicationContext.xml for annotation-style config.
 * Spring processes it at startup and registers every @Bean method.
 *
 * @Bean
 * ─────
 * Marks a method as a factory method for a Spring bean.
 * The method name becomes the default bean id.
 * The return value is registered in the ApplicationContext.
 *
 * Two injection styles are shown:
 *   studentAnnotationConstructor  → calls the all-args constructor
 *   studentAnnotationSetter       → calls no-arg, then setters
 */
@Configuration
public class AppConfig {

    // ═══════════════════════════════════════════════════════════════
    //  BEAN 4 — Constructor Injection  (Annotation style)
    // ═══════════════════════════════════════════════════════════════

    /**
     * CONSTRUCTOR INJECTION via @Bean method.
     *
     * Spring calls this method to create the bean.
     * We pass all four values directly to the all-args constructor.
     *
     * Equivalent XML:
     *   <bean id="studentAnnotationConstructor" class="...Student">
     *       <constructor-arg value="STU-ANN-01"/>
     *       ...
     *   </bean>
     */
    @Bean
    public Student studentAnnotationConstructor() {
        System.out.println("\n[AppConfig] Creating studentAnnotationConstructor — Constructor Injection");
        return new Student("STU-ANN-01", "David Lee", "Software Engineering", 3);
    }

    // ═══════════════════════════════════════════════════════════════
    //  BEAN 5 — Setter Injection  (Annotation style)
    // ═══════════════════════════════════════════════════════════════

    /**
     * SETTER INJECTION via @Bean method.
     *
     * 1. Calls the no-arg constructor (new Student())
     * 2. Calls each setter explicitly inside the @Bean method body.
     *
     * Equivalent XML:
     *   <bean id="studentAnnotationSetter" class="...Student">
     *       <property name="studentId" value="STU-ANN-02"/>
     *       ...
     *   </bean>
     */
    @Bean
    public Student studentAnnotationSetter() {
        System.out.println("\n[AppConfig] Creating studentAnnotationSetter — Setter Injection");
        Student s = new Student();        // Step 1: no-arg constructor
        s.setStudentId("STU-ANN-02");     // Step 2: setter injection
        s.setName("Eve Williams");
        s.setCourse("Artificial Intelligence");
        s.setYear(4);
        return s;
    }

    // ═══════════════════════════════════════════════════════════════
    //  BEAN 6 — Mixed Injection  (Annotation style)
    // ═══════════════════════════════════════════════════════════════

    /**
     * MIXED INJECTION — constructor first, then setter override.
     * Useful when some fields are mandatory (constructor) and
     * others are optional or need post-construction adjustment.
     */
    @Bean
    public Student studentAnnotationMixed() {
        System.out.println("\n[AppConfig] Creating studentAnnotationMixed — Mixed Injection");
        Student s = new Student("STU-ANN-03", "Frank Patel", "Cybersecurity", 2);
        s.setCourse("Cybersecurity (Advanced)");   // setter overrides constructor value
        return s;
    }
}
