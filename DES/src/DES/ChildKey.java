package DES;

/**
 * Created by 兆禄 on 2016/11/28.
 */
public class ChildKey {
    private static int[] moveStep = {1,  1,  2,  2,  2,  2,  2,  2,  1,   2,  2,   2,   2,   2,   2,  1};
    public int[][] getChildKey(int[][] C){
        for(int i = 1;i<=16;i++){
            C[i] = moveLocation(C[i-1],moveStep[i-1]);
        }
        return C;
    }


    public int[] moveLocation(int [] temp,int step){
        int[] moveTemp = new int[temp.length];
        int j=1;
        for (int i = step+1;i<temp.length;i++){
            moveTemp[j++] = temp[i];
        }
        for (int i = 1;i <= step;i++){
            moveTemp[j++] =temp[i];
        }
        return moveTemp;
    }

    public static void main(String args[]){
        ChildKey childKey = new ChildKey();
        SecretKey secretKey = new SecretKey();
//        int[] temp = {-1,1,2,3,4,5,6,7};
//        int[] moveTemp = childKey.moveLocation(temp,2);
//        secretKey.show(moveTemp);
        int[][] C = new int[17][29];
        int[] temp = {0,0,1,0,0,1,0,0,0,1,0,1,1,0,0,1,0,0,0,0,0,1,0,0,1,1,0,1};
        C[0] = temp;
        secretKey.show(C[0]);
//        C[1] = childKey.moveLocation(C[0],1);
//        secretKey.show(C[1]);
        C = childKey.getChildKey(C);
    }
}
