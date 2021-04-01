import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* Part One
 *  In this part the program counts the number of valid passports.
 *  The required fields for a passport are as follow:
 *  byr(Birth Year), iyr(Issue Year), eyr(Expiration Year),hgt(Height),hcl(Hair Color),ecl(Eye Color),
 *  pid(Passport ID), cid(Country ID)
 *  The passport is valid - all eight fields are present or if the only missing field is cid
 */

public class Day4 {

    private static int count1; // number of valid passports of part One
    private static int count2; // number of valid passports of part Two

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\inputDay4.txt"));
        String line = reader.readLine();
        // required fields for a valid passport
        List<String> requirements = Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");
        String passport = "";
        while (true) {
            if (line == null) {
                partOneCheck(requirements, passport);
                partTwoCheck(requirements, passport);
                break;
            }
            if (line.isEmpty()) {
                partOneCheck(requirements, passport);
                partTwoCheck(requirements, passport);
                passport = "";
            } else {
                passport += (line + " ");

            }
            line = reader.readLine();

        }
        reader.close();
        System.out.println("Part One: " + count1);
        System.out.println("Part Two: " + count2);
    }

    private static void partOneCheck(List<String> requirements, String passport) {
        String[] elements = passport.split(" ");
        boolean isValid = true;
        for (String requirement : requirements) {
            if (!contains(elements, requirement)) {
                isValid = false;
                break;
            }
        }
        count1 += isValid ? 1 : 0;
    }

    /*
     * We  can continue to ignore the cid field, but each other field has strict rules about
     * what values are valid for automatic validation:
     * byr (Birth Year) - four digits; at least 1920 and at most 2002.
     * iyr (Issue Year) - four digits; at least 2010 and at most 2020.
     * eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
     * hgt (Height) - a number followed by either cm or in:
     * If cm, the number must be at least 150 and at most 193.
     * If in, the number must be at least 59 and at most 76.
     * hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
     * ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
     * pid (Passport ID) - a nine-digit number, including leading zeroes.
     * cid (Country ID) - ignored, missing or not.
     * This part counts the passports where all required fields are both present
     * and valid according to the above rules
     */

    private static void partTwoCheck(List<String> requirements, String passport) {
        String[] elements = passport.split(" ");
        boolean isValid = true;
        for (String requirement : requirements) {
            if (!contains(elements, requirement)) {
                isValid = false;
                break;
            }
        }
        for (String element : elements) {
            if (!matches(element, element.substring(0, 3))) {
                isValid = false;
                break;
            }
        }
        count2 += isValid ? 1 : 0;
    }

    private static boolean contains(String[] array, String str) {
        for (String x : array) {
            if (x.substring(0, 3).equals(str)) {
                return true;
            }
        }
        return false;
    }

    private static boolean matches(String mapping, String str) {
        String value = mapping.substring(4);
        Set<Character> digits = new HashSet<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
        switch (str) {
            case "byr":
                int birth = Integer.parseInt(value);
                return birth >= 1920 && birth <= 2002;
            case "iyr":
                int issue = Integer.parseInt(value);
                return issue >= 2010 && issue <= 2020;
            case "eyr":
                int expiration = Integer.parseInt(value);
                return expiration >= 2020 && expiration <= 2030;
            case "hgt":
                if (value.length() <= 2) {
                    return false;
                }
                int height = Integer.parseInt(value.substring(0, value.length() - 2));
                if (value.endsWith("cm")) {
                    return height >= 150 && height <= 193;
                } else {
                    return height >= 59 && height <= 76;
                }
            case "hcl":
                Set<Character> valid = new HashSet<>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'));
                List<Character> chars = new ArrayList<>();
                for (char c : value.toCharArray()) {
                    chars.add(c);
                }
                if (value.length() != 7) {
                    return false;
                }
                if (chars.get(0) != '#') {
                    return false;
                }
                String x1 = value.substring(1);
                for (int i = 0; i < x1.length(); i++) {
                    if (!valid.contains(x1.charAt(i))) {
                        return false;
                    }
                }
                return true;
            case "ecl":
                return Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value);
            case "pid":
                for (char c : value.toCharArray()) {
                    if (!digits.contains(c)) {
                        return false;
                    }
                }
                return value.length() == 9;
            case "cid":
                return true;
            default:
                return false;
        }
    }

}
