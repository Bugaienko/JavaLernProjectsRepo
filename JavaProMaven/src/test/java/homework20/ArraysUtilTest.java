package homework20;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Sergii Bugaienko
 */

public class ArraysUtilTest {
    private static ArraysUtils arraysUtils;

    @BeforeAll
    public static void initCalc() {
        arraysUtils = new ArraysUtils();
    }

    @ParameterizedTest
    @MethodSource("dataForArrayTest")
    void testAfter4ArrayReturn(int[] target, int[] result) {
        Assertions.assertArrayEquals(result, arraysUtils.after4ArrayReturn(target));
    }

    @Test
    public void exceptionIfAbsentDivider() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            arraysUtils.after4ArrayReturn(new int[]{1, 2, 3, 5, 6, 7, 8, 9, 0});
        });
    }

    @ParameterizedTest
    @MethodSource("dataForCheckValuesTest")
    public void testCheckArraysValues(int[] target, boolean result) {
        Assertions.assertEquals(result, arraysUtils.checkArraysValues(target));
    }

    public static Stream<Arguments> dataForArrayTest() {
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[]{1, 7}));
        out.add(Arguments.arguments(new int[]{1, 2, 4, 2, 3, 4}, new int[]{}));
        out.add(Arguments.arguments(new int[]{}, new int[]{}));
        out.add(Arguments.arguments(new int[]{4, 1, 2, 3, 5}, new int[]{1, 2, 3, 5}));
        return out.stream();
    }

    public static Stream<Arguments> dataForCheckValuesTest() {
        List<Arguments> out = new ArrayList<>();

        out.add(Arguments.arguments(new int[]{1, 1, 1, 4, 4, 1, 4, 4}, true));
        out.add(Arguments.arguments(new int[]{1, 1, 1, 1, 1, 1, 1}, false));
        out.add(Arguments.arguments(new int[]{4, 4, 4, 4}, false));
        out.add(Arguments.arguments(new int[]{1, 1, 4, 4, 1, 4, 3}, false));
        out.add(Arguments.arguments(new int[]{1}, false));
        out.add(Arguments.arguments(new int[]{1, 4}, true));

        return out.stream();
    }


}
