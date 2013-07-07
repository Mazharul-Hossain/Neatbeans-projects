/* 
 * File:   main.cpp
 * Author: mazharul
 *
 * Created on October 23, 2010, 12:29 AM
 */

#include <cstdlib>
#include "rbtree.cpp"

using namespace std;

/*
 * 
 */
int main(int argc, char** argv)
{
    int a;
    Red_Black tree;

    while(1)
    {
        int n;
        cout<<"1. Insert"<<endl;
        cout<<"2. Delete"<<endl;
        cout<<"3. Show(Inorder Travarsal)"<<endl;
        cout<<"4. Exit"<<endl<<endl;

        cout<<"Enter Your Choice: ";
        cin>>n;
        cout<<endl<<endl;

        if(n==1)
        {
            cout<<"Insert Value: ";
            cin>>a;
            tree.RBinsert(a);
        }

        else if(n==2)
        {
            cout<<"Value To Delete: ";
            cin>>a;
            tree.RBdelete(a);
        }

        else if(n==3)
        {
            cout<<"InOrder Travarsal: (Value Depth Color)"<<endl<<endl;
            tree.inOrderTravarsal(tree.root());
        }

        else if(n==4)
        {
            break;
        }

        else
        {
            cout<<"Please Give Choice Between (1-4)";
        }

        cout<<endl<<endl;
    }

    return 0;
}