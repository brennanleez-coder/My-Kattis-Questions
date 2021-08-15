//Name: Brennan Lee
//Student No; A0217067E

public class AVLTree {
    public AVLTreeVertex root;

    public AVLTree() {
        this.root = null;
    }

    // A utility function to get height of the tree
    public int height(AVLTreeVertex N) {
        return N == null ? -1 :N.height;
    }

    public int bFactor(AVLTreeVertex T) {
        return T==null? 0: height(T.left)-height(T.right);
    }

    // A utility function to get size of the tree
    public int size(AVLTreeVertex N) {
        if (N == null)
            return 0;
        return N.size;
    }

    public int rank(Team v) {
        int res = rank(root, v);
        return res == -1 ? -1 : res;
    }

    public int rank(AVLTreeVertex T, Team v) {
        if (T == null) return -1;

        if (T.key.compareTo(v) < 0) {
            return rank(T.left, v);
        } else if (T.key.compareTo(v) > 0) {
            return size(T.left) + rank(T.right, v) + 1;
        } else {
            if (T.left == null) {
                return 1;
            } else {
                return T.left.size + 1;
            }
        }
    }

    

    AVLTreeVertex leftRotate(AVLTreeVertex T) {
        AVLTreeVertex y = T.right;
        y.parent = T.parent;
        T.parent = y;
        T.right = y.left;
        if (y.left != null) {
            y.left.parent = T;
        }
        int temp = size(y.left);
        y.left = T;

        T.height = y.height - 1;
        T.size = temp + size(T.left) + 1;
        y.size = size(T) + size(y.right) + 1;

        return y;
    }

    AVLTreeVertex rightRotate(AVLTreeVertex T) {
        AVLTreeVertex y = T.left;
        y.parent = T.parent;
        T.parent = y;
        T.left = y.right;
        if (y.right != null) {
            y.right.parent = T;
        }
        int x = size(y.right);
        y.right = T;

        T.height = y.height - 1;
        T.size = x + size(T.right) + 1;
        y.size = size(T) + size(y.left) + 1;

        return y;
    }
    // public method called to insert a new key with value v into AVLTree   
    public void insert(Team v) { root = insert(root, v); }

    // helper recursive method to perform insertion of new vertex into AVLTree
    public AVLTreeVertex insert(AVLTreeVertex T, Team v) {
        if (T == null) return new AVLTreeVertex(v);// insertion point is found

        if (T.key.compareTo(v) < 0) {// search to the right
            T.left = insert(T.left, v);
            T.left.parent = T;
        }
        else {// search to the left
            T.right = insert(T.right, v);
            T.right.parent = T;
        }
        T.size = size(T.left) + size(T.right) + 1;
        T.height =Math.max(height(T.left), height(T.right)) + 1;

        int balance = bFactor(T);
        //LL case
        if (balance >= 2 && bFactor(T.left) >= 0 && bFactor(T.left) <= 1) {
            return rightRotate(T);
        }

        // RR case
        if (balance <= -2 && bFactor(T.right) <= 0 && bFactor(T.right) >= -1) {
            return leftRotate(T);
        }

        // LR case
        if (balance >= 2 && bFactor(T.left) == -1) {
            T.left = leftRotate(T.left);
            return rightRotate(T);
        }

        //RL case
        if (balance <= -2 && bFactor(T.right) == 1) {
            T.right = rightRotate(T.right);
            return leftRotate(T);
        }

        return T;                                          // return the updated AVLTree
    }  

    // public method to delete a vertex containing key with value v from AVLTree
    public void delete(Team v) { root = delete(root, v); }

