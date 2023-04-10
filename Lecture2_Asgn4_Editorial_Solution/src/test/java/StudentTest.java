import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private Student student;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        try {
            Constructor<Student> constructor = Student.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            student = constructor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
                 InvocationTargetException e) {
            fail("Default constructor does not exist or is not accessible");
        }
    }

    @Test
    public void testAgeExists() {
        try {
            Field field = Student.class.getDeclaredField("age");
            assertFalse(Modifier.isPrivate(field.getModifiers()));
            assertEquals("int", field.getType().getSimpleName());
        } catch (NoSuchFieldException e) {
            fail("Field age does not exist");
        }
    }

    @Test
    public void testNameExists() {
        try {
            Field field = Student.class.getDeclaredField("name");
            assertFalse(Modifier.isPrivate(field.getModifiers()));
            assertEquals("String", field.getType().getSimpleName());
        } catch (NoSuchFieldException e) {
            fail("Field name does not exist");
        }
    }

    @Test
    public void testDefaultConstructorExists() {
        try {
            Constructor<?> constructor = Student.class.getDeclaredConstructor();
            assertNotNull(constructor);
            assertEquals(0, constructor.getParameterCount());
        } catch (NoSuchMethodException e) {
            fail("Default constructor does not exist");
        }
    }

    @Test
    public void testConstructorWithIntParameterExists() {
        try {
            Constructor<?> constructor = Student.class.getDeclaredConstructor(int.class);
            assertNotNull(constructor);
            assertEquals(1, constructor.getParameterCount());
            assertEquals("int", constructor.getParameterTypes()[0].getSimpleName());
        } catch (NoSuchMethodException e) {
            fail("Constructor with int parameter does not exist");
        }
    }

    @Test
    public void testConstructorWithStringParameterExists() {
        try {
            Constructor<?> constructor = Student.class.getDeclaredConstructor(String.class);
            assertNotNull(constructor);
            assertEquals(1, constructor.getParameterCount());
            assertEquals("String", constructor.getParameterTypes()[0].getSimpleName());
        } catch (NoSuchMethodException e) {
            fail("Constructor with String parameter does not exist");
        }
    }

    @Test
    public void testConstructorWithIntAndStringParametersExists() {
        try {
            Constructor<?> constructor = Student.class.getDeclaredConstructor(int.class, String.class);
            assertNotNull(constructor);
            assertEquals(2, constructor.getParameterCount());
            assertEquals("int", constructor.getParameterTypes()[0].getSimpleName());
            assertEquals("String", constructor.getParameterTypes()[1].getSimpleName());
        } catch (NoSuchMethodException e) {
            fail("Constructor with int and String parameters does not exist");
        }
    }

    @Test
    public void testDefaultConstructor() throws NoSuchFieldException, IllegalAccessException {
        Field ageField = Student.class.getDeclaredField("age");
        ageField.setAccessible(true);
        Field nameField = Student.class.getDeclaredField("name");
        nameField.setAccessible(true);

        assertEquals(0, ageField.getInt(student));
        assertNull(nameField.get(student));
    }

    @Test
    public void testConstructorWithIntParameter() {
        try {
            int age = 21;
            Constructor<?> constructor = Student.class.getDeclaredConstructor(int.class);
            assertNotNull(constructor);
            assertEquals(1, constructor.getParameterCount());
            assertEquals("int", constructor.getParameterTypes()[0].getSimpleName());

            Object instance = constructor.newInstance(age);
            assertTrue(instance instanceof Student);

            Student student = (Student) instance;
            assertEquals(age, student.age);
            assertNull(student.name);
        } catch (Exception e) {
            fail("Failed to test constructor with int parameter using reflection");
        }
    }

    @Test
    public void testConstructorWithStringParameter() {
        try {
            String name = "John";
            Constructor<?> constructor = Student.class.getDeclaredConstructor(String.class);
            assertNotNull(constructor);
            assertEquals(1, constructor.getParameterCount());
            assertEquals("String", constructor.getParameterTypes()[0].getSimpleName());

            Object instance = constructor.newInstance(name);
            assertTrue(instance instanceof Student);

            Student student = (Student) instance;
            assertEquals(0, student.age);
            assertEquals(name, student.name);
        } catch (Exception e) {
            fail("Failed to test constructor with String parameter using reflection");
        }
    }

    @Test
    public void testConstructorWithIntAndStringParameters() {
        try {
            int age = 21;
            String name = "John";
            Constructor<?> constructor = Student.class.getDeclaredConstructor(int.class, String.class);
            assertNotNull(constructor);
            assertEquals(2, constructor.getParameterCount());
            assertEquals("int", constructor.getParameterTypes()[0].getSimpleName());
            assertEquals("String", constructor.getParameterTypes()[1].getSimpleName());

            Object instance = constructor.newInstance(age, name);
            assertTrue(instance instanceof Student);

            Student student = (Student) instance;
            assertEquals(age, student.age);
            assertEquals(name, student.name);
        } catch (Exception e) {
            fail("Failed to test constructor with int and String parameters using reflection");
        }
    }


}
