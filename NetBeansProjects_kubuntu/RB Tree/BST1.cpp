#include<iostream>

using namespace std;

#define RED 1
#define BLACK 0

class BST
{

protected:

    struct Node
    {
        int element;
        bool color;
        Node* parent;
        Node* left;
        Node* right;


        Node(int e=0,bool c=RED,Node* p=NULL,Node* l=NULL,Node* r=NULL)
        {
            parent=p;
            left=l;
            right=r;
            element=e;
            color=c;
        }

        Node* sibling()
        {
            if( this==parent->left )
                return parent->right;
            else
                return parent->left;
        }

        Node* grandfather()
        {
            return parent->parent;
        }

        Node* uncle()
        {
            return parent->sibling();
        }
    };



public:
    typedef Node* NodePtr;

    class Position
    {
        NodePtr node;

    public:

        Position( NodePtr n=NULL )
        {
            node=n;
        }

        int element()
        {
            return node->element;
        }

        bool color()
        {
            return node->color;
        }

        friend class BST;
    };


protected:

    NodePtr theRoot;
    int sz;

    NodePtr nodePtr(Position p)
    {
        if(p.node==NULL)
        {
            cout<<"Position is pointing to null";
            return NULL;
        }
        return p.node;
    }

public:

    BST()
    {
        theRoot=new Node;
        sz=1;
    }
    
    int size()
    {
        return sz;
    }

    bool isEmpty()
    {
        return sz==0;
    }

    Position root()
    {
        return Position(theRoot);
    }

    Position leftChild(Position &p)
    {
        return Position(nodePtr(p)->left);
    }

    Position rightChild(Position &p)
    {
        return Position( nodePtr(p)->right );
    }

    Position parent( Position &p )
    {
        return Position (nodePtr(p)->parent);
    }

    Position sibling(Position &p)
    {
        return Position(nodePtr(p)->sibling());
    }

    Position uncle(Position &p)
    {
        return Position(nodePtr(p)->uncle());
    }

    Position grandfather(Position &p)
    {
        return Position(nodePtr(p)->grandfather());
    }

    bool isRoot(Position &p)
    {
        return isRoot(nodePtr(p));
    }

    bool isRoot(NodePtr n)
    {
        return n == theRoot;
    }

    bool isExternal(Position p)
    {
        return isExternal(nodePtr(p) );
    }

    bool isExternal(NodePtr n)
    {
        return ( (n->left==NULL) && (n->right==NULL) );
    }

    bool isInternal(Position &p)
    {
        return isInternal(nodePtr(p));
    }

    bool isInternal(NodePtr n)
    {
        return !isExternal(n);
    }

    void replaceElement(Position &p,int n)
    {
        replaceElement(nodePtr(p),n);
    }

    void replaceElement(NodePtr node,int n)
    {
        node->element=n;
    }

    void swapElement(Position &p,Position &q)
    {
        swapElement(nodePtr(p),nodePtr(q));
    }

    void swapColor(Position &p,Position &q)
    {
        swapColor(nodePtr(p),nodePtr(q) );
    }

    void swapElement(NodePtr m,NodePtr n)
    {
        int temp=m->element;
        m->element=n->element;
        n->element=temp;
    }

    void swapColor(NodePtr m,NodePtr n)
    {
        bool temp=m->color;
        m->color=n->color;
        n->color=temp;
    }

    void expandExternal(Position &p)
    {
        expandExternal(nodePtr(p));
    }

    void expandExternal(NodePtr n)
    {
        n->left=new Node;
        n->right=new Node;

        n->left->parent=n;
        n->right->parent=n;

        n->left->color=BLACK;
        n->right->color=BLACK;


        sz+=2;
    }

    void setRoot(NodePtr n)
    {
        theRoot=n; n->parent=NULL;
    }

    Position removeAboveExternal(Position p)
    {
        return Position(removeAboveExternal(nodePtr(p)) );
    }

    NodePtr removeAboveExternal(NodePtr n)
    {
        NodePtr p=n->parent;
        NodePtr s=n->sibling();

        if(isRoot(p) )
            setRoot(s);
        else
        {
            NodePtr g=p->parent;

            if(p==g->left)
                g->left=s;
            else
                g->right=s;

            s->parent=g;

        }
        delete n;
        delete p;

        sz-=2;

        return s;
    }

    int depth(Position p)
    {
        if(isRoot(p) )
            return 0;
        else
            return (1+depth(parent(p) ) );
    }

    void inOrderTravarsal(Position p)
    {
        if(isExternal(p) )
            return;
        else
        {
            inOrderTravarsal( leftChild(p) );

            if(p.color()==0)
                cout<<"("<<p.element()<<" "<<depth(p)<<" "<<"BLACK"<<") ";
            else
                cout<<"("<<p.element()<<" "<<depth(p)<<" "<<"RED"<<") ";

            inOrderTravarsal(rightChild(p));
        }
    }


    void preOrderTravarsal(Position p)
    {
        if(isExternal(p) )
            return;
        else
        {
            cout<<p.element()<<" ";
            preOrderTravarsal(leftChild(p));
            preOrderTravarsal(rightChild(p));
        }
    }

    void postOrderTravarsal(Position p)
    {
        if(isExternal(p) )
            return;
        else
        {
            postOrderTravarsal(leftChild(p));
            postOrderTravarsal(rightChild(p));
            cout<<p.element()<<" ";
        }
    }



    Position inOrderSuccessor(Position &r)
    {
        Position p=rightChild(r);

        while(1)
        {
            if(isExternal(p))return parent(p);
            p=leftChild(p);
        }
    }


    Position search(Position p,int n)
    {
        if(isExternal(p) )
            throw -1;
        else if(n==p.element() )
            return p;
        else if(n<p.element() )
            return search(leftChild(p),n);
        else
            return search(rightChild(p),n);
    }

    Position deletion(int n)
    {

        Position p=search(root(),n);
        
        if( isExternal(leftChild(p) ) )
        {
            return removeAboveExternal(leftChild(p) );
        }
        else if(isExternal(rightChild(p)))
        {
            return removeAboveExternal(rightChild(p) );
        }
        else
        {
            Position q=inOrderSuccessor(p);
            replaceElement(p,q.element() );

            return removeAboveExternal(leftChild(q) );
        }


    }

    Position insertion(Position p,int n)
    {
        if(isExternal(p))
        {
            expandExternal(p);
            replaceElement(p,n);
            nodePtr(p)->color=RED;
            return p;
        }

        else if(n<=p.element())
        {
            return insertion(leftChild(p),n);
        }

        else
        {
            return insertion(rightChild(p),n);
        }
    }

    int max(int a,int b)
    {
        if(a>=b)return a;
        else return b;
    }

    int height(Position p)
    {
        if(isExternal(p) )
            return 0;
        else
            {
			int h=0;
			h=max(h,height(leftChild(p)));
			h=max(h,height(rightChild(p)));
			return h+1;
		}
            //return ( 1+max( height(leftChild(p) ) , height(rightChild(p) ) ) );
    }
};