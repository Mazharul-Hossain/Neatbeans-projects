package javaapplication6;

import java.io.*;
import java.util.Locale;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

import com.sun.speech.freetts.Tokenizer;
import com.sun.speech.freetts.UtteranceProcessor;
//import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.JavaClipAudioPlayer;

import javax.swing.UnsupportedLookAndFeelException;

public class VoicePad extends JFrame
{
	private JScrollPane ps = null;
	private JTextArea textArea = null;
	private JFileChooser fileChooser = null;

	private JMenuBar menuBar = null;

	private JMenu fileMenu = null;
	private JMenu speechMenu = null;

	private JMenuItem newMenuItem = null;
	private JMenuItem openMenuItem = null;
	private JMenuItem saveMenuItem = null;
	private JMenuItem exitMenuItem = null;
	private JMenuItem playMenuItem = null;
	private JMenuItem pauseMenuItem = null;
	private JMenuItem resumeMenuItem = null;
	private JMenuItem stopMenuItem = null;

	private ActionListener myActionListener = null;

	// TBD: add necessary variables for speech synthesis
	private Synthesizer synthesizer = null;
	private Voice voice = null;
	private String voiceName = "";
	private String VOICE_SELECTED = "kevin16";

	// constructor
	public VoicePad()
	{
		super("Novusware - VoicePad");
		setSize(800, 600);
                
                try {

                    System.out.println("NOVUSWARE : SeaGlassLookAndFeel obtained.");
                    UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(VoicePad.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(VoicePad.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(VoicePad.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(VoicePad.class.getName()).log(Level.SEVERE, null, ex);
                }

		// initialize the application settings
		init();

		// set up the file selection
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));

		WindowListener wndCloser = new WindowAdapter()
		{
                    @Override
			public void windowClosing(WindowEvent e)
			{
				closeSpeechSynthesisEngine();
				System.exit(0);
			}
		};
		addWindowListener(wndCloser);
	}

	// execute voicepad application
	public static void main(String argv[])
	{
		VoicePad voicePad = new VoicePad();
		voicePad.setVisible(true);
	}

	// initialization
	private void init()
	{
            System.out.println("NOVUSWARE : Entrance obtained.");
		textArea = new JTextArea();
		ps = new JScrollPane(textArea);
		this.getContentPane().add(ps, BorderLayout.CENTER);

		textArea.append("Voice-enabled editor.");

		initSpeechSynthesisEngine();

		this.setJMenuBar(getVoicePadMenuBar());

		System.out.println("NOVUSWARE : VoicePad application initialized.");
	}

	private void initSpeechSynthesisEngine()
	{
            System.out.println("NOVUSWARE : initSpeechSynthesisEngine obtained.");
            String message = "";
		String synthesizerName = System.getProperty("synthesizerName",
                        "Unlimited domain FreeTTS Speech Synthesizer from Sun Labs");
                System.out.println(synthesizerName);

		// Create a new SynthesizerModeDesc that will match the FreeTTS synthesizer.
		SynthesizerModeDesc desc = new SynthesizerModeDesc (
                        synthesizerName, null,	Locale.getDefault(), Boolean.FALSE, null);

                System.out.println(desc);
		// obtain the Speech Synthesizer instance
		try
		{
			synthesizer = Central.createSynthesizer(null);//.createSynthesizer(desc);
                        System.out.println(synthesizer);
                        synthesizer.allocate();

			
                        if(synthesizer == null)
			{
				message = "Make sure that there is a \"speech.properties\" file " +
									"at either of these locations: \n";
									message += "user.home    : " +
									System.getProperty("user.home") + "\n";
									message += "java.home/lib: " + System.getProperty("java.home")
									+ File.separator + "lib\n";

				System.out.println("NOVUSWARE : ERROR! Synthesizer not found!");
				System.out.println(message);

				throw new Exception("Synthesizer not found!");
			}

			System.out.println("NOVUSWARE : Speech synthesizer obtained.");

			voiceName = System.getProperty("voiceName", VOICE_SELECTED);
			voice = new Voice(voiceName, Voice.GENDER_DONT_CARE, Voice.AGE_DONT_CARE, null);

                        //VoiceManager voiceManager = VoiceManager.getInstance();
                        //voice = voiceManager.getVoice(voiceName);

			if(voice == null)
			{
				System.out.println("NOVUSWARE : ERROR! No voice selected!");
				throw new Exception("No voice selected!");
			}

			System.out.println("NOVUSWARE : Voice " + VOICE_SELECTED + " selected.");
			synthesizer.allocate();
			synthesizer.resume();
			//synthesizer.getSynthesizerProperties().setVoice(voice);
                       
		}
		catch(Exception e)
		{
			System.out.println("NOVUSWARE : ERROR! initializing speech engine." + e);
			closeSpeechSynthesisEngine();
		}

		System.out.println("NOVUSWARE : Speech engine initialized.");
	}

	private void closeSpeechSynthesisEngine()
	{
		try
		{
			if(synthesizer != null)
				synthesizer.deallocate();
			System.out.println("NOVUSWARE : Speech engine shutdown.");
		}
		catch(Exception e)
		{
			System.out.println("NOVUSWARE : ERROR! closing speech synthesis engine." + e);
			closeSpeechSynthesisEngine();
		}
	}

	// menu bar creation
	private JMenuBar getVoicePadMenuBar()
	{
		if(menuBar == null)
		{
			menuBar = new JMenuBar();

			menuBar.add(getFileMenu());
			menuBar.add(getSpeechMenu());

			System.out.println("NOVUSWARE : Menubar added.");
		}

		return menuBar;
	}

	// file menu and file menu items begin
	private JMenu getFileMenu()
	{
		if(fileMenu == null)
		{
			// create menu
			fileMenu = new JMenu("File");
			myActionListener =
				new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
                                            System.out.println("NOVUSWARE : File menu action performed.");
					}
				};
			fileMenu.setMnemonic('f');
			fileMenu.addActionListener(myActionListener);

			fileMenu.add(getNewMenuItem());
			fileMenu.add(getOpenMenuItem());
			fileMenu.add(getSaveMenuItem());
			fileMenu.add(getExitMenuItem());

			System.out.println("NOVUSWARE : File menu created.");
		}

