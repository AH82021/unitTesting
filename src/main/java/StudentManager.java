import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StudentManager {

    Map<String,Student>  studentList = new ConcurrentHashMap<>();

    public void addStudent(Long id,String firstName, String lastName,String email,String phoneNumber) {
        Student student = new Student(id,firstName,lastName,email,phoneNumber);
        validateStudent(student);
        checkIfStudentAlreadyExists(student);
        studentList.put(createKey(student),student);

    }

    public Collection<Student> getAllStudents() {
        return studentList.values();
    }

    private void checkIfStudentAlreadyExists(Student student) {
        if (studentList.containsKey(createKey(student))) {
            throw new RuntimeException("Student already exists");

        }
    }

    private String createKey(Student student) {
        return String.format("%s-%s", student.getFirstName(), student.getLastName());
    }

    private void validateStudent(Student student) {
        student.validateFirstName();
        student.validateLastName();
        student.validateEmail();
        student.validatePhoneNumber();
    }
}
