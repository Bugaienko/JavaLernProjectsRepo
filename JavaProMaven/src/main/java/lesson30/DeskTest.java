package lesson30;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * @author Sergii Bugaienko
 */

public class DeskTest {

    @ParameterizedTest
    @MethodSource("dataRandomForDeskTest")
    public void testDesk(int seconds) {
        System.out.println(Desk.calculateHours(seconds));
    }

    public static Stream<Arguments> dataRandomForDeskTest(){
        List<Arguments> out = new ArrayList<>();
        out.add(Arguments.arguments(new Random().nextInt(28800)));
        out.add(Arguments.arguments(new Random().nextInt(28800)));
        out.add(Arguments.arguments(new Random().nextInt(28800)));
        out.add(Arguments.arguments(new Random().nextInt(28800)));
        out.add(Arguments.arguments(new Random().nextInt(28800)));
        return out.stream();

    }
}
<dependency>
<groupId>org.junit.jupiter</groupId>
<artifactId>junit-jupiter</artifactId>
<version>5.9.2</version>
<scope>test</scope>
</dependency>
