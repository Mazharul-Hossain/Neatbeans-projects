#include <iostream>
#include "Graph.cpp"
using namespace std;

int main(int argc, char** argv)
{
    Graph G;

    int a, b, n;
	NodeList Vertex[100];

	cout<<"How many Vertex: ";
	cin>>n;

	if( n < 1 || n > 100)
	{	return 0; }

	while (1)
	{
		cout<<("To EXIT Enter 0\n");
		cout<<"Enter 1st Vertex: ";
		cin>>a;

		if( a == 0 )    break;

		cout<<"Enter 2nd Vertex: ";
		cin>>b;

		if( b == 0 )    break;

		setAdjacent( a, b );

	}

    return 0;
}
