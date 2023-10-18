public class PobitOper {

        public static void main(String[] args) {
            String numberA = "e035c6cfa42609b998b883bc1699df885cef74e2b2cc372eb8fa7e7";
            String numberB = "5072f028943e0fd5fab3273782de14b1011741bd0c5cd6ba6474330";
            String numberC = "";
            for (int i = 0; i < numberA.length(); i++) {
                int num = Convert.hexCharToDec(numberA.charAt(i)) ^ Convert.hexCharToDec(numberB.charAt((i)));
                numberC += Integer.toHexString(num);//Convert.intToString(num);
            }
            System.out.println(numberC);
            //b04736e73018066c620ba48b9447cb395df8355fbe90e194dc8e4d7
        }
//        public static void main(String[] args) {
//        int number = 5; // Бинарное представление: 00000101
//
//        int invertedNumber = ~number; // Выполняем побитовую инверсию
//
//        System.out.println("Исходное число: " + number);
//        System.out.println("Инвертированное число: " + invertedNumber);

    }

