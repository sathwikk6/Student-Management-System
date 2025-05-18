import java.io.*;
import java.util.*;
class Student implements Serializable {
    int id;
    String name;
    double marks;
    Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks;
    }
}
public class StudentManagementSystem {
    static final String FILE_NAME = "students.dat";
    static List<Student> studentList = new ArrayList<>();
    public static void main(String[] args) {
        loadData();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Delete Student by ID");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    displayAll();
                    break;
                case 3:
                    searchStudent(sc);
                    break;
                case 4:
                    deleteStudent(sc);
                    break;
                case 5:
                    saveData();
                    System.out.println("Exiting... Data saved.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
        sc.close();
    }
    static void addStudent(Scanner sc) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();
        studentList.add(new Student(id, name, marks));
        System.out.println("Student added successfully!");
    }
    static void displayAll() {
        if (studentList.isEmpty()) {
            System.out.println("No student records found.");
        } else {
            for (Student s : studentList) {
                System.out.println(s);
            }
        }
    }
    static void searchStudent(Scanner sc) {
        System.out.print("Enter ID to search: ");
        int id = sc.nextInt();
        boolean found = false;
        for (Student s : studentList) {
            if (s.id == id) {
                System.out.println("Found: " + s);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }
    static void deleteStudent(Scanner sc) {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();
        Iterator<Student> it = studentList.iterator();
        boolean found = false;
        while (it.hasNext()) {
            if (it.next().id == id) {
                it.remove();
                found = true;
                System.out.println("Student deleted.");
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }
    static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(studentList);
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }
    static void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            studentList = (List<Student>) ois.readObject();
        } catch (Exception e) {
            studentList = new ArrayList<>();
        }
    }
}
