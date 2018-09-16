import helpers.RunnerExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@Tag("all")
@Tag("junit")
@DisplayName("Пример сюита JUnit")
@ExtendWith(RunnerExtension.class)
class JunitTest {

    @BeforeAll
    static void beforeAll() { System.out.println("Действия перед всем сюитом"); }

    @BeforeEach
    void beforeEach() { System.out.println("Действия перед каждым тестом"); }

    @Test
    @DisplayName("Успешный тест")
    void passedTest() { Assertions.assertTrue(true); }

    @Test
    @DisplayName("Упавший тест")
    void failedTest() { Assertions.assertFalse(true); }

    @Test
    @Disabled
    @DisplayName("Отключенный тест")
    void disabledTest() { Assertions.assertEquals(1, 2); }

    @AfterEach
    void afterEach() { System.out.println("Действия после каждого теста"); }

    @AfterAll
    static void afterAll() { System.out.println("Действия после всего сюита"); }
}