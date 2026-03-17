package com.experiment.di.model;

/**
 * ╔══════════════════════════════════════════════════════════════════╗
 * ║  TASK 1 – Student class with fields:                            ║
 * ║           studentId, name, course, year                         ║
 * ║  TASK 2 – Constructor for all fields (Constructor Injection)     ║
 * ║  TASK 3 – Setter methods (Setter Injection)                      ║
 * ╚══════════════════════════════════════════════════════════════════╝
 *
 * This plain Java class (POJO) is the target of Spring DI.
 * Spring will inject values into it either via:
 *   • Constructor Injection  – values passed through the constructor
 *   • Setter Injection       – values passed through setter methods
 *
 * The class itself has NO Spring annotations — keeping it a pure POJO
 * shows that Spring can manage any Java class, even without annotations.
 * Bean configuration is done externally (XML or Java @Configuration).
 */
public class Student {

    // ─── TASK 1: Fields ───────────────────────────────────────────

    private String studentId;   // e.g. "STU-001"
    private String name;        // e.g. "Alice Johnson"
    private String course;      // e.g. "Computer Science"
    private int    year;        // e.g. 2  (year of study 1–4)

    // ─── TASK 2: No-arg Constructor (required by Spring for setter injection) ─

    /**
     * No-argument constructor.
     * Required by Spring when using SETTER injection —
     * Spring first calls this, then calls each setter.
     */
    public Student() {
        System.out.println("  [Spring] Student() — no-arg constructor called.");
    }

    // ─── TASK 2: All-args Constructor (used by constructor injection) ─────────

    /**
     * All-argument constructor.
     * Spring calls this SINGLE time with all values injected
     * when using CONSTRUCTOR injection.
     *
     * In XML this maps to:
     *   <constructor-arg index="0" value="STU-002"/>
     *   <constructor-arg index="1" value="Bob Smith"/>
     *   ...
     *
     * In @Configuration this maps to:
     *   new Student("STU-003", "Carol White", "Data Science", 1)
     */
    public Student(String studentId, String name, String course, int year) {
        System.out.println("  [Spring] Student(args) — all-args constructor called.");
        this.studentId = studentId;
        this.name      = name;
        this.course    = course;
        this.year      = year;
    }

    // ─── TASK 3: Setter Methods (used by setter injection) ───────────────────

    /**
     * Setter for studentId.
     * In XML:  <property name="studentId" value="STU-001"/>
     */
    public void setStudentId(String studentId) {
        System.out.println("  [Spring] setStudentId() called → " + studentId);
        this.studentId = studentId;
    }

    /**
     * Setter for name.
     * In XML:  <property name="name" value="Alice Johnson"/>
     */
    public void setName(String name) {
        System.out.println("  [Spring] setName() called → " + name);
        this.name = name;
    }

    /**
     * Setter for course.
     * In XML:  <property name="course" value="Computer Science"/>
     */
    public void setCourse(String course) {
        System.out.println("  [Spring] setCourse() called → " + course);
        this.course = course;
    }

    /**
     * Setter for year.
     * In XML:  <property name="year" value="2"/>
     */
    public void setYear(int year) {
        System.out.println("  [Spring] setYear() called → " + year);
        this.year = year;
    }

    // ─── Getters ──────────────────────────────────────────────────

    public String getStudentId() { return studentId; }
    public String getName()      { return name;      }
    public String getCourse()    { return course;    }
    public int    getYear()      { return year;      }

    // ─── Display helper (Task 6) ──────────────────────────────────

    /**
     * Prints a formatted summary of this Student's injected values.
     */
    public void display() {
        System.out.println("  ┌─────────────────────────────────────┐");
        System.out.printf ("  │  Student ID : %-22s │%n", studentId);
        System.out.printf ("  │  Name       : %-22s │%n", name);
        System.out.printf ("  │  Course     : %-22s │%n", course);
        System.out.printf ("  │  Year       : %-22s │%n", "Year " + year);
        System.out.println("  └─────────────────────────────────────┘");
    }

    @Override
    public String toString() {
        return String.format("Student{studentId='%s', name='%s', course='%s', year=%d}",
                studentId, name, course, year);
    }
}
