import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Day6 {

    public static void main(String[]args) throws IOException {
       final List<String> lines = Files.lines(Path.of("C:\\inputDay6.txt")).collect(Collectors.toUnmodifiableList());

       List<List<String>> linesStr = new ArrayList<>();
       List<String> tempList = new ArrayList<>();
       for(String items : lines) {
           if (items.equals("")) {
               linesStr.add(tempList);
               tempList = new ArrayList<>();
           } else {
               tempList.add(items);
           }
       }
       if(!tempList.isEmpty())
       {
           linesStr.add(tempList);
       }


        long result1 = part1(linesStr);
       System.out.println("part One := " + result1);
       long result2 = part2(linesStr);
       System.out.println(" part Two := " + result2);

       }


    /** Part One
     * The form asks a series of 26 yes-or-no questions marked a through z. All you need to do is identify the questions for which anyone in your group answers "yes".
     * Since your group is just you, this doesn't take very long.
     *
     * However, the person sitting next to you seems to be experiencing a language barrier and asks if you can help.
     * For each of the people in their group, you write down the questions for which they answer "yes", one per line. For example:
     *
     * abcx
     * abcy
     * abcz
     *
     * In this group, there are 6 questions to which anyone answered "yes": a, b, c, x, y, and z.
     * (Duplicate answers to the same question don't count extra; each question counts at most once.)
     *
     * Another group asks for your help, then another, and eventually you've collected answers from
     * every group on the plane (your puzzle input). Each group's answers are separated by a blank line,
     * and within each group, each person's answers are on a single line. For example:
     *
     * abc
     *
     * a
     * b
     * c
     *
     * ab
     * ac
     *
     * a
     * a
     * a
     * a
     *
     * b
     * This list represents answers from five groups:
     *
     * The first group contains one person who answered "yes" to 3 questions: a, b, and c.
     * The second group contains three people; combined, they answered "yes" to 3 questions: a, b, and c.
     * The third group contains two people; combined, they answered "yes" to 3 questions: a, b, and c.
     * The fourth group contains four people; combined, they answered "yes" to only 1 question, a.
     * The last group contains one person who answered "yes" to only 1 question, b.
     * In this example, the sum of these counts is 3 + 3 + 3 + 1 + 1 = 11.
     */




    static long part1(List<List<String>> groups) {
        return groups.stream().mapToLong(Day6::countDistinct).sum();
    }

    private static long countDistinct(List<String> answers) {
        return answers.stream().flatMapToInt(String::chars).distinct().count();
    }

    // Part Two

    /** Part Two
     *  As you finish the last group's customs declaration, you notice that you misread one word in the instructions:
     *
     * You don't need to identify the questions to which anyone answered "yes"; you need to identify the questions to which everyone answered "yes"!
     *
     * Using the same example as above:
     *
     * abc
     *
     * a
     * b
     * c
     *
     * ab
     * ac
     *
     * a
     * a
     * a
     * a
     *
     * b
     *
     *  This list represents answers from five groups:
     *
     * In the first group, everyone (all 1 person) answered "yes" to 3 questions: a, b, and c.
     * In the second group, there is no question to which everyone answered "yes".
     * In the third group, everyone answered yes to only 1 question, a. Since some people did not answer "yes" to b or c, they don't count.
     * In the fourth group, everyone answered yes to only 1 question, a.
     * In the fifth group, everyone (all 1 person) answered "yes" to 1 question, b.
     * In this example, the sum of these counts is 3 + 0 + 1 + 1 + 1 = 6.
     *
     *
     */
    static long part2(List<List<String>> groups) {
        return groups.stream().mapToLong(Day6::countDistinctExcludeDuplicates).sum();
    }

    private static long countDistinctExcludeDuplicates(List<String> answers) {
        Set<Character> distinctAnswers = new HashSet<>(answers.get(0).chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toUnmodifiableSet()));

        for (int i = 1; i < answers.size(); i++) {
            distinctAnswers.retainAll(answers.get(i).chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.toUnmodifiableSet()));
        }

        return distinctAnswers.size();
    }





    }





