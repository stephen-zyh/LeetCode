package BinarySearch;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 1011
 * @date 2022/9/19 10:42
 */
public class CapacityToShipPackagesWithinDDays {
    public int shipWithinDays(int[] weights, int days) {
        int minCapacity = 0, maxCapacity = 0;
        //确定下界为数组元素中的最大值（每一个货物都应该能装得下），上界为所有元素之和（至少要运一次）
        for(int weight : weights){
            maxCapacity += weight;
            minCapacity = Math.max(minCapacity, weight);
        }
        //二分，注意：time==days的情况划归else处理，是因为else中处理上界，能得到满足time==days的capacity的最小值
        //如果if中的条件为time < days，得到的是满足time==days的capacity的最大值
        while (minCapacity < maxCapacity){
            int middle = minCapacity + (maxCapacity - minCapacity) / 2;
            int time = calculateDays(weights, middle);
            if (time > days){
                minCapacity = middle + 1;
            }else{
                maxCapacity = middle;
            }
        }
        return minCapacity;
    }

    //计算在特定载重量时，运输需要的时间
    private int calculateDays(int[] weights, int capacity){
        int days = 0, load = 0;
        for (int weight : weights){
            if (load + weight <= capacity){
                load += weight;
            }else {
                days++;
                load = weight;
            }
        }
        //最后一轮循环结束后可能0 < load <= capacity，因此需要再加一天
        if (load > 0){
            days++;
        }
        return days;
    }

    public static void main(String[] args) {
//        int[] weights = {1,2,3,1,1};
//        int days = 4;
        int[] weights = {10,50,100,100,50,100,100,100};
        int days = 5;
        int result = new CapacityToShipPackagesWithinDDays().shipWithinDays(weights, days);
        System.out.println(result);
    }
}
