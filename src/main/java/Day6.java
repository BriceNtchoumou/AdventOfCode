import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6 {

    public static void main(String[]args) throws IOException {
        final List<String> lines = Files.lines(Path.of("C:\\inputDay6.txt")).collect(Collectors.toUnmodifiableList());
     //   List<List<String>> linesList = lines.stream().;




     //   long result1 = partOne(linesList);
     //   System.out.println("part One := " + result1);
    }

    private static long countDistinct(List<String> answers) {
        return answers.stream().flatMapToInt(String::chars).distinct().count() ;
    }

    static long partOne(List<List<String>> groups) {
        return groups.stream().mapToLong(Day6::countDistinct).sum();
    }


}
