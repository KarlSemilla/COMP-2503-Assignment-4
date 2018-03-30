import java.util.Iterator;
public class BST<T extends Comparable<T>> 
{
    class BSTNode implements Comparable<BSTNode>
    {
        private T data;
        private BSTNode left; 
        private BSTNode right; 

        public BSTNode( T d) 
        {
            setLeft( null);
            setRight( null);
            setData( d);
        }

        public T getData() { return data; }

        public void setData( T d) { data = d; }

        public void setLeft( BSTNode l) { left = l;}

        public void setRight( BSTNode r) { right = r;}

        public BSTNode getLeft()  { return left;}

        public BSTNode getRight()  { return right;}

        public boolean isLeaf() 
        { 
            return ( getLeft() == null) && ( getRight() == null);
        }

        public int compareTo( BSTNode o) 
        {
            return this.getData().compareTo( o.getData());
        }

    }

    // The different traversal types. 
    public static final int INORDER = 0;
    public static final int PREORDER = 1;
    public static final int POSTORDER = 2;
    public static final int LEVELORDER = 3;

    private BSTNode root;
    private int size;  

    public BST()
    {
        root = null;
        size = 0;
    }

    /** Return true if element d is present in the tree. 
     */
    public T find( T d) 
    {
        return find( d, root); 
    }

    /** Return the height of the tree. 
     */
    public int height() 
    {
        return height( root);
    }

    /** Return the number of nodes in the tree. 
     */
    public int size() { 
        return size;
    }

    public void inOrder() 
    {
        traverse( root, INORDER);
    }

    public void postOrder() 
    {
        traverse( root, POSTORDER);
    }

    public void preOrder() 
    {
        traverse( root, PREORDER);
    }

    public void levelOrder() 
    {
        traverse( root, LEVELORDER);
    }

    // Private methods. 
    private T find( T d, BSTNode r) 
    {
        if ( r == null) 
            return null;
        int c = d.compareTo( r.getData()); 
        if ( c == 0) 
            return r.getData();
        else if ( c < 0) 
            return find( d, r.getLeft());
        else 
            return find( d, r.getRight());
    } 

    /** Add element d to the tree. 
     */
    public void add( T d) 
    {
        BSTNode n = new BSTNode( d); 
        if ( root == null) 
        {
            size++;
            root = n; 
        }
        else 
        {
            add( root, n); 
        }
    }

    /* Do the actual add of node r to tree rooted at n */
    private void add( BSTNode r, BSTNode n) 
    {
        int c = n.compareTo( r);
        if ( c < 0) //if n less than  r
        {
            if(r.getLeft() == null)
            {
                r.setLeft(n);
                size++;
            }
            else
            {
                add(r.getLeft(), n);
            }
        }
        else //if n greater than or equal to r (allows duplicate enteries)
        {
            if(r.getRight() == null)
            {
                r.setRight(n);
                size++;
            }
            else
            {
                add(r.getRight(), n);
            }
        }
    }

    /* Implement a height method. */
    private int height( BSTNode r) 
    {
        int h = 0;

        if (r != null)

        {
            int rh = height(r.getRight());
            int lh = height(r.getLeft());

            h =(rh > lh ? 1+ rh : 1 +lh);

        }
        return h;
    } 

    /* Traverse the tree.  travtype determines the type of 
    traversal to perform. */
    private void traverse( BSTNode r, int travType) 
    {
        if ( r != null) 
        {
            switch ( travType) 
            {
                case INORDER: 
                traverse(r.getLeft(), travType);
                visit(r);
                traverse(r.getRight(), travType);
                break;

                case PREORDER: 
                visit(r);
                traverse(r.getLeft(), travType);
                traverse(r.getRight(), travType);
                break;

                case POSTORDER: 
                traverse(r.getLeft(), travType);
                traverse(r.getRight(), travType);
                visit(r);
                break;
                
                case LEVELORDER:
                levelOrder( r);
                break;
            }
        }
    }

    private void visit( BSTNode r) 
    {
        if ( r != null) System.out.println( r.getData());
    }

    // traverse the subtree r using level order. */
    private void levelOrder( BSTNode r) 
    {
        BSTNode currNode = r;
        Queue<BSTNode> q = new Queue<BSTNode>();
        q.enqueue(currNode);
        
        while(!q.isEmpty())
        {
            currNode = q.dequeue();
            visit(currNode);
            if(currNode.getLeft() != null){
                q.enqueue(currNode.getLeft());
            }
            if(currNode.getRight() != null){
                q.enqueue(currNode.getRight());
            }
        }

    }
    
    // This method mainly calls deleteRec()
    public void deleteData(T key)
    {
        root = deleteRec(root, key);
        size--;
    }
 
    /* A recursive function to insert a new key in BST */
    public BSTNode deleteRec(BSTNode root, T key)
    {
        /* Base Case: If the tree is empty */
        if (root == null)  return root;
 
        /* Otherwise, recur down the tree */
        int val = key.compareTo(root.getData());
        if (val < 0) {
            root.left = deleteRec(root.left, key);
        }
        else if (val > 0) {
            root.right = deleteRec(root.right, key);
        }
        
 
        // if key is same as root's key, then This is the node
        // to be deleted
        else
        {
            // node with only one child or no child
            if (root.left == null) {
            		return root.right;
            	}
            	
            else if (root.right == null) {
            		return root.left;
            }
 
            // node with two children: Get the inorder successor (smallest
            // in the right subtree)
            root.setData(minValue(root.right));
            
            // Delete the inorder successor
            root.right = deleteRec(root.right, root.getData());
        }
        return root;
    }
 
    public T minValue(BSTNode r)
    {
        T minv = r.getData();
        while (r.left != null)
        {
            minv = r.left.getData();
            root = r.left;
        }
        return minv;
    }
    
    public T getData() 
    {
    	T data = root.getData();
    	return data;
    }
}