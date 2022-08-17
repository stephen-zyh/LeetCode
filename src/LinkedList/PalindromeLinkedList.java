package LinkedList;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 234
 * @date 2022/7/19 11:06
 */
public class PalindromeLinkedList {
    //较为容易理解的方法，将链表值放入数组中再判断
    public boolean isPalindrome(ListNode head) {
        StringBuilder builder = new StringBuilder();
        while (head != null){
            builder.append(head.val);
            head = head.next;
        }
        char[] array = builder.toString().toCharArray();
        int left = 0, right = array.length - 1;
        while (left < right) {
            if (array[left] == array[right]) {
                left++;
                right--;
            }else {
                break;
            }
        }
        return left == right || left - right == 1;
    }

    public static void main(String[] args) {
        ListNode p5 = new ListNode(1, null);
        ListNode p4 = new ListNode(2, p5);
        ListNode p3 = new ListNode(3, p4);
        ListNode p2 = new ListNode(2, p3);
        ListNode p1 = new ListNode(1, p2);

        boolean ispalindrome = new PalindromeLinkedList().isPalindrome(p1);
        System.out.println(ispalindrome);
    }
}
