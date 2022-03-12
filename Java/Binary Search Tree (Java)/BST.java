/*	BST.java
 * 	BST.java is used to create a binary search tree. Within this program
 * 	are methods that will insert nodes into a tree from a TreeDriver program. Every
 *  time a new node is put into the tree, if it isn't the first value, it'll find 
 *  the correct location to put the node. The program keeps track order while inserting
 *  new nodes, placing the new nodes in alphabetical order. When the program is done 
 *	inserting nodes, we can see what our values are in a print function that also 
 *	takes care of printing the values in order. It will also allow the user to delete
 *  nodes from within the tree while keeping order and making sure that values are 
 *  not out of bounds. The program can also make copies of the binary tree and compare
 *  if the values within the copy tree are equal to the original tree. It also can 
 *  find the predecessr and successor nodes within the tree. 
 *
 *  Created By: Jason Shirley
 * 	Date: 05/24/2017
*/ 




public class BST
{
	private TreeNode root;
	
	public BST ()
	{
		this.root = null;
	}

	/* Insert is a method that is used to insert new nodes into the tree
	 * being created. If the tree is a new tree with no nodes the program
	 * can detect that and insert the first node as the root of the tree.
	 * The program then checks to see if the value is less than or greater 
	 * than the root of the tree. Depending on the value of the root the
	 * program will either insert the new node on the left side or the right
	 * side of the tree. It constantly checks new nodes on where to place 
	 * them according to the root of the current branch of the tree. The
	 * public insert is responsible for inserting the root of the tree
	 * while the private takes care of the rest. 
	*/
	public boolean insert (String x)
	{
		boolean success = true;
		// If this is true there is no root node. Insert the new node as the root.
		if (this.root == null) 
		{
			TreeNode q = new TreeNode (x);
			this.root = q;
		}
		// If this is true, there is a root. The program needs to call the private
		// insert to insert the node. 
		else if (this.root != null)
		{
			success = insert(x, this.root);
		}
		return success;
	}
	
	private boolean insert (String x, TreeNode p)
	{
		boolean success = true;
		TreeNode q = new TreeNode(x);
		
		if (root.getData() == (x))
		{
			success = false;
		}
		// If this is true, go right.
		if (x.compareToIgnoreCase(p.getData()) < 0)
		{
			// If there is no left child, insert it as the left child.
			if (p.getLChild() == null) 
			{
				q.setParent(p);
				p.setLChild(q);

			}
			else
			{
				success = insert(x,p.getLChild()); // The value needs to go into the left subtree.
			}
		}	
		// If this is true, go left.
		else if ( x.compareToIgnoreCase(p.getData()) > 0) 
		{
			if (p.getRChild() == null)
			{
				q.setParent(p);
				p.setRChild(q);				

			}
			else
			{
				success = insert(x,p.getRChild()); 
			}
		}
		else 
		{
			success = false; // The node was a duplicate or out of bounds.
		}
		return success;
	}
	/* toString and inOrder are used to sort the values in the BST and
	 * print those values out. toString calls inOrder to take care of sorting
	 * the values in the BST before printing out the values. inOrder takes
	 * care of sorting the values in the BST.
	*/
	public String toString ()
	{
		return inOrder(this.root);
	}
	
	private String inOrder( TreeNode p )
	{
		String order = "";
		if (p != null)
		{
			order = inOrder(p.getLChild()) + " " + p.getData() + " " + inOrder(p.getRChild()); 
		}
		return order;
	}


	/* locate is used to find a value within the binary search tree. It first
	 * checkts to see if there is anything in the tree, and then searches 
	 * the rest of the tree to see if it exists. If its the root, it'll return
	 * the node. If it isn't if first checks the right tree, then the left tree
	 * and all of their children to see if the value exists within the tree.
	*/
	
	public TreeNode locate (String v)
	{
		TreeNode p = this.root;
		TreeNode q = null;
		if(p.getData() != null)
		{
			if (p.getData().equals(v)) // The value was found here.
			{
				q = p;
			}
			if (v.compareToIgnoreCase(p.getData()) < 0) // The value is down the right subtree.
			{
				q = locate(p.getLChild(), v); 
			}
			if (v.compareToIgnoreCase(p.getData()) > 0) // The value is down the left subtree.
			{
				q = locate(p.getRChild(), v);
			}
		}

		return q;
	}
	
