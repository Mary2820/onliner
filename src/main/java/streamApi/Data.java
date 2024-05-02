package streamApi;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private static List<Student> students = new ArrayList<>();

    static {
        Student student1 = new Student("Natali", "Gray", 17);
        Student student2 = new Student("Ola", "Smit", 18);
        Student student3 = new Student("Ivanka", "Lose", 21);
        Student student4 = new Student("Maria", "Krag", 20);
        Student student5 = new Student("Katy", "Groove", 19);
        Student student6 = new Student("Ken", "Moll", 17);
        Student student7 = new Student("Sam", "Perkis", 20);
        Student student8 = new Student("Dan", "West", 19);
        Student student9 = new Student("Ami", "Dark", 21);
        Student student10 = new Student("Fin", "Bong", 18);

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);
        students.add(student7);
        students.add(student8);
        students.add(student9);
        students.add(student10);
    }

    public static List<Student> getStudents() {
        return students;
    }

}
