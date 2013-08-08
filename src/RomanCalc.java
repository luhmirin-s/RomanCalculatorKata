import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 "As a Roman Bookkeeper I want to add Roman numbers because doing it manually is too tedious."
 Given the Roman numerals,
 (IVXLCDM which means one, five, ten, fifty, hundred, fivehundred and a thousand respectively),
 create two numbers and add them. As we are in Rome there is no such thing as decimals or int,
 we need to do this with the strings. An example would be "XIV" + "LX" = "LXXIV"

 There are some rules to a Roman number:
 Numerals can be concatenated to form a larger numeral ("XX" + "II" = "XXII")
 If a lesser numeral is put before a bigger it means
 subtraction of the lesser from the bigger ("IV" means four, "CM" means ninehundred)
 If the numeral is I, X or C you can't have more than three ("II" + "II" = "IV")
 If the numeral is V, L or D you can't have more than one ("D" + "D" = "M")

 Clues:
 String grouping and concatenation is key to solving this kata.
 But remember the rule that lesser numerals can preceede bigger ones.

 Some reference:
 http://turner.faculty.swau.edu/mathematics/materialslibrary/roman/
 */
public class RomanCalc {

    /**
     * These are roman numerals, folks.
     * in list to get indexOf() for sorting.
     */
    private static final List<Character> NUMBERS =
            new ArrayList<Character>(Arrays.asList('I','V','X','L','C','D','M'));

    /**
     *  List of combination that cat be replaced with one number.
     */
    private static final String[][] REPLACEMENTS = { {"IIIII", "VV", "XXXXX", "LL", "CCCCC", "DD"},
                                                   {"V",     "X",  "L",     "C",  "D",     "M" }};

    /**
     *  List of combination where smaller number goes before larger.
     */
    private static final String[][] SHORTENING = {{ "VIIII", "IIII", "LXXXX", "XXXX", "DCCCC",  "CCCC"},
                                                   { "IX",    "IV",   "XC",    "XL",   "CM",     "CD"  }};


    /**
     The algorithm has just five steps:

     1) Substitute for any subtractives in both values;
     2) Put the two values together—catenate them.
     3) Sort the symbols in order from left-to-right with the "largest" symbols on the left.
     4) Starting with the right end, combine groups of the same symbols that can make a "larger" one.
     5) Compact the result by substituting subtractives where possible.

     * @param first - roman numeral
     * @param second - roman numeral
     * @return - sum of those numerals
     */
    public static String add(String first, String second) {
        String result = expand(first) + expand(second); // Steps 1 and 2
        result = sort(result);                          // Step 3
        result = group(result);                         // Step 4
        result = shorten(result);                       // Step 5
        return result;
        // Or in a more awesome way:
        //return shorten(group(sort(expand(first) + expand(second))));
    }

    /**
     * Simplest possible sorting.
     * It's effective enough, because numbers are usually short.
     * @param unsortedNumbers - String with unsorted Symbols.
     * @return - sorted string.
     */
    private static String sort(String unsortedNumbers) {
        char[] data = unsortedNumbers.toCharArray();
        for (int i=0; i < data.length - 1; i++) {
            for (int j=i+1; j < data.length; j++) {
                if (NUMBERS.indexOf(data[i]) < NUMBERS.indexOf(data[j])) {
                    char tmp = data[i];
                    data[i] = data[j];
                    data[j] = tmp;
                }
            }
        }
        String result = "";
        for (char c: data){
            result += c;
        }
        return result;
    }

   private static String expand(String number) {
        for(int i=0; i < SHORTENING[0].length; i++) {
            number = number.replace(SHORTENING[1][i], SHORTENING[0][i]);
        }
        return number;
    }

    private static String group(String number) {
        for(int i=0; i < REPLACEMENTS[0].length; i++) {
            number = number.replace(REPLACEMENTS[0][i], REPLACEMENTS[1][i]);
        }
        return number;
    }

    private static String shorten(String number) {
        for(int i=0; i < SHORTENING[0].length; i++) {
            number = number.replace(SHORTENING[0][i], SHORTENING[1][i]);
        }
        return number;
    }

}
