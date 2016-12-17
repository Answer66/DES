package DES;

/**
 * Created by 兆禄 on 2016/11/30.
 */
public class SortMessage {
    public int[] transformInfo(String str){
        String result = "";
        String message = "";

        //先把他变为字符数组
        char[] chs = str.toCharArray();

        //然后通过integer中的toBinaryString方法来一个一个转

        for (int i = 0; i < chs.length; i++) {
            String temp = Integer.toBinaryString(chs[i]);
//                System.out.printf("%02x\n",(int)chs[i]);
//                System.out.println(Integer.toBinaryString(chs[i]).length());
            if(temp.length() != 8){
                int L = 8 - temp.length();
                result = "";
                for (int j = 0;j < L;j++){
                    result += "0";
                }
                result += temp;
            }
//            System.out.print(result);
            message += result;
        }
        int length = message.length();
//        System.out.println("原本结果长度"+length);
        //对高位补零，使其成为64的倍数。
        int zero = 64-(length % 64);
        String t = "";
        for (int i  =0 ;i<zero;i++){
            t += "0";
        }
        t += message;
        message = t;
//        System.out.println(message);
        //将字符串转为数组
        int[] temp = new int[message.length()+1];
        for (int i = 1;i<=message.length();i++){
            temp[i] = Integer.parseInt(String.valueOf(message.charAt(i-1)));
        }
//        ShowKey showKey = new ShowKey();
//        showKey.show(temp);
        return temp;
    }
    public int[] sortInfo(int[] temp,int start,int end){
        int[] result = new int[end-start+1];
        int j = 0;
        for (int i = start ;i<=end;i++){
            result[j++] = temp[i];
        }
        return result;
    }
}
