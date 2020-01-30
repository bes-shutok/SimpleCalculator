import org.junit.jupiter.api.Test;
import utils.RomanArabicConverter;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculatorTest {

    @Test
    public void given2018Roman_WhenConvertingToArabic_ThenReturn2018() {
        String roman2018 = "MMXVIII";

        int result = RomanArabicConverter.romanToArabic(roman2018);
        assertEquals(2018, result);
    }

    @Test
    public void given1999Arabic_WhenConvertingToRoman_ThenReturnMCMXCIX() {
        int arabic1999 = 1999;

        String result = RomanArabicConverter.arabicToRoman(arabic1999);

        assertEquals("MCMXCIX", result);
    }
}