    // helper recursive method to perform deletion 
    public AVLTreeVertex delete(AVLTreeVertex T, Team v) {
        if (T == null)  return T;              // cannot find the item to be deleted

        if (T.key.compareTo(v) < 0)                                    // search to the right
            T.left = delete(T.left, v);
        else if (T.key.compareTo(v) > 0)                               // search to the left
            T.right = delete(T.right, v);
        else {                                            // this is the node to be deleted
            if (T.left == null && T.right == null)                   // this is a leaf
                T = null;                                      // simply erase this node
            else if (T.left == null && T.right != null) {   // only one child at right        
                if(T.key.compareId(v.id)) {
                    T.right.parent = T.parent;
                    T=T.right;
                } else {
                    T.right=null;
                }                                            // bypass T        
            }
            else if (T.left != null && T.right == null) {    // only one child at left        
                if (T.key.compareId(v.id)) {
                    T.left.parent = T.parent;
                    T = T.left;      
                } else {
                    T.left = null;
                }                                         // bypass T        
            }
            else {                                 // has two children, find successor
                Team successorV = successor(v);
                T.key = successorV;         // replace this key with the successor's key
                T.right = delete(T.right, successorV);      // delete the old successorV
            }
        }
        if (T == null) return T;

        T.size = size(T.left) + size(T.right) + 1;
        T.height =Math.max(height(T.left), height(T.right)) + 1;

        int balance = bFactor(T);
        // LL Case
        if (bFactor(T.left) <= 1 && balance >= 2 && bFactor(T.left) >= 0 ) {
            return rightRotate(T);
        }

        // LR Case
        if (bFactor(T.left) == -1 && balance >= 2) {
            T.left = leftRotate(T.left);
            return rightRotate(T);
        }

        // RR Case
        if (bFactor(T.right) <= 0 && bFactor(T.right) >= -1 && balance <= -2) {
            return leftRotate(T);
        }

        // RL Case
        if (bFactor(T.right) == 1 && balance <= -2) {
            T.right = rightRotate(T.right);
            return leftRotate(T);
        }
        return T;                                          // return the updated AVLTree
    }

    /* 
    *Code below not needed for this assignment
    */

    // public method called to search for a value v. 
    // Return v if it is found in the AVLTree otherwise return -1.
    // Here the assumption is that -1 is never a valid key value.
    public Team search(Team v) {
        AVLTreeVertex res = search(root, v);
        return res == null ? null : res.key;
    }

    // helper method to perform search
    public AVLTreeVertex search(AVLTreeVertex T, Team v) {
        if (T == null)  return null;// not found
        else if (T.key.compareTo(v) > 0)  return search(T.right, v);// search to the right
        else if (T.key.compareTo(v) == 0) return T;// found
        else return search(T.left, v);// search to the left

    }

    // public method called to find Minimum key value in AVLTree
    public Team findMin() { return findMin(root); }

    // helper method to perform findMin
    // Question: What happens if AVLTree is empty?
    public Team findMin(AVLTreeVertex T) {
        return T.left != null ? findMin(T.left) : T.key;
    }

    // public method called to find Maximum key value in AVLTree
    public Team findMax() { return findMax(root); }

    // helper method to perform findMax
    // Question: Again, what happens if AVLTree is empty?
    public Team findMax(AVLTreeVertex T) {
        return T.right != null ? findMax(T.right) : T.key;
    }

        // public method to find predecessor to given value v in AVLTree
    public Team predecessor(Team v) { 
            AVLTreeVertex vPos = search(root, v);
            return vPos == null ? null : predecessor(vPos);
        }
    
        // helper recursive method to find predecessor to for a given vertex T in AVLTree
    public Team predecessor(AVLTreeVertex T) {
            if (T.left != null)                         // this subtree has left subtree
                return findMax(T.left);  // the predecessor is the maximum of left subtree
            else {
                AVLTreeVertex par = T.parent;
                AVLTreeVertex cur = T;
                // if par(ent) is not root and cur(rent) is its left children
                while ((par != null) && (cur == par.left)) { 
                    cur = par;                                         // continue moving up
                    par = cur.parent;
                }
                return par == null ? null : par.key;           // this is the successor of T
            }
        }
    
        // public method to find successor to given value v in AVLTree.
    public Team successor(Team v) { 
            AVLTreeVertex vPos = search(root, v);
            return vPos == null ? null : successor(vPos);
        }
    
        // helper recursive method to find successor to for a given vertex T in AVLTree
    public Team successor(AVLTreeVertex T) {
            if (T.right == null) {
                AVLTreeVertex par = T.parent;
                AVLTreeVertex cur = T;
                // if par(ent) is not root and cur(rent) is its right children
                while ((par != null) && (cur == par.right)) {
                    cur = par;                                         // continue moving up
                    par = cur.parent;
                }
                return par == null ? null : par.key;           // this is the successor of T
            } else {
                return findMin(T.right);  // the successor is the minimum of right subtree
            }
        }

     // public method called to perform inorder traversal
    public void inorder() { 
        inorder(root);
        System.out.println();
    }

    // helper method to perform inorder traversal
    public void inorder(AVLTreeVertex T) {
        if (T == null) return;
        inorder(T.left);                               // recursively go to the left
        System.out.printf(" %d", T.key.id);                      // visit this AVLTree node
        inorder(T.right);                             // recursively go to the right
    }



}
