import java.util.*;
import java.io.*;
class Student {
    int Id;
    String name;
    int age;
    String course;
    float cgpa;

    Student(int S_id, String S_name, int S_age, String S_course, float S_cgpa) {
        this.Id = S_id;
        this.name = S_name;
        this.age = S_age;
        this.course = S_course;
        this.cgpa = S_cgpa;

    }
}
    class StudentServices {

        static ArrayList<Student> Sdata = new ArrayList<Student>();

        public static void addStudent() {
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter the Id of Student:");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter the Name of Student:");
            String name = sc.nextLine();

            System.out.println("Enter the age of Student:");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter the Enrolled Course:");
            String course = sc.nextLine();

            System.out.println("Enter the CGPA: ");
            float cgpa = sc.nextFloat();
            sc.nextLine();

            Student s1 = new Student(id, name, age, course, cgpa);
            Sdata.add(s1);
            System.out.println("Data Added Successfully !!");


        }

        public static void viewStudent() {

            if (Sdata.isEmpty()) {
                System.out.println(" Sorry No Data Found in List !!");
                return;
            }
            System.out.println("***********// List of All Studnets //**********");
            for (Student s : Sdata) {
                System.out.println(s.Id + "\t" + s.name + "\t" + s.age + "\t" + s.course + "\t" + s.cgpa);
            }

        }

        public static void searchStudent() {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter the Id of Student:");
            int id = sc.nextInt();

            boolean found = false;
            for (Student s : Sdata) {
                if (s.Id == id) {
                    System.out.println("Student Found:");
                    System.out.println("ID: " + s.Id);
                    System.out.println("Name: " + s.name);
                    System.out.println("Age: " + s.age);
                    System.out.println("Course: " + s.course);
                    System.out.println("CGPA: " + s.cgpa);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Student with ID " + id + " not found.");
            }

        }

        public static void updateStudent() {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Student ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();

            boolean found = false;

            for (Student s : Sdata) {

                if (s.Id == id) {

                    System.out.println("Enter new Name:");
                    s.name = sc.nextLine();

                    System.out.println("Enter new Age:");
                    s.age = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter new Course:");
                    s.course = sc.nextLine();

                    System.out.println("Enter new CGPA:");
                    s.cgpa = sc.nextFloat();
                    sc.nextLine();

                    System.out.println("Student updated successfully.");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Student not found.");
            }
        }

        public static void deleteStudnet() {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Student ID to delete: ");
            int id = sc.nextInt();

            boolean found = false;

            for (int i = 0; i < Sdata.size(); i++) {

                if (Sdata.get(i).Id == id) {
                    Sdata.remove(i);
                    System.out.println("Student deleted successfully.");
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Student not found.");
            }
        }

        public static void saveToFile() {


            try {

                PrintWriter pw = new PrintWriter(new FileWriter("students.txt"));

                for (Student s : Sdata) {

                    pw.println(s.Id + "," + s.name + "," + s.age + "," + s.course + "," + s.cgpa);

                }

                pw.close();

                System.out.println("Data saved successfully.");

            } catch (IOException e) {

                System.out.println("Error saving file.");

            }
        }

        public static void loadFromFile() {
            try {

                BufferedReader br = new BufferedReader(new FileReader("students.txt"));

                String line;

                while ((line = br.readLine()) != null) {

                    String[] data = line.split(",");

                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    int age = Integer.parseInt(data[2]);
                    String course = data[3];
                    float cgpa = Float.parseFloat(data[4]);

                    Student s = new Student(id, name, age, course, cgpa);

                    Sdata.add(s);
                }

                br.close();

                System.out.println("Data loaded successfully.");

            } catch (IOException e) {

                System.out.println("No saved data found.");

            }
        }
    }
        public class StudentManagementSystem extends StudentServices {
            public static void main(String ar[]) {
                Scanner sc = new Scanner(System.in);

                loadFromFile();


                for (int i = 0; i < 50; i++) {
                    System.out.print("-");
                }
                System.out.println();
                System.out.println(" !! Welcome To S_M Portal !!");
                for (int i = 0; i < 50; i++) {
                    System.out.print("-");
                }
                System.out.println();
                boolean available = true;
                while (available) {
                    System.out.println(" Please Select the Mode from Below");
                    System.out.println("1.Add Student");
                    System.out.println("2.View Student");
                    System.out.println("3.Search Student");
                    System.out.println("4.Update Student Details");
                    System.out.println("5.Delete Student");
                    System.out.println("6.Save Data");
                    System.out.println("0. Exit");
                    int n = sc.nextInt();
                    switch (n) {
                        case 1:
                            addStudent();
                            break;
                        case 2:
                            viewStudent();
                            break;
                        case 3:
                            searchStudent();
                            break;
                        case 4:
                            updateStudent();
                            break;
                        case 5:
                            deleteStudnet();
                            break;
                        case 6:
                            saveToFile();
                            break;
                        case 0:
                            available = !available;
                            break;
                        default:
                            System.out.println("Please Select from the range 1-7");


                    }
                }


            }

        }


