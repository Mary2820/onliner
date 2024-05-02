package streamApi;

import java.util.List;
import java.util.stream.Collectors;

public class workWithStream {
    public static void main(String[] args) {
        List<Student> students = Data.getStudents();

        List<Student> students2 = students.stream()
                .filter(student -> student.getAge() > 18)
                .sorted()
                .collect(Collectors.toList());

        for (Student student : students2) {
            System.out.println(student);
        }
    }
}
