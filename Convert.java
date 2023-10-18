public class Convert {
    private static String[] hexChars = new String[]{"a", "b", "c", "d", "e", "f"};
    private static int[] decInts = new int[]{10, 11, 12, 13, 14, 15};


    public static int hexCharToDec(char ch) {
        for (int i = 0; i < hexChars.length; i++)
            if (String.valueOf(ch).equals(hexChars[i])) return decInts[i];
        return Integer.parseInt(String.valueOf(ch));
    }

    public static String intToString(int num) {
        for (int i = 0; i < decInts.length; i++)
            if (num == decInts[i]) return hexChars[i];
        return String.valueOf(num);
    }

    public static String decToBin(int dec) {
        String bin = Integer.toBinaryString(dec);
        while (bin.length() < 4) bin = 0 + bin;
        return bin;
    }

    public static String invertBin(String binNum) {
        String invert = "";
        for (int i = 0; i < binNum.length(); i++)
            invert += binNum.charAt(i) == '1' ? 0 : 1;
        return invert;
    }
}