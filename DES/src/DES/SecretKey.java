package DES;

import java.util.Random;

/**
 * Created by 兆禄 on 2016/11/25.
 */
public class SecretKey {
    private Random random = new Random();
    private int[] key = new int[65];
    private int[][] C = new int[17][29];
    private int[][] D = new int[17][29];
    private int[] moveKey ={57, 49, 41, 33, 25, 17,   9,  1, 58, 50, 42, 34, 26, 18, 10,  2, 59, 51, 43, 35, 27, 19, 11,  3, 60, 52, 44, 36,
                            63, 55, 47, 39, 31, 23, 15,  7, 62, 54, 46, 38, 30, 22, 14,  6, 61, 53, 45, 37, 29, 21, 13,  5, 28, 20, 12, 4};

    //---产生密钥
    public int[] createSecretKey(){
        for(int i =1;i <= 64;i++){
            key[i] = Math.abs(random.nextInt()) % 2;
        }
        return key;
    }

    //显示密钥
    public void show(int[] t){
        String showKey = new String();
        for (int i = 1 ;i <t.length;i++){
            showKey += t[i]+"";
//            System.out.println(key[i]);
        }
        System.out.println(showKey);
    }


    //密钥移位
    public int[] switchKey(int[] switchTemp){
        int[] temp = new int[57];   //换位前的key
        for(int i = 1;i <= moveKey.length;i++){
            temp[i] = switchTemp[moveKey[i-1]];
        }
        return temp;
    }

    //等分密钥
    public int[] sortSecretKeyC(int[] sortTemp){
//        int[] temp = new int[57];
        //密钥分组
        for (int i =1 ;i <= 28;i++){
            C[0][i] = sortTemp[i];
        }
        return C[0];
    }
    public int[] sortSecretKeyD(int[] sortTemp){
//        int[] temp = new int[57];
        //密钥分组
        int k=1;
        for (int i = 29;i<=56;i++){
            D[0][k++] = sortTemp[i];
        }
        return D[0];
    }


}
