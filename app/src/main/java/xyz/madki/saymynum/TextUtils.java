package xyz.madki.saymynum;

/**
 * Created by madki on 03/02/16.
 */
public final class TextUtils {

    private TextUtils() {
    }

    public static boolean isEmpty(String s) {
        return (s == null) || s.isEmpty();
    }

    /**
     * Capitalizes the first character of a words
     *
     * @param word variable word
     * @return word with first character capitalized
     */
    public static String getProperCaseForWord(String word) {
        if (word == null) return null;

        String firstChar = Character.toString(word.charAt(0));
        return word.replaceFirst(firstChar, firstChar.toUpperCase());
    }

    /**
     * Capitalizes first word of every sentence except for `except`
     * @param sentence sentence to be capitalized
     * @param except word to be excepted
     * @return capitalized sentence
     */
    public static String getProperCaseForSentence(String sentence, String except) {
        String[] words = sentence.split(" ");
        SpacedStringBuilder sb = new SpacedStringBuilder(sentence.length());
        for(String s: words) {
            if(!s.equals(except)) {
                sb.append(getProperCaseForWord(s));
            } else {
                sb.append(s);
            }
        }
        return sb.toString();
    }

}
