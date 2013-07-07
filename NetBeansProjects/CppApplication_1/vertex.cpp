#include<iostream>
#include<string>
//#include"adjacencyList.cpp"
using namespace std;

#define WHITE 0
#define GRAY 1
#define BLACK 2

class Vertex
{
	char id[5];					// Vertex Identifier	
	int num_inIncidentEdges;	//number of incoming edges
	int num_outIncidentEdges;	//number of outgoing edges
	Vertex *next;				//next vertex in the list
	Vertex *prev;				//prev vertex in the list
	int color;
public:
	Vertex()
        {					// default constructor
		id = NULL;
		num_inIncidentEdges = 0;
		num_outIncidentEdges = 0;
		next = NULL;
		prev = NULL;
		color = WHITE;
	}

	Vertex(char *s, Vertex *n, Vertex *p)
        {
		if(s != NULL)
			setId(s);
		num_inIncidentEdges = 0;
		num_outIncidentEdges = 0;
		next = n;
		prev = p;
		color = WHITE;
	}

	int getColor()
        {
		return color;
	}
	void setColor()
        {
		color = GRAY;
	}
	
	Vertex *getNext()
        {
		return next;
	}
	Vertex *getPrev()
        {
		return prev;
	}
	char *getId()
        {
		return id;
	}
	int getNum_inIncidentEdges()
        {
		return num_inIncidentEdges;
	}
	int getNum_outIncidentEdges()
        {
		return num_outIncidentEdges;
	}
	void inc_num_inIncidentEdges()
        {
		num_inIncidentEdges++;		
	}
	void inc_num_outIncidentEdges()
        {
		num_outIncidentEdges++;
	}
	void setId(char *s)
        {
		strcpy(id,s);
	}
	void setNext(Vertex *v)
        {
		next = v;					
	}
	void setPrev(Vertex *v)
        {
		prev = v;
	}

};


class Edge
{
	char id[5];						// Edge Identifier
	Vertex *origin;					// Origin of the edge
	Vertex *destination;			// Destiation of the edge
	Edge *next,*prev;				// Next and previous edge on the edge list
	int color;						// 0 = unexplored; 1 = tree edge and 2 = back edge
public: 
	Edge(char *s, Vertex *v1, Vertex *v2, Edge *n, Edge *p)
        {
		if(s != NULL)
                {
			setId(s);
		}
		origin = v1;
		destination = v2;
		next = n;
		prev = p;
		color = 0;
	}
	Edge()
        {							// default constructor
		origin = NULL;
		destination = NULL;
		next = NULL;
		prev = NULL;
		color = 0;
	}
	int getColor()
        {
		return color;
	}
	void setColor(int i)
        {
		color = i;
	}
        void setId(char *s)
        {
		strcpy(id,s);
	}
	char *getId(){
		return id;
	}
	Vertex *getOrigin()
        {
		return origin;
	}
	Vertex *getDest()
        {
		return destination;
	}
	Edge *getNext(){
		return next;
	}
	Edge *getPrev(){
		return prev;
	}
	void setNext(Edge *e)
        {
		next = e;
	}
	void setPrev(Edge *e)
        {
		prev = e;
	}
	void setDest(Vertex *v)
        {
		destination = v;
	}
	void setOrigin(Vertex *v)
        {
		origin = v;
	}
};