#include<iostream>
#include <vector>
using namespace std;

#define WHITE 0
#define GRAY 1
#define BLACK 2

int n, source, dest, component;

class VERTEX
{
public:
	int Colour;
	vector <int> Adj;
}   vertex[100];

void DFS_Visit(int v)
{
	vertex[v].Colour=GRAY;

	for(int i=0; i<vertex[v].Adj.size(); i++)
	{
		if (vertex[vertex[v].Adj[i]].Colour == WHITE)
		{   DFS_Visit(vertex[v].Adj[i]);    }
	}

	vertex[v].Colour=BLACK;
}

void DFS()
{
	int i;
	component=0;

	for(i=1;i<=n;i++)
	{	vertex[i].Colour=WHITE; }

	for(i=1;i<=n;i++)
	{
		if (vertex[i].Colour==WHITE)
		{
			component++;
			DFS_Visit(i);
		}
	}
}

int main()
{
	freopen("in.txt","r",stdin);

	cout<<"How many vertices?";
	cin>>n;

	 while (1)
	{
		cout<<("\nTo EXIT Enter 0\n");
		cout<<"Enter 1st Vertex: ";
		cin>>source;

		if( source == 0 )    break;

		cout<<"Enter 2nd Vertex: ";
		cin>>dest;

		if( dest == 0 )    break;

		vertex[source].Adj.push_back(dest);
		vertex[dest].Adj.push_back(source);
	}

	DFS();

	cout<<"Total components: "<<component<<endl;

	return 0;
}
