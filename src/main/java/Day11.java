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
 *
 * The seat layout fits neatly on a grid. Each position is either floor (.), an empty seat (L),
 * or an occupied seat (#). For example, the initial seat layout might look like this:
 *
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
 *
 *
 * Now, you just need to model the people who will be arriving shortly. Fortunately,
 * people are entirely predictable and always follow a simple set of rules.
 * All decisions are based on the number of occupied seats adjacent to a given seat
 * (one of the eight positions immediately up, down, left, right, or diagonal from the seat).
 * The following rules are applied to every seat simultaneously:
 *
 * If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
 * If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
 * Otherwise, the seat's state does not change.
 * Floor (.) never changes; seats don't move, and nobody sits on the floor.
 */

public class Day11 {

    public static void main(String[]args) throws IOException {
        final String fileName = "C:\\test.txt";

        List<String> inputFile;
        try (Stream<String> lines = Files.lines(Paths.get(fileName)))
        {
            inputFile = lines.collect(Collectors.toList());
        }
        List<char[]> myCharacterArrayList = convertListStringToListOfCharacterArray(inputFile);
     //   List<char[]> checkRulesList = checkRules(myCharacterArrayList);
     //   int result1 = countOccupiedSeats(checkRulesList);
     //   System.out.println("part One := " + result1);


    }
    private static List<char[]> convertListStringToListOfCharacterArray(List<String> stringList)
    {
        List<char[]> arrayListChar = new ArrayList<>();
        for(String str : stringList)
        {
            arrayListChar.add(str.toCharArray());
        }
        return arrayListChar;
    }

    private static int countOccupiedSeats(List<char[]> list)
    {
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

    private static List<char[]> checkRules(List<char[]> mySeat)
    {
        List<char[]> myList = new ArrayList<>(mySeat);
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

    private static int countAdjacentSeats(List<char[]> mySeat, int row,int col)
    {
        int count = 0;
        // check up
        if(row >= 0)
        {
            if((mySeat.get(row - 1)[col]) == '#')
            {
                if(mySeat.get(row)[col] == 'L')
                {
                    return 0;
                }
                else
                {
                    count++;
                }
            }
        }

        // check down
        if(row + 1 < mySeat.size() )
        {
            if((mySeat.get(row + 1)[col]) == '#')
            {
                if(mySeat.get(row)[col] == 'L')
                {
                    return 0;
                }
                else
                {
                    count++;
                }
            }
        }

        // Check left
        if(col - 1 >= 0 )
        {
            if((mySeat.get(row)[col - 1]) == '#')
            {
                if(mySeat.get(row)[col] == 'L')
                {
                    return 0;
                }
                else
                {
                    count++;
                }
            }
        }

        // Check right
        if(col + 1 < mySeat.get(0).length )
        {
            if((mySeat.get(row)[col + 1]) == '#')
            {
                if(mySeat.get(row)[col] == 'L')
                {
                    return 0;
                }
                else
                {
                    count++;
                }
            }
        }
        // Check diagonals
        if(row - 1 >= 0 && col - 1 >= 0 )
        {
            if((mySeat.get(row - 1)[col - 1]) == '#')
            {
                if(mySeat.get(row)[col] == 'L')
                {
                    return 0;
                }
                else
                {
                    count++;
                }
            }
        }
        if(((row + 1) < mySeat.size()) && ((col + 1 ) < mySeat.get(0).length))
        {
            if((mySeat.get(row + 1)[col + 1]) == '#')
            {
                if(mySeat.get(row)[col] == 'L')
                {
                    return 0;
                }
                else
                {
                    count++;
                }
            }
        }
        if(((row - 1) >= 0) && ((col + 1 ) < mySeat.get(0).length))
        {
            if((mySeat.get(row - 1)[col + 1]) == '#')
            {
                if(mySeat.get(row)[col] == 'L')
                {
                    return 0;
                }
                else
                {
                    count++;
                }
            }
        }
        if(((row + 1) < mySeat.size()) && ((col - 1 ) >= 0))
        {
            if((mySeat.get(row + 1)[col - 1]) == '#')
            {
                if(mySeat.get(row)[col] == 'L')
                {
                    return 0;
                }
                else
                {
                    count++;
                }
            }
        }
        return count;
    }
}
