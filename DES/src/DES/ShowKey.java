package DES;

/**
 * Created by 兆禄 on 2016/11/29.
 */
public class ShowKey {
    //显示密钥
    public String show(int[] t){
        String showKey = new String();
        for (int i = 1 ;i <t.length;i++){
            showKey += t[i]+"";
//            System.out.println(key[i]);
        }
//        System.out.println(showKey);
        return showKey;
    }
}
