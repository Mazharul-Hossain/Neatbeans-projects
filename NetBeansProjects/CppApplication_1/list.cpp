#include<iostream>
#include<string>
#include"vertex.cpp"
using namespace std;

class EdgeList
{
	int numOfEdges;
	Edge *header,*trailer;
public:
	EdgeList(){						
		numOfEdges = 0;
		header = new Edge(NULL, NULL, NULL, NULL, NULL);
		trailer = new Edge(NULL, NULL, NULL, NULL, header);
		header->setNext(trailer);
	}
	Edge *getHeader(){
		return header;
	}
	Edge *getTrailer(){
		return trailer;
	}
	void addEdge(char *id, Vertex *v1, Vertex *v2){		// Add an edge at the end of the list
		numOfEdges++;
		Edge *e = new Edge(id,v1,v2,trailer,trailer->getPrev());
		trailer->getPrev()->setNext(e);
		trailer->setPrev(e);
	}
	void addEdge(Edge *e){
		numOfEdges++;
		trailer->getPrev()->setNext(e);
		trailer->setPrev(e);
	}
	void printList(){				// Print the vertices
		Edge *e = header->getNext();
		while(e != trailer){
			cout<<"("<<e->getId()<<" "<<e->getOrigin()->getId()<<" "<<e->getDest()->getId()<<" "<<e->getColor()<<") ";
			if(e->getColor() == 1){
				cout<<"Tree edge"<<endl;
			}
			else{
				cout<<"Back edge"<<endl;
			}
			e = e->getNext();
		}
		cout<<endl;
	}
	void printListFile(FILE *f)
        {				// Print the vertices
		Edge *e = header->getNext();
		while(e != trailer){
			//cout<<"("<<e->getId()<<" "<<e->getOrigin()->getId()<<" "<<e->getDest()->getId()<<" "<<e->getColor()<<") ";
			fprintf(f,"( %s %s %s )", e->getId(), e->getOrigin()->getId(), e->getDest()->getId());
			if(e->getColor() == 1){
				//cout<<"Tree edge"<<endl;
				fprintf(f,"Tree edge\n");
			}
			else{
				//cout<<"Back edge"<<endl;
				fprintf(f,"Back edge\n");
			}
			e = e->getNext();
		}
		cout<<endl;
	}
};



class VertexList
{					
	int numOfVertex;
	Vertex *header,*trailer;
public:
	VertexList()
        {
		numOfVertex = 0;
		header = new Vertex(NULL, NULL, NULL);
		trailer = new Vertex(NULL, NULL, header);
		header->setNext(trailer);
	}
	Vertex *getHeader()
        {
		return header;
	}
	Vertex *getTrailer(){
		return trailer;
	}
	void addVertex(char *id)
        {		// Add a vertex at the end of a list
		numOfVertex++;
		Vertex *v = new Vertex(id,trailer,trailer->getPrev());
		trailer->getPrev()->setNext(v);
		trailer->setPrev(v);
	}
	Vertex *getVertex(char *id)
        {
		Vertex *v = header->getNext();
		while(v != trailer){
			if(!strcmp(v->getId(),id))
				return v;
			v = v->getNext();
		}
		return NULL;
	}
	void printList()
        {				// Print the vertices.
		Vertex *v = header->getNext();
		cout<<"Number of Vertices = "<<numOfVertex<<endl;
		while(v != trailer){
			cout<<v->getId()<<" in = "<<v->getNum_inIncidentEdges()<<" out = " << v->getNum_outIncidentEdges()<<endl;
			v = v->getNext();
		}
		cout<<endl;
	}

	void printListFile(FILE *f)
        {				// Print the vertices.
		Vertex *v = header->getNext();
		//cout<<"Number of Vertices = "<<numOfVertex<<endl;
		fprintf(f,"Number of Vertices = %d\n",numOfVertex);
		while(v != trailer){
			//cout<<v->getId()<<" in = "<<v->getNum_inIncidentEdges()<<" out = " << v->getNum_outIncidentEdges()<<endl;
			fprintf(f,"%s, in = %d, out = %d\n",v->getId(), v->getNum_inIncidentEdges(), v->getNum_outIncidentEdges());
			v = v->getNext();
		}
		//cout<<endl;
		fprintf(f,"\n");
	}
	
};
