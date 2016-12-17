package DES;

/**
 * Created by 兆禄 on 2016/11/28.
 */
public class MadeKey48 {
    private static int[] moveStep = {14, 17, 11, 24,  1,   5,  3, 28, 15,   6, 21, 10, 23, 19, 12,   4, 26,    8, 16,   7, 27, 20, 13,   2,
            41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32,};
    private static int[] testDataC = {1,0,1,0,1,1,0,0,0,1,0,1,0,1,0,1,0,0,1,1,0,0,0,1,1,1,0,1};
    private static int[] testDataD = {1,0,1,1,0,1,0,1,1,1,0,1,1,0,1,0,0,1,0,1,0,0,0,1,1,1,0,0};
    private ShowKey showKey = new ShowKey();

    public int[] combineKey(int[] temp1,int[] temp2){
        int[] key56 = new int[57];
        for (int i = 1;i<temp1.length;i++){
            key56[i] = temp1[i];
        }
        for (int i = 1;i<temp2.length;i++){
            key56[28+i] = temp2[i];
        }
//        System.out.println("合并后的字符串为:"+showKey.show(key56));
        return key56;
    }

    public int[] moveKey(int[] Key){
        int[] temp = new int[Key.length];
        int[] Key48 = new int[49];
        System.arraycopy(Key,1,temp,1,Key.length-1);
        for(int i = 1;i <= moveStep.length;i++){
            Key48[i] = temp[moveStep[i-1]];
        }
        return Key48;
    }

    public static void main(String args[]){
        MadeKey48 madeKey48 = new MadeKey48();
        SecretKey secretKey = new SecretKey();
        int[] c = testDataC;
        int[] d = testDataD;
        int[] key = madeKey48.combineKey(c,d);
        System.out.println("组合后的56位子串");
        secretKey.show(key);
        System.out.println(key.length);
        key = madeKey48.moveKey(key);
        System.out.println("移位后的48位子串");
        secretKey.show(key);
        System.out.println(key.length);
    }
}
