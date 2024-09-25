/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        // BFS
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            boolean x_found = false;
            boolean y_found = false;

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();
                if (curr.val == x) {
                    x_found = true;
                }
                if (curr.val == y) {
                    y_found = true;
                }
                if (curr.left != null && curr.right != null) {
                    // check if they are siblings
                    if (curr.left.val == x && curr.right.val == y)
                        return false;
                    if (curr.left.val == y && curr.right.val == x)
                        return false;
                }
                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
            }

            if (x_found && y_found)
                return true;
            if (x_found || y_found)
                return false;

        }
        return false;
    }
}

class CousinsInBinaryTree {
    // DFS
    // level should be equal but parents should be different
    TreeNode x_parent;
    TreeNode y_parent;
    int x_level;
    int y_level;

    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null)
            return false;
        dfs(root, null, 0, x, y);
        return x_parent != y_parent && x_level == y_level;
    }

    public void dfs(TreeNode root, TreeNode parent, int level, int x, int y) {
        // base
        if (root == null)
            return;

        // logic
        if (root.val == x) {
            x_parent = parent;
            x_level = level;
        }

        if (root.val == y) {
            y_parent = parent;
            y_level = level;
        }

        // left child
        dfs(root.left, root, level + 1, x, y);

        // right child

        dfs(root.right, root, level + 1, x, y);
    }
}
