
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class Day_11Test {

    @Test
    void test_convertListStringToListOfCharachterArray() {
        // given
        List<String> stringList = new ArrayList<>();
        stringList.add("Willkommen");
        stringList.add("bei");
        stringList.add("Java");
        stringList.add("lernen");

        char[] firstArray = new char[]{'W', 'i', 'l', 'l', 'k', 'o', 'm', 'm', 'e', 'n'};
        char[] secondArray = new char[]{'b', 'e', 'i'};
        char[] thirdArray = new char[]{'J', 'a', 'v', 'a'};
        char[] fourthArray = new char[]{'l', 'e', 'r', 'n', 'e', 'n'};

        // when
        List<char[]> myListOfCharacterArray = Day_11.convertListStringToListOfCharacterArray(stringList);

        // then
        assertThat(myListOfCharacterArray).containsExactlyInAnyOrder(secondArray, fourthArray, thirdArray, firstArray);
        assertThat(myListOfCharacterArray).containsExactly(firstArray, secondArray, thirdArray, fourthArray);
        assertThat(myListOfCharacterArray).endsWith(fourthArray);
        assertThat(myListOfCharacterArray).startsWith(firstArray);
    }

    @Test
    void test_countOccupiedSeat() {
        // given
        List<char[]> list = new ArrayList<>();
        list.add(new char[]{'L', '#', '#', '.', '#'});
        list.add(new char[]{'L', 'L', '.', '#', '#'});
        list.add(new char[]{'#', '#', '.', 'L', 'L'});
        list.add(new char[]{'#', '#', '#', '.', '#'});

        // when
        int result = Day_11.countOccupiedSeats(list);
        // then

        Assertions.assertEquals(11, result);
        Assertions.assertNotEquals(15, result);
        assertThat(result).isEqualTo(11);
        assertThat(result).isGreaterThan(10);
        assertThat(result).isLessThan(15);
    }

    @Test
    void test_checkRules() {
        // given
        List<char[]> mySeat = new ArrayList<>();
        mySeat.add(new char[]{'#', '.', '#', '#', '.', '#', '#', '.', '#', '#'});
        mySeat.add(new char[]{'#', '#', '#', '#', '#', '#', '#', '.', '#', '#'});
        mySeat.add(new char[]{'#', '.', '#', '.', '#', '.', '.', '#', '.', '.'});
        mySeat.add(new char[]{'#', '#', '#', '#', '.', '#', '#', '.', '#', '#'});
        mySeat.add(new char[]{'#', '.', '#', '#', '.', '#', '#', '.', '#', '#'});
        mySeat.add(new char[]{'#', '.', '#', '#', '#', '#', '#', '.', '#', '#'});
        mySeat.add(new char[]{'.', '.', '#', '.', '#', '.', '.', '.', '.', '.'});
        mySeat.add(new char[]{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'});
        mySeat.add(new char[]{'#', '.', '#', '#', '#', '#', '#', '#', '.', '#'});
        mySeat.add(new char[]{'#', '.', '#', '#', '#', '#', '#', '.', '#', '#'});

        // when

        List<char[]> myList = Day_11.checkRules(mySeat);

        // then
        assertThat(myList).isNotEqualTo(mySeat);
        assertThat(myList).size().isEqualTo(mySeat.size());
        assertThat(myList).hasSameSizeAs(mySeat);
    }

    @Test
    void test_countAdjacentSeats() {
        // given
        List<char[]> mySeat = new ArrayList<>();
        mySeat.add(new char[]{'#', '.', '#', '#', '.', '#', '#', '.', '#', '#'});
        mySeat.add(new char[]{'#', '#', '#', '#', '#', '#', '#', '.', '#', '#'});
        mySeat.add(new char[]{'#', '.', '#', '.', '#', '.', '.', '#', '.', '.'});
        mySeat.add(new char[]{'#', '#', '#', '#', '.', '#', '#', '.', '#', '#'});
        mySeat.add(new char[]{'#', '.', '#', '#', '.', '#', '#', '.', '#', '#'});
        mySeat.add(new char[]{'#', '.', '#', '#', '#', '#', '#', '.', '#', '#'});
        mySeat.add(new char[]{'.', '.', '#', '.', '#', '.', '.', '.', '.', '.'});
        mySeat.add(new char[]{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'});
        mySeat.add(new char[]{'#', '.', '#', '#', '#', '#', '#', '#', '.', '#'});
        mySeat.add(new char[]{'#', '.', '#', '#', '#', '#', '#', '.', '#', '#'});

        // when
        int result = Day_11.countAdjacentSeats(mySeat, 3, 6);

        // then
        assertThat(result).isEqualTo(4);
        assertThat(result).isLessThan(6);
        assertThat(result).isGreaterThan(3);
        Assertions.assertEquals(4, result);
        Assertions.assertNotEquals(8, result);
    }

    @Test
    void test_changeState() {
        //given
        List<char[]> oldList = new ArrayList<>();
        oldList.add(new char[]{'#', '.', '#', '#', '.', '#', '#', '.', '#', '#'});
        oldList.add(new char[]{'#', '#', '#', '#', '#', '#', '#', '.', '#', '#'});
        oldList.add(new char[]{'#', '.', '#', '.', '#', '.', '.', '#', '.', '.'});
        oldList.add(new char[]{'#', '#', '#', '#', '.', '#', '#', '.', '#', '#'});
        oldList.add(new char[]{'#', '.', '#', '#', '.', '#', '#', '.', '#', '#'});
        oldList.add(new char[]{'#', '.', '#', '#', '#', '#', '#', '.', '#', '#'});
        oldList.add(new char[]{'.', '.', '#', '.', '#', '.', '.', '.', '.', '.'});
        oldList.add(new char[]{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'});
        oldList.add(new char[]{'#', '.', '#', '#', '#', '#', '#', '#', '.', '#'});
        oldList.add(new char[]{'#', '.', '#', '#', '#', '#', '#', '.', '#', '#'});

        List<char[]> newList = new ArrayList<>();
        newList.add(new char[]{'#', '.', '#', '#', '.', '#', '#', '.', '#', '#'});
        newList.add(new char[]{'#', '#', '#', '#', '#', '#', '#', '.', '#', '#'});
        newList.add(new char[]{'#', '.', '#', '.', '#', '.', '.', '#', '.', '.'});
        newList.add(new char[]{'#', '#', '#', '#', '.', '#', '#', '.', '#', '#'});
        newList.add(new char[]{'#', '.', '#', '#', '.', '#', '#', '.', '#', '#'});
        newList.add(new char[]{'#', '.', '#', '#', '#', '#', '#', '.', '#', '#'});
        newList.add(new char[]{'.', '.', '#', '.', '#', '.', '.', '.', '.', '.'});
        newList.add(new char[]{'#', '#', '#', '#', '#', '#', '#', '#', '#', '#'});
        newList.add(new char[]{'#', '#', '.', '#', '#', '#', '#', '#', '#', '#'});
        newList.add(new char[]{'#', '.', '#', '#', '#', '#', '#', '#', '#', '.'});

        // when
        boolean isStable = Day_11.changeState(newList, oldList);

        // then
        assertThat(isStable).isEqualTo(true);
        assertThat(isStable).isTrue();
        assertThat(isStable).isNotEqualTo(false);
        Assertions.assertTrue(isStable);
    }

    @Test
    void test_deepClone() {
        // given
        List<char[]> oldList = new ArrayList<>();
        oldList.add(new char[]{'L', '#', '#', '.', '#'});
        oldList.add(new char[]{'L', 'L', '.', '#', '#'});
        oldList.add(new char[]{'#', '#', '.', 'L', 'L'});
        oldList.add(new char[]{'#', '#', '#', '.', '#'});

        // when
        List<char[]> newList = Day_11.deepClone(oldList);

        // then
        assertThat(newList).size().isEqualTo(oldList.size());
        assertThat(newList).startsWith(new char[]{'L', '#', '#', '.', '#'});
        assertThat(newList).endsWith(new char[]{'#', '#', '#', '.', '#'});
        assertThat(newList).containsExactly(new char[]{'L', '#', '#', '.', '#'}, new char[]{'L', 'L', '.', '#', '#'},
                new char[]{'#', '#', '.', 'L', 'L'}, new char[]{'#', '#', '#', '.', '#'});
    }

    @Test
    void test_runPartOne() throws IOException {
        // given
        final String fileName = ("C:\\Users\\pontd\\IdeaProjects\\AdventOfCode\\src\\main\\resources\\inputDay11.txt");

        List<String> inputFile;
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            inputFile = lines.collect(Collectors.toList());
        }
        List<char[]> myCharacterArrayList = Day_11.convertListStringToListOfCharacterArray(inputFile);

        // when
        int result = Day_11.runPartOne(myCharacterArrayList);

        // then
        assertThat(result).isEqualTo(2270);
    }


    @Test
    void test_countVisibleSeats()   // Methode vom Part two
    {
        // given
        List<char[]> mySeat = new ArrayList<>();

        mySeat.add(new char[]{'.', '.', '.', '.', '.', '.', '.', '#', '.'});
        mySeat.add(new char[]{'.', '.', '.', '#', '.', '.', '.', '.', '.'});
        mySeat.add(new char[]{'.', '#', '.', '.', '.', '.', '.', '.', '.'});
        mySeat.add(new char[]{'.', '.', '.', '.', '.', '.', '.', '.', '.'});
        mySeat.add(new char[]{'.', '.', '#', 'L', '.', '.', '.', '.', '#'});
        mySeat.add(new char[]{'.', '.', '.', '.', '#', '.', '.', '.', '.'});
        mySeat.add(new char[]{'.', '.', '.', '.', '.', '.', '.', '.', '.'});
        mySeat.add(new char[]{'#', '.', '.', '.', '.', '.', '.', '.', '.'});
        mySeat.add(new char[]{'.', '.', '.', '#', '.', '.', '.', '.', '.'});

        // when
        int result = Day_11.countVisibleSeats(mySeat,4,3);

        // then
        assertThat(result).isEqualTo(8);

    }
}