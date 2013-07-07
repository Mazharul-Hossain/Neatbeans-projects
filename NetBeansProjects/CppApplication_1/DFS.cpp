#include<iostream>
#include"list.cpp"

using namespace std;

#define WHITE 0
#define GRAY 1
#define BLACK 2

class Graph
{
public:
	EdgeList eList;
	VertexList vList;
}   G;

void DFS ()
{
    Vertex u = G.vList->getHeader();
    u = u->getNext();
    
    while( u != G.vList->getTrailer() )
    {
        if( u->getColor() == WHITE )
        {
            DFS_Visit( u );
        }
        u = u->getNext();
    }

}

void DFS_Visit( Vertex u )
{
    u->setColor(GRAY);

    Vertex v = G->eList->getHeader()->getNext();

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
