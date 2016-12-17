package DES;

/**
 * Created by 兆禄 on 2016/11/30.
 */
public class SwitchKey{
    public int[] Switch(int[] temp,int[] switchTable){
        int[] t = new int[temp.length];
        System.arraycopy(temp,1,t,1,temp.length-1);
        for(int i = 1;i <= switchTable.length;i++){
            t[i] = temp[switchTable[i-1]];
        }
        return t;
    }

    public int[] addLength(int[] Key,int[] lengthTable){
        int[] temp = new int[Key.length];
        int[] addTemp = new int[lengthTable.length+1];
        System.arraycopy(Key,1,temp,1,Key.length-1);
        for(int i = 1;i <= lengthTable.length;i++){
            addTemp[i] = temp[lengthTable[i-1]];
        }
        return addTemp;
    }
//    private static int[] moveStep = {58, 50, 12, 34, 26, 18, 10,  2, 60, 52, 44, 36, 28, 20, 12,  4,
//            62, 54, 46, 38, 30, 22, 14,  6, 64, 56, 48, 40, 32, 24, 16,  8,
//            57, 49, 41, 33, 25, 17,   9,  1, 59, 51, 43, 35, 27, 19, 11,  3,
//            61, 53, 45, 37, 29, 21, 13,  5, 63, 55, 47, 39, 31, 23, 15,  7};
//    public static void main(String args[]){
//        int[] testDataC = new int[65];
//        for (int i = 0;i<=64;i++){
//            testDataC[i] = i;
//        }
//        SwitchKey switchKey = new SwitchKey();
//        ShowKey showKey = new ShowKey();
//        int[] t = switchKey.Switch(testDataC,moveStep);
//        showKey.show(t);
//
//    }
}