		return fileMenu;
	}

	private JMenuItem getNewMenuItem()
	{
		if(newMenuItem == null)
		{
			newMenuItem = new JMenuItem("New");
			myActionListener =
				new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						textArea.setText("");

						System.out.println("NOVUSWARE : New menu item action performed.");
					}
				};
			newMenuItem.setMnemonic('n');
			newMenuItem.addActionListener(myActionListener);

			System.out.println("NOVUSWARE : New menu item created.");
		}

		return newMenuItem;
	}

	private JMenuItem getOpenMenuItem()
	{
		if(openMenuItem == null)
		{
			openMenuItem = new JMenuItem("Open");
			myActionListener =
				new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						VoicePad.this.repaint();
						if(fileChooser.showOpenDialog(VoicePad.this) ==
								JFileChooser.APPROVE_OPTION)
						{
							File fSelected = fileChooser.getSelectedFile();
							try
							{
								FileReader in = new FileReader(fSelected);
								textArea.read(in, null);
								in.close();
							}
							catch(IOException ioe)
							{
							}
						}

						System.out.println("NOVUSWARE : Open menu item action performed.");
					}
				};
			openMenuItem.setMnemonic('o');
			openMenuItem.addActionListener(myActionListener);

			System.out.println("NOVUSWARE : Open menu item created.");
		}

		return openMenuItem;
	}

	private JMenuItem getSaveMenuItem()
	{
		if(saveMenuItem == null)
		{
			saveMenuItem = new JMenuItem("Save");
			myActionListener =
				new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						VoicePad.this.repaint();
						if(fileChooser.showSaveDialog(VoicePad.this) ==
								JFileChooser.APPROVE_OPTION)
						{
							File fSelected = fileChooser.getSelectedFile();
							try
							{
								FileWriter out = new FileWriter(fSelected);
								VoicePad.this.textArea.write(out);
								out.close();
							}
							catch(IOException ioe)
							{
							}
						}

						System.out.println("NOVUSWARE : Save menu item action performed.");
					}
				};
			saveMenuItem.setMnemonic('s');
			saveMenuItem.addActionListener(myActionListener);

			System.out.println("NOVUSWARE : Save menu item created.");
		}

		return saveMenuItem;
	}

	private JMenuItem getExitMenuItem()
	{
		if(exitMenuItem == null)
		{
			exitMenuItem = new JMenuItem("Exit");
			myActionListener =
				new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						closeSpeechSynthesisEngine();

						System.out.println("NOVUSWARE : Exit menu item action performed.");

						System.exit(0);
					}
				};
			exitMenuItem.setMnemonic('x');
			exitMenuItem.addActionListener(myActionListener);

			System.out.println("NOVUSWARE : Exit menu item created.");
		}

		return exitMenuItem;
	}
	// file menu and file menu items end

	// speech menu and speech menu items begin
	private JMenu getSpeechMenu()
	{
		if(speechMenu == null)
		{
			// create menu
			speechMenu = new JMenu("Speech");
			myActionListener =
				new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
                                            System.out.println("NOVUSWARE : Speech menu action performed.");
					}
				};
			speechMenu.setMnemonic('s');
			speechMenu.addActionListener(myActionListener);

			speechMenu.add(getPlayMenuItem());
			speechMenu.add(getPauseMenuItem());
			speechMenu.add(getResumeMenuItem());
			speechMenu.add(getStopMenuItem());

			System.out.println("NOVUSWARE : Speech menu created.");
		}
		return speechMenu;
	}

	private JMenuItem getPlayMenuItem()
	{
		if(playMenuItem == null)
		{
			playMenuItem = new JMenuItem("Play");
			myActionListener =
				new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						String textToPlay = "";

						try
						{
							// retrieve the text to be played
							if(textArea.getSelectedText() != null)
								textToPlay = textArea.getSelectedText();
							else
								textToPlay = textArea.getText();

                                                        System.out.println("NOVUSWARE : Play menu item action started.");
							
                                                        // play the text
							synthesizer.speakPlainText(textToPlay, null);

                                                        System.out.println("NOVUSWARE : Play menu item action running.");

							// wait till speaking is done
							synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

							System.out.println("NOVUSWARE : Play menu item action performed.");
						}
						catch(Exception e)
						{
							System.out.println("NOVUSWARE : ERROR! Play menu item action Hero." + e);
						}

					}
				};
			playMenuItem.setMnemonic('p');
			playMenuItem.addActionListener(myActionListener);

			System.out.println("NOVUSWARE : Play menu item created.");
		}
		return playMenuItem;
	}

	private JMenuItem getPauseMenuItem()
	{
		if(pauseMenuItem == null)
		{
			pauseMenuItem = new JMenuItem("Pause");
			myActionListener =
				new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						try
						{
							// pause the speech synthesizer
							synthesizer.pause();

							System.out.println("NOVUSWARE : Pause menu item action performed.");
						}
						catch(Exception e)
						{
							System.out.println("NOVUSWARE : ERROR! Pause menu item action." + e);
						}
					}
				};
			pauseMenuItem.setMnemonic('a');
			pauseMenuItem.addActionListener(myActionListener);

			System.out.println("NOVUSWARE : Pause menu item created.");
		}
		return pauseMenuItem;
	}

	private JMenuItem getResumeMenuItem()
	{
		if(resumeMenuItem == null)
		{
			resumeMenuItem = new JMenuItem("Resume");
			myActionListener =
				new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						try
						{
							// resume the speech synthesizer
							synthesizer.resume();
						}
						catch(Exception e)
						{
							System.out.println("NOVUSWARE : ERROR! Resume menu item action." + e);
						}

						System.out.println("NOVUSWARE : Resume menu item action performed.");
					}
				};
			resumeMenuItem.setMnemonic('r');
			resumeMenuItem.addActionListener(myActionListener);

			System.out.println("NOVUSWARE : Resume menu item created.");
		}
		return resumeMenuItem;
	}

	private JMenuItem getStopMenuItem()
	{
		if(stopMenuItem == null)
		{
			stopMenuItem = new JMenuItem("Stop");
			myActionListener =
				new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						try
						{
							synthesizer.cancelAll();

							System.out.println("NOVUSWARE : Stop menu item action performed.");
						}
						catch(Exception e)
						{
							System.out.println("NOVUSWARE : ERROR! Stop menu item action." + e);
						}
					}
				};
			stopMenuItem.setMnemonic('t');
			stopMenuItem.addActionListener(myActionListener);

			System.out.println("NOVUSWARE : Stop menu item created.");
		}
		return stopMenuItem;
	}
	// speech menu and speech menu items end
}