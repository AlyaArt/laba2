public class Main {

    public static void main(String[] args) {
        MyBigInt numberA = new MyBigInt();
        numberA.setHex("51bf608414ad5726a3c1bec098f77b1b54ffb2787f8d528a74c1d7fde6470ea4");
        MyBigInt numberB = new MyBigInt("403db8ad88a3932a0b7e8189aed9eeffb8121dfac05c3512fdb396dd73f6331c");
        MyBigInt numberC = XOR(numberA, numberB);
        //a78865c13b14ae4e25e90771b54963ee2d68c0a64d4a8ba7c6f45ee0e9daa65b
//        System.out.println(ADD(new MyBigInt("36f028580bb02cc8272a9a020f4200e346e276ae664e45ee80745574e2f5ab80"),
//                new MyBigInt("70983d692f648185febe6d6fa607630ae68649f7e6fc45b94680096c06e4fadb")).getHex());
//        System.out.println(SUB(new MyBigInt("33ced2c76b26cae94e162c4c0d2c0ff7c13094b0185a3c122e732d5ba77efebc"),
//                new MyBigInt("22e962951cb6cd2ce279ab0e2095825c141d48ef3ca9dabf253e38760b57fe03")).getHex());
        System.out.println(ADD(new MyBigInt("8"), new MyBigInt("c")).getHex());
    }

    //XOR (побітове виключне або)
    private static MyBigInt XOR(MyBigInt number1, MyBigInt number2) {
        int[] num1 = number1.myBigInt;
        int[] num2 = number2.myBigInt;
        int[][] alignArrays = alignArraysLength(num1, num2);
        int[] result = new int[alignArrays[0].length];
        for (int i = 0; i < result.length; i++) {
            result[i] = alignArrays[0][i] ^ alignArrays[1][i];
        }
        return new MyBigInt(result);
    }

    private static MyBigInt OR(MyBigInt number1, MyBigInt number2) {
        int[] num1 = number1.myBigInt;
        int[] num2 = number2.myBigInt;
        int[][] alignArrays = alignArraysLength(num1, num2);
        int[] result = new int[alignArrays[0].length];
        for (int i = 0; i < result.length; i++) {
            result[i] = alignArrays[0][i] | alignArrays[1][i];
        }
        return new MyBigInt(result);
    }

    private static MyBigInt AND(MyBigInt number1, MyBigInt number2) {
        int[] num1 = number1.myBigInt;
        int[] num2 = number2.myBigInt;
        int[][] alignArrays = alignArraysLength(num1, num2);
        int[] result = new int[alignArrays[0].length];
        for (int i = 0; i < result.length; i++) {
            result[i] = alignArrays[0][i] & alignArrays[1][i];
        }
        return new MyBigInt(result);
    }

    private static MyBigInt INV(MyBigInt number) {
        int[] result = new int[number.getNumberLength()];
        for (int i = 0; i < number.getNumberLength(); i++) {
            String bin = Integer.toBinaryString(number.myBigInt[i]);
            bin = addZero(bin);
            result[i] = inverse(bin);
        }
        return new MyBigInt(result);
    }

    private static int inverse(String binNum) {
        String inversedNum = "";
        for (int i = 0; i < binNum.length(); i++) {
            inversedNum += binNum.charAt(i) == '1' ? 0 : 1;
        }
        return Integer.parseInt(inversedNum, 2);
    }

    private static int[][] alignArraysLength(int[] arr1, int[] arr2) {
        int[] result;
        int delta = arr1.length - arr2.length;
        if (delta == 0) return new int[][]{arr1, arr2};
        if (delta > 0) {
            result = new int[arr1.length];
            for (int i = delta; i < arr1.length; i++) {
                result[i] = arr2[i - delta];
            }
            return new int[][]{arr1, result};
        } else {
            result = new int[arr1.length];
            for (int i = delta; i < arr2.length; i++) {
                result[i] = arr1[i - delta];
            }
            return new int[][]{result, arr2};
        }
    }

    //INV (побітова інверсія)
    private static int IN(char ch1, char ch2) {

        return Integer.parseInt(String.valueOf(ch1), 16) ^
                Integer.parseInt(String.valueOf(ch2), 16);
    }

    public static String addZero(String str) {
        while (str.length() < 4) {
            str = 0 + str;
        }
        return str;
    }

    private static MyBigInt shiftR(MyBigInt number, int shift) {
        int numBits = 4;
        String bits = number.toBinaryString();
        String shiftBits = bits.substring(shift) + bits.substring(0, shift);
        return new MyBigInt(bitsStringToIntArray(shiftBits, numBits));
    }

    private static MyBigInt shiftL(MyBigInt number, int shift) {
        int numBits = 4;
        String bits = number.toBinaryString();
        String shiftBits = bits.substring(bits.length() - shift) + bits.substring(0, bits.length() - shift);
        return new MyBigInt(bitsStringToIntArray(shiftBits, numBits));
    }

    private static int[] bitsStringToIntArray(String bitsString, int numBits) {
        int[] nums = new int[bitsString.length() / numBits];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt((bitsString.substring(i * numBits, (i + 1) * numBits)), 2);
        }
        return nums;
    }

    private static MyBigInt ADD(MyBigInt number1, MyBigInt number2) {
        int[] num1 = number1.myBigInt;
        int[] num2 = number2.myBigInt;
        int[][] alignArrays = alignArraysLength(num1, num2);
        int[] result = new int[alignArrays[0].length];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = alignArrays[0][i] + alignArrays[1][i];
            if (result[i] > 15) {
                result[i] -= 16;
                if (i != 0) {
                    alignArrays[0][i - 1] += 1;
                } else {
                    result = increaseArrayLength(result);
                }
            }
        }
        return new MyBigInt(result);
    }

    private static int[] increaseArrayLength(int[] arr) {
        int[] newArr = new int[arr.length + 1];
        newArr[0] = 1;
        for (int i = 0; i < arr.length; i++) {
            newArr[i + 1] = arr[i];
        }
        return newArr;
    }

    private static MyBigInt SUB(MyBigInt number1, MyBigInt number2) {
        int[] num1 = number1.myBigInt;
        int[] num2 = number2.myBigInt;
        int[][] alignArrays = alignArraysLength(num1, num2);
        int[] result = new int[alignArrays[0].length];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = alignArrays[0][i] - alignArrays[1][i];
            if (result[i] < 0) {
                result[i] += 16;
                alignArrays[0][i - 1] -= 1;
            }
        }
        return new MyBigInt(result);
    }
}


