#include<iostream>
using namespace std;

int max(int i,int j)
{
	if(i>j)return i;
	else return j;
}

template < typename Key, typename Element >
class BinarySearchTree
{
protected:
    struct Node
    {
        Element      element;				// the element
        Node*       parent;				// parent
        Node*       left;				// left child
        Node*       right;				// right child
        Key         key;                                // the key
        Node()                                          // default constructor
        {
            parent = left = right = 0;
        }
        Node* sibling() const
        {                                               // get our sibling
            return (this == parent->left ? parent->right : parent->left);
        }
    };
    typedef Node* NodePtr;                              //a node pointer
public:
    class Position
    {
    public:
        NodePtr node;
    public:
        Position(NodePtr n= 0)
        {
            node=n;
        }
        Element& element()
        {
            return node->element;
        }
        bool isNull()
        {
            return node == 0;
        }
        Key& key()
        {
            return node->key;
        }
        friend class BinarySearchTree;
    };
private:
    NodePtr theRoot;
    int sz;
protected:
    NodePtr nodePtr(const Position& v)		// convert to NodePtr
    {
        return v.node;
    }
    bool isExternal(NodePtr n) const 			// is node external?
    {
        return (n->left == 0 && n->right == 0);
    }
    bool isInternal(NodePtr n) const 			// is node internal?
    {
        return ! isExternal(n);
    }
    bool isRoot(NodePtr n) const 				// is node the root?
    {
        return (n == theRoot);
    }
    void setRoot(NodePtr r)				// make r the root
    {
        theRoot = r;
        r->parent = 0;
    }
    void replaceElement(NodePtr n, const Element& o)	// replace element
    {
        n->element = o;
    }
    void replaceKey(NodePtr n, const Key& k)	// replace element
    {
        n->key = k;
    }
    void swapElements(NodePtr n, NodePtr w)             // swap elements
    {
        Element temp = w->element;
        w->element = n->element;
        n->element = temp;
    }
    void expandExternal(NodePtr n)                      // expand external node
    {
        n->left   = new Node;
        n->left->parent = n;
        n->right  = new Node;
        n->right->parent = n;
        sz += 2;
    }
    NodePtr removeAboveExternal(NodePtr n)		// remove n and parent
    {
        NodePtr p = n->parent;
        NodePtr s = n->sibling();
        if (isRoot(p)) setRoot(s);				// p was root; now s is
        else
        {
            NodePtr g = p->parent;				// the grandparent
            if (p == g->left)	    g->left  = s;		// replace parent by sibling
            else 		    g->right = s;
            s->parent = g;
        }
        delete n;
        delete p;					// delete removed nodes
        sz -= 2;						// two fewer nodes
        return s;
    }
    Position finder(const Key &k,const Position &p)
    {
        if(isExternal(p)) return p;
        Key curKey = nodePtr(p)->key;
        if(k<curKey) return finder(k,leftChild(p));
        else if(k>curKey) return finder(k,rightChild(p));
        else return p;
    }
    Position inserter(const Key &k,const Element &o)
    {
        Position p=finder(k,root());
        while(isInternal(p)) p=finder(k,rightChild(p));
        expandExternal(p);
        setItem(p,o,k);
        return p;
    }
    Position remover(const Position &r)
    {
        Position p;
        if(isExternal(leftChild(r))) p = leftChild(r);
        else if(isExternal(rightChild(r))) p = rightChild(r);
        else
        {
            p = rightChild(r);
            do
            {
                p=leftChild(p);
            }
            while(isInternal(p));
            setItem(r,parent(p).element(),parent(p).key());
        }
        return removeAboveExternal(p);
    }
public:
    BinarySearchTree()					// constructor
    {
        theRoot = new Node;
        sz = 1;
    }
    int size() const  					// size of tree
    {
        return (sz-1)/2;
    }
    bool isEmpty() const 					// is tree empty?
    {
        return (size() == 0);
    }
    Position root() const 				// returns root
    {
        return Position(theRoot);
    }
    Position leftChild(const Position& v) const 		// returns left child
    {
        return Position((v.node)->left);
    }
    Position rightChild(const Position& v) const 		// returns right child
    {
        return Position((v.node)->right);
    }
    Position parent(const Position& v) const 		// returns parent
    {
        return Position(v.node->parent);
    }
    Position sibling(const Position& v) const 		// returns sibling
    {
        return Position(nodePtr(v)->sibling());
    }
    bool isRoot(const Position& v) const 			// is v the root?
    {
        return isRoot(v.node);
    }
    bool isInternal(const Position& v) const 		// is v internal?
    {
        return isInternal(v.node);
    }
    bool isExternal(const Position& v) const 		// is v external?
    {
        return isExternal(v.node);
    }
    Key key( Position& v)
    {
        return v.key();
    }
    Element element( Position& v)
    {
        return v.element();
    }
    void replaceElement(const Position& v, const Element& o)			// replace element
    {
        replaceElement(nodePtr(v), o);
    }
    void replaceKey(const Position& v, const Key& k)
    {
        replaceKey(nodePtr(v),k);
    }
    void setItem(const Position &v,const Element &elem,const Key &k)
    {
        replaceElement(nodePtr(v),elem);
        replaceKey(nodePtr(v),k);
    }
    void swapElements(const Position& v, const Position& w)
    {
        swapElements(nodePtr(v), nodePtr(w));
    }		// swap elements
    void expandExternal(const Position& v)
    {
        expandExternal(nodePtr(v));
    }			// expand external node
    Position removeAboveExternal(const Position& v)	// remove v and parent
    {
        return Position(removeAboveExternal(nodePtr(v)));
    }
    Position find(const Key &k)
    {
        Position p =finder(k,root());
        return p;
    }
    void insertItem(const Key &k,const Element &o)
    {
        inserter(k,o);
    }
    void removeElement(const Key &k)
    {
        Position p=finder(k,root());
        remover(p);
    }
    void printInOrder(const Position &p)
    {
        if(isInternal(leftChild(p)))printInOrder(leftChild(p));
        cout<< p.node->key << "\n" << p.node->element << "\n";
        if(isInternal(rightChild(p)))printInOrder(rightChild(p));
    }
	int depth(const Position &p)
	{
		if(isRoot(p)) return 0;
		else return 1+depth(parent(p));
	}
	int depth(const Key &k)
	{
		return depth(find(k));
	}
	int height(const Position &p)
	{
		if(isExternal(p)) return 0;
		else
		{
			int h=0;
			h=max(h,height(leftChild(p)));
			h=max(h,height(rightChild(p)));
			return h+1;
		}
	}
};