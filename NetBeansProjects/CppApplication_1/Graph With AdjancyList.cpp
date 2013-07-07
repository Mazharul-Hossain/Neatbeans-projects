/*
 * File:   main.cpp
 * Author: MAZHAR
 *
 * Created on March 8, 2011, 7:48 PM
 */

#include <iostream>
using namespace std;

/*
 *
 */

template <typename Object>
class NodeList
{
protected:
  // ... (insert Node class definition here)
    struct Node
    {					// node in the NodeList
        Object  element;
        Node*   prev;
        Node*   next;
        Node(const Object& e = Object(), Node* p = NULL, Node* n = NULL)
                : element(e), prev(p), next(n) { }      	// constructor
      };
      typedef Node* NodePtr;				// pointer to a Node

public:
  // ... (insert Position class definition here)
    class Position
    {					// position in NodeList
    private:
        NodePtr node;					// pointer to the node
    public:

        Position(NodePtr n = NULL)				// constructor
        { node = n; }

        Object& element() const 				// return element
                //throw(InvalidPositionException)
        {
            // if (node == NULL) throw InvalidPositionException("Null position");
            if (node != NULL)
                return node->element;
        }

        bool isNull() const 				// a null position?
        { return node == NULL; }

        friend class NodeList<Object>;			// allow access
    };

protected:                          // utility to convert Position to node pointer
  NodePtr nodePtr(const Position& p) const
          //throw(InvalidPositionException)
  {
      //if (p.node == NULL)
      //    throw InvalidPositionException("Attempt to use null position");
      //else return p.node;
      if (p.node != NULL)
          return p.node;
  }

protected: 						// data members
    int       sz;        					// number of items
    NodePtr   header;					// head of list sentinel
    NodePtr   trailer;					// tail of list sentinel

public:

    NodeList()
    {						// default constructor
        sz = 0;
        header   = new Node;				// create sentinels
        trailer  = new Node;
        header->next   = trailer;				// head points to trailer
        trailer->prev  = header;				// trailer points to head
    }

    int size() const 					// list size
    { return sz; }

    bool isEmpty() const 					// is the list empty?
    { return (sz == 0); }

    bool isFirst(const Position& p) const 		// is this the first?
      //throw(InvalidPositionException)
    {
        NodePtr v = nodePtr(p);
        return v->prev == header;
    }

    bool isLast(const Position& p) const 			// is this the last?
      //throw(InvalidPositionException)
    {
        NodePtr v = nodePtr(p);
        return v->next == trailer;
    }

    Position first() const 				// return first element
        //throw(EmptyContainerException)
    {
        //if (isEmpty()) throw EmptyContainerException("List is empty");

        if ( ! isEmpty() )
            return Position(header->next);
    }

    Position before(const Position& p) const 		// return item before p
       //throw(BoundaryViolationException, InvalidPositionException)
    {
        NodePtr v = nodePtr(p);
        NodePtr prev = v->prev;
        //if (prev == header) throw BoundaryViolationException("Advance past beginning of list");

        if ( prev != header )
            return Position(prev);
    }

   Position insertBefore(const Position& p, const Object& element)
       //throw(InvalidPositionException)
    {			// insert after p
        NodePtr v = nodePtr(p);
        sz++;
        NodePtr newNode  = new Node(element, v->prev, v);
        v->prev->next    = newNode;				// link node into list
        v->prev	     = newNode;
        return Position(newNode);
    }

    Position insertAfter(const Position& p, const Object& element)
       //throw(InvalidPositionException)
    {			// insert after p
        NodePtr v = nodePtr(p);
        sz++;
        NodePtr newNode  = new Node(element, v, v->next);
        v->next->prev    = newNode;				// link node into list
        v->next	     = newNode;
        return Position(newNode);
    }

    void insertFirst( const Object& element)			// insert in a given node
        //throw(InvalidPositionException)
    {
        insertAfter( header, element);
    }

    void insertLast( const Object& element)			// insert in a given node
        //throw(InvalidPositionException)
    {
        insertBefore( trailer, element);
    }
    void remove(const Position& p)			// remove a given node
        //throw(InvalidPositionException)
    {
        sz--;
        NodePtr v = nodePtr(p);
        v -> prev -> next    = v -> next;				// unlink from the list
        v -> next -> prev    = v -> prev;
        delete v;
    }

    Object& elementAt(const Position& p)
    {
        NodePtr v  = nodePtr(p);
        return v -> element;
    }

    void replaceElement(const Position& p, const Object& element)
        //throw(InvalidPositionException)
    {			// replace element
        NodePtr v  = nodePtr(p);
        v -> element = element;
    }

    void checkVertex( const Object& element )
    {
        NodePtr v  = nodePtr( header->next->next);

        for( v;  ;  v  = nodePtr ( v->next ) )
        {
            if( v->element == element )
            {
                cout<<("They have edge\n");
                break;
            }
            if( v->next == trailer )
            {
                cout<<("They have no edge\n");
                break;
            }
        }
    }

    void showAdjacent( void )
    {
        NodePtr v  = nodePtr( header->next->next);

        for( v;  ;  v  = nodePtr ( v->next ) )
        {
            cout<<( v->element );
            cout<<( "   " );

            if( v->next == trailer )
            {
                cout<<( "\n" );
                break;
            }
        }

    }

};

int main(int argc, char** argv)
{
    NodeList <int> Vertex[100];

    int a, b, n;

    cout<<("how many vertex : ");
    cin>>(n);

    for(int i = 0; i<n; i++)
    {
        cout<<("Enter vertex : ");
        cin>>(a);
        Vertex[i].insertLast(a);
        while(1)
        {
            cout<<("To EXIT Press 0\n");
            cout<<("Enter adjacent vertex : ");
            cin>>(a);
            if( a==0 )    break;
            Vertex[i].insertLast(a);
        }
    }
    while(1)
    {
        cout<<("1. Checking Adjacency\n");
        cout<<("2. Showing Adjacent\n");
        cout<<("3. EXIT\n\n");
        cout<<("Enter your choice : ");
        cin>>(a);

        if( a == 1 )
        {
            cout<<("Enter first vertex : ");
            cin>>(a);
            cout<<("Enter first vertex : ");
            cin>>(b);
            for(int i = 0; i<n; i++)
            {
                if( Vertex[i].elementAt( Vertex[i].first() ) == a )
               {
                   Vertex[i].checkVertex(b);
                   break;
               }
            }
        }
        else if( a == 2 )
        {
            cout<<("Enter vertex : ");
            cin>>(a);
            for(int i = 0; i<n; i++)
            {
                if( Vertex[i].elementAt( Vertex[i].first() ) == a )
               {
                   Vertex[i].showAdjacent();
                   break;
               }
            }
        }
        else
            break;

    }

    return 0;
}

