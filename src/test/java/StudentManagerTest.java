import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

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
        assertFalse(studentManager.getAllStudents().isEmpty());
        assertEquals(1,studentManager.getAllStudents().size());
    }

    @Test
    @DisplayName("Should Not Create Contact When First Name is Null")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            studentManager.addStudent(1L,null, "Doe","nullDoe@example.com", "0123456789");
        });
    }
    @Test
    @DisplayName("Should Not Create Contact When Last Name is Null")
    public void shouldThrowRuntimeExceptionWhenLastNameIsNull(){
        Assertions.assertThrows(RuntimeException.class,()->{
            studentManager.addStudent(1L,"John", null,"nullDoe@example.com", "0123456789");
        });
    }


    @Test
    @DisplayName("Should Not Create Contact When Email is Null")
    public void shouldThrowRuntimeExceptionWhenEmailIsNull(){
        Assertions.assertThrows(RuntimeException.class,()->{
            studentManager.addStudent(1L,"John", "Doe",null, "0123456789");
        });
    }


    @Test
    @DisplayName("Should Not Create Contact When Email is Null")
    public void shouldThrowRuntimeExceptionWhenEmailIsNotValid(){
        Assertions.assertThrows(RuntimeException.class,()->{
            studentManager.addStudent(1L,"John", "Doe","Johnexample.com", "0123456789");
        });
    }

    @Test
    @DisplayName("Should Create Contact")
    @EnabledOnOs(value = OS.MAC, disabledReason = "Should Run only on MAC")
    public void shouldAddStudentOnMAC() {
    studentManager.addStudent(1L,"John", "Doe","John@example.com", "0123456789");
        assertFalse(studentManager.getAllStudents().isEmpty());
        assertEquals(1, studentManager.getAllStudents().size());
    }
    @Test
    @DisplayName("Test Contact Creation on Developer Machine")
    public void shouldTestContactCreationOnDEV() {
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        studentManager.addStudent(1L,"John", "Doe","John@example.com", "0123456789");
        assertFalse(studentManager.getAllStudents().isEmpty());
        assertEquals(1, studentManager.getAllStudents().size());
    }
    @Test
    @DisplayName("Phone Number should start with 0")
    public void shouldTestPhoneNumberFormat() {
        studentManager.addStudent(1L,"John", "Doe","John@example.com", "0123456789");
        assertFalse(studentManager.getAllStudents().isEmpty());
        assertEquals(1, studentManager.getAllStudents().size());
    }


    @Nested
    class ParameterizedTests {
        @DisplayName("Phone Number should match the required Format")
        @ParameterizedTest
        @ValueSource(strings = {"0123456789", "0123456798", "0123456897"})
        public void shouldTestPhoneNumberFormatUsingValueSource(String phoneNumber) {
            studentManager.addStudent(1L, "John", "Doe", "John@example.com", "0123456789");
            assertFalse(studentManager.getAllStudents().isEmpty());
            assertEquals(1, studentManager.getAllStudents().size());
        }
    }

        @DisplayName("CSV Source Case - Phone Number should match the required Format")
        @ParameterizedTest
        @CsvSource({"0123456789", "0123456798", "0123456897"})
        public void shouldTestPhoneNumberFormatUsingCSVSource(String phoneNumber) {
            studentManager.addStudent(1L, "John", "Doe", "John@example.com", "0123456789");
            assertFalse(studentManager.getAllStudents().isEmpty());
            assertEquals(1, studentManager.getAllStudents().size());
        }

        @DisplayName("CSV File Source Case - Phone Number should match the required Format")
        @ParameterizedTest
        @CsvFileSource(resources = "/data.csv")
        public void shouldTestPhoneNumberFormatUsingCSVFileSource(String phoneNumber) {
            studentManager.addStudent(1L, "John", "Doe", "John@example.com", "0123456789");
            assertFalse(studentManager.getAllStudents().isEmpty());
            assertEquals(1, studentManager.getAllStudents().size());
        }


        @DisplayName("Method Source Case - Phone Number should match the required Format")
        @ParameterizedTest
        @MethodSource("phoneNumberList")
        public void shouldTestPhoneNumberFormatUsingMethodSource(String phoneNumber) {
            studentManager.addStudent(1L, "John", "Doe", "John@example.com", "0123456789");
            assertFalse(studentManager.getAllStudents().isEmpty());
            assertEquals(1, studentManager.getAllStudents().size());
        }

        private static List<String> phoneNumberList() {
            return Arrays.asList("0123456789", "0123456798", "0123456897");
        }


        @Test
        @DisplayName("Test Should Be Disabled")
        @Disabled
        public void shouldBeDisabled() {
            throw new RuntimeException("Test Should Not be executed");
        }


        @AfterEach
        public void tearDown() {
            System.out.println("Should Execute After Each Test");
        }

        @AfterAll
        public static void tearDownAll() {
            System.out.println("Should be executed at the end of the Test");
        }


    }
