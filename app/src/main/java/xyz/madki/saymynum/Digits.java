package xyz.madki.saymynum;

import java.util.Arrays;

/**
 * Created by madki on 03/02/16.
 * Class that converts strings with unicode numerals to human readable strings
 */
public class Digits {
    /**
     * Error message thrown by IllegalArgumentException when an invalid number is provided
     */
    public static final String ERROR_NAN = "NAN";
    /**
     * Error message thrown by IllegalArgumentException when number is too large
     */
    public static final String ERROR_NUM_TOO_LARGE = "NUM_TOO_LARGE";
    /**
     * each int in the array is a single digit numeric value.
     */
    private final int[] digits;
    /**
     * number of digits
     */
    private final int count;

    public Digits(String number) {
        validate(number);

        digits = charToIntArray(trimZeroes(number).toCharArray());
        count = digits.length;

        if ((count - 1) / 3 >= suffix.length) {
            throw new IllegalArgumentException(ERROR_NUM_TOO_LARGE);
        }
    }

    /**
     * @param index index at which the digit is required
     * @return the digit if index is valied, -1 otherwise
     */
    public int digitAt(int index) {
        if (index >= 0 && index < count) return digits[index];
        else return -1;
    }

    /**
     * @return comma separated digits for easy readability
     */
    public String prettify() {
        StringBuilder sb = new StringBuilder(count + suffix.length);
        for (int i = 0; i < count; i++) {
            sb.append(digitAt(i));
            if ((count - i - 1) % 3 == 0 && (count != (i + 1))) sb.append(",");
        }
        return sb.toString();
    }

    /**
     * @return digits as human readable text
     */
    public String asReadableText() {
        Block block;
        SpacedStringBuilder sb = new SpacedStringBuilder("");
        for (int i = (count - 1) % 3; i <= (count - 1); i = i + 3) {
            block = new Block(i);
            block.appendSelf(sb);
        }
        String text = sb.toString();
        return text.isEmpty() ? "zero" : text;
    }

    @Override
    public String toString() {
        return Arrays.toString(digits);
    }

    /**
     * convenience class that helps in grouping digits into blocks of max-size three
     * The block is identified by it's units place (endOfBlock) and contains digits at endOfBlock-1
     * and endOfBlock-2 at tens and hundreds places if available
     *
     * E.g if digits = {1,2,3,4,5}; {1,2} and {3,4,5} act as blocks with endOfBlock 1 and 4 resp.
     */
    class Block {
        /**
         * index of the units place of block in digits
         */
        private final int endOfBlock;

        Block(int endOfBlock) {
            this.endOfBlock = endOfBlock;
        }

        boolean isNullStr() {
            return (hundredsPlace() <= 0)
                    && (tensPlace() <= 0)
                    && (unitsPlace() == 0);
        }

        boolean isLastBlock() {
            return endOfBlock == count - 1;
        }

        int hundredsPlace() {
            return digitAt(endOfBlock - 2);
        }

        int tensPlace() {
            return digitAt(endOfBlock - 1);
        }

        int unitsPlace() {
            return digitAt(endOfBlock);
        }

        void appendSelf(SpacedStringBuilder sb) {
            appendHundredsPlace(sb);
            appendAnd(sb);
            appendLastTwoDigit(sb);
            appendBlockSuffix(sb);
        }

        void appendHundredsPlace(SpacedStringBuilder sb) {
            int digit = hundredsPlace();
            if (digit > 0) {
                sb.append(units[digit])
                        .append("hundred");
            }
        }

        void appendAnd(SpacedStringBuilder sb) {
            if(isLastBlock() && (tensPlace() > 0 || unitsPlace() > 0) && count > 2) {
                sb.append("and");
            }
        }

        void appendLastTwoDigit(SpacedStringBuilder sb) {
            if (tensPlace() == 1) {
                appendTeen(sb);
            } else {
                appendTensPlace(sb);
                appendUnitsPlace(sb);
            }
        }

        void appendBlockSuffix(SpacedStringBuilder sb) {
            if(!isNullStr()) sb.append(suffix[(count - endOfBlock)/3]);
        }

        void appendTeen(SpacedStringBuilder sb) {
            sb.append(teens[unitsPlace()]);
        }

        void appendTensPlace(SpacedStringBuilder sb) {
            int digit = tensPlace();
            if(digit > 1) sb.append(tens[digit]);
        }

        void appendUnitsPlace(SpacedStringBuilder sb) {
            int digit = unitsPlace();
            if(digit > 0) sb.append(units[digit]);
        }
    }


    /**
     * Checks if given string is a valid digit
     *
     * @param number string to be verified as Digits
     * @throws NullPointerException     if number is empty or null
     * @throws IllegalArgumentException if number contains chars other than numerals [0-9]
     */
    static void validate(String number) throws NullPointerException, IllegalArgumentException {
        if (TextUtils.isEmpty(number)) {
            throw new NullPointerException("Null string cannot be converted to Digits");
        }
        if (!isNumber(number)) {
            throw new IllegalArgumentException(ERROR_NAN);
        }
    }

    /**
     * Checks if given string consists only of unicode numbers [0-9]
     *
     * @param possibleNumber string to be tested
     * @return true if given string contains only [0-9]
     */
    static boolean isNumber(String possibleNumber) {
        return possibleNumber.matches("[0-9]+");
    }

    /**
     * Removes multiple zeroes at the start
     *
     * @param number the string whose repeating zeroes are to be removed
     * @return number with zeroes at the beginning removed, 0 if number contains all zeroes
     */
    static String trimZeroes(String number) {
        return number.replaceFirst("^0+(?!$)", "");
    }

    /**
     * Works only for unicode numbers [0-9] but has better performance. If non unicode chars
     * (ex: hindi numbers) are to be supported should use {@link Character#getNumericValue(char)}
     * instead of <code>c - '0'</code>
     * <p/>
     * This does not check whether it is input is a valid numeric char.
     *
     * @param c a single numeric digit
     * @return value of the digit
     */
    static int charToInt(char c) {
        return c - '0';
    }

    /**
     * This method does not check whether the given char array is a valid int array
     * Supports only unicode numbers [0-9]
     *
     * @param chars array of characters
     * @return array of ints
     */
    static int[] charToIntArray(char[] chars) {
        int[] ints = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            ints[i] = charToInt(chars[i]);
        }
        return ints;
    }

    /**
     * `n` in units digit coverts to `units[n]` in words
     */
    static final String[] units = {
            "",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine"
    };

    /**
     * `n` in tens digit converts to `tens[n]` in words
     */
    static final String[] tens = {
            "",
            "",
            "twenty",
            "thirty",
            "forty",
            "fifty",
            "sixty",
            "seventy",
            "eighty",
            "ninety"
    };

    /**
     * if tens digit has `1` and units digit has `n` then `1n` together coverts to `teens[n]` in words
     */
    static final String[] teens = {
            "ten",
            "eleven",
            "twelve",
            "thirteen",
            "fourteen",
            "fifteen",
            "sixteen",
            "seventeen",
            "eighteen",
            "nineteen"
    };

    /**
     * digits between [3n, 3n + 2] correspond to suffix[n]
     */
    static final String[] suffix = {
            "",
            "thousand",
            "million",
            "billion",
            "trillion",
            "quadrillion",
            "quintillion",
            "sextillion",
            "septillion",
            "octillion",
            "nonillion"
    };


}
