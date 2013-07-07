/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package alubyshakil;


import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.net.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;

class Main extends JFrame implements ActionListener{
    public JPanel addressPanel,descriptionPanel;
    public JButton Home,Stop,Reload,backPage,forwardPage;
    public JTextField address,searchHere;
    public JComboBox Combo;
    public JEditorPane contents;
    public JLabel description;
    public String[] searchItem={"Google","Ask.com","Yahoo","Amazon.com","Answer.com","eBay","Wikipedia"};
    final String key="file:///";
    public String str,copiedStr;

    Stack<String> backLog=new Stack<String>();
    Stack<String> forLog=new Stack<String>();

    boolean backButtonClicked=false;
    boolean forwardButtonClicked=false;
    boolean copyClicked=false;
    boolean bookmarkFlag=false;
    boolean addressClicked=false;
    boolean searchHereClicked=false;

    //////////////////////////////////////////////////////////////////////////////////////////

    public Main(){

        super("WebBrowser");

        MouseKarikuri Mouse = new MouseKarikuri();

        int i;
        ImageIcon backIcon=new ImageIcon("Back.png");
        ImageIcon forwardIcon=new ImageIcon("Forward.png");
        ImageIcon stopIcon=new ImageIcon("Stop.png");
        ImageIcon reloadIcon=new ImageIcon("reload.png");
        ImageIcon homeIcon=new ImageIcon("home.png");

        addressPanel=new JPanel();

        backPage=new JButton(backIcon);
        if(backLog.size()<=1) backPage.setEnabled(false);
        backPage.addActionListener(this);

        forwardPage=new JButton(forwardIcon);
        if(forLog.size()==0)forwardPage.setEnabled(false);
        forwardPage.addActionListener(this);


        Reload=new JButton(reloadIcon);
        Reload.addActionListener(this);
        Stop=new JButton(stopIcon);
        Stop.addActionListener(this);
        Home=new JButton(homeIcon);
        Home.addActionListener(this);


        addressPanel.add(backPage);
        addressPanel.add(forwardPage);
        addressPanel.add(Reload);
        addressPanel.add(Stop);
        addressPanel.add(Home);
        float f[]=new float[3];
        Color.RGBtoHSB(211,218,237,f);
        addressPanel.setBackground(Color.getHSBColor(f[0],f[1],f[2]));

        address=new JTextField(20);
        address.setSize(100,40);
        address.setText("Enter Address Here");

        address.addActionListener(this);
		address.addMouseListener(Mouse);

        addressPanel.add(address);

        Combo=new JComboBox();
        for(i=0;i<searchItem.length;i++) Combo.addItem(searchItem[i]);
        addressPanel.add(Combo);
        //Combo.setEditable(true);

        searchHere=new JTextField(10);
        searchHere.setText("Search Here!!!");
        searchHere.addMouseListener(Mouse);
        addressPanel.add(searchHere);

		MenuInterface();

        contents = new JEditorPane();
        contents.setEditable(false);
		LinkListener linkHandler = new LinkListener();
		contents.addHyperlinkListener(linkHandler);
		contents.addMouseListener(Mouse);
		//showPage("http://www.google.com");
		add(new JScrollPane(contents),BorderLayout.CENTER);

		descriptionPanel=new JPanel();
		description=new JLabel("");
		descriptionPanel.add(description);


        add(BorderLayout.NORTH,addressPanel);
        add(BorderLayout.SOUTH,descriptionPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        pack();
    }

    JMenuBar menuBar;
    JMenu file,edit,view,history,bookmark,tool,help;
    JMenuItem j[]=new JMenuItem[23];
    JMenuItem b[]=new JMenuItem[10];
    JMenuItem r[]=new JMenuItem[10];
    int recentItem;

    public void MenuInterface(){

        menuBar=new JMenuBar();
        file=new JMenu("File");
        file.setMnemonic('F');
        edit=new JMenu("Edit");
        edit.setMnemonic('E');
        view=new JMenu("View");
        view.setMnemonic('V');
        history=new JMenu("History");
        history.setMnemonic('s');
        bookmark=new JMenu("Bookmarks");
        bookmark.setMnemonic('B');
        tool=new JMenu("Tools");
        tool.setMnemonic('T');
        help=new JMenu("Help");
        help.setMnemonic('H');

        j[0] = new JMenuItem("Open Location");
        j[1] = new JMenuItem("Open File");
        j[2] = new JMenuItem("Save");
        j[3] = new JMenuItem("Exit");

        j[4] = new JMenuItem("Cut");
        j[4].setEnabled(false);
        j[5] = new JMenuItem("Copy");
        j[5].setEnabled(false);
        j[6] = new JMenuItem("Paste");
        j[6].setEnabled(false);
        j[7] = new JMenuItem("Delete");
        j[7].setEnabled(false);
        j[8] = new JMenuItem("Find");
        j[9] = new JMenuItem("Select All");

        j[10] = new JMenuItem("Status Bar");
        j[11] = new JMenuItem("Menu Bar");
        j[12] = new JMenuItem("Navigation Bar");
        j[13] = new JMenuItem("Page Source");

        j[14] = new JMenuItem("Back");
        j[14].setEnabled(false);
        j[15] = new JMenuItem("Forward");
        j[15].setEnabled(false);
        j[16] = new JMenuItem("Show All History");
        j[17] = new JMenuItem("Recent Pages");

        j[18] = new JMenuItem("Bookmark This Page");
        j[19] = new JMenuItem("Recently Bookmarked");

        j[20] = new JMenuItem("Page Info");
        j[21] = new JMenuItem("Clear History");

        j[22] = new JMenuItem("About Us");
        j[22].setBackground(Color.white);


  		int k;
  		for(k=0;k<=3;k++) file.add(j[k]);
  		for(k=4;k<=9;k++) edit.add(j[k]);
  		for(k=10;k<=13;k++) view.add(j[k]);
  		for(k=14;k<=17;k++) history.add(j[k]);
  		for(k=18;k<=19;k++) bookmark.add(j[k]);
  		for(k=20;k<=21;k++) tool.add(j[k]);
  		help.add(j[22]);
  		bookmark.addSeparator();

  		for(k=0;k<j.length;k++) j[k].addActionListener(this);

        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(view);
        menuBar.add(history);
        menuBar.add(bookmark);
        menuBar.add(tool);
        menuBar.add(help);

		try{
			addBookmark();
		}catch(IOException e){
			System.out.println("Error Reading File");
		}
		addRecentHistory();
        setJMenuBar(menuBar);
    }

    public void addBookmark()throws IOException
    {
    	FileReader ff=null;
    	try{
    		ff=new FileReader("bookmarkLog.txt");
    	}catch(FileNotFoundException e){
    		System.out.println("File Not Found");
    	}

    	BufferedReader br=new BufferedReader(ff);

    	String s;
    	int i=0;
    	while((s=br.readLine())!=null){
    		b[i]=new JMenuItem(s);
    		bookmark.add(b[i]);
    		b[i].addActionListener(this);
    		i++;
    		if(i>=10)return;
    	}
    	ff.close();

    }

    public void addRecentHistory(){
    	FileReader f=null;
        try{
       		f=new FileReader("historyLog.txt");
       		BufferedReader br=new BufferedReader(f);
       		int count=0;
       		String s=null;
       		while((s=br.readLine())!=null)count++;
       		f.close();
       		br=null;


       		String ss[]=new String[10];
       		s=null;
       		f=new FileReader("historyLog.txt");
       		br=new BufferedReader(f);
       		int v=count-ss.length;

       		for(int i=0;i<v;i++){
       			s=br.readLine();
       		}
       		int j=0;
       		while((s=br.readLine())!=null){
       			ss[j]=s;
       			j++;
       		}
       		recentItem=j;
       		j--;
       		int k=0;
       		while(j>=0){
       			r[k]=new JMenuItem(ss[j]);
       			r[k].addActionListener(this);
       			history.addSeparator();
       			history.add(r[k]);
       			k++;j--;
       		}


       	}catch(FileNotFoundException e){
       		System.out.println("File Not Found");
       	}catch(IOException ee){
       		System.out.println("Error Reading File");
       	}
    }

    private class MouseKarikuri implements MouseListener{
		public void mousePressed(MouseEvent e) {

			if(e.getComponent()==address){
				if(copyClicked)j[6].setEnabled(true);
			}
			else if(e.getComponent()==searchHere){
				if(copyClicked)j[6].setEnabled(true);
			}
			else j[6].setEnabled(false);
			if(e.getComponent()==address) {
				if(addressClicked==false){
					address.setSelectionStart(0);
					addressClicked=true;
				}
				else addressClicked=false;

			}
		    else if(e.getComponent()==searchHere)searchHere.setSelectionStart(0);
		}
    	public void mouseReleased(MouseEvent e) {
    		if(e.getComponent()==contents){
				str=contents.getSelectedText();
				if(str!=null){
					j[5].setEnabled(true);
				}
				else j[5].setEnabled(false);
			}

			else if(e.getComponent()==address){
				str=address.getSelectedText();
				if(str!=null){
					j[5].setEnabled(true);
				}
				else j[5].setEnabled(false);
			}

			else if(e.getComponent()==searchHere){
				str=searchHere.getSelectedText();
				if(str!=null){
					j[5].setEnabled(true);
				}
				else j[5].setEnabled(false);
			}


			else
			{
				j[5].setEnabled(false);
			}
    	}
    	public void mouseEntered(MouseEvent e) {}
    	public void mouseExited(MouseEvent e) {}

    	public void mouseClicked(MouseEvent e) {}
    }

    private class LinkListener implements HyperlinkListener{
		public void hyperlinkUpdate(HyperlinkEvent event)
		{
            if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
                showPage(event.getURL().toString());
            if (event.getEventType() == HyperlinkEvent.EventType.ENTERED){
            	description.setText(event.getURL().toString());
            }
            else description.setText("");
		}
    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource()==address){
            showPage(address.getText());
        }
//////////////////////////////////////////////////////////
		if(ae.getSource()==j[0]) showPage(address.getText());
        else if(ae.getSource()==j[3]){
        	int i=JOptionPane.showConfirmDialog(this,"Do you want to exit browser?","Web Browser",JOptionPane.OK_CANCEL_OPTION);
        	if(i==JOptionPane.OK_OPTION) System.exit(1);
        }

        else if(ae.getSource()==j[5]){
        	copiedStr=str;
        	copyClicked=true;
        }

        else if(ae.getSource()==j[6]){

        	String s=address.getSelectedText();
        	String ss=address.getText();
        	int i=address.getCaretPosition();
        	int len;
        	if(s==null)len=0;
        	else len=s.length();


        	StringBuffer bs=new StringBuffer(ss);

        		bs.delete(i-len,i);
        		bs.insert(i-len,copiedStr);



        	address.setText(bs.toString());
        }

        else if(ae.getSource()==j[13]){
        	StringBuffer ss=null;
        	try{
        		URL hp=new URL(address.getText());
	        	URLConnection hpCon=hp.openConnection();

	        	InputStream input=hpCon.getInputStream();
	        	int c;
	        	ss=new StringBuffer("");
	        	while((c=input.read())!=-1){
	        		ss.append((char)c);
	        	}
	        	input.close();
	        	Font f=new Font("Comic Sans MS",Font.PLAIN,18);
	        	contents.setFont(f);
	        	contents.setText(ss.toString());

	        }catch(IOException e){
	        	JOptionPane.showMessageDialog(this,"Unable to retrive page source","Web Browser",JOptionPane.ERROR_MESSAGE);
	        }

        }

        else if(ae.getSource()==j[14]){
        	ae.setSource(backPage);
        	return;
        }

        else if(ae.getSource()==j[15]){
        	ae.setSource(forwardPage);
        	return;
        }

        else if(ae.getSource()==j[18]){
        	FileWriter f=null;
        	if(bookmarkFlag==true)
        	{

        		String s=address.getText()+'\n';
        		char buffer[]=new char[s.length()];
        		s.getChars(0,s.length(),buffer,0);
        		System.out.println(s);
        		System.out.println(buffer);
        		try{
        			f=new FileWriter("bookmarkLog.txt",true);
        			f.write(buffer);
        			f.close();
        		}catch(IOException e){
        			System.out.println("File Error!");
        		}

        	}
        }

        else if(ae.getSource()==j[22]){
        	String s="Adviser: Wasiur Rahman\n"+
            	"Programmer: Mir Tazbinur Shovon (0805040)\n"+
            	"Programmer: Ananta Ghosh (0805041)\n";
            JOptionPane.showMessageDialog(this,s,"About Us",JOptionPane.INFORMATION_MESSAGE);
        }

        else if(ae.getSource()==j[20]){
        	URL hp=null;

        	String loc,st=address.getText();
	    	if(st.startsWith(key)==false)loc=key+st;
	    	else loc=st;

        	try{
        		hp=new URL(loc);
        		st="Protocol: "+hp.getProtocol()+'\n'+
        			"Port: "+hp.getPort()+'\n'+
        			"Host: "+hp.getHost()+'\n'+
        			"File: "+hp.getFile()+'\n'+
        			"Ext: "+hp.toExternalForm()+'\n'+
        			"ip: "+InetAddress.getByName(loc);

        	}catch(MalformedURLException e){
        		st="Unable to Retrive Any Information";
        	}catch(UnknownHostException ee){
        		st="Unable to Retrive Any Information";
        	}
			JOptionPane.showMessageDialog(this,"Page Info:\n"+st,"Page Info",JOptionPane.INFORMATION_MESSAGE);
        }

        else if(ae.getSource()==j[16]){
        	JFrame histFrame=new JFrame("History");
        	histFrame.setSize(400,400);
        	JTextArea histArea=new JTextArea();
        	histFrame.add(new JScrollPane(histArea));
        	histFrame.setVisible(true);
        	histArea.setBackground(Color.gray);


        	Font f=new Font("Comic Sans MS",Font.PLAIN,18);
        	histArea.setFont(f);


        	BufferedReader br=null;
        	FileReader ff=null;
        	try{
        		String s=null;
        		StringBuffer sb=new StringBuffer("");
	        	ff=new FileReader("historyLog.txt");
	        	br=new BufferedReader(ff);
	        	boolean flag=true;
	        	while((s=br.readLine())!=null)
	        	{
	        		flag=false;
	        		sb.append(s+'\n');
	        	}
	        	if(flag) sb.append("\tNo History Found");

	        	histArea.setText(sb.toString());
	        	histArea.setEditable(false);

        		ff.close();

        	}catch(FileNotFoundException e){
        		System.out.println("File Not Found");

        	}catch(IOException ee){
        		System.out.println("File Reading Error!!!");

        	}
        }

        else if(ae.getSource()==j[21]){
        	FileWriter f=null;

        	int i=JOptionPane.showConfirmDialog(this,"Are You Sure To Clear History?","History",JOptionPane.OK_CANCEL_OPTION);
        	if(i==JOptionPane.CANCEL_OPTION)return;

        	try{
        		f=new FileWriter("historyLog.txt");
        		f.write("");
        		f.close();
        		addRecentHistory();

        	}catch(FileNotFoundException e){
        		System.out.println("History Can't be cleared");
        	}catch(IOException ee){
        		System.out.println("History Can't be cleared");
        	}


        }

        for(int q=0;q<10;q++){
        	if(ae.getSource()==b[q]){ showPage(b[q].getText()); }
        }
        for(int p=0;p<recentItem;p++){
        	if(ae.getSource()==r[p]){ showPage(r[p].getText()); }
        }

///////////////////////////////////////////////////////////
        if(ae.getSource()==Home){
        	showPage("http://www.google.com");
        }
        else if(ae.getSource()==Reload){
        	showPage(address.getText());
        }

        else if(ae.getSource()==backPage){
        	forLog.push(backLog.pop());
        	backButtonClicked=true;
        	showPage(backLog.peek());
        }

        else if(ae.getSource()==forwardPage){
        	forwardButtonClicked=true;
        	showPage(forLog.pop());
        }
    }

