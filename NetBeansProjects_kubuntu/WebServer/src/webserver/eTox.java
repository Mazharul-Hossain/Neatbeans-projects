import javax.swing.JOptionPane;
public class eTox
{
	public static double eX(double  x)
	{
		double sum=1,y=1;
		for(int i=1;i<250;i++)
		{
			y=y*x/i;
			sum=sum+y;	
		}
		
		return sum;		
	}
	public static StringBuffer deduction(StringBuffer str1,StringBuffer str2)
	 {
	 	StringBuffer str3=new StringBuffer();
	 	StringBuffer inverse=new StringBuffer(); 
	 	StringBuffer result=new StringBuffer();
	 	char c1,c2,c3,c4;
	 	int i=str1.length(),k=0;
	 	int j=str2.length();
	 	int check=3;
	int a=0,b,pre=0,foro=0,i1,c,n,flag1=0,flag2=0,r,t;
	 	while(str2.charAt(k)!='\0'&&flag2!=1)
	 	{
	 		if(str2.charAt(k)=='.')
	   	{
	 		flag2=1;
	  		break;
	    	}
	   	k++;
	 	}
	 	while(str1.charAt(a)!='\0'&&flag1!=1)
	 	{
	   	if(str1.charAt(a)=='.')
	   	{
			flag1=1;
	 		break;
	    	}
	    	a++;
	 	}
	   if(k>a)
		check=1;
	 	if(a>k)
		check=0;
	 	if(k==a&&(flag1==1||flag2==1)&&check==3)
	 	{
		r=a;
		for(i1=0;i1<r&&check==3;i1++)
		{
	   		c=str1.charAt(i1);
	   		t=str2.charAt(i1);
	   		if(t>c)
				check=1;
	   		if(c>t)
				check=0;
		}
	 	}
	 	if(flag1==0&&flag2==0&&check==3)
	 	{
		if(j>i)
			check=1;
		if(i>j)
		check=0;
	 	}
	 	int len1=0,len2=0;
	 	if(flag1==1&&flag2==0)
	 	{
	   	len1=i-(a+1);
	   	len2=j-k;
	   	str2.setCharAt(j,'.');
	   	for(n=0;n<len1;n++)
			str2.setCharAt(++j,'0');
	   	//str2[++j]='\0';
	 	}
	 	if(flag1==0&&flag2==1)
		{
	   	len2=j-(k+1);
	   	len1=i-a;
	   	str1.setCharAt(i,'.');
	   	for(n=0;n<len2;n++)
			str2.setCharAt(++i,'0');
	   	//str2[i]='\0';
	 	}
	 	if(flag1==1&&flag2==1)
	 	{
	   	len1=i-a;
	   	len2=j-k;
	   	if(len1>len2)
	   	{
	   		for(n=0;n<(len1-len2);n++)
	     			str2.setCharAt(j+n,'0');
	    		//str2[j+n]='\0';
	   	}
	   	if(len2>len1)
	   	{
	     		for(n=0;n<(len2-len1);n++)
	     			str1.setCharAt(i+n,'0');
	     		//str1[i+n]='\0';
	   	}
	 	}
	 	i=str1.length();
	 	j=str2.length();
	 	b=i;
	 	c=j;
	 	if(a>k)
	 	{
	   	for(r=i;r>=0;)
	   	{
	      		if(c>0)
	      			str3.setCharAt(--r,str2.charAt(--c));
	      		else
	      			str3.setCharAt(--r,'0');
	   	}
	   	//str3[i]='\0';
	   	//strcpy(str2,str3);
		i1=0;
		while (str3.charAt(i1)!='\0')
	 	{
	 		str2.setCharAt(i1,str3.charAt(i1));
			i1++;		 
	 	}
		
	 	}
	 	else if(k>a)
	 	{
	   	i1=0;
	   	for(r=j;r>=0;)
	   	{
	      		if(b>0)
				str3.setCharAt(--r,str1.charAt(--b));
	      		else
	      		{
				str3.setCharAt(--r,'0');
				i1++;
	      		}
	   }
	  	// str3[j+i1-1]='\0';
	   //str3[j]='\0';
	   //strcpy(str1,str3);
	i1=0;
	while (result.charAt(i1)!='\0')
	{
		str3.setCharAt(i1,result.charAt(i1));
		i1++;		 
	}
		
	 	}
	 	if(check==3)
	 	{
		r=i;
		for(i1=0;i1<r&&check==3;i1++)
		{
			c=str1.charAt(i1);
			t=str2.charAt(i1);
			if(t>c)
				check=1;
			if(c>t)
				check=0;
		}	
	 	}
	 	if(check==1)
	 	{
		//strcpy(str3,str1);
		i1=0;
		while (str1.charAt(i1)!='\0')
	 	{
	 		str3.setCharAt(i1,str1.charAt(i1));
			i1++;		 
	 	}
		i1=0;
		while (str2.charAt(i1)!='\0')
	 	{
	 		str1.setCharAt(i1,str2.charAt(i1));
			i1++;		 
	 	}
		//strcpy(str1,str2);
		//strcpy(str2,str3);
		i1=0;
		while (str3.charAt(i1)!='\0')
	 	{
	 		str2.setCharAt(i1,str3.charAt(i1));
			i1++;		 
	 	}
		
	 	}
	 	i=str1.length();
	 	j=str2.length();
	 	c=0;
	 	for(n=i-1,k=j-1;n>=0&&k>=0;n--,k--)
	 	{
	   	c1=str1.charAt(n);
	    	c2=str2.charAt(n);
	    	if(c1=='.')
	      	{
	 		c1=str1.charAt(--n);
			c2=str2.charAt(--k);
	 		inverse.setCharAt(c++,'.');
	      	}
	    	a=c1;//-'0';
	    	b=c2;//-'0';
	    	if(pre==1)
	    	{
			a=a+1;
			pre=0;
	    	}
	    	if(foro==1)
	    	{
			b=b+1;
			if(b==10)
			{
				b=0;
				pre=1;
			}
			foro=0;
	    	}
	    	if(a<b)
	    	{
			a=10+a;
			foro=1;
			a=a-b;
	    	}
	    	else
			a=a-b;
	    	c3=(char)a;
	    	inverse.setCharAt(c++,c3);
	 	}
	/* 	if(foro!=0)
	 		inverse.setCharAt(c;
	 	//inverse[c++]='\0';*/
	 	b=inverse.length()-1;
	 	i1=b;
	 	for(a=0;b>=0;a++,b--)
		{
	   	result.setCharAt(a,inverse.charAt(b));
	 	}
	 	//result.setCharAt(i1+1,'\0';
	 	if(check==1)
	 	{
		a=result.length();
		str3.setCharAt(0,'-');
		for(i1=0;i1<a;i1++)
			str3.setCharAt(i1+1,result.charAt(i1));
		//str3[a+1]='\0';
		//strcpy(result,str3);
		i1=0;
		while (str3.charAt(i1)!='\0')
	 	{
	 		result.setCharAt(i1,str3.charAt(i1));
			i1++;		 
	 	}
		
	 	}
	 	return result;
	 }
	
