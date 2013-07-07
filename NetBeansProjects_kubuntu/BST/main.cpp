/* 
 * File:   main.cpp
 * Author: mazhar
 *
 * Created on September 27, 2010, 9:52 PM
 */

#include <cstdlib>
#include<cstdio>
#include<iostream>
#include<cmath>

using namespace std;

/*
 * 
 */

class TreeNode
{
private:

public:
  int key;
  TreeNode *left, *right, *parent;
  int height;
  TreeNode(int _key)
  {
    key=_key;
    left=right=parent=NULL;
    height=0;
  }
};

typedef TreeNode* TreeNodePtr;

class BinaryTree
{
private:

public:
  TreeNodePtr root;
  TreeNodePtr treeSearch(TreeNodePtr ,int);
  TreeNodePtr treeMaximum(TreeNodePtr );
  TreeNodePtr treeMinimum(TreeNodePtr );
  TreeNodePtr treeSucessor(TreeNodePtr );
  void treeInsert(BinaryTree& ,int );
  TreeNodePtr treeDelete(BinaryTree& ,int );

  void inorderTreeWalk(TreeNodePtr );
  void treeHeightBalance(TreeNodePtr _node);
  void treeNodeBalance(BinaryTree &_tree,TreeNodePtr _node);
  TreeNodePtr maxNode(TreeNodePtr a,TreeNodePtr b);
};

TreeNodePtr BinaryTree:: treeSearch(TreeNodePtr _start,int _key)
{
  if(_start==NULL||_start->key==_key)return _start;
  if(_key<_start->key)return treeSearch(_start->left,_key);
  else return treeSearch(_start->right,_key);
}

TreeNodePtr BinaryTree:: treeMinimum(TreeNodePtr _start)
{
  while(_start->left!=NULL)
    _start=_start->left;
  return _start;
}

TreeNodePtr BinaryTree:: treeMaximum(TreeNodePtr _start)
{
  while(_start->right!=NULL)
    _start=_start->right;
  return _start;
}

TreeNodePtr BinaryTree:: treeSucessor(TreeNodePtr _start)
{
  TreeNodePtr temp = _start->parent;
  if(_start->right!=NULL) return treeMinimum(_start->right);
  while(temp!=NULL&&_start==temp->right)
  {
    _start=temp;
    temp=temp->parent;
  }
  return temp;
}

int MAX(int a,int b)
{
  return a>b?a:b;
}

TreeNodePtr BinaryTree:: maxNode(TreeNodePtr a,TreeNodePtr b)
{
  if(a==NULL)return b;
  if(b==NULL)return a;
  return a->height>b->height?a:b;
}

int recursiveHeightFixUp(TreeNodePtr _node)
{
  if(_node->left==NULL&&_node->right==NULL)return _node->height=1;
  if(_node->left==NULL)return _node->height = recursiveHeightFixUp(_node->right)+1;
  if(_node->right==NULL)return _node->height = recursiveHeightFixUp(_node->left)+1;
  else return _node->height = MAX(recursiveHeightFixUp(_node->right),recursiveHeightFixUp(_node->left))+1;
}

