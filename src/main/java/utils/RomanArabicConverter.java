package utils;

import java.util.List;

public class RomanArabicConverter {

    public static List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

    /**
     * Конвертер римского числа в арабское
     * Thanks to https://www.baeldung.com/java-convert-roman-arabic
     *
     * @param input String
     * @return int
     */

    public static int romanToArabic(String input) {

        // Исходная строчка с римскими числами в верхнем регистре
        String romanNumeral = input.toUpperCase();
        int result = 0;
        // Вспомогательный лист для поиска наибольшего римского числа

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            // берем следующее наибольшее римское число
            RomanNumeral symbol = romanNumerals.get(i);

            // если нашли римское число
            if (romanNumeral.startsWith(symbol.name())) {
                // добавляем его значение
                result += symbol.getValue();
                // и берем остаток исходной строчки
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                // иначе переходим к следующему наибольшему римскому числу
                i++;
            }
        }
        // Если все римские числа перебраны и все еще что то осталось - бросаем ошибку кончертации
        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }

    /**
     * Конвертер арабского числа в римское
     * Thanks to https://www.baeldung.com/java-convert-roman-arabic
     *
     * @param number int
     * @return String
     */

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " is not in range (0,4000]");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}
