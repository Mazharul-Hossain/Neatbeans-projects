/* 
 * File:   main.cpp
 * Author: mazharul
 *
 * Created on February 20, 2011, 6:27 PM
 */

#include <stdio.h>
#include <cstdlib>
#include <iostream>

using namespace std;

/*
 * 
 */
int main(int argc, char** argv) {

    int i, j, N = 4;

    for( i = 0; i < N; i++)
    {
        for( j = 0; j < ( N - i ); j++ )
        {
            printf(" ");
        }
        for( j = 0; j <= i; j++ )
        {
            printf("*");
            printf(" ");
        }

        printf("\n");
    }

    for( i = i - 1; i > 0; i--)
    {
        for( j = 0; j < ( N - i + 1 ); j++ )
        {
            printf(" ");
        }
        for( j = 0; j < i; j++ )
        {
            printf("*");
            printf(" ");
        }

        printf("\n");
    }

    return 0;
}

