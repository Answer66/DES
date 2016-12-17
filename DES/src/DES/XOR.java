package DES;

/**
 * Created by 兆禄 on 2016/12/1.
 */
public class XOR {
    public int[] makeXOR(int[] a,int[] b){
        int[] temp  = new int[a.length];
        for (int i = 0;i<a.length;i++){
            temp[i] = (a[i] + b[i]) % 2;
        }
        return temp;
    }
}
