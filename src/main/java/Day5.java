import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * You write a quick program to use your phone's camera to scan all of the nearby boarding passes (your puzzle input);
 * perhaps you can find your seat through process of elimination.
 *Instead of zones or groups, this airline uses binary space partitioning to seat people.
 *  A seat might be specified like FBFBBFFRLR, where F means "front", B means "back", L means "left",
 *  and R means "right".
 *
 */


public class Day5 {



    public static void main(String[] args) throws IOException {
        final List<String> lines = Files.lines(Path.of("C:\\inputDay5.txt")).collect(Collectors.toUnmodifiableList());
        int result1 = partOne(lines);
        System.out.println(" Part One  := " + result1);
        int result2 = partTwo(lines);
        System.out.println(" Part Two := " + result2);

    }

    static int partOne(List<String> codes) {
        return getHighestSeatID(codes.stream().map(Day5::computeSeatNumber).collect(Collectors.toUnmodifiableSet()));
    }

    /**It's a completely full flight, so your seat should be the only missing boarding pass in your list.
     * However, there's a catch: some of the seats at the very front and back of the plane
     * don't exist on this aircraft, so they'll be missing from your list as well.
     * Your seat wasn't at the very front or back, though; the seats with IDs +1 and -1
     * from yours will be in your list.
     *
     */

    static int partTwo(List<String> codes) {
        Set<Integer> seats = codes.stream().map(Day5::computeSeatNumber).collect(Collectors.toUnmodifiableSet());
        int max = getHighestSeatID(seats);

        int result = 0;
        for (int i = 0; i < max; i++) {
            /**
            if (!seats.contains( i)) {
                result = i;

            }*/
            if(seats.contains(i))
            {
                continue;

            }
            result = i;
        }
        return result;
    }

    /**
     * The first 7 characters will either be F or B; these specify exactly one of the 128 rows on the plane
     * (numbered 0 through 127). Each letter tells you which half of a region the given seat is in.
     * Start with the whole list of rows; the first letter indicates whether the seat is in the front (0 through 63)
     * or the back (64 through 127).
     * The next letter indicates which half of that region the seat is in,
     * and so on until you're left with exactly one row.
     */

    private static int computeRowNumber(String code) {
        int minRowNumber = 0;
        int maxRowNumber = 127;
        int row = 0;

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            switch (c) {
                case 'F': {
                    maxRowNumber = minRowNumber + (maxRowNumber - minRowNumber) / 2;
                    row = maxRowNumber;
                    break;
                }
                case 'B': {
                    minRowNumber = minRowNumber + (maxRowNumber - minRowNumber) / 2 + 1;
                    row = minRowNumber;
                    break;
                }
                default:
                    throw new IllegalStateException("Invalid character: " + c);
            }
        }

        return row;
    }

    /**
     * The last three characters will be either L or R; these specify exactly one of the 8 columns of seats
     * on the plane (numbered 0 through 7). The same process as above proceeds again, this time with only three steps.
     * L means to keep the lower half, while R means to keep the upper half.
     */

    private static int computeColumnNumber(String code) {
        int minColNumber = 0;
        int maxColNumber = 7;
        int col = 0;

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            switch (c) {
                case 'L': {
                    maxColNumber = minColNumber + (maxColNumber - minColNumber) / 2;
                    col = maxColNumber;
                    break;
                }
                case 'R': {
                    minColNumber = minColNumber + (maxColNumber - minColNumber) / 2 + 1;
                    col = minColNumber;
                    break;
                }
                default:
                    throw new IllegalStateException("Invalid character: " + c);
            }
        }

        return col;
    }

    /**
     * Every seat also has a unique seat ID: multiply the row by 8, then add the column
     */

    private static int computeSeatNumber(String code) {
        int row = computeRowNumber(code.substring(0, 7));
        int column = computeColumnNumber(code.substring(7));

        return row * 8 + column;
    }
    /**
     * Compute the highest seat ID on the boarding pass
     */
    private static int getHighestSeatID(Set<Integer>seats)
    {
        return  seats.stream().mapToInt(i->i).max().orElseThrow();
    }

}
