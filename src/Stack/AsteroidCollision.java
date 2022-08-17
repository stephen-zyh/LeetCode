package Stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 735
 * @date 2022/8/1 15:36
 */
public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int asteroid : asteroids) {
            boolean alive = true;   //alive记录当前行星是否被击碎
            //只有两个行星相向而行时才会相撞
            while (alive && !stack.isEmpty() && stack.peek() > 0 && asteroid < 0) {
                alive = stack.peek() < - asteroid;
                if (stack.peek() <= - asteroid){
                    //当前行星将栈顶行星击碎
                    stack.pop();
                }
            }
            //当前行星最终没有被击碎，加入栈中
            if (alive) {
                stack.push(asteroid);
            }
        }
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] asteroids = {5,10,-5};
//        int[] asteroids = {8,-8};
//        int[] asteroids = {10,2,-5};
//        int[] asteroids = {-2,-1,1,2};
        int[] asteroids = {1,-2,-2,-2};
        int[] result = new AsteroidCollision().asteroidCollision(asteroids);
        System.out.println(Arrays.toString(result));
    }
}
