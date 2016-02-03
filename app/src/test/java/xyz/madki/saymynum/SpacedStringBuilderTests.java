package xyz.madki.saymynum;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by madki on 03/02/16.
 */
public class SpacedStringBuilderTests {

    @Test
    public void string() {
        assertEquals("one hundred and fifty", new SpacedStringBuilder("")
                .append("one").append("hundred").append("and").append("fifty").toString());
    }
}
