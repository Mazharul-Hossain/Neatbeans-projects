template <typename Key, typename Element>

class RBTree : public BinarySearchTree<Key, Element, RBItem<Key,Element> >
{

protected:

    typedef RBItem<Key, Element> 		Item;		// a tree node item
    typedef BinarySearchTree<Key, Element, Item>  BST;	// base search tree
    typedef BST::BTPosition 		BTPosition;	// a tree position

public: 						// public types

    typedef BST::Position 		Position;	// a position

protected: 						// local utilities
    Color color(const BTPosition& p) const  // get position's color
    {
        if (T.isExternal(p))
            return BLACK;			// externals are black
        return p.element().color();
    }

    bool isRed(const BTPosition& p) const 		// is p red?
    {
        return color(p) == RED;
    }

    bool isBlack(const BTPosition& p) const 		// is p black?
    {
        return color(p) == BLACK;
    }

    void setRed(const BTPosition& p)			// make p red
    {
        if ( T.isInternal(p) )
        {
            p.element().setColor(RED);
        }
    }

    void setBlack(const BTPosition& p)			// make p black
    {
        if ( T.isInternal(p) )
        {
            p.element().setColor(BLACK);
        }
    }

    void setColor(const BTPosition& p, Color color)	// set p's color
    {
        if (T.isInternal(p))
        {
            p.element().setColor(color);
        }
    }

    bool hasTwoExternalChildren(const BTPosition& p) const // 2 external children?
    {
        return ( T.isExternal( T.leftChild(p) ) &&
    	      T.isExternal( T.rightChild(p) ) );
    }

    bool hasRedChild(const BTPosition& p) const 		// does p have red child?
    {
        return ( isRed(T.leftChild(p) ) || isRed( T.rightChild(p) ) );
    }
  // ... (other utilities omitted)

public:
    RBTree() : BST()
    {
    }					// constructor

    void insertItem(const Key& k, const Element& e)   // insert (key,element)
    {
        BTPosition z = inserter(k, e);			// insert in base tree
        if (T.isRoot(z))
            setBlack(z);					// root is always black
        else
            remedyDoubleRed(z);				// rebalance if needed
    }

protected:
    void remedyDoubleRed(const BTPosition& z)   // fix double-red z
    {
        BTPosition v = T.parent(z);				// v is z's parent
        if (T.isRoot(v) || isBlack(v))
            return;		// v is black, all ok
    							// z, v are double-red
        if (isBlack(T.sibling(v)))
        {			// Case 1: restructuring
            v = T.restructure(z);
            setBlack(v);					// top vertex now black
            setRed(T.leftChild(v)); setRed(T.rightChild(v));	// children are red
        }
        else
        {						// Case 2: recoloring
            setBlack(v);					// make v black
            setBlack(T.sibling(v));				// ..and its sibling
            BTPosition u = T.parent(v);			// u is v's parent

            if (T.isRoot(u))
                return;
            setRed(u);					// make u red
            remedyDoubleRed(u);				// may need to fix u now
        }
    }

public:
    void removeElement(const Key& k)			// remove using key
        throw( NonexistentElementException )
    {
        BTPosition u = finder(k, T.root());			// find the node
        if (u.isNull())					// not found?
            throw NonexistentElementException("Remove nonexistent element");

        BTPosition r = remover(u);				// remove u

        if (T.isRoot(r) || isRed(r) || wasParentRed(r))
            setBlack(r);					// fix by color change
        else 						// r, parent both black
            remedyDoubleBlack(r);				// fix double-black r
    }

protected:
    void remedyDoubleBlack ( const BTPosition& r)  // fix double-black r
    {
        BTPosition x, y, z;
        x = T.parent(r);
        y = T.sibling(r);
        if (isBlack(y))
        {
            if ( hasRedChild(y) )
            {				// Case 1: restructuring
                z = redChild(y);
                Color oldColor = color(x);			// save top vertex color
                z = T.restructure(z);				// restructure x,y,z
                setColor(z, oldColor);
                setBlack(r);	// fix colors
                setBlack(T.leftChild(z));    setBlack(T.rightChild(z));
            }
            else            // Case 2: recoloring
            {
                setBlack(r); setRed(y);				// r=black, y=red
                if ( isBlack(x) && !T.isRoot(x) )
                    remedyDoubleBlack(x);				// fix double-black x
                setBlack(x);
            }
        }
        else
        {						// Case 3: adjustment
            if ( y == T.rightChild(x) )
                z = T.rightChild(y);	// z is the grandchild
            else
                z = T.leftChild(y);	// ...on same side as y

            T.restructure(z);					// restructure x,y,z
            setBlack(y);
            setRed(x);				// y=black, x=red
            remedyDoubleBlack(r);				// fix by Case 1 or 2
        }
    }
};