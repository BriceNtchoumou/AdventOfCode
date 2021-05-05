import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Due to recent aviation regulations, many rules (your puzzle input) are being enforced about bags and
 * their contents; bags must be color-coded and must contain specific quantities of other color-coded bags.
 * Apparently, nobody responsible for these regulations considered how long they would take to enforce!
 *
 * light red bags contain 1 bright white bag, 2 muted yellow bags.
 * dark orange bags contain 3 bright white bags, 4 muted yellow bags.
 * bright white bags contain 1 shiny gold bag.
 * muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
 * shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
 * dark olive bags contain 3 faded blue bags, 4 dotted black bags.
 * vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
 * faded blue bags contain no other bags.
 * dotted black bags contain no other bags.
 *
 *
 * These rules specify the required contents for 9 bag types. In this example,
 * every faded blue bag is empty, every vibrant plum bag contains 11 bags
 * (5 faded blue and 6 dotted black), and so on.
 *
 * You have a shiny gold bag. If you wanted to carry it in at least one other bag,
 * how many different bag colors would be valid for the outermost bag? (In other words:
 * how many colors can, eventually, contain at least one shiny gold bag?)
 *
 * In the above rules, the following options would be available to you:
 *
 * A bright white bag, which can hold your shiny gold bag directly.
 * A muted yellow bag, which can hold your shiny gold bag directly, plus some other bags.
 * A dark orange bag, which can hold bright white and muted yellow bags,
 * either of which could then hold your shiny gold bag.
 * A light red bag, which can hold bright white and muted yellow bags,
 * either of which could then hold your shiny gold bag.
 *
 * So, in this example, the number of bag colors that can eventually
 * contain at least one shiny gold bag is 4.
 *
 * How many bag colors can eventually contain at least one shiny gold bag?
 */


public class Day7 {

    public static void main(String[]args) throws IOException
    {
        final String fileName = "C:\\Users\\pontd\\IdeaProjects\\AdventOfCode\\src\\main\\resources\\test.txt";
        // final String fileName = "C:\\Users\\pontd\\IdeaProjects\\AdventOfCode\\src\\main\\resources\\inputDay7.txt";
        List<String> inputFile;
        try (Stream<String> lines = Files.lines(Paths.get(fileName)))
        {
            inputFile = lines.collect(Collectors.toList());
        }
        HashMap<String,ArrayList<String>> containedBy = parseContained((ArrayList<String>) inputFile );

        int result1 = part1(containedBy, "shiny gold");

        System.out.println("part One : " + result1);


    }

    public static HashMap<String,ArrayList<String>> parseContained (ArrayList<String> inputFile)
    {
        HashMap<String,ArrayList<String>> rules = new HashMap<>();

        for(String input : inputFile)
        {
            String[] pieces = input.split(" bags contain ");
            String outerBag = pieces[0];
            String[] innerBags = pieces[1].split(" bags?(,|\\.)\\s?");
            ArrayList<String> parsedInnerBags = new ArrayList<>();
            for(String innerBag : innerBags)
            {
                String[]pieces2 = innerBag.split(" ");
                if(pieces2[0].equals("no"))
                {
                    continue;
                }
                parsedInnerBags.add(pieces2[1] + " " + pieces2[2]);
            }
            for(String parsedInnerBag : parsedInnerBags)
            {
                ArrayList<String> possibleOuterBags = rules.get(parsedInnerBag);
                possibleOuterBags = possibleOuterBags == null ? new ArrayList<>() : possibleOuterBags;
                if(!possibleOuterBags.contains(outerBag))
                {
                    possibleOuterBags.add(outerBag);
                    rules.put(parsedInnerBag,possibleOuterBags);
                }
            }
        }
        return rules;
    }



    public static int part1(HashMap<String,ArrayList<String>> rules, String bag)
    {
        HashMap<String,Integer> visited = new HashMap<>();
        ArrayDeque<String> toInvestigate = new ArrayDeque<>();
        toInvestigate.add(bag);
        while(toInvestigate.size() > 0)
        {
            String s = toInvestigate.remove();
            if(rules.get(s) == null)
            {
                continue;
            }
            for(String outerBag : rules.get(s))
            {
                if(visited.get(outerBag) == null)
                {
                    visited.put(outerBag,1);
                    toInvestigate.add(outerBag);
                }
            }
        }
        return visited.keySet().size();
    }


}
