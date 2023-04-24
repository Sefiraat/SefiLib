package dev.sefiraat.sefilib.number;

import java.util.TreeMap;

public class RomanNumeral {

    private static final TreeMap<Integer, String> NUMERAL_MAP = new TreeMap<>();

    static {
        NUMERAL_MAP.put(0, "0");
        NUMERAL_MAP.put(1, "I");
        NUMERAL_MAP.put(4, "IV");
        NUMERAL_MAP.put(5, "V");
        NUMERAL_MAP.put(9, "IX");
        NUMERAL_MAP.put(10, "X");
        NUMERAL_MAP.put(40, "XL");
        NUMERAL_MAP.put(50, "L");
        NUMERAL_MAP.put(90, "XC");
        NUMERAL_MAP.put(100, "C");
        NUMERAL_MAP.put(400, "CD");
        NUMERAL_MAP.put(500, "D");
        NUMERAL_MAP.put(900, "CM");
        NUMERAL_MAP.put(1000, "M");
    }

    public static String getFromInt(int value) {
        int floor = NUMERAL_MAP.floorKey(value);
        String numeral = NUMERAL_MAP.get(floor);
        int diff = value - floor;
        if (diff > 0) {
            numeral = numeral + getFromInt(diff);
        }
        return numeral;
    }
}
