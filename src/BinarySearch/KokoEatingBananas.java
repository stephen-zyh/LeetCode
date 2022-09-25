package BinarySearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 875
 * @date 2022/9/18 19:42
 */
public class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        //求得的pile一定在minSpeed和maxSpeed之间
        int minSpeed = 1, maxSpeed = 0, middle;
        for(int pile : piles){
            maxSpeed = Math.max(pile, maxSpeed);
        }
        while (minSpeed < maxSpeed){
            middle = minSpeed + (maxSpeed - minSpeed) / 2;
            if (times(piles, middle) > h){
                minSpeed = middle + 1;
            }else {
                maxSpeed = middle;
            }
        }
        return minSpeed;
    }

    //计算特定速度下吃完香蕉需要的时间
    private int times(int[] piles, int speed){
        int hours = 0;
        for (int pile : piles){
            hours += (pile-1) / speed + 1;
        }
        return hours;
    }

    public static void main(String[] args) {
        int[] piles = {30,11,23,4,20};
        int h = 6;
        int result = new KokoEatingBananas().minEatingSpeed(piles, h);
        System.out.println(result);
    }
}
