package Tree;

/**
 * @author zhaoyh
 * @version 1.0
 * @description: leetcode 968
 * @date 2022/8/21 10:09
 */
public class BinaryTreeCameras {
    private int result = 0;

    public int minCameraCover(TreeNode root) {
        int ans = preorderTraversal(root);
        //对根节点进行额外处理
        if (ans == 0){
            return ++result;
        }
        return result;
    }

    private int preorderTraversal(TreeNode root){
        if (root == null){
            return 1;
        }

        int left = preorderTraversal(root.left);
        int right = preorderTraversal(root.right);

        //0：左右子树都没有摄像头，但被摄像头覆盖，因此本节点未被覆盖，但为尽可能少安装摄像头，本节点不安装摄像头（根节点除外，需额外处理）
        //1：本节点没有摄像头，但左右节点的摄像头能覆盖到本节点
        //2：左右子树有没有安装摄像头且未被覆盖的节点（0），本节点需要安装摄像头，更新result的值
        if (left == 1 && right == 1){
            return 0;
        }
        if (left == 0 || right == 0){
            result++;
            return 2;
        }
        if (left == 2 || right == 2){
            return 1;
        }
        return 1;
    }

    public static void main(String[] args) {
        TreeNode n4 = new TreeNode(0);
        TreeNode n3 = new TreeNode(0);
        TreeNode n2 = new TreeNode(0, n3, n4);
        TreeNode n1 = new TreeNode(0, n2, null);

        int result = new BinaryTreeCameras().minCameraCover(n1);
        System.out.println(result);
    }
}
