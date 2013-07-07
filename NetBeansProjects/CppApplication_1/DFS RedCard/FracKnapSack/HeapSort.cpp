#include<iostream>
using namespace std;

int M, N, W;

struct Goods
{
	int index;
	int wieght_has;
	int wieght_taken;
	int benifit;
	double pi;
} goods[100];

void maxHeapify(int i)
{
	int largest;

	int l = 2*i;
	int r = 2*i + 1;

	if ( ( l < N ) &&
         (goods[l].pi > goods[i].pi) )
	{
	    largest = l;
    }
	else
	{
	    largest = i;
    }

	if ( (r < N) &&
         (goods[r].pi > goods[largest].pi) )
	{
	    largest = r;
    }

	if (largest != i)
	{
		struct Goods temp = goods[i];
		goods[i] = goods[largest];
		goods[largest] = temp;

		maxHeapify(largest);
	}
}

void buildMaxHeap()
{
	for(int i = N/2; i>=0; i--)
	{
		maxHeapify(i);
	}
}

int main (void)
{
	freopen("in.txt","r",stdin);

	int i, a, b;

	i = 0;
	while(1)
	{
		cout<<"\nTo Exit press -1 -1 \n";
		cout<<"Enter Weight : ";
		cin>>a;

		cout<<"Enter Benifit : ";
		cin>>b;

		if( a == -1 && b == -1 )
		{
			break;
		}

		goods[i].wieght_has = a;
		goods[i].benifit = b;
		goods[i].pi = double(b)/a;
        goods[i].index = i + 1;
        goods[i].wieght_taken = 0;
        i++;

	}

	N = i;

	cout<<"\nEnter Total Weight : ";
	cin>>W;
	cout<<"\n\n";
	for(i=0; i<N; i++)
	{
		cout<<goods[i].index
            <<"	Weight : "<<goods[i].wieght_has
			<<"	Benifit : "<<goods[i].benifit
			<<"	Ratio : "<<goods[i].pi<<"\n";
	}
	cout<<"\n\n";

	buildMaxHeap();

	int w = 0, n = N;
	i = 0;
    a = 0;
    double sum = 0,temp;

    while( w < W )
	{
	    a = ( goods[0].wieght_has > ( W-w ) )
			? (W-w) : goods[0].wieght_has ;

		goods[0].wieght_taken = a;

		temp = goods[0].pi * goods[0].wieght_taken;
	    sum = sum + temp;
		cout<<goods[0].index
			<<"	Ratio : "<<goods[0].pi
			<<"	Weight : "<<goods[0].wieght_taken
			<<"	Benifit : "<<temp<<"\n";

		w = w + a;
		i++;

		if( i == n )
		{
			break;
		}

		struct Goods temps = goods[0];
		goods[0] = goods[N-1];
		goods[N-1] = temps;
		N--;

		maxHeapify(0);
	}
	cout<<"\n\n";
	cout<<"\nTotal Weight : "<<W<<"\n";
	cout<<"TOTAL BENIFIT : "<<sum<<"\n\n\n";

	return 0;
}
