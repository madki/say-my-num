package xyz.madki.saymynum;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by madki on 03/02/16.
 */
public class TestUtilsTests {

    @Test
    public void isEmpty() {
        assertTrue(TextUtils.isEmpty(""));
        assertTrue(TextUtils.isEmpty(null));
        assertFalse(TextUtils.isEmpty("a"));
    }

    @Test
    public void getProperCaseForWord() {
        assertEquals("Word", TextUtils.getProperCaseForWord("word"));
    }

    @Test
    public void getProperCaseForSentence() {
        assertEquals("Forty and Fifty", TextUtils.getProperCaseForSentence("forty and fifty", "and"));
    }

}
