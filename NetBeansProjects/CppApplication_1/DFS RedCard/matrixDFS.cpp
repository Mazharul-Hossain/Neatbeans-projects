#include <iostream>
using namespace std;

#define WHITE 0
#define GRAY 1
#define BLACK 2

int Array[100][100];
int Colour[100];
int component, n;

void initial ( int n )
{
    int i,j;

    for( i = 1; i<=n; i++)
    {
        for( j = 1; j<=n; j++)
        {
            Array[i][j] = 0;
        }
    }
}

void DFS_Visit ( int v )
{
    int c, i;
    Colour[v] = GRAY;

	for( i=1;i <= n;i++)
	{
		c = Array[v][i];
		if ( c && Colour[i] == WHITE )
		{	DFS_Visit( i );    }
	}

	Colour[v] = BLACK;
}

void DFS (void)
{
    int i;
    component = 0;

	for( i = 1; i<=n ; i++)
    {

        Colour[i] = 0;
    }

	for( i=1;i<=n;i++ )
	{
		if ( Colour[i] == WHITE)
		{
			component++;
			DFS_Visit(i);
		}
	}
}

int main( void )
{
    freopen("in.txt","r",stdin);

    int a, b;

    cout<<("how many vertex : ");
    cin>>(n);

    initial ( n );

    while (1)
	{
		cout<<("\nTo EXIT Enter 0\n");
		cout<<"Enter 1st Vertex: ";
		cin>>a;

		if( a == 0 )    break;

		cout<<"Enter 2nd Vertex: ";
		cin>>b;

		if( b == 0 )    break;

		Array[a][b] = 1;
        Array[b][a] = 1;
	}


	cout<<"\n";
	for(int i = 0; i<n+1; i++)
    {
        for(int j = 0; j<n+1; j++)
        {
            cout<<Array[i][j];
            cout<<" ";
        }
        cout<<"\n";
    }

    DFS();
cout<<"Total components: "
                <<component<<endl;


    return 0;
}
