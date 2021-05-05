import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * --- Day 11: Seating System ---
 * Your plane lands with plenty of time to spare. The final leg of your journey is a ferry
 * that goes directly to the tropical island where you can finally start your vacation.
 * As you reach the waiting area to board the ferry, you realize you're so early, nobody else has even arrived yet!
 * By modeling the process people use to choose (or abandon) their seat in the waiting area,
 * you're pretty sure you can predict the best place to sit. You make a quick map of
 * the seat layout (your puzzle input).
 * <p>
 * The seat layout fits neatly on a grid. Each position is either floor (.), an empty seat (L),
 * or an occupied seat (#). For example, the initial seat layout might look like this:
 * <p>
 * L.LL.LL.LL
 * LLLLLLL.LL
 * L.L.L..L..
 * LLLL.LL.LL
 * L.LL.LL.LL
 * L.LLLLL.LL
 * ..L.L.....
 * LLLLLLLLLL
 * L.LLLLLL.L
 * L.LLLLL.LL
 * <p>
 * <p>
 * Now, you just need to model the people who will be arriving shortly. Fortunately,
 * people are entirely predictable and always follow a simple set of rules.
 * All decisions are based on the number of occupied seats adjacent to a given seat
 * (one of the eight positions immediately up, down, left, right, or diagonal from the seat).
 * The following rules are applied to every seat simultaneously:
 * <p>
 * If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
 * If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
 * Otherwise, the seat's state does not change.
 * Floor (.) never changes; seats don't move, and nobody sits on the floor.
 * <p>
 * After one round of these rules, every seat in the example layout becomes occupied:
 * <p>
 * #.##.##.##
 * #######.##
 * #.#.#..#..
 * ####.##.##
 * #.##.##.##
 * #.#####.##
 * ..#.#.....
 * ##########
 * #.######.#
 * #.#####.##
 * After a second round, the seats with four or more occupied adjacent seats become empty again:
 * <p>
 * #.LL.L#.##
 * #LLLLLL.L#
 * L.L.L..L..
 * #LLL.LL.L#
 * #.LL.LL.LL
 * #.LLLL#.##
 * ..L.L.....
 * #LLLLLLLL#
 * #.LLLLLL.L
 * #.#LLLL.##
 * This process continues for three more rounds:
 * #.##.L#.##
 * #L###LL.L#
 * L.#.#..#..
 * #L##.##.L#
 * #.##.LL.LL
 * #.###L#.##
 * ..#.#.....
 * #L######L#
 * #.LL###L.L
 * #.#L###.##
 * <p>
 * <p>
 * #.#L.L#.##
 * #LLL#LL.L#
 * L.L.L..#..
 * #LLL.##.L#
 * #.LL.LL.LL
 * #.LL#L#.##
 * ..L.L.....
 * #L#LLLL#L#
 * #.LLLLLL.L
 * #.#L#L#.##
 * <p>
 * #.#L.L#.##
 * #LLL#LL.L#
 * L.#.L..#..
 * #L##.##.L#
 * #.#L.LL.LL
 * #.#L#L#.##
 * ..L.L.....
 * #L#L##L#L#
 * #.LLLLLL.L
 * #.#L#L#.##
 * At this point, something interesting happens: the chaos stabilizes and further applications
 * of these rules cause no seats to change state! Once people stop moving around, you count 37 occupied seats.
 * <p>
 * Simulate your seating area by applying the seating rules repeatedly until no seats change state.
 * How many seats end up occupied?
 */

public class Day_11 {

    public static void main(String[] args) throws IOException {
        final String fileName = ("C:\\Users\\pontd\\IdeaProjects\\AdventOfCode\\src\\main\\resources\\inputDay11.txt");
        final String fName = ("C:\\test.txt");
        List<String> inputFile;
        try (Stream<String> lines = Files.lines(Paths.get(fName))) {
            inputFile = lines.collect(Collectors.toList());
        }
        List<char[]> myCharacterArrayList = convertListStringToListOfCharacterArray(inputFile);

        int result1 = runPartOne(myCharacterArrayList);
        System.out.println("part One := " + result1);
        int result2 = runPartTwo(myCharacterArrayList);
        System.out.println("part Two := " + result2);


    }

    // This method executes the part One of this exercise
    public static int runPartOne(List<char[]> myList) {
        List<char[]> mySeats;
        do {
            mySeats = deepClone(myList);
            myList = checkRules(myList);
        } while (changeState(mySeats, myList));
        return countOccupiedSeats(myList);
    }

    // This method converts a datatype List<String> to another datatype List<char[]>
    public static List<char[]> convertListStringToListOfCharacterArray(List<String> stringList) {
        List<char[]> arrayListChar = new ArrayList<>();
        for (String str : stringList) {
            arrayListChar.add(str.toCharArray());
        }
        return arrayListChar;
    }

    // This method counts the number of occupied seats
    public static int countOccupiedSeats(List<char[]> list) {
        int count = 0;
        for (char[] chars : list) {
            for (char aChar : chars) {
                if (aChar == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    /* This method checks the rules, to know if a a empty seat becomes occupied and an occupied
     * seat becomes empty
     */
    public static List<char[]> checkRules(List<char[]> mySeat) {
        List<char[]> myList = deepClone(mySeat);
        for (int i = 0; i < mySeat.size(); i++) {
            for (int j = 0; j < mySeat.get(i).length; j++) {
                switch (mySeat.get(i)[j]) {
                    case 'L' -> {
                        if (countAdjacentSeats(mySeat, i, j) == 0) {
                            myList.get(i)[j] = '#';
                        }
                    }
                    case '#' -> {
                        if (countAdjacentSeats(mySeat, i, j) >= 4) {
                            myList.get(i)[j] = 'L';
                        }
                    }
                }
            }
        }
        return myList;

    }

    /*
     * This method counts the number of adjacent seats at the position (row,col)
     */
    public static int countAdjacentSeats(List<char[]> list, int row, int col) {
        int num = 0;
        for (int i = Math.max(row - 1, 0); i <= row + 1 && i < list.size(); i++) {
            for (int j = Math.max(col - 1, 0); j <= col + 1 && j < list.get(i).length; j++) {
                if (!(i == row && j == col) && list.get(i)[j] == '#') {
                    num++;
                }
            }
        }
        return num;
    }

    /*
     * This method clones a List<char[]>
     */
    public static List<char[]> deepClone(List<char[]> oldList) {
        List<char[]> newList = new ArrayList<>();
        for (char[] array : oldList) {
            char[] characterArray = new char[array.length];
            System.arraycopy(array, 0, characterArray, 0, array.length);
            newList.add(characterArray);
        }
        return newList;
    }

    /*
     * This method checks if the rules cause no seats to change state
     */
    public static boolean changeState(List<char[]> oldList, List<char[]> newList) {
        for (int i = 0; i < oldList.size(); i++) {
            for (int j = 0; j < oldList.get(i).length; j++) {
                if (oldList.get(i)[j] != newList.get(i)[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * As soon as people start to arrive, you realize your mistake.
     * People don't just care about adjacent seats - they care about the first seat
     * they can see in each of those eight directions!
     *
     * Now, instead of considering just the eight immediately adjacent seats,
     * consider the first seat in each of those eight directions.
     * For example, the empty seat below would see eight occupied seats:
     *
     * .......#.
     * ...#.....
     * .#.......
     * .........
     * ..#L....#
     * ....#....
     * .........
     * #........
     * ...#.....
     *
     * The leftmost empty seat below would only see one empty seat,
     * but cannot see any of the occupied ones:
     *
     * .............
     * .L.L.#.#.#.#.
     * .............
     *
     * The empty seat below would see no occupied seats:
     *
     * .##.##.
     * #.#.#.#
     * ##...##
     * ...L...
     * ##...##
     * #.#.#.#
     * .##.##.
     *
     * Also, people seem to be more tolerant than you expected:
     * it now takes five or more visible occupied seats for an occupied seat to become empty
     * (rather than four or more from the previous rules).
     * The other rules still apply: empty seats that see no occupied seats become occupied,
     * seats matching no rule don't change, and floor never changes
     * Again, at this point, people stop shifting around and the seating area reaches equilibrium.
     * Once this occurs, you count 26 occupied seats.
     *
     * Given the new visibility method and the rule change for occupied seats becoming empty,
     * once equilibrium is reached, how many seats end up occupied?
     * /


    /*
     * This method counts the number of visibility seats
     */

    public static int countVisibleSeats(List<char[]> mySeats, int row, int col) {
        int count = 0;
        for (int dirX = -1; dirX <= 1; dirX++) {
            for (int dirY = -1; dirY <= 1; dirY++) {
                if (dirX == 0 && dirY == 0) {
                    continue;
                }
                int x = row + dirX;
                int y = col + dirY;
                while (x >= 0 && x < mySeats.size() && y >= 0 && y < mySeats.get(0).length) {
                    if (mySeats.get(x)[y] == 'L') {
                        break;
                    } else if (mySeats.get(x)[y] == '#') {
                        count++;
                        break;
                    }
                    x += dirX;
                    y += dirY;
                }
            }
        }
        return count;
    }

    public static List<char[]> checkRulesPartTwo(List<char[]> myList) {
        List<char[]> newList = deepClone(myList);
        for (int i = 0; i < myList.size(); i++) {
            for (int j = 0; j < myList.get(i).length; j++) {
                switch (myList.get(i)[j]) {
                    case 'L' -> {
                        if (countVisibleSeats(myList, i, j) != -1) {
                            newList.get(i)[j] = '#';
                        }
                    }
                    case '#' -> {
                        if (countVisibleSeats(myList, i, j) >= 5) {
                            newList.get(i)[j] = 'L';
                        }
                    }
                }
            }
        }
        return newList;
    }

    // This method executes the part Two of this exercise
    public static int runPartTwo(List<char[]> myList) {
        List<char[]> mySeats;
        do {
            mySeats = deepClone(myList);
            myList = checkRulesPartTwo(myList);
        } while (changeState(mySeats, myList));
        return countOccupiedSeats(myList);
    }


}
