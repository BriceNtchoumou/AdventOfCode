import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/** @version 1.0  22.03.2021
 * @author Brice Gaetan
 * This Program is divided in two parts.
 * In the first part, the program find in a file two entries a und b such that their sum is 2020
 * and then multiply those two numbers together.
 * In the second part, the program find in the same file three entries x, y and z that their sum
 * is 2020 and then multiply those three number together
 */

public class Day1 {


    public static void main(String[]args) throws IOException {

        /**
         *  Part One
         */
        // Read the file into an ArrayList

       // Charset charset = Charset.forName("ISO-8859-1");
       //  List<String> result = Files.readAllLines(Paths.get("C:\\inputDay1.txt"), charset);

        final List<String> lines = Files.lines(Path.of("C:\\inputDay1.txt")).collect(Collectors.toUnmodifiableList());

        // transform the String List in an Integer List

        List<Integer> integerList = lines.stream()
                .map(Integer::valueOf).collect(Collectors.toList());


        // Find in the Integer List the two entries such that their sum is 2020 and multiply those
        // number together

        List<Integer> linesWithSum2020 = findLinesWithSum2020(integerList);
        if (linesWithSum2020.size() == 2) {
            System.out.printf(" a := %d , b := %d. %n", linesWithSum2020.get(0), linesWithSum2020.get(1));
            System.out.printf(" a * b := %d.%n", linesWithSum2020.get(0) * linesWithSum2020.get(1));
        }


        /** Part two
         *  The program will find in the same list three entries that the sum is 2020
         *  and then multiply those numbers together
         */

        for( int x : integerList)
        {
            for(int y : integerList)
            {
                for(int z : integerList){

                    if((x + y + z)== 2020)
                    {
                        System.out.printf(" x := %d , y := %d , z := %d.%n",x,y,z);
                        System.out.printf(" x * y * z = %d.%n",x * y * z);
                    }

                }
            }
        }

    }

    public static List<Integer> findLinesWithSum2020(List<Integer> integerList) {
        for( int a : integerList)
        {
            for(int b : integerList)
            {
                if(a + b == 2020)
                {
                    return List.of(a, b);
                }
            }
        }
        return new ArrayList<>();
    }

}