	public static StringBuffer multi(StringBuffer str3,StringBuffer str4)
	{
		char c1,c2,c3;
		StringBuffer str1=new StringBuffer();
		StringBuffer str5=new StringBuffer();
		StringBuffer str2=new StringBuffer();
		StringBuffer result=new StringBuffer();
		StringBuffer mult1=new StringBuffer();
		StringBuffer mult2=new StringBuffer();  	
		int a=0,b,pre,foro=0,c,n,flag1=0,flag2=0,r,t;
	   	int k1=0,b1=0,d1=0,d,s,i=0,j=0,check1=0,check2=0,i1;
	 	int len1=str3.length();
	 	int len2=str4.length();
	 	while(str3.charAt(i)!='\0')
	 	{
	   	if(str3.charAt(i)=='.')
	    	{
			check1=1;
			b1=i;
	    	}
	    	++i;
	 	}
	 	while(str4.charAt(j)!='\0')
	 	{
	   	if(str4.charAt(j)=='.')
	   	{
			check2=1;
			d1=j;
	   	}
	 		++j;
	 	}
	 	c=0;
	 	b=0;
	 	if(check1==1)
	 	{
	   	s=len1-1;
	    	while(c<=s)
	    	{
	      		if(b==b1)
	 			b++;
	      		else
	 			str1.setCharAt(c++,str3.charAt(b++));
	    	}
	    	len1=len1-(b1+1);
	 	}
	 	else
	 	{
	    	len1=0;
	    	//strcpy(str1,str3);
	 	i1=0;
	 	while (str3.charAt(i1)!='\0')
	 	{
	    		str1.setCharAt(i1,str3.charAt(i1));  	 
	 		i1++;
	 	}
	 	}
	 	c=0;
		b=0;
	 	if(check2==1)
	 	{
	   	r=len2-1;
	    	while(c<=r)
	    	{
	      		if(b==d1)
	 			b++;
	      		else
	 			str2.setCharAt(c++,str4.charAt(b++));
	    	}
	    	len2=len2-(d1+1);
	 	}
	
		else
	 	{
	   	len2=0;
	    	//strcpy(str2,str4);
	 	i1=0;
	 	while (str4.charAt(i1)!='\0')
	 	{
	 		str2.setCharAt(i1,str4.charAt(i1));
			i1++;		 
	 	}
	 	}
	 	i=str1.length();
	 	j=str2.length();
	 	s=i;
	 	//strcpy(str3,str1);
	 	i1=0;
	 	while (str1.charAt(i1)!='\0')
	 	{
	 		str3.setCharAt(i1,str1.charAt(i1));
	 		i1++;		 
	 	}
	 	for(n=j-1;n>=0;n--)
	 	{
	   		c2=str2.charAt(n);
	    	r=c2;//-'0';
	    	if(r==0&&n==j-1)
	    	{
			c=str1.length();
			//str3[c]='\0';
			while(c>0)
	 			str3.setCharAt(--c,'0');
	    	}
	    	else if(r==0)
	    	{
			c=mult1.length();
			//str3[c]='\0';
			while(c>0)
	  			str3.setCharAt(--c,'0');
	    	}
	   	else
		{
	   		i1=0;
			while (str1.charAt(i1)!='\0')
	 		{
	 			str3.setCharAt(i1,str1.charAt(i1));
				i1++;		 
	 		} 	
	   		//strcpy(str3,str1);
		}
		for(t=1;t<r;t++)
	  	 	{
			c=0;
			foro=0;
			s=str3.length();
			for(d=s-1;d>=0;d--)
			{
	  			c1=str1.charAt(d);
	  			c3=str3.charAt(d);
	  			a=c1;//-'0';
	  			b=c3;//-'0';
	  			a=a+b+foro;
	  			foro=a/10;
	  			pre=a%10;
	  			c3=(char)pre;//+'0';
	  			str4.setCharAt(c++,c3);
			}
			if(foro!=0)
	  		str4.setCharAt(c,(char)foro);
			//str4[c]='\0';
			for(a=0,b=str4.length()-1;b>=0;a++,b--)
			{
	  			result.setCharAt(a,str4.charAt(b));
			}
			//result[a]='\0';
			//strcpy(str3,result);
			i1=0;
			while (result.charAt(i1)!='\0')
	 		{
	 			str3.setCharAt(i1,result.charAt(i1));
				i1++;		 
	 		}
			s=str3.length();
			b1=str1.length();
			//str5[s]='\0';
			do
			{
	  			str5.setCharAt(--s,str1.charAt(--b1));
			}while(b1>=0);
			while(s>=0)
	  			str5.setCharAt(s--,'0');
			//strcpy(str1,str5);
	   	}
	   	//strcpy(mult2,str3);
		i1=0;
		while (str3.charAt(i1)!='\0')
		{
			mult2.setCharAt(i1,str3.charAt(i1));
			i1++;		 
		}
	   	d1=mult2.length();
	   	for(b1=0;k1>b1;b1++)
	   	{
	   		mult2.setCharAt(((d1+k1)-(b1+1)),'0');
	   	}
	   	//mult2[d1+k1]='\0';
	   	d1=mult2.length();
	   	if(k1>0)
	   	{
	      		s=mult2.length();
			b1=mult1.length();
			if(s>b1)
			{
				//str5[s]='\0';
				do
				{
	  				str5.setCharAt(--s,mult1.charAt(--b1));
				}while(b1>=0);
				while(s>=0)
					str5.setCharAt(s--,'0');
				//strcpy(mult1,str5);
				i1=0;
				while (str5.charAt(i1)!='\0')
				{
					mult1.setCharAt(i1,str5.charAt(i1));
					i1++;		 
				}
			}
			if(b1>s)
			{
				//str5[b1]='\0';
				do
				{
	  				str5.setCharAt(--b1,mult2.charAt(--s));
				}while(s>=0);
				while(b1>=0)
	   				str5.setCharAt(b1--,'0');
	   			//strcpy(mult2,str5);
	   			i1=0;
	 			while (str5.charAt(i1)!='\0')
	 			{
	 				mult2.setCharAt(i1,str5.charAt(i1));
					i1++;		 
	 			}
			}
			c=0;
			foro=0;
			s=mult1.length();
			for(d=s-1;d>=0;d--)
			{
	  			c1=mult1.charAt(d);
	  			c3=mult2.charAt(d);
	  			a=c1;//-'0';
	  			b=c3;//-'0';
	  			a=a+b+foro;
	  			foro=a/10;
	  			pre=a%10;
	  			c3=(char)pre;//+'0';
	  			str4.setCharAt(c++,c3);
			}
			if(foro!=0)
	  		str4.setCharAt(c,'1');
			//str4[c]='\0';
			for(a=0,b=str4.length()-1;b>=0;a++,b--)
			{
				mult1.setCharAt(a,str4.charAt(b));
			}
			//mult1[a]='\0';
	   	}    	
		if (k1==0)
		{		
			//strcpy(mult1,mult2);
			i1=0;
			while (mult2.charAt(i1)!='\0')
			{
				mult1.setCharAt(i1,mult2.charAt(i1));
				i1++;		 
			}
		}
		k1++;
	   	if (n==0)
		{  	  	
			//strcpy(str5,mult1);
	 			i1=0;
	 		while (mult1.charAt(i1)!='\0')
	 		{
	 			str5.setCharAt(i1,mult1.charAt(i1));
				i1++;		 
	 		}
	
	 		}
	 	}	
		i=0;
	 		s=str5.length();
	 		j=s;
	 		len1=len1+len2;
	 	if(len1>0)
	 	{
	   	//result[s+1]='\0';
	   	while(j>0)
	    	{
			if(i==len1)
	  			result.setCharAt(s-i,'.');
			else
	  			result.setCharAt(s-i,str5.charAt(--j));
			i++;
	     	}
	}
	 	else
	{		
		//strcpy(result,str5);
		i1=0;
	 	while (str5.charAt(i1)!='\0')
	 	{
	 		result.setCharAt(i1,str5.charAt(i1));
			i1++;		 
	 	}
	 	
	
	}
	
		return result;
	} 
	public static StringBuffer sum(StringBuffer str1,StringBuffer str2)
  	{
  		StringBuffer str3=new StringBuffer();
  		StringBuffer inverse=new StringBuffer(); 
  		StringBuffer result=new StringBuffer();
  		char c1,c2,c3,c4;
  		int i=str1.length(),k=0;
  		int j=str2.length();	
  		int a=0,b,pre,foro=0,i1,c,n,flag1=0,flag2=0,r,t;
  		while(str2.charAt(k)!='\0'&&flag2!=1)
  		{
  		if(str2.charAt(k)=='.')
    	{
	 		flag2=1;
	  		break;
     	}
    	k++;
  	}
  	while(str1.charAt(a)!='\0'&&flag1!=1)
  	{
    	if(str1.charAt(a)=='.')
    	{
			flag1=1;
	 		break;
     	}
     	a++;
  	}
  	int len1=0,len2=0;
  	if(flag1==1&&flag2==0)
  	{
    	len1=i-(a+1);
    	len2=j-k;
    	str2.setCharAt(j,'.');
    	for(n=0;n<len1;n++)
		str2.setCharAt(++j,'0');
    	//str2[++j]='\0';
  	}
  	if(flag1==0&&flag2==1)
  	{
    	len2=j-(k+1);
    	len1=i-a;
    	str1.setCharAt(i,'.');
    	for(n=0;n<len2;n++)
			str1.setCharAt(++i,'0');
  		//  str2[++i]='\0';
  	}
  	if(flag1==1&&flag2==1)
  	{
    	len1=i-a;
    	len2=j-k;
    	if(len1>len2)
    	{
      		for(n=0;n<(len1-len2);n++)
      			str2.setCharAt(n+j,'0');
      		//str2[j+n]='\0';
    	}
    	if(len2>len1)
    	{
      		for(n=0;n<(len2-len1);n++)
      			str1.setCharAt(i+n,'0');
      		//str1[i+n]='\0';
    	}
  	}
   	i=str1.length();
   	j=str2.length();
   	b=i;
   	c=j;
  	if(a>k)
  	{
    	for(r=i-1;r>=0;)
    	{
       		if ((c-1)>=0)
	   			str3.setCharAt(r--,str2.charAt(--c));
	   		else
        		str3.setCharAt(r--,'0');
    	}
   		// strcpy(str2,str3);
 		i1=0;
    	while (str3.charAt(i1)!='\0')
    	{
   			str2.setCharAt(i1,str3.charAt(i1));	   	
   			i1++;
    	}
  	}
  	else if(k>a)
  	{
    	for(r=j-1;r>=0;)
    	{
       		if((b-1)>=0)
       			str3.setCharAt(r--,str1.charAt(--b));
       		else
       			str3.setCharAt(r--,'0');
    	}
   		// strcpy(str1,str3);
   		i1=0;
   		while (str3.charAt(i1)!='\0')
   		{
   			str1.setCharAt(i1,str3.charAt(i1));	   	
   			i1++;
   		}
  	}
  	i=str1.length();
  	j=str2.length();
  	c=0;
  	for(n=i-1,k=j-1;n>=0&&k>=0;n--,k--)
  	{
     	c1=str1.charAt(n);
     	c2=str2.charAt(k);
    	if(c1=='.')
       	{
	 		c1=str1.charAt(--n);
     		c2=str2.charAt(--k);
	 		inverse.setCharAt(c++,'.');
       	}
     	a=c1;//-'0';
     	b=c2-'0';
     	a=a+b+foro;
     	pre=a%10;
     	foro=a/10;
     	c3=(char)pre;//+'0';
     	inverse.setCharAt(c++,c3);
  	}
  	if(foro!=0)
 		inverse.setCharAt(c,(char)foro);
  	//inverse[c++]='\0';
  	for(a=0,b=inverse.length()-1;b>=0;a++,b--)
  	{
    	 result.setCharAt(a,inverse.charAt(b));
  	}
  	return result;
}
	public static StringBuffer divide(StringBuffer str3,StringBuffer str4 )
	{
 		StringBuffer str1=new StringBuffer();
  		StringBuffer inverse=new StringBuffer(); 
  		StringBuffer result=new StringBuffer();
		StringBuffer str2=new StringBuffer();
  		char c1,c2,c3;
		int check=3;
		int point=-1;
		int a=0,b,pre,foro=0,c,n,check1=0,check2=0,r,t,i1;
		int i=0,j=0,b1=0,d1=0,s;
		int k=0,v;
		int len1=str3.length();
		int len2=str4.length();
		while(str3.charAt(a)!='\0'&&check1!=1)
		{
			if(str3.charAt(a)=='.')
			{
				check1=1;
				b1=a;
			}
			++a;
		}
		while(str4.charAt(k)!='\0'&&check2!=1)
		{
			if(str4.charAt(k)=='.')
			{
				check2=1;
				d1=k;
			}
			++k;
		}
		b=0;
		c=0;
		if(check1==1&&check2==1)
		{
			if((len1-b1)>(len2-d1))
			{
				r=(len1-b1)-(len2-d1);
				s=len1-1;
				while(c<=s)
				{
					if(b==b1)
						b++;
					else
						str1.setCharAt(c++,str3.charAt(b++));

				}
				t=0;
				b=0;
				s=len2-1;
				while(t<s)
				{
					if(b==d1)
						b++;
					else
						str2.setCharAt(t++,str4.charAt(b++));

				}
				i1=0;
				while(i1<r)
				{	
					str2.setCharAt(t++,'0');
					i1++;
				}
				//str2[t]='\0';
			}
			else if((len1-b1)<(len2-d1))
			{	
				r=(len2-d1)-(len1-b1);
				b=0;
				c=0;
				s=len2-1;
				while(c<=s)
				{
					if(b==d1)
						b++;
					else
						str2.setCharAt(c++,str4.charAt(b++));
				}
				t=0;
				b=0;
				s=len1-1;
				while(t<s)
				{
					if(b==b1)
						b++;
					else
						str1.setCharAt(t++,str3.charAt(b++));

				}
				i1=0;
				while(i1<r)
				{
					str1.setCharAt(t++,'0');
					i1++;
				}
				//str1[t]='\0';
			}
			else
			{
				c=0;
				b=0;
				s=len1-1;
				while(c<=s)
				{
					if(b==b1)
						b++;
					else
						str1.setCharAt(c++,str3.charAt(b++));

				}
				c=0;
				b=0;
				s=len2-1;
				while(c<=s)
				{	
					if(b==d1)
						b++;
					else
						str2.setCharAt(c++,str4.charAt(b++));

				}
			}
		}
		else if(check1==1&&check2!=1)
		{
			b=0;
			c=0;
			s=len1-1;
			while(c<=s)
			{
				if(b==b1)
					b++;
				else
					str1.setCharAt(c++,str3.charAt(b++));
			}
			len1=len1-1;
			r=len1-b1;
			i1=0;
			while(str4.charAt(i1)!='\0')
			{
				str2.setCharAt(i1,str4.charAt(i1));
				i1++;
			}
			t=0;
			while(t<r)
			{
				str2.setCharAt(i1+t,'0');
				t++;
			}
			//str2[i1+t]='\0';
		}
		else if(check2==1&&check1!=1)
		{
			b=0;
			c=0;
			s=len2-1;
			while(c<=s)
			{
				if(b==d1)
					b++;
				else
					str2.setCharAt(c++,str4.charAt(b++));
			}
			len2=len2-1;
			r=len2-d1;
			i1=0;
			while(str3.charAt(i1)!='\0')
			{
				str1.setCharAt(i1,str3.charAt(i1));
				i1++;
			}
			t=0;
			while(t<r)
			{
				str1.setCharAt(i1+t,'0');
				t++;
			}
			//str1[i1+t]='\0';
		}
		else
		{
			//strcpy(str1,str3);
			//strcpy(str2,str4);
			v=0;
			while(str3.charAt(v)!='\0')
			{
				str1.setCharAt(v,str3.charAt(v));
			}		
			v=0;
			while(str4.charAt(v)!='\0')
			{
				str2.setCharAt(v,str4.charAt(v));
			}		
		}
		i=str1.length();
		j=str2.length();
		if(i>j)
		{
			i1=0;
			while(i1<(i-j))
			{
				str4.setCharAt(i1++,'0');
			}
			t=0;
			while(str2.charAt(t)!='\0')
			{
				str4.setCharAt(i1++,str2.charAt(t++));
			}
			//str4[i1]='\0';
			//strcpy(str2,str4);
			v=0;
			while(str4.charAt(v)!='\0')
			{
				str2.setCharAt(v,str4.charAt(v));
			}		
		}
		if(j>i)
		{
			t=0;
			while(str1.charAt(t)!='\0')
			{
				str3.setCharAt(t,str1.charAt(t));
				t++;
			}	
			i1=0;
			while(i1<(j-i))
			{
				str3.setCharAt(t++,'0');
				i1++;
				point+=1;
			}	
			//str3[t]='\0';
			//strcpy(str1,str3);
			v=0;
			while(str3.charAt(v)!='\0')
			{	
				str1.setCharAt(v,str3.charAt(v));
			}		
		}
		i1=0;
		while(check==3&&str1.charAt(i1)!='\0')
		{
			c1=str1.charAt(i1);
			c2=str2.charAt(i1);
			if(c1>c2)
			check=1;
			if(c2>c1)
				check=-1;
			i1++;
		}
		if(check==-1)
		{
			t=0;
			while(str1.charAt(t)!='\0')
			{
				str3.setCharAt(t,str1.charAt(t));
				t++;
			}
			str3.setCharAt(t++,'0');
			point+=1;
			//str3[t]='\0';
			//strcpy(str1,str3);
			v=0;
			while(str3.charAt(v)!='\0')
			{	
				str1.setCharAt(v,str3.charAt(v));
			}		
			i1=0;
			str4.setCharAt(i1++,'0');
			t=0;
			while(str2.charAt(t)!='\0')
			{
				str4.setCharAt(i1++,str2.charAt(t++));
			}
			//str4[i1]='\0';
			//strcpy(str2,str4);
			v=0;
			while(str4.charAt(v)!='\0')
			{	
				str2.setCharAt(v,str4.charAt(v));
			}		
		}
		i=str1.length();
		j=str2.length();
		int count=0,p=-1,count1=0;
		StringBuffer str5=new StringBuffer();
  		StringBuffer str6=new StringBuffer(); 
  		StringBuffer str7=new StringBuffer();
		StringBuffer str8=new StringBuffer();
  		StringBuffer div=new StringBuffer(); 
  		str5.setCharAt(0,'0');
		//str5[1]='\0';	
		str6.setCharAt(0,'1');
		//str6[1]='\0';			
		int len=0;
		i1=0;
		while(i1<8)
		{
			str7.setCharAt(i1++,'0');
		}
		//str7[i1]='\0';
		check=1;
		for(r=0;check==1&&len<8;r++)
		{

			foro=0;
			c=0;
			pre=0;
			for(n=i-1,k=j-1;n>=0&&k>=0;n--,k--)
			{
				c1=str1.charAt(n);
				c2=str2.charAt(k);
				if(c1=='.')
				{
					c1=str1.charAt(--n);
					c2=str2.charAt(--k);
					inverse.setCharAt(c++,'.');
				}
				a=c1-'0';
				b=c2-'0';
				if(pre==1)
				{
					a=a+1;
					pre=0;
				}
				if(foro==1)
				{
					b=b+1;
					if(b==10)
					{
						b=0;
						pre=1;
					}
					foro=0;
				}
				if(a<b)
				{
					a=10+a;
					foro=1;
					a=a-b;
				}
				else
					a=a-b;
				c3=(char)a;
				inverse.setCharAt(c++,c3);
			}
			//inverse[c]='\0';
			b=inverse.length()-1;
			i1=b;
			for(a=0;b>=0;a++,b--)
			{
				result.setCharAt(a,inverse.charAt(b));
			}
			//result[i1+1]='\0';
			if(str1.equals(str2))
			{
				check=0;
				if(point>=0)
				{
					a=str7.charAt(len)-'0';
					a=a+1;
					str7.setCharAt(len,(char)a);
				}
				else
				{
					i1=0;
					b1=str5.length();
					d1=str6.length();
					c=0;
					for(n=b1-1,k=d1-1;n>=0&&k>=0;n--,k--)
					{
						c1=str5.charAt(n);
						c2=str6.charAt(k);
						a=c1-'0';
						b=c2-'0';
						a=a+b+foro;
						pre=a%10;
						foro=a/10;
						c3=(char)pre;
						inverse.setCharAt(c++,c3);
					}
					if(foro!=0)
					{
						inverse.setCharAt(c,(char)foro);
						//inverse[++c]='\0';
					}
					/*else
						inverse[c]='\0';*/
					b=inverse.length()-1;
					t=b+1;
					for(a=0;b>=0;a++,b--)
					{
						str5.setCharAt(a,inverse.charAt(b));
					}
					//str5[t]='\0';
					b1=str5.length();
					d1=str6.length();
					if(b1>d1)
					{
						i1=0;
						str3.setCharAt(i1++,'0');
						t=0;
						while(str6.charAt(t)!='\0')
						{
							str3.setCharAt(i1++,str6.charAt(t++));
						}
						//str3[i1]='\0';
						//strcpy(str6,str3);
						v=0;
						while(str3.charAt(v)!='\0')
						{
							str6.setCharAt(v,str3.charAt(v));
						}		


					}
				}
			}

			if(check==0)
				break;
			//strcpy(str1,result);
			v=0;
			while(result.charAt(v)!='\0')
			{
				str1.setCharAt(v,result.charAt(v));
			}		


			i=str1.length();
			i1=0;
			if(point<0)
			{
				i1=0;
				b1=str5.length();
				d1=str6.length();
				c=0;
				for(n=b1-1,k=d1-1;n>=0&&k>=0;n--,k--)
				{
					c1=str5.charAt(n);
					c2=str6.charAt(k);
					a=c1-'0';
					b=c2-'0';
					a=a+b+foro;
					pre=a%10;
					foro=a/10;
					c3=(char)pre;
					inverse.setCharAt(c++,c3);
				}
				if(foro!=0)
				{
					inverse.setCharAt(c,(char)foro);
					//inverse[++c]='\0';
				}
				/*else
					inverse[c]='\0';*/
				b=inverse.length()-1;
				t=b+1;
				for(a=0;b>=0;a++,b--)
				{
					str5.setCharAt(a,inverse.charAt(b));
				}
				//str5[t]='\0';
				b1=str5.length();
				d1=str6.length();
				if(b1>d1)
				{
					i1=0;
					str3.setCharAt(i1++,'0');
					t=0;
					while(str6.charAt(t)!='\0')
					{
						str3.setCharAt(i1++,str6.charAt(t++));
					}
					//str3[i1]='\0';
					//strcpy(str6,str3);
					v=0;
					while(str3.charAt(v)!='\0')
					{
						str6.setCharAt(v,str3.charAt(v));
					}		


				}

			}
			else
			{
				a=str7.charAt(len);
				a=a+1;
				str7.setCharAt(len,(char)a);
			}
			check1=3;
			i1=0;
			while(check1==3&&str1.charAt(i1)!='\0')
			{
				c1=str1.charAt(i1);
				c2=str2.charAt(i1);
				if(c1>c2)
					check1=1;
				if(c2>c1)
					check1=-1;
				i1++;
			}
			if(check1==-1&&point>=0)
			{
				t=0;
				len+=1;
				while(str1.charAt(t)!='\0')
				{
					str3.setCharAt(t,str1.charAt(t));
					t++;
				}
				str3.setCharAt(t++,'0');
				//str3[t]='\0';
				//strcpy(str1,str3);
				v=0;
				while(str3.charAt(v)!='\0')
				{
					str1.setCharAt(v,str3.charAt(v));
				}		


				i1=0;
				str4.setCharAt(i1++,'0');
				t=0;
				while(str2.charAt(t)!='\0')
				{
					str4.setCharAt(i1++,str2.charAt(t++));
				}
				//str4[i1]='\0';
				//strcpy(str2,str4);
				v=0;
				while(str4.charAt(v)!='\0')
				{
					str2.setCharAt(v,str4.charAt(v));
				}		


				check1=3;
				i1=0;
				while(check1==3&&str1.charAt(i1)!='\0')
				{
					c1=str1.charAt(i1);
					c2=str2.charAt(i1);
					if(c1>c2)
						check1=1;
					if(c2>c1)
						check1=-1;
					i1++;
				}
			}
			while(check1==-1)
			{
				t=0;
				while(str1.charAt(t)!='\0')
				{
					str3.setCharAt(t,str1.charAt(t));
					t++;
				}
				str3.setCharAt(t++,'0');
				point+=1;
				//str3[t]='\0';
				//strcpy(str1,str3);
				v=0;
				while(str3.charAt(v)!='\0')
				{
					str1.setCharAt(v,str3.charAt(v));
				}		


				i1=0;
				str4.setCharAt(i1++,'0');
				t=0;
				while(str2.charAt(t)!='\0')
				{
					str4.setCharAt(i1++,str2.charAt(t++));
				}
				//str4[i1]='\0';
				//strcpy(str2,str4);
				v=0;
				while(str4.charAt(v)!='\0')
				{
					str2.setCharAt(v,str4.charAt(v));
				}		


				check1=3;
				i1=0;
				while(check1==3&&str1.charAt(i1)!='\0')
				{
					c1=str1.charAt(i1);
					c2=str2.charAt(i1);
					if(c1>c2)
						check1=1;
					if(c2>c1)
						check1=-1;
					i1++;
				}
			}

				i=str1.length();
				j=str2.length();
	}
	//str7[len+1]='\0';
	i1=0;
	while(str5.charAt(i1)!='\0')
	{
		str3.setCharAt(i1,str5.charAt(i1));
		i1++;
	}
	if(point>=0)
	{
		str3.setCharAt(i1++,'.');
		r=0;
		while(r<point)
		{
			str3.setCharAt(i1++,'0');
		}
		//str3[i1]='\0';
		//strcpy(str5,str3);	
		v=0;
		while(str3.charAt(v)!='\0')
		{
			str5.setCharAt(v,str3.charAt(v));
		}		

		i1=str5.length();
		t=0;
		while (str7.charAt(t)!='\0')
		{
			str5.setCharAt(i1-1,str7.charAt(t));
			t++;
			i1++;
			//str5=str5.concat((StringBuffer) str7);
		
		}
	}	
		return str5;
	
}

