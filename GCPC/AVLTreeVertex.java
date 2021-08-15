//Name: Brennan Lee
//Student No; A0217067E

public class AVLTreeVertex {
    // barebones implementation of AVLTree
    public AVLTreeVertex parent, left, right;
    public Team key;
    public int height; // will be used in lecture on AVL
    public int size; // will be used in lecture on AVL

    // This is just a sample implementation
    // There are other ways to implement AVLTree concepts...

    // Every vertex in this AVLTree is a Java Class
    AVLTreeVertex(Team v) { 
        this.key = v;
        this.parent = this.left = this.right = null;
        this.height = 0;
        this.size = 1;
    }
    // all these attributes remain public to slightly simplify the code
    

}
