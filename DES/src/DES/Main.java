package DES;

/**
 * Created by 兆禄 on 2016/11/25.
 */
public class Main {
    private SecretKey secretKey = new SecretKey();
    private ChildKey childKey = new ChildKey();
    private MadeKey48 madeKey48 = new MadeKey48();
    private ShowKey showKey = new ShowKey();
    private SwitchKey switchKey = new SwitchKey();
    private SortMessage sortMessage = new SortMessage();
    private SBoxSwitch sBoxSwitch = new SBoxSwitch();
    private XOR xor = new XOR();
    public static void main(String args[]){
        String information = "a406753854abcdef";
        String key1 = "a34457799bbcdff1";
        Main m = new Main();
        int[] key = {-1,1,0,1,0,0,0,1,1,0,1,0,0,0,1,0,0,0,1,0,1,0,1,1,1,0,1,1,1,1,0,0,1,1,0,0,1,1,0,1,1,1,0,1,1,1,1,0,0,1,1,0,1,1,1,1,1,1,1,1,1,0,0,0,1};
        int[][] K = new int[17][49];
        int[][] C = new int[17][29];
        int[][] D = new int[17][29];
        System.out.println("key1：");
        System.out.println(key1);
        System.out.println("K:");
        System.out.println(m.showKey.show(key));
        //Step1:密钥移位
        key = m.secretKey.switchKey(key);            //密钥移位
        System.out.println("K+");
        System.out.println(m.showKey.show(key));     //显示换位后密钥
        //Step2:密钥分组做左移
        C[0] = m.secretKey.sortSecretKeyC(key);
        D[0] = m.secretKey.sortSecretKeyD(key);
        System.out.println("       分组C                            分组D");
        System.out.println("第0组:"+ m.showKey.show(C[0])+"\t\t"+m.showKey.show(D[0]));
        //生成16个子密钥
        C = m.childKey.getChildKey(C);
        D = m.childKey.getChildKey(D);
        for (int i = 1;i <= 16;i++){
            System.out.println("第"+i+"组:"+ m.showKey.show(C[i])+"\t\t"+m.showKey.show(D[i]));
        }
        //Step3：串联密钥生成56位，再次移位生成48位
        for (int i = 1;i<=16;i++){
            int[] temp;
//            System.out.println(C[i].length+"-----------"+D[i].length);
            temp = m.madeKey48.combineKey(C[i],D[i]);
            temp = m.madeKey48.moveKey(temp);
            K[i] = temp;
        }
        for (int i = 1;i <= 16;i++){
            System.out.println("key"+i+":"+m.showKey.show(K[i]));
//            m.showKey.show(K[i]);
        }
        //对明文进行64位一组分组
//        int length = result.length;
//        int group = length/64;

//-----------------------------------------加密过程----------------------------------------------------------------------------
        //Step4:明文换位
        System.out.println("64位明文是：");
//        int[] info = m.sortMessage.transformInfo("Hello World");
        int[] info = {-1,1,0,1,0,0,1,0,0,0,0,0,0,0,1,1,0,0,1,1,1,0,1,0,1,0,0,1,1,1,0,0,0,0,1,0,1,0,1,0,0,1,0,1,0,1,0,1,1,1,1,0,0,1,1,0,1,1,1,1,0,1,1,1,1};
        System.out.println(m.showKey.show(info));
        int[] switchTable = {58, 50, 12, 34, 26, 18, 10,  2, 60, 52, 44, 36, 28, 20, 12,  4,
                62, 54, 46, 38, 30, 22, 14,  6, 64, 56, 48, 40, 32, 24, 16,  8,
                57, 49, 41, 33, 25, 17,   9,  1, 59, 51, 43, 35, 27, 19, 11,  3,
                61, 53, 45, 37, 29, 21, 13,  5, 63, 55, 47, 39, 31, 23, 15,  7};
        int[] message = m.switchKey.Switch(info,switchTable);
        System.out.println("64位明文IP换位后是");
        System.out.println(m.showKey.show(message));
        int[] messageA = new int[33];
        int[] messageB = new int[33];
        messageA = m.sortMessage.sortInfo(message,0,32);
        messageB = m.sortMessage.sortInfo(message,32,64);
        System.out.println("64位明文分为两组，组A为："+m.showKey.show(messageA)+"组B为："+m.showKey.show(messageB));

        //对每组64位明文进行加密
//        for(int gr = 1;gr <= group;gr++) {
        int[] SecretMessage = new int[65];
        for (int gr =1;gr<=1;gr++){
            //Step5:对分组后的组B进行扩展成48位
            System.out.println("第"+gr+"组明文加密过程如下：");
            System.out.println("第"+gr+"组的L[0]为："+m.showKey.show(messageA));
            System.out.println("第"+gr+"组的R[0]为："+m.showKey.show(messageB));
            //以下为每次的加密过程，一共循环16次
            int[][] L = new int[17][32];
            int[][] R = new int[17][32];
            int[][] E = new int[17][49];
            int[] addTable = {
                    32, 1, 2, 3, 4, 5,
                    4, 5, 6, 7, 8, 9,
                    8, 9, 10, 11, 12, 13,
                    12, 13, 14, 15, 16, 17,
                    16, 17, 18, 19, 20, 21,
                    20, 21, 22, 23, 24, 25,
                    24, 25, 26, 27, 28, 29,
                    28, 29, 30, 31, 32, 1
            };
            L[0] = messageA;
            R[0] = messageB;
//            E[0] = m.switchKey.addLength(messageB[0],addTable);
            for(int i = 1;i<=16;i++) {
                //Step6:对第i组64位明文加密
                System.out.println("--------------------第"+i+"次加密---------------------------");
                E[i-1] = m.switchKey.addLength(R[i-1], addTable);
                System.out.println("      E:"+m.showKey.show(E[i-1]));
                int[] f = m.xor.makeXOR(E[i-1],K[i]);
                System.out.println(" E异或K:"+m.showKey.show(f));
                int[] s = m.sBoxSwitch.sBoxChanged(f);
                System.out.println("S盒变换:"+m.showKey.show(s));
                //进行换位
                int[] sSwitch = {16,7,20,21,29,12,28,17, 1,15,23,26, 5,18,31,10, 2,8,24,14,32,27, 3, 9,19,13,30, 6,22,11, 4,25};
                s = m.switchKey.addLength(s,sSwitch);
                System.out.println("P换位为:"+m.showKey.show(s));
                R[i] = m.xor.makeXOR(s,L[i-1]);
                L[i] = R[i-1];
                System.out.println("      R:"+m.showKey.show(R[i]));
                System.out.println("      L:"+m.showKey.show(L[i]));
            }
            //Step7:合并L和R
            int smLength = 1;
            for (int i = 1;i<R[16].length;i++){
                SecretMessage[smLength++] = R[16][i];
            }
            for (int i = 1;i<L[16].length;i++){
                SecretMessage[smLength++] = L[16][i];
            }
            //生成的密文
            System.out.println("  合并R和L为:"+m.showKey.show(SecretMessage));
            //最后的逆变化
            int[] secretSwitch ={40, 8, 48, 16, 56, 24, 64, 32,
                    39, 7, 47, 15, 55, 23, 63, 31,
                    38, 6, 46, 14, 54, 22, 62, 30,
                    37, 5, 45, 13, 53, 21, 61, 29,
                    36, 4, 44, 12, 52, 20, 60, 28,
                    35, 3, 43, 11, 51, 19, 59, 27,
                    34, 2, 42, 10, 50, 18, 58, 26,
                    33, 1, 41,   9, 49, 17, 57, 25};
            SecretMessage = m.switchKey.addLength(SecretMessage,secretSwitch);
            System.out.println("最终的密文为:"+m.showKey.show(SecretMessage));
        }
        //-------------解密过程
        System.out.println("-----------------------解密过程-----------------------------------");
        System.out.println("64位密文是：");
//        int[] info = m.sortMessage.transformInfo("Hello World");
        System.out.println(m.showKey.show(SecretMessage));
        int[] sMessage = m.switchKey.Switch(SecretMessage,switchTable);
        System.out.println("64位密文IP换位后是");
        System.out.println(m.showKey.show(sMessage));
        int[] sMessageA = new int[33];
        int[] sMessageB = new int[33];
        sMessageA = m.sortMessage.sortInfo(sMessage,0,32);
        sMessageB = m.sortMessage.sortInfo(sMessage,32,64);
        System.out.println("64位密文分为两组，组A为："+m.showKey.show(sMessageA)+"组B为："+m.showKey.show(sMessageB));
        //对每组64位明文进行加密
        for (int gr =1;gr<=1;gr++) {
            //Step5:对分组后的组B进行扩展成48位
            System.out.println("第" + gr + "组密文解密过程如下：");
            System.out.println("第" + gr + "组的L[0]为：" + m.showKey.show(sMessageA));
            System.out.println("第" + gr + "组的R[0]为：" + m.showKey.show(sMessageB));
            //以下为每次的加密过程，一共循环16次
            int[][] L = new int[17][32];
            int[][] R = new int[17][32];
            int[][] E = new int[17][49];
            int[] addTable = {
                    32, 1, 2, 3, 4, 5,
                    4, 5, 6, 7, 8, 9,
                    8, 9, 10, 11, 12, 13,
                    12, 13, 14, 15, 16, 17,
                    16, 17, 18, 19, 20, 21,
                    20, 21, 22, 23, 24, 25,
                    24, 25, 26, 27, 28, 29,
                    28, 29, 30, 31, 32, 1
            };
            L[0] = sMessageA;
            R[0] = sMessageB;
//            E[0] = m.switchKey.addLength(messageB[0],addTable);
            for (int i = 1; i <= 16; i++) {
                //Step6:对第i组64位明文加密
                System.out.println("--------------------第" + i + "次解密---------------------------");
                E[i - 1] = m.switchKey.addLength(R[i - 1], addTable);
                System.out.println("      E:" + m.showKey.show(E[i - 1]));
                int[] f = m.xor.makeXOR(E[i - 1], K[17 - i]);
                System.out.println(" E异或K:" + m.showKey.show(f));
                int[] s = m.sBoxSwitch.sBoxChanged(f);
                System.out.println("S盒变换:" + m.showKey.show(s));
                //进行换位
                int[] sSwitch = {16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25};
                s = m.switchKey.addLength(s, sSwitch);
                System.out.println("P换位为:" + m.showKey.show(s));
                R[i] = m.xor.makeXOR(s, L[i - 1]);
                L[i] = R[i - 1];
                System.out.println("      R:" + m.showKey.show(R[i]));
                System.out.println("      L:" + m.showKey.show(L[i]));
            }
            //Step7:合并L和R
            int smLength = 1;
            int[] undoMessage = new int[65];
            for (int i = 1; i < R[16].length; i++) {
                undoMessage[smLength++] = R[16][i];
            }
            for (int i = 1; i < L[16].length; i++) {
                undoMessage[smLength++] = L[16][i];
            }
            //生成的密文
            System.out.println("  合并R和L为:" + m.showKey.show(undoMessage));
            //最后的逆变化
            int[] secretSwitch = {40, 8, 48, 16, 56, 24, 64, 32,
                    39, 7, 47, 15, 55, 23, 63, 31,
                    38, 6, 46, 14, 54, 22, 62, 30,
                    37, 5, 45, 13, 53, 21, 61, 29,
                    36, 4, 44, 12, 52, 20, 60, 28,
                    35, 3, 43, 11, 51, 19, 59, 27,
                    34, 2, 42, 10, 50, 18, 58, 26,
                    33, 1, 41, 9, 49, 17, 57, 25};
            undoMessage = m.switchKey.addLength(undoMessage, secretSwitch);
            System.out.println("最终的明文为:" + m.showKey.show(undoMessage));
        }
    }
}
