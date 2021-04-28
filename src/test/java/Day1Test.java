import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Day1Test {

    @Test
    void test_findLinesWithSum2020_returnsResultListAsExpected() {
        // arrange
        List<Integer> integerList = new ArrayList<>();
        integerList.add(2000);
        integerList.add(20);
        integerList.add(31);
        integerList.add(45);
        integerList.add(4551);
        integerList.add(11);
        integerList.add(83);

        // act
        List<Integer> result = Day1.findLinesWithSum2020(integerList);

        // assert
        assertThat(result).containsExactlyInAnyOrder(20, 2000);
    }

    @Test
    void test_findLinesWithSum2020_returnsEmptyListWhenNoSumOf2020WasFound() {
        // arrange
        List<Integer> integerList = new ArrayList<>();
        integerList.add(20);
        integerList.add(31);
        integerList.add(45);
        integerList.add(4551);
        integerList.add(11);
        integerList.add(83);

        // act
        List<Integer> result = Day1.findLinesWithSum2020(integerList);

        // assert
        assertEquals(result, Collections.emptyList());
    }
}