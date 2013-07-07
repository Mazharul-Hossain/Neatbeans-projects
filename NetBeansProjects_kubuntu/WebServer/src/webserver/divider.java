import javax.swing.JOptionPane; 
public class divider
{
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
		int len1=str3.length(),i3=str3.length();
		int len2=str4.length();
		while(i3!=a&&check1!=1)
		{
			if(str3.charAt(a)=='.')
			{
				check1=1;
				b1=a;
			}
			++a;
		}
		i3=str4.length();
		while(i3!=k&&check2!=1)
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
				str3.deleteCharAt(b1);
				str4.deleteCharAt(d1);
				i1=0;
				while(i1<r)
				{	
					str4.append('0');
					i1++;
				}
			}
			else if((len1-b1)<(len2-d1))
			{	
				r=(len2-d1)-(len1-b1);
				str3.deleteCharAt(b1);
				str4.deleteCharAt(d1);
				i1=0;
				while(i1<r)
				{
					str3.append('0');
					i1++;
				}
			}
			else
			{
				
				str3.deleteCharAt(b1);
				str4.deleteCharAt(d1);
			}
		}
		else if(check1==1&&check2!=1)
		{
			str3.deleteCharAt(b1);
			len1=str3.length();
			r=len1-b1;
			i1=0;
			while (i1<r)
			{
				str4.append('0');
				i1++;		
			}
		}
		else if(check2==1&&check1!=1)
		{
			str4.deleteCharAt(d1);
			len2=str4.length();
			r=len2-d1;
			i3=0;
			while (i3<r)
			{
				str3.append('0');
				i3++;
			}
		}
		i=str3.length();
		j=str4.length();
		if(i>j)
		{
			i1=0;
			while (i1<(i-j))
			{		
				str4.insert(0,'0');	
				i1++;			
			}	
		}
		if(j>i)
		{
			i1=0;
			while (i1<(j-i))
			{
				str3.append('0');
				i1++;
				point+=1;
			
			}				
		}
		i3=str3.length();
		i1=0;
		while(check==3&&i1!=i3)
		{
			c1=str3.charAt(i1);
			c2=str4.charAt(i1);
			if(c1>c2)
				check=1;
			if(c2>c1)
				check=-1;
			i1++;
		}
		if(check==-1)
		{
			str3.append('0');
			point+=1;
		}
		i=str3.length();
		j=str4.length();
		int count=0,p=-1,count1=0;
		StringBuffer str5=new StringBuffer();
  		StringBuffer str6=new StringBuffer(); 
  		StringBuffer str7=new StringBuffer();
		StringBuffer str8=new StringBuffer();
  		StringBuffer div=new StringBuffer(); 
  		str5.append('0');
		str6.append('1');
		int len=0;
		i1=0;
		while(i1<10)
		{
			str7.append('0');
			i1++;	
		}
		if(point>0)
			len=point;
		check=1;
		for(r=0;check==1&&len<10;r++)
		{

			foro=0;
			c=0;
			pre=0;
			for(n=i-1,k=j-1;n>=0&&k>=0;n--,k--)
			{
				c1=str3.charAt(n);
				c2=str4.charAt(k);
				if(c1=='.')
				{
					c1=str3.charAt(--n);
					c2=str4.charAt(--k);
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
				result.append(a);
			}
			result.reverse();
			if(str3.toString().equals(str4.toString()))
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
						inverse.append(pre);
					}
					if(foro!=0)
					{
						inverse.append(foro);
					}
					inverse.reverse();
					i1=str5.length();
					str5.delete(0,i1);
					i1=0;
					i3=inverse.length();
					while (i1!=i3)
					{
						c1=inverse.charAt(i1);
						i1++;
						str5.append(c1);					
					}
					inverse.delete(0,i3);
					b1=str5.length();
					d1=str6.length();
					if(b1>d1)
					{
						str6.insert(0,'0');		
					}
				}
			}

			if(check==0)
				break;
			i1=str3.length();
			str3.delete(0,i1);
			i1=0;
			i3=result.length();
			while (i1!=i3)
			{
				c1=result.charAt(i1);
				i1++;
				str3.append(c1);					
			}
			result.delete(0,i3);
			i=str3.length();
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
					inverse.append(pre);
				}
				if(foro!=0)
					inverse.append(foro);
				inverse.reverse();
				i1=str5.length();
				str5.delete(0,i1);
				i1=0;
				i3=inverse.length();
				while (i1!=i3)
				{
					c1=inverse.charAt(i1);
					i1++;
					str5.append(c1);					
				}
				inverse.delete(0,i3);
				b1=str5.length();
				d1=str6.length();
				if(b1>d1)
				{
					str6.insert(0,'0');

				}

			}
			else
			{
				a=str7.charAt(len);
				a=a+1;
				str7.setCharAt(len,(char)a);
			}
			check1=3;
			i3=str3.length();
			i1=0;
			while(check1==3&&i3!=i1)
			{
				c1=str3.charAt(i1);
				c2=str4.charAt(i1);
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
				str3.append('0');
				i1=0;
				str4.insert(0,'0');
				check1=3;
				i1=0;
				i3=str3.length();
				while(check1==3&&i1!=i3)
				{
					c1=str3.charAt(i1);
					c2=str4.charAt(i1);
					if(c1>c2)
						check1=1;
					if(c2>c1)
						check1=-1;
					i1++;
				}
			}
			while(check1==-1)
			{
				str3.append('0');
				point+=1;
				len+=1;
				i1=0;
				str4.insert(0,'0');
				check1=3;
				i1=0;
				i3=str3.length();
				while(check1==3&&i1!=i3)
				{
					c1=str3.charAt(i1);
					c2=str4.charAt(i1);
					if(c1>c2)
						check1=1;
					if(c2>c1)
						check1=-1;
					i1++;
				}
			}
			i=str3.length();
			j=str4.length();
		}
		if(point>=0)
		{
			str5.append('.');
			r=0;
			t=str7.length();
			i1=0;
			while (t!=i1)
			{
				c1=str7.charAt(i1);
				str5.append(c1);
				i1++;
			}
		}	
		return str5;
		
	}
public static void main(String args[])
	{
		
		int i=0;
		String str1=JOptionPane.showInputDialog("Enter a num");
		String str2=JOptionPane.showInputDialog("Enter another num");
		StringBuffer str3=new StringBuffer(str1);
		StringBuffer str4=new StringBuffer(str2);
		str3=divide(str3,str4);
		JOptionPane.showMessageDialog(null,"The sdfds result is :"+str3);
		System.exit(0);
	}
}
