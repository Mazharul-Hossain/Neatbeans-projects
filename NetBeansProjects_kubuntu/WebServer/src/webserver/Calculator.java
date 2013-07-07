import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Calculator extends JFrame implements ActionListener
{
	private JButton button[];
	private JTextArea output;
	private String operand1, operand2, operator,memory;
	private boolean flag = false;
	private boolean isResult = false;
	private	int i=0,j=0;
	public Calculator()
	{
		super("Sadi's Calculator");
		button=new JButton[36];
		JPanel cPanel = new JPanel();
		JPanel nPanel = new JPanel();
		cPanel.setLayout(new GridLayout(6,6));
		cPanel.add(button[0] = new JButton("x^2"));
		cPanel.add(button[1] = new JButton("1/x"));
		cPanel.add(button[2] = new JButton("3.rt.x"));
		cPanel.add(button[3] = new JButton("root"));
		cPanel.add(button[4] = new JButton("x^y"));
		cPanel.add(button[5] = new JButton("ln"));
		cPanel.add(button[6] = new JButton("e^x"));
		cPanel.add(button[7] = new JButton("nPr"));
		cPanel.add(button[8] = new JButton("nCr"));
		cPanel.add(button[9] = new JButton("x!"));
		cPanel.add(button[10] = new JButton("sin"));
		cPanel.add(button[11] = new JButton("cos"));		
		cPanel.add(button[12] = new JButton("tan"));
		cPanel.add(button[13] = new JButton("sin-1"));
		cPanel.add(button[14] = new JButton("cos-1"));
		cPanel.add(button[15] = new JButton("tan-1"));
		cPanel.add(button[16] = new JButton("MR"));
		cPanel.add(button[17] = new JButton("M_RC"));
		cPanel.add(button[18] = new JButton("PI"));
		cPanel.add(button[19] = new JButton("(-)"));
		cPanel.add(button[20] = new JButton("*"));
		cPanel.add(button[21] = new JButton("/"));
		cPanel.add(button[22] = new JButton("+"));
		cPanel.add(button[23] = new JButton("-"));
		cPanel.add(button[24] = new JButton("4"));
		cPanel.add(button[25] = new JButton("5"));
		cPanel.add(button[26] = new JButton("6"));
		cPanel.add(button[27] = new JButton("7"));
		cPanel.add(button[28] = new JButton("8"));
		cPanel.add(button[29] = new JButton("9"));
		cPanel.add(button[30] = new JButton("3"));
		cPanel.add(button[31] = new JButton("2"));
		cPanel.add(button[32] = new JButton("1"));
		cPanel.add(button[33] = new JButton("0"));
		cPanel.add(button[34] = new JButton("."));
		cPanel.add(button[35] = new JButton("="));
		for(int i=0;i<button.length;i++)
           button[i].addActionListener(this);	
		getContentPane().add(cPanel);
		nPanel.add(output = new JTextArea(1,40) );
		output.setBorder(BorderFactory.createEtchedBorder(1));
		getContentPane().add(nPanel, BorderLayout.NORTH);
		 
		
	}
	public static void main(String args[])
	{
	    Calculator frame = new Calculator();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(500,500);
	    frame.setVisible(true);
	    frame.setResizable(false);	
	}
	public void actionPerformed(ActionEvent ae)
    {
       
        String str = ae.getActionCommand();
		String str3,str2;
	
        if( str.equals("1")||str.equals("2")||str.equals("3")||str.equals("4")||
            str.equals("5")||str.equals("6")||str.equals("7")||str.equals("8")||
            str.equals("9")||str.equals("0")||str.equals(".")
          )
        {  
        	
			if (!flag)
			{
				output.setText(str);
				flag=true;
			}
			else
			{		
				output.append(str);   
				flag=true;
			}	
		}     
        
        else if( str.equals("+")||str.equals("*")||str.equals("-")||str.equals("/") )
                 
        {     
            if (operator!=null)
			{
				str2=output.getText();
				operand2=str2.substring(i); 
				//operand2=output.getText();
				process();
				operand1=output.getText();
				operator=str;
				output.append(str);
				str3=output.getText();
				i=str3.length();
			//	JOptionPane.showMessageDialog(null,"optor"+i);
				str3=null;
				flag=true;
			}
			else
			{		
				operand1 = output.getText();
            	output.append(str);
            	operator=str;
            	flag = true;
				str3=output.getText();
				i=str3.length();
			//	JOptionPane.showMessageDialog(null,"operator"+i);
				str3=null;
			}
		}
        else if(str.equals("sin")||str.equals("cos")||str.equals("tan")||
				str.equals("sin-1")||str.equals("cos-1")||str.equals("tan-1") )
        {
        	
            output.setText(str);
            i=str.length();
			operator=str;
			flag = true;
        }
        else if(str.equals("e^x")||str.equals("x!")||str.equals("ln")||
				str.equals("1/x")||str.equals("root")||str.equals("3.rt.x"))
        {
        	
            output.setText(str);
			i=str.length();
            operator=str;						          
			flag = true;
        }
		else if(str.equals("x^y")||str.equals("nPr")
				||str.equals("nCr") ||str.equals("x^2"))
        {
           	operand1=output.getText();
			output.append(str);
           	operator=str;
           	flag = true;
			str3=output.getText();
			i=str3.length();
			str3=null;
        }
        else if (str.equals("PI"))
		{
			double d=Math.PI;
			if (operand1==null)
			{
				operand1=""+d;
				output.setText(operand1);
			}
			else
			{
				operand2=""+d;
				output.setText(operand2);
			}	
		
		}
		else if (str.equals("MR"))
		{
			memory=null;
			memory=output.getText();
			if(operand1==null)
				operand1=memory;		
			if(operator!=null)
				operand1=memory;
					
		}
		else if (str.equals("M_RC"))
		{
			output.setText(memory);
		
		
		}
		else if (str.equals("(-)"))
		{
		/*	JOptionPane.showMessageDialog(null,"rty1"+operand1);
			JOptionPane.showMessageDialog(null,"rty2"+operator);*/
			if (operand1==null&&operator==null)
			{
				operand1 = output.getText();
				double d=Double.parseDouble(operand1);
				d=-d;		
				String str1=""+d;
				operand1 = null;
				operand1=str1;
				output.setText(operand1);
			}
			else if(operand2==null&&operator!=null)
			{
				str3 = output.getText();
				operand2=str3.substring(i);
				double d=Double.parseDouble(operand2);
				d=-d;		
				String str1=""+d;
				operand2 = null;
				operand2=str1;
				//output.setText(operand1);
				output.setText(operand1);
				output.append(operator);
				output.append(operand2);					
				str3=null;
			}
			else
			{
				str3 = output.getText();
				operand2=str3.substring(i);
				double d=Double.parseDouble(operand2);
				d=-d;		
				String str1=""+d;
				operand2 = null;
				operand2=str1;
				output.setText(operand1);
				output.append(operator);
				output.append(operand2);					
				str3=null;
			
			
			}			
		}
        else if( str.equals("=") )
        {
            
			str2=output.getText();
			operand2=str2.substring(i); 
			process();        
		}  
        
     
    }
    public void process()
    {
        if( operator.equals("+") )
        {
          	StringBuffer str7=new StringBuffer(operand1);
			StringBuffer str8=new StringBuffer(operand2);
			StringBuffer str9=new StringBuffer();
			str9=sum(str7,str8);
			output.setText(str9.toString());   	
        }
        else if( operator.equals("*") )
        {   
           	StringBuffer str7=new StringBuffer(operand1);
			StringBuffer str8=new StringBuffer(operand2);
			StringBuffer str9=new StringBuffer();
			str9=multi(str7,str8);
			output.setText(str9.toString());
		}        
		else if( operator.equals("-") )
        {
            StringBuffer str7=new StringBuffer(operand1);
			StringBuffer str8=new StringBuffer(operand2);
			StringBuffer str9=new StringBuffer();
			str9=deduc(str7,str8);
			output.setText(str9.toString());
		}
        else if( operator.equals("/") )
        { 
            StringBuffer str7=new StringBuffer(operand1);
			StringBuffer str8=new StringBuffer(operand2);
			StringBuffer str9=new StringBuffer();
			str9=divide(str7,str8);
			output.setText(str9.toString());
			output.setText(Double.toString( Double.parseDouble(operand1) / Double.parseDouble(operand2)  ) );   	
        }
        else if( operator.equals("sin") )
        {
            output.setText(Double.toString( Math.sin( Double.parseDouble(operand2)*Math.PI/180) ) );	
        }
        else if( operator.equals("cos") )
        {
            output.setText( Double.toString( Math.cos( Double.parseDouble(operand2)*Math.PI/180) ) );	
        }
        else if( operator.equals("tan") )
        {
            output.setText( Double.toString( Math.tan( Double.parseDouble(operand2 )*Math.PI/180) ) );	
        }
        else if( operator.equals("sin-1") )
        {
            output.setText( Double.toString(in_sin( Double.parseDouble(operand2))));	
        }
		else if( operator.equals("cos-1") )
        {
            output.setText( Double.toString(in_cos( Double.parseDouble(operand2))));	
        }
		else if( operator.equals("tan-1") )
        {
            output.setText( Double.toString( in_tan( Double.parseDouble(operand2))));	
        }
		else if( operator.equals("x^y") )
        {
        	output.setText( Double.toString( xtoy( Double.parseDouble(operand1), Double.parseDouble(operand2) ) ));
        }   
        else if( operator.equals("root") )
        {
        	output.setText( Double.toString( root( Double.parseDouble(operand2) ) ));
        }   
		else if( operator.equals("3.rt.x") )
        {
        	output.setText( Double.toString( cubic_root( Double.parseDouble(operand2) ) ));
        }   
		else if( operator.equals("x!") )
        {
        	output.setText( Double.toString( fact_n( Double.parseDouble(operand2) ) ));
        }   
   	    else if( operator.equals("1/x") )
        {
        	output.setText( Double.toString( inverse( Double.parseDouble(operand2) ) ));
        }
		else if( operator.equals("e^x") )
        {        	
			output.setText( Double.toString( eX( Double.parseDouble(operand2) ) ));
        }   
        else if( operator.equals("ln") )
        {        	
			output.setText( Double.toString( ln( Double.parseDouble(operand2) ) ));
        }   
        else if( operator.equals("x^2") )
        {        	
			output.setText( Double.toString( xTo2( Double.parseDouble(operand2) ) ));
        }
		else if( operator.equals("nCr") )
        {        	
			output.setText( Double.toString( nCr( Double.parseDouble(operand1),Double.parseDouble(operand2) ) ));
        }
		else if( operator.equals("nPr") )
        {        	
			output.setText( Double.toString( nPr( Double.parseDouble(operand1),Double.parseDouble(operand2) ) ));
        }
		operator = null;
        operand1 = null;
        operand2 = null;
        flag = false;
          	
    }
    /*public static double inverse(double z)
	{
		double d= 1/z;
		return d;
	}*/
	public static double cubic_root(double z)
	{
		double n=.3333333333333333333;
		double result=xtoy(z,n);
		return result; 
	}
	public static double root(double z)
	{
		double n=.5;
		double result=xtoy(z,n);
		return result; 
	}
	public static double xtoy(double z, double n)
    {
    	double i,sum,y,sum4=1,x,k,sum1,x1,j,n2=1,n3,n4,res=0;
		int  n1,c;
		if(z==0)
			return res;
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
	public static double xTo2(double x)
    {
    	return x*x;	
    
	}
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
	
	public static double nPr(double a,double b)
    {
    	double n=1,sum=1,result=0;
		int j=(int)a,k=(int)b;
		double r=1,i;
		double n1=a-j;
		double n2=b-k;
		if(n1!=0||n2!=0)
			return result;
		if(a<0||b<0)
			return result;
    	for(i=0;i<b;i++)
    		sum=sum*(a-i);
		return sum;	
    }	
    public static double nCr(double a,double b)
    {
    	double n=1,m=1,sum=1,result=0;
    	double r=1,i;
		int j=(int)a,k=(int)b;
		double n1=a-j;
		double n2=b-k;
		if(n1!=0||n2!=0)
			return result;

    	if(b>a)
			return result;
		if(a<0||b<0)
			return result;

		for(i=1;i<=a;i++)
    		sum=sum*i;
    	for(i=1;i<=b;i++)
    		r=r*i;	
    	sum=sum/r;
		for(i=1;i<=a-b;i++)
    		m=m*i;	
    	sum=sum/m;		
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
    	double z=0,y=x,sum=x,b=90;
		if(x==1)
			return b;
		for(int i=1;i<200;i++)
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
    	int check=0;
		if (x>1)
		{
			check=1;
			x=1/x;
		}
		for(double i=0;i<111;i++)
    	{
    		z=2*i+1;
    		y=Math.pow(-1,i)*Math.pow(x,z)/z;
    		sum=sum+y;	
    	}			
    	sum=sum*180/Math.PI;
		if(check==1)
			sum=90-sum;
    	return sum;
    }
    public static double fact_n(double n)
    {
    	double sum=1,result=0;
    	if(n<0)
			return result ;
		if (n!=0)
    	{
    		for(double i=n;i>=1;i--)
    			sum=sum*i;
    		return sum;
    	}		
    	
    	return sum;		
    }
    public static double ln(double x)
    {
    	double z=0,sum=0,y,error=-1,t=0;
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
    	if(x<=2&&x!=1)
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
		if(x==1)
			return t;
    	return error;	 	
    }	
    public static double inverse(double x)
    {
    	return(1/x);
    }
	public static StringBuffer deduc(StringBuffer str1,StringBuffer str2)
	{
		StringBuffer str3=new StringBuffer();
		StringBuffer inverse=new StringBuffer(); 
	 	StringBuffer result=new StringBuffer();
		StringBuffer result1=new StringBuffer();
	 	char c1='-',c2='-',c3,c4;
	 	int ck1=0,ck2=0,i1,i3;
		c3=str1.charAt(0);
		c4=str2.charAt(0);		
		if(c1==c3)
			ck1=1;
		if(c2==c4)
			ck2=1;	
		if (ck1==0&&ck2==1)
		{
			result1=sum(str1,str2);
			return result1;
		
		}
		if (ck1==1&&ck2==1)
		{
			str1.deleteCharAt(0);
			str2.deleteCharAt(0);
			i1=0;
			i3=str1.length();
			while (i1!=i3)
			{
				c1=str1.charAt(i1);
				str3.append(c1);
				i1++;
			}		
			i3=str1.length();
			str1.delete(0,i3);
			i1=0;
			i3=str2.length();
			while (i1!=i3)
			{
				c1=str2.charAt(i1);
				str1.append(c1);
				i1++;
			}
			i3=str2.length();
			str2.delete(0,i3);
			i1=0;
			i3=str3.length();
			while (i1!=i3)
			{
				c1=str3.charAt(i1);
				str2.append(c1);
				i1++;
			}
			str3.delete(0,i3);
		}
		if (ck1==1&&ck2==0)
		{
			str1.deleteCharAt(0);
			//str2.deleteCharAt(0);
			result1=sum(str1,str2);
			result1.insert(0,'-');
			return result1;	
		}
		int i=str1.length(),k=0;
	 	int j=str2.length();
	 	int check=3;
		int a=0,b,pre=0,foro=0,c,n,flag1=0,flag2=0,r,t;
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
	   	i1=0;
		c2='0';
		if (flag1==0)
		{
			c1=str1.charAt(i1);
			if(c1==c2)
			{
				str1.deleteCharAt(0);		
				i1++;
						
			}
			
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
	   		
			str1.deleteCharAt(a);
			str2.append('0');	   	
		}
	 	
		if(flag1==0&&flag2==1)
		{
	   		
			str2.deleteCharAt(k);
			str1.append('0');	   	
	   	}
	 	if(flag1==1&&flag2==1)
	 	{
	   		
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
		int len1=str3.length();
	 	int len2=str4.length(),i3;
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
	 	if(flag3==1&&flag4==0)
			mult2.insert(0,'-');
		if(flag4==1&&flag3==0)
			mult2.insert(0,'-');
		return mult2;
	}
	public static StringBuffer divide(StringBuffer str3,StringBuffer str4 )
	{
 		StringBuffer str1=new StringBuffer();
  		StringBuffer inverse=new StringBuffer(); 
  		StringBuffer result=new StringBuffer();
		StringBuffer str2=new StringBuffer();
  		char c1='-',c2='-',c3,c4;
		int check=3;
		int point=-1;
		int a=0,b,pre,foro=0,c,n,check1=0,check2=0,r,t,i1;
		int i=0,j=0,b1=0,d1=0,s,flag3=0,flag4=0;
		int k=0,v;
		int len1=str3.length(),i3;
		int len2=str4.length();
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
		i3=str3.length();
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
		if(flag3==1&&flag4==0)
			str5.insert(0,'-');
		if(flag4==1&&flag3==0)
			str5.insert(0,'-');
		return str5;
		
	}
	public static StringBuffer sum(StringBuffer str1,StringBuffer str2)
  	{
  		StringBuffer str3=new StringBuffer();
  		StringBuffer inverse=new StringBuffer(); 
  		StringBuffer result=new StringBuffer();
		StringBuffer result1=new StringBuffer();
  		char c1='-',c2='-',c3,c4;
  		int i=str1.length(),k=0;
  		int j=str2.length(),flag1=0,flag2=0;	
  		int a=0,b,pre,foro=0,i1,c,n,flag3=0,flag4=0,r,t;
  		int check1=0,check2=0,b1=0,d1=0;
		c3=str1.charAt(0);
		c4=str2.charAt(0);
		if(c1==c3)
			flag3=1;
		if(c2==c4)
			flag4=1;	
		if(flag3==1)
			str1.deleteCharAt(0);
		if(flag4==1)
			str2.deleteCharAt(0);
		if (flag3==0&&flag4==1)
		{
			result1=deduc(str1,str2);
			return result1;
			
		}
		if (flag4==0&&flag3==1)
		{
			result1=deduc(str2,str1);
			return result1;
		}
		int len3=str1.length(),len4=str2.length();
		int i3=str2.length();
		i=0;
		while(i3!=i)
	 	{
	   		if(str1.charAt(i)=='.')
	    	{
				check1=1;
				b1=i;
	    	}
	    	++i;
	 	}
	 	i3=str2.length();
		j=0;
		while(i3!=j)
	 	{
	   		if(str2.charAt(j)=='.')
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
	   		str1.deleteCharAt(b1);
			len3=len3-(b1+1);
			
	 	}
		else
			len3=0;
	 	c=0;
		b=0;
	 	if(check2==1)
	 	{
	   		str2.deleteCharAt(d1);
		    len4=len4-(d1+1);
		}
		else
			len4=0;
		i=str1.length();	 	
		j=str2.length();
	  	i3=0;
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
    		str2.append('.');
    		for(n=0;n<len1;n++)
				str2.append('0');
		}
  		
		if(flag1==0&&flag2==1)
  		{	
    		len2=j-(k+1);
    		len1=i-a;
    		str1.append('.');
    		for(n=0;n<len2;n++)
				str1.append('0');
			
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
   		i=str1.length()	;
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
	 				
					inverse.append('.');
    		   	}
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
			i=0;
	 		i1=inverse.length();
	 		j=i1;
			if(flag3==1)
				len3=len3-1;
			else if(flag4==1)
				len4=len4-1;	
	 		len3=len3+len4;
			if(len3>0)
	 		{
	   		
				inverse.insert(j-len3,'.');
			}
			if(flag3==1&&flag4==1)
				inverse.insert(0,'-');
  			return inverse;
	}
	
}