	public static int nPr(int a,int b)
	{
		int n=1,sum;
		int r=1,i;
		for(i=1;i<=a;i++)
			n=n*i;
		for(i=1;i<=a-b;i++)
			r=r*i;	
		sum=n/r;		
		return sum;	
	}	
	public static int nCr(int a,int b)
	{
		int n=1,m=1,sum;
		int r=1,i;
		for(i=1;i<=a;i++)
			n=n*i;
		for(i=1;i<=b;i++)
			r=r*i;	
		for(i=1;i<=a-b;i++)
			m=m*i;	
		sum=n/(r*m);		
		return sum;	
	}			
		
	public static double sin(double x)
	{
		x=(Math.PI/180)*x;
		double sum=x,y=x,z,i;
		for(i=1;i<500;i++)
		{
			z=Math.pow(-1,1);
			y=y*x*x*z/((2*i+1)*2*i);		
			sum=sum+y;
		}	
		return sum;
	}	
	public static double cos(double x)
	{
		x=(Math.PI/180)*x;
		double sum=1,y=1,z,i;
		for(i=1;i<500;i++)
		{
			z=Math.pow(-1,1);
			y=y*x*x*z/((2*i-1)*2*i);		
			sum=sum+y;
		}	
		return sum;
	}		
	public static double tan(double x)
	{
		double m,n;
		m=sin(x);
		n=cos(x);
		x=m/n;
		return x;
	}
	public static double in_sin(double x)
	{
		double z=0,y=x,sum=x;
		for(int i=1;i<2000;i++)
		{
			z=2*(i-1)+1;
			y=y*x*x/((2*i+1)*2*i);		
			z=y*z*z;
			sum=sum+z;	
		}		
		sum=sum*180/Math.PI;
		return sum;
	}
	
