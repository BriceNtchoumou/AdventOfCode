import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class Day11Test {

    @Test
    void test_convertListStringToListOfCharacterArray() {
        // arrange
        List<String> stringList = new ArrayList<>();
        stringList.add("Hello");
        stringList.add("World");
        stringList.add("a");

        char[] firstArray = new char[] {'H', 'e', 'l', 'l', 'o'};
        char[] secondArray = new char[] {'W', 'o', 'r', 'l', 'd'};
        char[] thirdArray = new char[] {'a'};

        // act
        List<char[]> result = Day11.convertListStringToListOfCharacterArray(stringList);

        // assert
        assertThat(result)
                .containsExactlyInAnyOrder(firstArray, secondArray, thirdArray);
    }

    @Test
    void test_countOccupiedSeats() {
        // arrange

        // act

        // assert
    }

}