    public void showPage(String str){

    	if(str.equals("Enter Address Here") || str.equals("")) return;

    	String location;
    	if(str.startsWith(key)==false)location=key+str;
    	else location=str;
    	////////////////////////////////
    	if(backButtonClicked==false){
    		backLog.push(location);
    		forLog.clear();
    	}
    	else backButtonClicked=false;

    	////////////////////////////////

    	if(backLog.size()<=1) {
    		backPage.setEnabled(false);
    		j[14].setEnabled(false);
    	}
    	else {
    		backPage.setEnabled(true);
    		j[14].setEnabled(true);
    	}

    	if(forLog.size()==0) {
    		forwardPage.setEnabled(false);
    		j[15].setEnabled(false);
    	}
    	else {
    		forwardPage.setEnabled(true);
    		j[15].setEnabled(false);
    	}

    	////////////////////////////////

        try{
        	address.setText(location);
            contents.setPage(location);

            bookmarkFlag=true;

            FileWriter f=new FileWriter("historyLog.txt",true);
            String now = new java.util.Date().toString();
            String ss=location+'\n';
            f.write(ss);
            f.close();

        }catch(IOException e){
        	bookmarkFlag=false;
            JOptionPane.showMessageDialog(this, "Unable to retrieve URL", "Invalid URL", JOptionPane.ERROR_MESSAGE);
        }
    }

	static Main frame;
    public static void main(String[] args) {

        frame=new Main();
        frame.setSize(1200,600);


    }
}