	public static double in_cos(double x)
	{
		double z=0,y=x,sum=x;
		for(int i=1;i<200;i++)
		{
			z=2*(i-1)+1;
			y=y*x*x/((2*i+1)*2*i);		
			z=y*z*z;
			sum=sum+z;	
		}		
		sum=sum*180/Math.PI;
		sum=90-sum;
		return sum;
	}
	public static double in_tan(double x)
	{
	 	double z,sum=0,y;
		for(double i=0;i<111;i++)
		{
			z=2*i+1;
			y=Math.pow(-1,i)*Math.pow(x,z)/z;
			sum=sum+y;	
		}			
		sum=sum*180/Math.PI;
		return sum;
	}
	public static int fact_n(int n)
	{
		int sum=1;
		if (n!=0)
		{
			for(int i=n;i>=1;i--)
				sum=sum*i;
			return sum;
		}		
		
		return sum;		
	}
	public static double ln(double x)
	{
		double z=0,sum=0,y,error=-1;
		int i;
		String str=" ";
		if(x>2)	
		{
			z=(1/x)-1;
			sum=z;
			y=1;			
			for(i=1;i<500;i++)
			{
				y=Math.pow(z,i+1)*Math.pow(-1,i)/(i+1);
				sum=sum+y;
			}		
			sum=sum*(-1);
			return sum;		
		}
		if(x>0&&x<=2)
		{
			z=x-1;
			sum=z;
			y=1;
			for(i=1;i<500;i++)
			{
				y=Math.pow(z,i+1)*Math.pow(-1,i)/(i+1);
				sum=sum+y;	
			}		
			return sum;		
		}
		return error;	 	
	}	
	public static double inverse(double x)
	{
		return(1/x);
	}
	public static void main(String args[])
	{
		
		int i=0;
		StringBuffer str3=new StringBuffer();
	//	StringBuffer str4=new StringBuffer();
		String str1=JOptionPane.showInputDialog("Enter a num");
	//	String str2=JOptionPane.showInputDialog("Enter another num");
		//str=StringBuffer.parseStringBuffer(str2);
		double m=Double.parseDouble(str1);
		//String str1=JOptionPane.showInputDialog("Enter r");
		//int r=Integer.parseInt(str);
		//String str2=ln(n);
		double x=in_sin(m);
		//StringBuffer str1=new StringBuffer();
	/*	while (str1.charAt(i)!='\0')
		{
			str3.setCharAt(i,str1.charAt(i));
			i++;		
		}  
		i=0;
		while (str2.charAt(i)!='\0')
		{
			str4.setCharAt(i,str2.charAt(i));
			i++;		
		}  

		str3=deduction(str3,str4);*/
		JOptionPane.showMessageDialog(null,"The result is :"+x);
		System.exit(0);
	}
}
