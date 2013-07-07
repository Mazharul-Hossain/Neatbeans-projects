import javax.swing.JOptionPane; 
public class deduction
{
	public static StringBuffer multi(StringBuffer str3,StringBuffer str4)
	{
		char c1='-',c2='-',c3,c4;
		StringBuffer str1=new StringBuffer();
		StringBuffer str5=new StringBuffer();
		StringBuffer str2=new StringBuffer();
		StringBuffer result=new StringBuffer();
		StringBuffer mult1=new StringBuffer();
		StringBuffer mult2=new StringBuffer();  	
		int a=0,b,pre,foro=0,c,n,flag1=0,flag2=0,r,t,flag3=0,flag4=0;
	   	int k1=0,b1=0,d1=0,d,s,i=0,j=0,check1=0,check2=0,i1;
	 	int len1=str3.length();
	 	int len2=str4.length(),i3;
	 	c3=str3.charAt(0);
		c4=str4.charAt(0);
		if(c1==c3)
			flag3=1;
		if(c2==c4)
			flag4=1;	
		if(flag3==1)
			str3.deleteCharAt(0);
		if(flag4==1)
			str4.deleteCharAt(0);	
		JOptionPane.showMessageDialog(null,"Enter a num"+str3);
		JOptionPane.showMessageDialog(null,"Enter another num"+str4);
		i3=str3.length();
		while(i3!=i)
	 	{
	   		if(str3.charAt(i)=='.')
	    	{
				check1=1;
				b1=i;
	    	}
	    	++i;
	 	}
	 	i3=str4.length();
		while(i3!=j)
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
	   		str3.deleteCharAt(b1);
			len1=len1-(b1+1);
			
	 	}
		else
			len1=0;
	 	c=0;
		b=0;
	 	if(check2==1)
	 	{
	   		str4.deleteCharAt(d1);
		    len2=len2-(d1+1);
		}
		else
			len2=0;
		i=str3.length();	 	
		j=str4.length();
	 	s=i;
	 	i3=0;
		while (i3!=i)
		{
			c1=str3.charAt(i3);
			str1.append(c1);
			i3++;		
		}
		for(n=j-1;n>=0;n--)
	 	{
	   		c2=str4.charAt(n);
	    	r=c2-'0';
			if(r==0&&n==j-1)
	    	{
				c=str3.length();
				i3=str1.length();
				str1.delete(0,i3);
				while (c>0)
				{		
					str1.append('0');
					c--;				
				}
				
			}
			else if(r==0)
	    	{
				c=mult2.length();
				i3=str1.length();
				str1.delete(0,i3);
				while (c>0)
				{		
					str1.append('0');
					c--;	
				}	    	
			}
	   		else
			{
	   			i3=str1.length();
				str1.delete(0,i3);
				//str3=str1;
				i1=str3.length();
				i3=0;
				while (i3!=i1)
				{
					c1=str3.charAt(i3);
					str1.append(c1);
					i3++;
				}
				
			}
			i3=str2.length();
			str2.delete(0,i3);
			for(t=1;t<r;t++)
	  	 	{
				c=0;
				foro=0;
				s=str1.length();
				d=s-1;
				i3=str2.length();
				str2.delete(0,i3);
				for(d=s-1;d>=0;d--)
				{
	  				
					c1=str3.charAt(d);
	  				c3=str1.charAt(d);
	  				a=c1-'0';
	  				b=c3-'0';
	  				a=a+b+foro;
					foro=a/10;
	  				pre=a%10;
					str2.append(pre);
				}
				if(foro!=0)
			  		str2.append(foro);
				str2.reverse();
				s=str2.length();
				b1=str3.length();
				i3=0;
				while (i3<(s-b1))
				{		
					str3.insert(0,'0');
					i3++;			
				}
				i3=str1.length();
				str1.delete(0,i3);
				i3=0;
				i1=str2.length();	
				while (i3!=i1)
				{
					c3=str2.charAt(i3);
					str1.append(c3);				
					i3++;				
				}								
	   		}
	   		if (r<=1)
			{		
				i3=0;
				i1=str1.length();	
				while (i3!=i1)
				{
					c3=str1.charAt(i3);
					str2.append(c3);				
					i3++;				
				}
	 		}
			b1=0;
			while (k1>b1)
			{		
				str2.append('0');
				b1++;			
			}
			if(k1>0)
	   		{
	   	   		s=str2.length();
				b1=mult2.length();
				if(s>b1)
				{
					i3=0;
					while (i3<(s-b1))
					{		
						mult2.insert(0,'0');
						i3++;					
					}			
				}
				if(b1>s)
				{
					i3=0;
					while (i3<(b1-s))
					{		
						str2.insert(0,'0');
						i3++;
					}
				}
				c=0;
				foro=0;
				s=mult2.length();
				for(d=s-1;d>=0;d--)
				{
		  			c1=mult2.charAt(d);
		  			c3=str2.charAt(d);
	  				a=c1-'0';
	  				b=c3-'0';
	  				a=a+b+foro;
	  				foro=a/10;
	  				pre=a%10;
	  				result.append(pre);
				}
				if(foro!=0)
	  				result.append('1');
				result.reverse();
				i3=mult2.length();
				mult2.delete(0,i3);
				i1=result.length();
				i3=0;
				while (i3!=i1)
				{
					c3=result.charAt(i3);
					mult2.append(c3);
					i3++;
				}
				result.delete(0,i1);
				
	   		}    	
			if (k1==0)
			{		
				i3=str2.length();
				i1=0;
				while (i1!=i3)
				{
					c3=str2.charAt(i1);
					mult2.append(c3);
					i1++;		 
					
				}
			}
			k1++;
	   		
		}	
		i=0;
	 	s=mult2.length();
	 	j=s;
	 	len1=len1+len2;
		if(len1>0)
	 	{
	   		
			mult2.insert(j-len1,'.');
		}
	 	JOptionPane.showMessageDialog(null,""+mult2);
		if(flag3==1&&flag4==0)
			mult2.insert(0,'-');
		if(flag4==1&&flag3==0)
			mult2.insert(0,'-');
		return mult2;
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
		int a=0,i3,b,pre=0,foro=0,i1,c,n,flag1=0,flag2=0,r,t;
	 	i3=str2.length();
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
		while(i3!=a&&flag1!=1)
	 	{
	   		if(str1.charAt(a)=='.')
	   		{
				flag1=1;
	 			break;
	    	}
	    	a++;
	 	}
	   	JOptionPane.showMessageDialog(null,"The multiple result is :"+a+k);
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
	   		/*len1=i-(a+1);
	   		len2=j-k;
	   		str2.append('.');
	   		for(n=0;n<len1;n++)
				str2.append('0');*/
			str1.deleteCharAt(a);
			str2.append('0');	   	
		}
	 	
		if(flag1==0&&flag2==1)
		{
	   		/*len2=j-(k+1);
	   		len1=i-a;
	   		str1.append('.');
	   		for(n=0;n<len2;n++)
				str2.append('0');*/
			str2.deleteCharAt(k);
			str1.append('0');	   	
	   	}
	 	if(flag1==1&&flag2==1)
	 	{
	   		/*len1=i-a;
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
	     	}*/
			str1.deleteCharAt(a);
			str2.deleteCharAt(k);
	 	}
		i=str1.length();
	 	j=str2.length();
	 	b=i;
	 	c=j;
	 	if(a>k)
	 	{
	   		
			i1=0;
			while (i1<(a-k))
			{
				str2.insert(0,'0');
				i1++;		
			}
		}
	 	else if(k>a)
	 	{
	   		i1=0;
			while (i1<(k-a))
			{
				str1.insert(0,'0');
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
			str3=str1;
			str1=str2;
			str2=str3;	 	
		}
	 	i=str1.length();
	 	j=str2.length();
	 	c=0;
	 	JOptionPane.showMessageDialog(null,"The  str1 is :"+str1);
		JOptionPane.showMessageDialog(null,"The  str2 is :"+str2);
	 	len1=a;
		len2=k;
		for(n=i-1,k=j-1;n>=0&&k>=0;n--,k--)
	 	{
	   		c1=str1.charAt(n);
	    	c2=str2.charAt(n);
	    	if(c1=='.')
	      	{
	 			c1=str1.charAt(--n);
				c2=str2.charAt(--k);
	 			inverse.append('.');
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
	    	inverse.append(a);
	 	}
		
	 	inverse.reverse();
		JOptionPane.showMessageDialog(null,"The multiple result is :"+len1+len2);
		if(len2>len1)
			len1=len2;
		if(flag1==1||flag2==1)
			inverse.insert(len1,'.');
		if(check==1)
	 	{
			inverse.insert(0,'-');	
	 	}
		return inverse;
	 }
public static void main(String args[])
	{
		
		int i=0;
		String str1=JOptionPane.showInputDialog("Enter a num");
		String str2=JOptionPane.showInputDialog("Enter another num");
		StringBuffer str3=new StringBuffer(str1);
		StringBuffer str4=new StringBuffer(str2);
		str3=deduction(str3,str4);
		JOptionPane.showMessageDialog(null,"The multiple result is :"+str3);
		System.exit(0);
	}
}
