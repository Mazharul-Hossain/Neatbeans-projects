#include<stdio.h>
//#include<conio.h>

 int main(void)
{
//clrscr();

int a[10],min,i;
min=65000;
for(i=0;i<10;i++)

{scanf("%d",&a[i]);
}

for(i=0;i<10;i++)

{printf("%d",a[i]);
}
printf("\n",min);


for(i=0;i<10;i++){
if (a[i]<min)
min=a[i];}
printf(" %d",min);
//getch();
return 0;
}
