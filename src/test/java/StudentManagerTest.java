import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentManagerTest {
    private StudentManager studentManager;
    @BeforeAll
    public static void setupAll(){
        System.out.println("Should print Before All tests");
    }

    @BeforeEach
    public void setup(){
        System.out.println("Creating studentManager");
        studentManager = new StudentManager();
    }
    @Test
    public void shouldCreateStudent(){
        studentManager.addStudent(1L,"John","Doe","John@example.com","0571444234");
        assertFalse(studentManager.getAllContacts().isEmpty());
        assertEquals(1,studentManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should Not Create Contact When First Name is Null")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            studentManager.addStudent(1L,null, "Doe","nullDoe@example.com", "0123456789");
        });
    }

}