	private TreeNode locate (TreeNode p, String v)
	{
		TreeNode q = null;
		if(p.getData() != null)
		{
			// If this statement is true, we found our value.
			if (p.getData().equals(v))
			{
				q = p;
			}
			// The value hasn't been found, check down the left subtree.
			else if (v.compareToIgnoreCase(p.getData()) < 0 && p.getLChild() != null) 
			{
				q = locate(p.getLChild(), v);
			}
			// The value hasn't been found, check down the right subtree.
			else if (v.compareToIgnoreCase(p.getData()) > 0 && p.getRChild() != null) 
			{
				q = locate(p.getRChild(), v);
			}
		}
		return q;	
	}
	/* delete takes care of deleting nodes in a BST. It sniffs out where 
	 * the node that needs to be taken out is located and removes it by 
	 * resetting the child and parents pointers of the node being deleted. 
	*/
	public void delete ( TreeNode p )
	{
		TreeNode q = p; // Node for storing data, I'll refer to as temporary.
		// If this is true, the found node doesn't have children.
		if ( p.getLChild() == null && p.getRChild() == null) 
		{
			// If it's the root, delete the root.
			if ( p == this.root ) 
			{
				this.root = null; 
			}
			// If the value is the left child of a node, delete it.
			else if ( p.getParent().getLChild() == p ) 
			{
				p.getParent().setLChild(null);
			}
			// If the value the right child of a node, delete it.
			else if(p.getParent().getRChild() == p)
			{
				p.getParent().setRChild(null);
			}
		
		}			
		// If this is true, the found node has a left child. 
		else if ( p.getRChild() == null) 
		{
			// If the value the root, set it to the root's left child.
			if ( p == root ) 
			{
				root = root.getLChild();

			}
			else if (p.getParent().getLChild() == p) 
			{
				q = p.getParent(); 	// Set temporary node to the passed nodes parent.
				p.getParent().setLChild(p.getLChild()); // Set the parent as the left child of the node 
														// passed into the function
				p.getLChild().setParent(q); // Set the parent of the node's left child as the value 
											// of the temporary node. 
			}
			// Or else we have a turn. We set the right child of the found node's point as the left child
			// of the found node. We then set the left child's parent as the 
			else
			{
				q = p.getParent(); // Set the temporary node to the passed node's parent.
				p.getParent().setRChild(p.getLChild()); // Set the right child of the node being passed
														// as the left child of the node being passed.
				p.getLChild().setParent(q);				// Set the parent of the node's left child as the 
														// value of the temporary node.
				p.getParent().setRChild(p.getLChild());	// Set the right child of the passed node's parent
														// as the left child of the passed node.
			}
		}
		
		else if ( p.getLChild() == null) // The node has only a right child.
		{
			// If its the root node, set the roots temporary to the roots right child.
			if ( p == root ) 
			{
				root = root.getRChild();

			}
			// If the statement is true, move the found node's pointers for the nodes parent and left child.
			// Move the right child's temporary to the current node's parent and the left
			// child's temporary to the current node's parent. 
			else if (p.getParent().getLChild() == p)
			{
				q = p.getParent(); // Set the temporary node as the parent of the passed node.
				p.getParent().setLChild(p.getRChild());	// Set the parent of the passed node's
														// left child as the right child of the 
														// passed node.
				p.getRChild().setParent(q);				// Set the parent of the passed node's right 
														// child as the value of the pointer.
			} 
			else
			{
				q = p.getParent(); // Set the value of the temporary node as the parent of the passed 
								   // node
				p.getParent().setRChild(p.getRChild()); // Set the right child of the parent of the passed node	
														// as the right child of the passed node.
				p.getRChild().setParent(q);	// set the parent of the right child of the passed node as the 
											// value of the temporary node. 
				p.getParent().setRChild(p.getRChild()); // Set the right child of the parent of the passed node
														// as the right child of the passed node.
			}
		}
		// If true, then the node being passed has two children. 
		else if ( p.getRChild() != null && p.getLChild() != null )
		{
				q = successor(p); // Find the successor of p and set it to the temporary node.
				p.setData(q.getData()); // Set the data of the passed node as the temporary node.
				delete(q);	// Delete the temporary node. 
		}	
	}
	
	/* predecessor is used to find the value the preceeds the value
	 * of the node being passed. The node being passed was set by the driver
	 * as a node that was found in search. In predecessor we start by looking
	 * down the left sub tree. 
	*/
	public TreeNode predecessor ( TreeNode p ) 
	{
		TreeNode q = null; // Search node.

		if (p.getLChild() != null) // If true there is a left child.
		{
			q = p.getLChild(); // Set the value of the search node as the 
							   // left child of the node being passed.
			// We have to traverse the right sub tree until we hit the end. 
			while(q.getRChild() != null) // While there is a right child.
			{
				q = q.getRChild(); // Set the search node as the right child.
			}
		}
		// Or else 
		else if ( p.getParent() != null )
		{
			q = p.getParent(); // Set the temporary node as the parent. 
			// While q doesn't equl to null and p is greater than q...
			while ( q != null && p.getData().compareToIgnoreCase(q.getData()) < 0 )
			{
				q = q.getParent(); // set the value of the search node as the parent of the search node.
			}
		}
		return q;
	}
	// In successor we do the same moves as predecessor, but reverse which children we look at.
	public TreeNode successor ( TreeNode p ) 
	{
		TreeNode q = null;
		if (p.getRChild() != null)
		{
			q = p.getRChild();
			while(q.getLChild() != null)
			{
				q=q.getLChild();
			}
		}
		else if (p.getParent() != null)
		{
			q = p.getParent();
			while ( q != null && p.getData().compareToIgnoreCase(q.getData()) > 0 )
			{
				q = q.getParent();
			}
		}
		return q;
	}
	/* copy makes a copy of the binary search tree we created before. It traverses the whole tree
	 * while creating copies of the nodes in the tree and placing them in the same order as the 
	 * original binary search tree. Once done we have an identical tree to the original tree.
	*/
	public BST copy ( )
	{
		BST cloneTree = new BST ();
		TreeNode clone = new TreeNode(root.getData());
		// If this is true, see the node of the copy tree.
		if ( root != null)
		{
			cloneTree.root = clone;
			copy(root, clone); // Once the copy root is created, send it off to build the new tree off of it.
		}
		return cloneTree;
	}
	
