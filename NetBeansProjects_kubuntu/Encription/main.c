/* 
 * File:   main.c
 * Author: mazharul
 *
 * Created on November 19, 2010, 2:24 PM
 */

#include <stdio.h>
#include <stdlib.h>
#include<math.h>
#include<string.h>

/*
 * 
 */

int ASCII_Converter(char c)
{
    int A;
    A=c;
    return A;
}

int main(int argc, char** argv)
{
/*    int limit;

    printf("In how many letters you want to write your message");
    scanf("%d",&limit);

    FILE *message;

    char ch, B[limit],A[limit];
    int i,j;
//    message=fopen("message.txt","w");

    if(message==NULL)
    {
        printf("\n Error:File does not exist");
    }
    else
    {
        /*printf("\n A Data file is open for you.\n write your message.\n\n press CTRL+Z at end \n\n  ");
        while ((ch=getchar())!='0')
        {
            putc(ch,message);
        }
        fclose(message);
*/
/*        message=fopen("message.txt","r");
        i=1;
        while((ch=getc(message))!=EOF)
        {
            A[i]=ch;
            i++;
        }
        for(i=1;i<1000;i++)
        {
            if(A[i]==95)
            {
                B[i]=ASCII_Converter(A[i]);
                break;
            }
            else
                B[i]=ASCII_Converter(A[i]);
        }
        fclose(message);
        int l,k=0,q=0,r,s,t=1;

        l= strlen (B);
        for(i=1;i<l;i++)
        {
            if(B[i]==32)
            {
                if(q<=k)
                {
                    q=k;
                    k=0;
                }
                else
                k=0;
            }
            else
            k++;
        }
        char E[q][q];
        j=0,s=0;
        for(i=1;i<=strlen (B);i++)
        {
           if(B[i]!=32)
           {
               E[s][j]=B[i];
               s++;
           }
           if(B[i]==32)
           {
               s=0;
               j=j+1;
               E[s][j]=B[i];
               s++;
           }
        }
        for(j=0;j<=q;j++)
        for(i=0;i<=q;i++)
        {
            if(E[i][j]=='\0')
            {
                E[i][j]=0;
            }
        }

          char Q[q][q];
          for(i=0;i<=q;i++)
          for(j=0;j<=q;j++)
          {
              Q[i][j]=t;
              t++;
          }
          char Sum[q][q];
          for(i=0;i<=q;i++)
          {
              for(j=0;j<=q;j++)
              {
                  Sum[i][j]=Q[i][j]+E[i][j];
              }
          }

          printf("Your encrypted message is : \n");
          for(i=0;i<=q;i++)
          {
              for(j=0;j<=q;j++)
              {
                  printf("\t %c",Sum[i][j]);
              }
              printf("\n \n");
        }
    }
*/
    int limit,i,B[10000];
    char A[100000];
    printf("Enter your limit");
    scanf("%d",&limit);
    printf("%d",limit);
    printf("\n Enter your input");

    for(i=0; i<limit; i++)
    {
        printf("%d",i);
        scanf("%c",&A[i]);
        printf("\t %c",A[i]);
    }
    return (EXIT_SUCCESS);
}



