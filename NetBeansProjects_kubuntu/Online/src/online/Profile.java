package online;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;


public class Profile extends JFrame
{
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    private JButton plainJButton;

    private JTextArea Pt;

    String str, str1, str2;

    public Profile ()
    {
        super( "Entering Profile Info" );
        setLayout( new FlowLayout() );

        label1 = new JLabel();
        label1.setText( "Name" );
        label1.setToolTipText( "Enter your Name" );
        add( label1 );

        textField1 = new JTextField( 35 );
        add( textField1 );

        label2 = new JLabel();
        label2.setText( "Address" );
        label2.setToolTipText( "Enter your Address" );
        add( label2 );

        textField2 = new JTextField(35);
        add( textField2 );

        label3 = new JLabel();
        label3.setText( "Phone No" );
        label3.setToolTipText( "Enter your Phone No" );
        add( label3 );

        textField2 = new JTextField(30);
        add( textField2 );

        plainJButton = new JButton("Profile");
        add( plainJButton );

        //ButtonHandler e = new ButtonHandler();
        plainJButton.addActionListener(
    		new ActionListener()
    		{
    			public void actionPerformed(ActionEvent event)
    			{
    				str = textField1.getText();
    				str1 = textField2.getText();
    				str2 = textField3.getText();
    				Pt.setText("Name:"+str+"\n"+"Address:"+str1+"\n"+"Phone No:"+str2+"\n");
    			}
    		}
    		);
    		Pt=new JTextArea(15,25);
    		Pt.setEditable(false);
    		add(Pt);

    }


}