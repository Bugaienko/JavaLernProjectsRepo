package lesson20;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Sergii Bugaienko
 */

public class CalculatorTest {
    private static Calculator calculator;
    @BeforeAll //@BeforeEach
    public static   void createCalc() {
        calculator = new Calculator();
    }

    @CsvSource({
            "2, 2, 4",
            "-2, -2, -4",
            "-2, 1, -1"
    })

    @ParameterizedTest
    public void massTestAdd(int a, int b, int result) {
        Calculator calc = new Calculator();
        Assertions.assertEquals(result, calc.add(a, b));
    }



    @ParameterizedTest
    @MethodSource("dataForSubTest")
    public void testSub (int a, int b, int result) {
        Assertions.assertEquals(result, calculator.sub(a, b));
    }

    public static Stream<Arguments> dataForSubTest() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(3,1,2));
        out.add(Arguments.arguments(1,4,-3));
        out.add(Arguments.arguments(-3,-2,-1));
        out.add(Arguments.arguments(0,-1, 1));

        return out.stream();

    }
    @Test
    public void divisionByZeroException() {
        ArithmeticException thrown = Assertions.assertThrows(ArithmeticException.class, () ->{
        calculator.div(1, 0);}, "ArithmeticException was expected"
        );
    }



    @Test
    public void testDiv() {
        Assertions.assertEquals(3, calculator.div(6, 2));
    }

    @Test
    public void testMul() {
        Assertions.assertEquals(21, calculator.mul(7, 3));
    }
}