void BinaryTree:: treeNodeBalance(BinaryTree &_tree,TreeNodePtr _node)
{
  TreeNodePtr a,b,c,x,y,z,T0,T1,T2,T3;
  T0=T1=T2=T3=NULL;
  int leftHeight,rightHeight;
  while(_node!=NULL)
  {
    if(_node->left!=NULL)leftHeight=_node->left->height;
    else leftHeight=0;
    if(_node->right!=NULL)rightHeight=_node->right->height;
    else rightHeight=0;
    _node->height = MAX(leftHeight,rightHeight)+1;
    if(abs(leftHeight-rightHeight)>1)
    {
      z=_node;
      y=_tree.maxNode(z->left,z->right);
      x=_tree.maxNode(y->left,y->right);

      if(y==z->right&&x==y->right)
      {
	a=z;
	b=y;
	c=x;
	if(a)T0=a->left;
	if(b)T1=b->left;
	if(c)T2=c->left;
	if(c)T3=c->right;
      }

      if(y==z->left&&x==y->left)
      {
	c=z;
	b=y;
	a=x;
	if(a)T0=a->left;
	if(a)T1=a->right;
	if(b)T2=b->right;
	if(c)T3=c->right;
      }

      if(y==z->right&&x==y->left)
      {
	a=z;
	c=y;
	b=x;
	if(a)T0=a->left;
	if(b)T1=b->left;
	if(b)T2=b->right;
	if(c)T3=c->right;
      }

      if(y==z->left&&x==y->right)
      {
	c=z;
	a=y;
	b=x;
	if(a)T0=a->left;
	if(b)T1=b->left;
	if(b)T2=b->right;
	if(c)T3=c->right;
      }


      if(T0)T0->parent = a;
      if(T1)T1->parent = a;
      if(T2)T2->parent = c;
      if(T3)T3->parent = c;
      b->parent = z->parent;
      if(z->parent)
      {
	if(z==z->parent->left)z->parent->left = b;
	else z->parent->right = b;
      }
      else _tree.root = b;

      a->parent = b;
      c->parent = b;
      b->left = a;
      b->right = c;
      a->left = T0;
      a->right = T1;
      c->left = T2;
      c->right = T3;

      recursiveHeightFixUp(b);
      treeHeightBalance(b);
      break;
    }
    _node=_node->parent;
  }
}

void BinaryTree:: treeHeightBalance(TreeNodePtr _node)
{
  while(_node!=NULL)
  {
    if(_node->right==NULL&&_node->left==NULL)_node->height=0;
    else if(_node->right==NULL)_node->height=_node->left->height;
    else if(_node->left==NULL)_node->height=_node->right->height;
    else
    _node->height = _node->right->height>_node->left->height?_node->right->height:_node->left->height;
    _node->height++;
    _node=_node->parent;
  }
}

void BinaryTree:: treeInsert(BinaryTree &_tree,int _val)
{
  TreeNodePtr z = new TreeNode(_val);
  TreeNodePtr y = NULL;
  TreeNodePtr x = _tree.root;
  while(x!=NULL)
  {
    y=x;
    if(z->key<x->key)x=x->left;
    else x=x->right;
  }

  z->parent=y;
  if(y==NULL)
  {
    _tree.root=z;
  }
  else if(z->key<y->key)y->left=z;
  else y->right=z;
  _tree.treeNodeBalance(_tree,z);
}

TreeNodePtr BinaryTree:: treeDelete(BinaryTree &_tree,int _val)
{
  TreeNodePtr x,y;
  TreeNodePtr z = treeSearch(_tree.root,_val);
  if(z->left==NULL || z->right==NULL)y=z;
  else y= treeSucessor(z);

  if(y->left!=NULL)x=y->left;
  else x=y->right;

  if(x!=NULL) x->parent=y->parent;

  if(y->parent==NULL)_tree.root=x;
  else if(y==y->parent->left)y->parent->left=x;
  else y->parent->right=x;

  if(y!=z)z->key=y->key;
  y->height=0;
  _tree.treeNodeBalance(_tree,y->parent);
  return y;
}
void BinaryTree:: inorderTreeWalk(TreeNodePtr _start)
{
  if(_start!=NULL)
  {
    inorderTreeWalk(_start->left);
    cout<<_start->key<<" ";
    inorderTreeWalk(_start->right);
  }
}

int main(int argc, char** argv)
{
    BinaryTree T;
  T.root=NULL;
  int nodeNo,nodeVal;
  cout<<"How many nodes you want to insert...??\n";

  cin>>nodeNo;
  while(nodeNo--)
  {
    cin>>nodeVal;
    T.treeInsert(T,nodeVal);
  }
  T.inorderTreeWalk(T.root);
  cin>>nodeNo;
  while(nodeNo--)
  {
    cin>>nodeVal;
    delete T.treeDelete(T,nodeVal);
  }
  T.inorderTreeWalk(T.root);
  return 0;
}
//12 44 17 32 28 29 88 97 65 54 82 76 80 2 44 54