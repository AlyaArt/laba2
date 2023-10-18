public class Primer {
    public static void main(String[] args) {
        int decimalNumber = 5; // Ваше десятичное число

        String binaryNumber = Integer.toBinaryString(decimalNumber);

        System.out.println("Десятичное число: " + decimalNumber);
        System.out.println("Двоичное представление: " + binaryNumber);
    }
}