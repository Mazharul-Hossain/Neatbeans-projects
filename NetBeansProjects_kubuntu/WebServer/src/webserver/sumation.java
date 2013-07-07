import javax.swing.JOptionPane; 
public class sumation
{
	public static StringBuffer sum(StringBuffer str1,StringBuffer str2)
  	{
  		StringBuffer str3=new StringBuffer();
  		StringBuffer inverse=new StringBuffer(); 
  		StringBuffer result=new StringBuffer();
  		JOptionPane.showMessageDialog(null,str1);
  		char c1,c2,c3,c4;
  		int i=str1.length(),k=0;
  		int j=str2.length();	
  		int a=0,b,pre,foro=0,i1,c,n,flag1=0,flag2=0,r,t;
  		int i3=str2.length();
		while(i3!=k&&flag2!=1)
  		{
  			if(str2.charAt(k)=='.')
    		{
	 			flag2=1;
	  			break;
     		}
    		k++;
  		}
  		i3=str1.length();
		while(a!=i3&&flag1!=1)
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
    }
  	JOptionPane.showMessageDialog(null,"The result is :"+str2);
	if(flag1==0&&flag2==1)
  	{
    	len2=j-(k+1);
    	len1=i-a;
    	str1.setCharAt(i,'.');
    	for(n=0;n<len2;n++)
			str1.setCharAt(++i,'0');
  	}
  	if(flag1==1&&flag2==1)
  	{
    	len1=i-a;
    	len2=j-k;
    	if(len1>len2)
    	{
      		for(n=0;n<(len1-len2);n++)
      			str2.append('0');
      	}
    	if(len2>len1)
    	{
      		for(n=0;n<(len2-len1);n++)
      			str1.append('0');
      	}
  	}
   	i=str1.length();
   	j=str2.length();
   	b=i;
   	c=j;
  	if(a>k)
  	{
    	/*for(r=i-1;r>=0;r--)
    	{
       		/*if ((c-1)>=0)
	   			str3.setCharAt(r--,str2.charAt(--c));
	   		//else
			str3.setCharAt(r--,'0');
    	}*/
   		// strcpy(str2,str3);
 		i1=0;
		while (i1<(a-k))
		{
			str1.insert(0,'0');
			i1++;		
		}
		/*i1=0;
    	while (str3.charAt(i1)!='\0')
    	{
   			str2.setCharAt(i1,str3.charAt(i1));	   	
   			i1++;
    	}*/
  	}
  	else if(k>a)
  	{
    	/*for(r=j-1;r>=0;)
    	{
       		if((b-1)>=0)
       			str3.setCharAt(r--,str1.charAt(--b));
       		else
       			str3.setCharAt(r--,'0');
    	}
   		// strcpy(str1,str3);*/
   		i1=0;
		while (i1<(k-a))
		{
			str2.insert(0,'0');
			i1++;		
		}
		/*i1=0;
   		while (str3.charAt(i1)!='\0')
   		{
   			str1.setCharAt(i1,str3.charAt(i1));	   	
   			i1++;
   		}*/
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
     	a=c1;
     	b=c2-'0';
     	a=a+b+foro;
     	pre=a%10;
     	foro=a/10;
     	c3=(char)pre;
     	inverse.setCharAt(c++,c3);
  	}
  	if(foro!=0)
 		inverse.setCharAt(c,(char)foro);
  	//inverse[c++]='\0';
  	for(a=0,b=inverse.length()-1;b>=0;a++,b--)
  	{
    	 result.setCharAt(a,inverse.charAt(b));
  	}
  	JOptionPane.showMessageDialog(null,result);
  	return result;
}
public static void main(String args[])
	{
		
		int i=0;
		StringBuffer str3=new StringBuffer();
		StringBuffer str4=new StringBuffer();
		String str1=JOptionPane.showInputDialog("Enter a num");
		String str2=JOptionPane.showInputDialog("Enter another num");
		while (str1.charAt(i)!='\0')
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

		str3=sum(str3,str4);
		JOptionPane.showMessageDialog(null,"The result is :"+str3);
		System.exit(0);
	}
}



