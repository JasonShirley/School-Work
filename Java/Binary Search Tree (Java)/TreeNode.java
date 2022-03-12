public class TreeNode 
{
    private String data;
    private TreeNode lChild;
    private TreeNode rChild;
    private TreeNode parent;
    
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

class BST
{
	private TreeNode root;
	
	public BST ()
	{
		this.root = null;
	}
	
	public boolean insert (String x)
	{
		boolean success = true;

		if (this.root == null)
		{
			this.root = new TreeNode(x);
		}
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
		else if (x.compareToIgnoreCase(p.getData()) < 0)
		{
			if (p.getLChild() == null)
			{
				q.setParent(p);
				p.setLChild(q);
			}
			else
			{
				success = insert(x,p.getLChild());
			}
		}	
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
			success = false;
		}

		return success;
	}
	
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
	
	public TreeNode locate (String v)
	{
		TreeNode p = this.root;
		TreeNode q = null;
		if(p.getData() != null)
		{
			if (p.getData().equals(v))
			{
				q = p;
			}
			else if (v.compareToIgnoreCase(p.getData()) < 0)
			{
				q = locate(p.getLChild(), v);
			}
			else
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
			if (p.getData().equals(v))
			{
				q = p;
			}
			else if (v.compareToIgnoreCase(p.getData()) < 0 && p.getLChild() != null)
			{
				q = locate(p.getLChild(), v);
			}
			else if (v.compareToIgnoreCase(p.getData()) > 0 && p.getLChild() != null)
			{
				q = locate(p.getRChild(), v);
			}
		}

		
		return q;	
	}
	
	public void delete ( TreeNode p )
	{
		if ( p.getLChild() == null && p.getRChild() == null)
		{
			if ( p == root)
			{
				root = null;
			}

			if ( p.getParent().getLChild() == p )
			{
				p.getParent().setLChild(null);
			}
		
			if ( p.getParent().getRChild() == p ) 
			{
				p.getParent().setRChild(null);
			}
		}
		else if ( p.getLChild() == null)
		{
			if ( p == root ) 
			{
				root = root.getRChild();
			}
			
			if (p.getParent().getLChild() == p)
			{
				p.getParent().setLChild(p.getRChild());
			}
			else
			{
				p.getParent().setRChild(p.getRChild());
			}
		}
		
		else if ( p.getRChild() == null)
		{
			if ( p == root ) 
			{
				root = root.getLChild();

			}
			if (p.getParent().getLChild() == p)
			{
				p.getParent().setLChild(p.getLChild());
			}
			else
			{
				p.getParent().setRChild(p.getLChild());
			}
		}


		else if ( p.getRChild() != null && p.getLChild() != null )
		{
			TreeNode q = successor(p);
			p.setData(q.getData());
			delete(q);
		}	
		
	}
			
}
