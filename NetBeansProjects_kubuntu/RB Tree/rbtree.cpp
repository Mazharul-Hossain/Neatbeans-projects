#include "BST1.cpp"

class Red_Black : public BST
{

public:

    void leftRotate(NodePtr x)
    {
        NodePtr y=x->right;
        x->right=y->left;

        y->left->parent=x;
        y->parent=x->parent;

        if(x->parent==NULL)
            setRoot(y);

        else if(x==x->parent->left)
            x->parent->left=y;

        else
            x->parent->right=y;

        y->left=x;
        x->parent=y;

    }


    void rightRotate(NodePtr y)
    {
        NodePtr x=y->left;
        y->left=x->right;

        x->right->parent=y;
        x->parent=y->parent;

        if(y->parent==NULL)
            setRoot(x);

        else if(y==y->parent->right)
            y->parent->right=x;

        else
            y->parent->left=x;

        x->right=y;
        y->parent=x;

    }


    void RBinsert(int n)
    {
        NodePtr z=nodePtr(insertion(root(),n));

        if(z==theRoot)
        {
            z->color=BLACK;
            return ;
        }
        NodePtr y;
        
        while(z->parent->color==RED)
        {


            if(z->parent==z->parent->parent->left)
            {
                y=z->parent->parent->right;

                if(y->color==RED)
                {
                    z->parent->color=BLACK;
                    y->color=BLACK;

                    z->parent->parent->color=RED;
                    z=z->parent->parent;
                }

                else if(z==z->parent->right)
                {
                    z=z->parent;
                    leftRotate(z);
                }

                else if(z==z->parent->left)
                {
                    z->parent->color=BLACK;
                    z->parent->parent->color=RED;

                    rightRotate(z->parent->parent);
                }
            }

            else
            {
                y=z->parent->parent->left;

                if(y->color==RED)
                {
                    z->parent->color=BLACK;
                    y->color=BLACK;

                    z->parent->parent->color=RED;
                    z=z->parent->parent;
                }

                else if(z==z->parent->left)
                {
                    z=z->parent;
                    rightRotate(z);
                }

                else if(z==z->parent->right)
                {
                    z->parent->color=BLACK;
                    z->parent->parent->color=RED;

                    leftRotate(z->parent->parent);
                }
            }
            if(theRoot==z)break;
        }

        theRoot->color=BLACK;
    }

    void RBdelete(int n)
    {
        try
        {
            NodePtr x=nodePtr(deletion(n));
            NodePtr w;

            while(x!=theRoot && x->color==BLACK)
            {
                if(x==x->parent->left)
                {
                    w=x->parent->right;
                    if(w->color==RED)
                    {
                        w->color=BLACK;
                        x->parent->color=RED;
                        leftRotate(x->parent);
                        w=x->parent->right;
                    }
                    if(isExternal(w) )break;
                    if(w->left->color==BLACK && w->right->color==BLACK)
                    {
                        w->color=RED;
                        x=x->parent;
                    }
                    else
                    {
                        if(w->right->color==BLACK)
                        {
                            w->left->color=BLACK;
                            w->color=RED;
                            rightRotate(w);
                            w=x->parent->right;
                        }

                        w->color=x->parent->color;
                        x->parent->color=BLACK;
                        w->right->color=BLACK;
                        leftRotate(x->parent);
                        x=theRoot;
                    }
                }

                else
                {
                    w=x->parent->left;
                    if(w->color==RED)
                    {
                        w->color=BLACK;
                        x->parent->color=RED;
                        rightRotate(x->parent);
                        w=x->parent->left;
                    }
                    if(isExternal(w) )break;
                    if(w->right->color==BLACK && w->left->color==BLACK)
                    {
                        w->color=RED;
                        x=x->parent;
                    }
                    else
                    {
                        if(w->left->color==BLACK)
                        {
                            w->right->color=BLACK;
                            w->color=RED;
                            leftRotate(w);
                            w=x->parent->left;
                        }

                        w->color=x->parent->color;
                        x->parent->color=BLACK;
                        w->left->color=BLACK;
                        rightRotate(x->parent);
                        x=theRoot;
                    }
                }
            }
            x->color=BLACK;
        }
        catch(int i)
        {
            if(i==-1)cout<<"Value: "<<n<<" Not Found "<<endl;
        }
    }
};