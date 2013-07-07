#include <iostream>
#include "List.cpp"
using namespace std;


class Graph
{
private:
    int size;
    int component;
public:
	NodeList <int> Vertex[100];

	Graph( int s )
	{
	    size = s;
    }

	void setAdjacent( int a, int b)
	{
	    Vertex[a].setAdjacent(b);
	    Vertex[b].setAdjacent(a);
    }

    void DFS_Visit( Position u )
    {
        u->setColor(GRAY);

        Position v = Vertex[ u->getAdjacent() ]->getHeader()->getNext();

        while( v != G.eList->getTrailer() )
        {
             if( v->getColor() == WHITE )
            {
                v.setPrev( u );
                DFS_Visit( v );
            }

             v = v->getNext();
        }
        u->setColor( BLACK );
    }

	void DFS ()
    {
        int i = 1;

        Position u = Vertex[i]->getHeader();
        u = u->getNext();

        while( i <= (size+1) )
        {
            if( u->getColor() == WHITE )
            {
                DFS_Visit( u );
            }

            i++;
            u = Vertex[i]->getHeader();
            u = u->getNext();
        }
    }
};
