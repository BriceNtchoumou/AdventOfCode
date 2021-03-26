import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */


public class Day2 {
    public static void main(String [] args) throws IOException {


        // Read the file into an ArrayList

         Charset charset = Charset.forName("ISO-8859-1");
          List<String> result = Files.readAllLines(Paths.get("C:\\inputDay2.txt"), charset);
          List<Password> passwords = new ArrayList<>();

          for( String s : result)
          {
              String[] full = s.split(" ");
              String[] numbers = full[0].split("-");
              int min = Integer.parseInt(numbers[0]);
              int max = Integer.parseInt(numbers[1]);
              char character = full[1].charAt(0);
              String passcode = full[2];
              passwords.add(new Password(passcode,character,min,max));
          }
        System.out.println("Part One: " + PartOne(passwords));
          System.out.println("Part Two: " + PartTwo(passwords));

    }
   /* Part One
    *
    */
   public static int PartOne(List<Password> passwords)
   {
       int ergebnis = 0; // gibt die Anzahl der gültigen Passworte
       for(Password pass : passwords)
       {
           String p = pass.passcode;
           int count = 0; // gibt die Anzahl der Passworte
           for(char c: p.toCharArray())
           {
               if(pass.c  == c)
               {
                   count++;
               }
           }
           if(count >= pass.low && count <= pass.high)
           {
               ergebnis++;
           }
       }
       return ergebnis;

   }

   /* Part Two
    *
    */

   public static int PartTwo(List<Password> passwords)
   {
       int ergebnis = 0; // gibt die Anzahl der gültigen Passworte

       for(Password pass : passwords)
       {
           String p = pass.passcode;
           if(p.charAt(pass.low - 1) == pass.c && p.charAt(pass.high - 1) != pass.c)
           {
               ergebnis++;
           }
           else if(p.charAt(pass.high - 1) == pass.c && p.charAt(pass.low - 1) != pass.c)
           {
               ergebnis++;
           }
       }


       return ergebnis;

   }

    public static class Password
    {
        private final String passcode;
        private final char c;
        private final int low;
        private final int high;

        public Password(String passcode,char c, int low,int high)
        {
            this.passcode = passcode;
            this.c = c;
            this.low = low;
            this.high = high;
        }

    }


}
