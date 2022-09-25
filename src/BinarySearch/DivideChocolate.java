package BinarySearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1231
 * @date 2022/9/20 11:24
 */
public class DivideChocolate {
    public int maximizeSweetness(int[] sweetness, int K) {
        int minSweetness = 0, maxSweetness, sum = 0;
        //确定上下界，粥多僧少，至少应该得到所有甜度巧克力中甜度最低的一块；孔融让梨，至多不能超过平均分配的甜度
        for (int sweet : sweetness){
            sum += sweet;
            minSweetness = Math.min(minSweetness, sweet);
        }
        maxSweetness = sum / (K + 1);
        //二分，由于需要求得自己能得到的最大甜度，每次得到可行方案都修改下界
        while (minSweetness < maxSweetness){
            int middle = minSweetness + (maxSweetness - minSweetness + 1) / 2;  //上取整，避免死循环
            if (workableSolution(middle, sweetness, K + 1)){
                minSweetness = middle;
            }else {
                maxSweetness = middle - 1;
            }
        }
        return maxSweetness;
    }

    //判断是否能分为指定的块数，如果能分割的块数大于要求的块数，则返回true
    private boolean workableSolution(int capacity, int[] sweetness, int parts){
        int people = 0, curr = 0;
        for (int i = 0; i < sweetness.length; i++) {
            curr += sweetness[i];
            if (curr >= capacity){
                people++;
                curr = 0;
            }
            if (people >= parts){
                return true;
            }
        }
        return people >= parts;
    }

    public static void main(String[] args) {
//        int[] sweetness = {1,2,3,4,5,6,7,8,9};
//        int K = 5;
//        int[] sweetness = {5,6,7,8,9,1,2,3,4};
//        int K = 8;
        int[] sweetness = {1,2,2,1,2,2,1,2,2};
        int K = 2;
        int result = new DivideChocolate().maximizeSweetness(sweetness, K);
        System.out.println(result);
    }
}
