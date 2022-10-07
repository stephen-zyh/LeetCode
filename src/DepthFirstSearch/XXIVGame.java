package DepthFirstSearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 679
 * @date 2022/10/5 16:01
 */
public class XXIVGame {
    public boolean judgePoint24(int[] cards) {
        //int[]转double[]，避免运算时精度损失过大
        double[] nums = {cards[0], cards[1], cards[2], cards[3]};
        return hasSolution(nums);
    }

    private boolean hasSolution(double[] a){
        //base case，最后四则运算得到的结果，检查是否能得到24
        if (a.length == 1){
            return Math.abs(a[0] - 24) < 0.00001;
        }
        //取其中的a[i]和a[j]进行四则运算放入b数组中，其余元素直接放入b中
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                double[] b = new double[a.length - 1];
                int index = 0;
                for (int k = 0; k < a.length; k++) {
                    if (k != i && k != j){
                        b[index++] = a[k];
                    }
                }
                double[] c = new double[]{a[i]+a[j], a[i]-a[j], a[j]-a[i], a[i]*a[j], a[i]/a[j], a[j]/a[i]};
                //DFS，检查任意两个数字进行任意的四则运算是否能够得到24，只要能就返回true
                for (double d : c){
                    b[b.length - 1] = d;
                    if (hasSolution(b)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] cards = {1, 2, 1, 2};
        boolean result = new XXIVGame().judgePoint24(cards);
        System.out.println(result);
    }
}
