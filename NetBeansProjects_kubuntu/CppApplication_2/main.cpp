/* 
 * File:   main.cpp
 * Author: mazharul
 *
 * Created on November 26, 2010, 2:31 PM
 */

#include <cstdlib>
#include <iostream>

using namespace std;

/*
 * 
 */
int main(int argc, char** argv)
{
    int limit,i,B[10000];
    char A[100000];
    cout<<"Enter your limit : ";
    cin>>limit;
    cout<<limit;
    cout<<"\n Enter your input : ";

    for(i=0; i<limit; i++)
    {
        cout<<i;
        cin>>A[i];
        cout<<A[i];
    }

    return 0;
}

