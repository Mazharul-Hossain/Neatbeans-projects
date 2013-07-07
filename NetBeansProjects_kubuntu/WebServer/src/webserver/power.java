import javax.swing.JOptionPane;
public class power
{
	public static double pow(double z,double n)
	{
		double i,sum,y,sum4=1,x,k,sum1,x1,j,n2=1,n3,n4;
		int  n1,c;
		if(n<0)
		{
			n2=0;
			n=-1*n;
		}
		n1=(int)n;
		x=z-1;
		x1=x;
		n3=n1;
		j=n-n3;
		n4=n-n3;
		sum=1;
		y=n4*x;
		i=1;
		if(n3!=0)
		{
			sum=z;
			while(n3!=i)
			{
				sum=sum*z;
				i++;
			}
		}
		if(j!=0)
		{
			if(x<=1)
			{
				sum4=1+n4*x;
				y=n4*x;
				for(i=1;i<100;i++)
				{
					y=y*(n4-i)*x/(i+1);
					sum4=sum4+y;
				}
			}
			if(x>1)
			{
				k=(1/x);
				sum1=1+n4*k;
				y=n4*k;
				for(i=1;i<100;i++)
				{
					y=y*(n4-i)*k/(i+1);
					sum1=sum1+y;
				}
				sum4=sum1;
				while(x1>2)
				{
					x1=x1-1;
					k=1/x1;
					sum1=1+n4*k;
					y=n4*k;
					for(i=1;i<100;i++)
					{
						y=y*(n4-i)*k/(i+1);
						sum1=sum1+y;
					}
					sum4=sum4*sum1;
				}
				if(x1>=1&&x1<2)
				{
					x1=x1-1;
					sum1=1+n4*x1;
					y=n4*x1;
					for(i=1;i<50;i++)
					{
						y=y*(n4-i)*x1/(i+1);
						sum1=sum1+y;
					}
					sum4=sum4*sum1;	
				}
				if(x1==2)
				{
					sum1=1+n4;
					y=n4;
					for(i=1;i<500;i++)
					{
						y=y*(n4-i)/(i+1);
						sum1=sum1+y;
					}
					sum4=sum4*sum1;
				}
			}
		}
		sum=sum4*sum;
		if(n2==0)
			sum=1/sum;
		return sum;	
	}
	public static void main(String args[])
	{
		
		int i=0;
		StringBuffer str3=new StringBuffer();
		String str1=JOptionPane.showInputDialog("Enter a num");
		double m=Double.parseDouble(str1);
		String str2=JOptionPane.showInputDialog("Enter a num");
		double n=Double.parseDouble(str2);
		double x=pow(m,n);
		JOptionPane.showMessageDialog(null,"The result is :"+x);
		System.exit(0);
	}
}
