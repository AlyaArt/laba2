public class MyBigInt {
    public int[] myBigInt;
    public MyBigInt(String str) {
        setHex(str);
    }
    public MyBigInt(int[] nums) {
        this.myBigInt = nums;
    }
    public MyBigInt() {}

    public int getNumberLength() {
        return this.myBigInt.length;
    }
    public void setHex(String str) {
        this.myBigInt = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            char num = str.charAt(i);
            myBigInt[i] = Integer.parseInt(String.valueOf(num), 16);
        }
    }

    public String getHex() {
        String hexNum = "";
        for (int i = 0; i < this.myBigInt.length; i++) {
            hexNum += Integer.toHexString(myBigInt[i]);
        }
        return hexNum;
    }

    public String toBinaryString(){
        String binaryString="";
        for(int num : myBigInt) {
            binaryString += Main.addZero(Integer.toBinaryString(num));
        }
        return binaryString;
    }
}