	private TreeNode copy (TreeNode p, TreeNode q)
	{
		// If this is true we can set a new left child.
		if (p.getLChild() != null)
		{
			q.setLChild(new TreeNode(p.getLChild().getData()));
			copy(p.getLChild(), q.getLChild()); // Call copy again with the values for the 
												// original tree's left node and the new tree's 
												// left node.
		}
		// If this is true we can set a new right child.
		if (p.getRChild() != null)
		{
			q.setRChild(new TreeNode(p.getRChild().getData()));
			copy(p.getRChild(), q.getRChild());// Call copy again with the values for the 
											   // original tree's right node and the new tree's
											   // right node.
		}
		
		return q; // Return our new tree. 
	}
	
	
	/* equals takes care of checking to see if the original tree
	  * created by BST.java is the same as another tree. It traverses
	  * the tree constantly checking if the node that is being passed 
	  * is equal to the node being compared to.
	*/
	public boolean equals ( BST that )
	{	
		boolean same = false; // Set the statement to false and change to 
							  // true if it's equal.
		// If this is true, then the roots of both trees are equl.					  
		if (this.root != null && that.root != null)
		{
			same = equals(this.root, that.root); // Check to see if the rest of the tree is euqal. 
		}

		return same;
	}
	// Private boolean checks to see if the tree after the root is equal to the copy. 
	private boolean equals( TreeNode p, TreeNode q )
	{
		boolean lSame = false; // Left node check, set to false and change to true if it's equal.
		boolean rSame = false; // Right node check, set to false and change to true if it's equal.
		// If true the data from the copy and original are equal. 
		if (p.getData() == q.getData())
		{
			// If true, there are left children in the copy and original trees.
			if (p.getLChild() != null && q.getLChild() != null) 
			{
				lSame = equals(p.getLChild(), q.getLChild()); // Recursive call to check to see if the 
															  // next left child in both trees are equal.
			}
			else if (p.getLChild() == null && q.getLChild() == null)
			{
				lSame = true;
			}
			// If true, there are right children in the copy and original trees. 
			if (p.getRChild() != null && q.getRChild() != null)
			{
				rSame = equals(p.getRChild(), q.getRChild()); // Recursive call to check to see if the
															  // next right child in both trees are equal. 
			}
			else if (p.getRChild() == null && q.getRChild() == null)
			{
				rSame = true;
			}
		}
		return (rSame && lSame);	
	}
	
	public int wordCount(int x)
	{
		return wordCount(this.root, x);
	}
	
	private int wordCount(TreeNode p, int x)
	{
		int increment = 0;
		if (p != null)
		{
			if (p.getData().length() >= x)
			{
				increment++;
			}		
			increment = wordCount(p.getLChild(), x) + wordCount(p.getRChild(), x) + increment;
		}		
		return increment;
	}				
}
// TreeNode is used to create new nodes for the Binary Search Tree.
// It also takes care of setting the data for the tree nodes. 
class TreeNode 
{
    private String data;
    private TreeNode lChild;
    private TreeNode rChild;
    private TreeNode parent;
    private int count;
    
    public TreeNode ( )
    {
    }
    
    public TreeNode ( String x )
    {
        this.setData ( x );
    }
    
    public void setData ( String x )
    {
        this.data = x;
    }
    
    public void setLChild ( TreeNode p )
    {
        this.lChild = p;
    }

    public void setRChild ( TreeNode p )
    {
        this.rChild = p;
    }

    public void setParent ( TreeNode p )
    {
        this.parent = p;
    }
    
    public String getData ( )
    {
        return this.data;
    }
    
    public TreeNode getLChild ( )
    {
        return this.lChild;
    }
    
    public TreeNode getRChild ( )
    {
        return this.rChild;
    }

    public TreeNode getParent ( )
    {
        return this.parent;
    }
}
