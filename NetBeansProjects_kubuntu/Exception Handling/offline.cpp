#include <iostream>

using namespace std;

class MyException
{
protected:
	char  exception_type[100] ;
	char exception_message[100];
public:
	MyException(char *type,char* msg)
	{
		strcpy(exception_type,type);
		strcpy(exception_message,msg);
	}
	virtual void show()=0;
};


class NumberFormatException:public MyException
{
public:
	NumberFormatException():MyException("NumberFormatException","Not Valid Type!"){};
	void show()
	{
		cout<<exception_type<<"  "<<exception_message<<endl;
	}
};


class DivByZero : public MyException
{
public:
	DivByZero():MyException("Divide By Zero","Division by ZERO is Not Possible!!!"){}
	void show()
	{
		cout<<exception_type<<"  "<<exception_message<<endl;
	}
};

class IntegerOverFlow:public MyException
{
public:
	IntegerOverFlow():MyException("IntegerOverFlow","Integer can not store value"){};
	void show()
	{
		cout<<exception_type<<"  "<<exception_message<<endl;
	}
};

class NegetiveArraySize : public MyException
{
public:
	NegetiveArraySize():MyException("NegetiveArraySize","Array size can not be negative!"){}
	void show()
	{
		cout<<exception_type<<"  "<<exception_message<<endl;
	}
};

class ArrayIndexOutOfBound : public MyException
{
public:
	ArrayIndexOutOfBound():MyException("ArrayIndexOutOfBound","Index is out of bound,cant access"){}
	void show()
	{
		cout<<exception_type<<"  "<<exception_message<<endl;
	}
};


class Integer
{
	int n;
public:

	Integer(int num=1)
	{
		n=num;
	}

	Integer(char *str)
	{

		int mark,sign;
		int i=0;
		for(mark=0;isspace(str[mark]);mark++)
			;
		sign=1;
		if(str[mark]=='+'||str[mark]=='-')
		{
			sign=(str[mark]=='-')?-1:1;
			mark++;
		}

		for(int j=0 ;isdigit(str[mark])&&(str[mark]!=0);mark++,j++)
		{
			i=10*i+(str[mark]-'0');
		}


		if(str[mark]!=0)
		{
			NumberFormatException a;
			throw a;
		}
		if(j>10)
		{
			IntegerOverFlow a;
			throw a;
		}
		n=sign*i;
	}

	void operator += (Integer& temp)
	{
		this->n += temp.n;
	}

	friend ostream& operator << (ostream& output,const Integer& temp)
	{
		output<<temp.n;
		return output;
	}
	friend int operator / (int a,Integer n);

};

int operator / (int a,Integer d)
{
	if(d.n==0)
	{
		DivByZero a;
		throw a;
	}
	return a/d.n;
}

class IntArray
{
	Integer* arr;
	int size;
public:
	IntArray(int n)
	{
		if(n<0)
		{
			NegetiveArraySize a;
			throw a;
		}
		arr= new Integer[n];
		size=n;
	}


	IntArray(IntArray &temp)
	{
		size = temp.size;

		delete [] arr;
		arr = new Integer[size];

		for(int i=0;i<size;i++)
		{
			arr[i]=temp[i];
		}
	}

	~IntArray()
	{
		delete [] arr;
	}

	Integer& operator [] (int n)
	{
		if(n<0 || n>size-1)
		{
			ArrayIndexOutOfBound a;
			throw a;
		}
		return arr[n];
	}


	void operator = (IntArray &temp)
	{
		size = temp.size;

		delete [] arr;
		arr = new Integer[size];

		for(int i=0;i<size;i++)
		{
			arr[i]=temp[i];
		}
	}

};

int main()
{

	int i;
	int a[]={4,3,2,1,0};

	IntArray arr(5);
	try
	{
		IntArray arr2(-1);
	}
	catch(NegetiveArraySize a)
	{
		a.show();
	}

	for( i=0;i<5;i++)
		arr[i]=a[i];



	arr[2]="62";
	try
	{
		arr[3]="z2";
	}
	catch(NumberFormatException a)
	{
		a.show();
	}



	arr[2]+=arr[3];


	for(i=0;i<6;i++)
	{
		try
		{
			Integer x=10/arr[i];
			cout<<x<<endl;
		}
		catch(DivByZero n)
		{
			n.show();
		}
		catch(ArrayIndexOutOfBound t)
		{
			t.show();
		}
		catch(IntegerOverFlow a)
		{
			a.show();
		}

	}

	return 0;

}
