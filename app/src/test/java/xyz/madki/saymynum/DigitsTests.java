package xyz.madki.saymynum;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by madki on 03/02/16.
 */
public class DigitsTests {
    @Test
    public void charToInt() {
        assertEquals(0, Digits.charToInt('0'));
        assertEquals(1, Digits.charToInt('1'));
        assertEquals(2, Digits.charToInt('2'));
        assertEquals(3, Digits.charToInt('3'));
        assertEquals(4, Digits.charToInt('4'));
        assertEquals(5, Digits.charToInt('5'));
        assertEquals(6, Digits.charToInt('6'));
        assertEquals(7, Digits.charToInt('7'));
        assertEquals(8, Digits.charToInt('8'));
        assertEquals(9, Digits.charToInt('9'));
    }

    @Test
    public void trimZeroes() {
        assertEquals("0", Digits.trimZeroes("00"));
        assertEquals("1", Digits.trimZeroes("01"));
        assertEquals("20300", Digits.trimZeroes("0020300"));
        assertEquals("12456", Digits.trimZeroes("000012456"));
    }

    @Test
    public void isNumber() {
        assertTrue(Digits.isNumber("001234"));
        assertFalse(Digits.isNumber("00a1234"));
    }

    @Test
    public void prettyPrint() {
        assertEquals("1,234", new Digits("1234").prettify());
        assertEquals("234", new Digits("234").prettify());
        assertEquals("123,499", new Digits("123499").prettify());
        assertEquals("1,000,000", new Digits("1000000").prettify());
    }

    @Test
    public void asReadableString() {
        assertEquals("one", new Digits("1").asReadableText());
        assertEquals("twenty one", new Digits("21").asReadableText());
        assertEquals("one hundred and five", new Digits("105").asReadableText());
        assertEquals("forty five million and fifty", new Digits("45000050").asReadableText());
        assertEquals("three hundred forty five thousand and twenty",
                new Digits("345020").asReadableText());
        assertEquals("Nine Hundred Ninety Nine Billion One Hundred Twenty Three Million Four Hundred Fifty Six Thousand One Hundred and Twenty Three".toLowerCase(),
                new Digits("999123456123").asReadableText());
        assertEquals("Ninety Nine Nonillion Nine Hundred Twelve Octillion Three Hundred Forty Five Septillion Six Hundred Twelve Sextillion Three Hundred Eighty Eight Quintillion Eight Hundred Twenty One Quadrillion Twenty Two Trillion One Hundred Ninety Eight Billion One Hundred Twenty Nine Million Eight Hundred Twelve Thousand Nine Hundred and Three".toLowerCase(),
                new Digits("99912345612388821022198129812903").asReadableText());

    }
}
