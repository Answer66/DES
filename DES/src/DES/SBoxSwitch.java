package DES;

/**
 * Created by 兆禄 on 2016/12/1.
 */
public class SBoxSwitch {
    private static int[][] s1 = new int[4][16];
    private static int[][] s2 = new int[4][16];
    private static int[][] s3 = new int[4][16];
    private static int[][] s4 = new int[4][16];
    private static int[][] s5 = new int[4][16];
    private static int[][] s6 = new int[4][16];
    private static int[][] s7 = new int[4][16];
    private static int[][] s8 = new int[4][16];
    private int[][] S = new int[9][5];
    private ShowKey showKey = new ShowKey();
    public int[] sBoxChanged(int[] temp){
        SBoxSwitch sBoxSwitch = new SBoxSwitch();
        int[] result = new int[33];
        int[] m1 = {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7,

                0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8,

                4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0,

                15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13};
        int[] m2 = {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10,

                3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5,

                0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15,

                13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9};
        int[] m3 = {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8,

                13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1,

                13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7,

                1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12};
        int[] m4 = {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5 ,11, 12, 4, 15,

                13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9,

                10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4,

                3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14};
        int[] m5 = {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9,

                14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6,

                4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14,

                11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3};
        int[] m6 = {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11,

                10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8,

                9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6,

                4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13};
        int[] m7 = {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1,

                13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6,

                1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2,

                6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12};
        int[] m8 = {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7,

                1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2,

                7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8,

                2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11};
        s1 = sBoxSwitch.madeSbox(m1);
        s2 = sBoxSwitch.madeSbox(m2);
        s3 = sBoxSwitch.madeSbox(m3);
        s4 = sBoxSwitch.madeSbox(m4);
        s5 = sBoxSwitch.madeSbox(m5);
        s6 = sBoxSwitch.madeSbox(m6);
        s7 = sBoxSwitch.madeSbox(m7);
        s8 = sBoxSwitch.madeSbox(m8);
//        int[] temp = {-1,1,0,0,1,0,0,1,0,0,0,1,1,1,1,0,1,1,1,0,0,1,1,0,0,1,0,0,1,1,1,0,0,1,0,0,1,1,0,1,0,0,1,1,1,0,0,0,1};
        result = sBoxSwitch.SboxChange(temp);
        return result;
    }

    private int[][] madeSbox(int[] temp){
        int[][] sbox = new int[4][16];
        int k = 0;
        for (int i = 0;i < 4;i++){
            for (int j =0;j<16;j++){
                sbox[i][j] = temp[k++];
            }
        }
        return sbox;
    }

    public int[] SboxChange(int[] temp){
        //将48位分成8份，6位一份{
        int[][] B = new int[9][7];
        int t = 1;
        for (int i=1;i<=8;i++){
            for (int j=1;j<=6;j++){
                B[i][j] = temp[t++];
            }
        }
        for (int i =1;i<=8;i++){
//            showKey.show(B[i]);
            //求m即为S盒的第m行
            int m = 0;
            m = B[i][1]*2+B[i][6]*1;
            //求n即为S盒的第n列
            int n=0;
            for (int j =2;j<=5;j++){
                n += Math.pow(2,5-j)*B[i][j];
            }
//            System.out.println(m+"========="+n);
            switch (i){
                case 1:
                    B[i][6] =s1[m][n];
                    break;
                case 2:
                    B[i][6] =s2[m][n];
                    break;
                case 3:
                    B[i][6] =s3[m][n];
                    break;
                case 4:
                    B[i][6] =s4[m][n];
                    break;
                case 5:
                    B[i][6] =s5[m][n];
                    break;
                case 6:
                    B[i][6] =s6[m][n];
                    break;
                case 7:
                    B[i][6] =s7[m][n];
                    break;
                case 8:
                    B[i][6] =s8[m][n];
                    break;
                default:
                    System.out.println("异常错误");
                    break;
            }
//            System.out.println("b="+B[i][6]);
            for (int p = 1;p<=4;p++){
                S[i][p] = (int)(B[i][6] / Math.pow(2,4-p));
                B[i][6] = B[i][6] % (int)Math.pow(2,4-p);
            }
//            showKey.show(S[i]);
        }
        int[] sBoxchange = new int[33];
        int k = 1;
        for (int i=1;i<=8;i++){
            for (int j =1;j<=4;j++){
                sBoxchange[k++] = S[i][j];
            }
        }
//        showKey.show(sBoxchange);
        return sBoxchange;
    }
}
