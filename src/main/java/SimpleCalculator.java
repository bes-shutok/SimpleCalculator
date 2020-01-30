import utils.RomanArabicConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class SimpleCalculator {
    static final String[] allowedOperations = {"+","-","*","/"};

    // Проверка на допустимость значений операндов
    public static int resolve(int a, int b, String op, String op1,String op2) {
        int result = 0;
        if (a > 10 ||  a < 0 || b >10 || b < 0) {
            System.out.println( "Как минимум один из указанных операндов '" + op1 + "' и '" + op2 + "' не " +
                    "соответствует исходным требованиям.");
            System.exit(0);
        }
        // выбор операции
        switch (op) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
        }
        return result;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Калькулятор для операций сложения, вычитания, умножения и деления с двумя целыми числами:" +
                " a + b, a - b, a * b, a / b.");
        System.out.println("Числа и знак операции должны быть разделены одним пробелом");
        System.out.println("Калькулятор умеет работать либо с арабскими (1,2,3,4,5…), либо с римскими (I,II,III," +
                "IV,V…) числами от 1 до 10 включительно (но не более).");
        System.out.println("Введите требуемую операцию");
        String[] request = br.readLine().split(" ");
        if (request.length != 3) {
            System.out.println("Количество аргументов не соответствует требованиям!");
            System.exit(0);
        }
        String a = request[0];
        String op = request[1];
        String b = request[2];

        // Операция должна быть одной из допустимых
        if (Arrays.stream(allowedOperations).anyMatch(op::equals) ) {
            // Все цифры римские?
            try {
                int intA = RomanArabicConverter.romanToArabic(a);
                int intB = RomanArabicConverter.romanToArabic(b);
                int result = resolve(intA,intB,op,a,b);
                if (result < 0) {
                    result = - result;
                    System.out.println("-" + RomanArabicConverter.arabicToRoman(result));
                } else {
                    System.out.println(RomanArabicConverter.arabicToRoman(result));
                }
            }
            catch(IllegalArgumentException e1) {
                // Все цифры арабсие?
                    try {
                        int intA = Integer.parseInt(a);
                        int intB = Integer.parseInt(b);
                        System.out.println(resolve(intA,intB,op,a,b));
                    }
                    catch(NumberFormatException e2) {
                        System.out.println( "Указанные операнды '" + a + "' и '" + b + "' не соответствуют " +
                            "исходным требованиям.");
                    }
            }

        } else {
            throw new IllegalArgumentException("'" + op + "' не является допустимой операцией. Пожалуйста используйте " +
                    "для расчетов следующие допустиые операции: " +
                    Arrays.stream(allowedOperations).collect(Collectors.joining(" , ")));
        }

    }
}
