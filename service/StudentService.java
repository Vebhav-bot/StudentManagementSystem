package service;

import model.Student;
import util.FileUtil;


import java.util.List;
import java.util.Scanner;

public class StudentService {
    private List<Student> students;
    private Scanner scanner;

    public StudentService() {
        FileUtil.initFile();
        students = FileUtil.readStudents();
        scanner = new Scanner(System.in);
    }

    public void addStudent() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (findById(id) != null) {
            System.out.println("Student with this ID already exists.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        if (age <= 0) {
            System.out.println("Age must be positive.");
            return;
        }

        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        Student s = new Student(id, name, age, course);
        students.add(s);
        FileUtil.writeStudents(students);
        System.out.println("Student added.");
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(s -> System.out.println(s.toString()));
        }
    }

    public void searchStudent() {
        System.out.print("Enter ID to search: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student s = findById(id);
        if (s != null) {
            System.out.println("Student Found: " + s);
        } else {
            System.out.println("Student not found.");
        }
    }

    public void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student s = findById(id);
        if (s != null) {
            students.remove(s);
            FileUtil.writeStudents(students);
            System.out.println("Student deleted.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }
}
