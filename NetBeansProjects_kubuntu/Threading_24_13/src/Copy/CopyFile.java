package Copy;

 import java.awt.*;
  import java.awt.event.*;
  import javax.swing.*;
  import javax.swing.border.*;
  import java.io.*;

  public class CopyFile extends JFrame
  {
      private JProgressBar jprogressbar = new JProgressBar();
      private JButton jbuttonCopy = new JButton("Copy");
      private JTextField jtextfieldFrom = new JTextField();
      private JTextField jtextfieldTo = new JTextField();

      public CopyFile()
      {
            JPanel jPanel2 = new JPanel();
            jPanel2.setLayout(new BorderLayout());
            jPanel2.setBorder(new TitledBorder("From"));
            jPanel2.add(jtextfieldFrom, BorderLayout.CENTER);

            JPanel jPanel3 = new JPanel();
            jPanel3.setLayout(new BorderLayout());
            jPanel3.setBorder(new TitledBorder("To"));
            jPanel3.add( jtextfieldTo, BorderLayout.CENTER);

            JPanel jPanel1 = new JPanel();
            jPanel1.setLayout(new GridLayout(2, 1));
            jPanel1.add(jPanel2);
            jPanel1.add(jPanel3);

            JPanel jPanel4 = new JPanel();
            jPanel4.add(jbuttonCopy);

            this.add(jprogressbar, BorderLayout.NORTH);
            this.add(jPanel1, BorderLayout.CENTER);
            this.add(jPanel4, BorderLayout.SOUTH);

            jprogressbar.setStringPainted(true);    // Paint the percent in a string

            jbuttonCopy.addActionListener ( new ActionListener()
                {
                      public void actionPerformed(ActionEvent e)
                      {
                           // Create a thread for copying files
                            new Thread(new CopyFileTask()).start();
                      }
                }
            ) ;
      }

      // Copy file and update progress bar in a separate thread
      class CopyFileTask implements Runnable
      {
            private int currentValue;

            @SuppressWarnings("CallToThreadDumpStack")
            public void run()
            {
                  BufferedInputStream in = null;
                  BufferedOutputStream out = null;
                  try
                  {
                        // Create file input stream
                        File inFile = new File(jtextfieldFrom.getText().trim());
                        in = new BufferedInputStream(new FileInputStream(inFile));

                        // Create file output stream
                        File outFile = new File( jtextfieldTo.getText());
                        out = new BufferedOutputStream(new FileOutputStream(outFile));

                        // Get total bytes in the file
                        long totalBytes = in.available();

                        // Start progress meter bar
                        jprogressbar.setValue(0);
                        jprogressbar.setMaximum(100);

                        int r;
                        long bytesRead = 0;
                        // You may increase buffer size to improve IO speed
                        byte[] b = new byte[10];
                        while ((r = in.read(b, 0, b.length)) != -1)
                        {
                              out.write(b, 0, r);
                              bytesRead += r;
                              currentValue = (int) ( ( bytesRead * 100 ) / totalBytes );

                              // Update the progress bar
                              jprogressbar.setValue(currentValue);
                        }
                  }
                  catch (FileNotFoundException ex)
                  {
                    ex.printStackTrace();
                    System.exit(1);
                  }
                  catch (IOException ex)
                  {
                    ex.printStackTrace();
                    System.exit(1);
                  }
                  finally
                  {
                        try
                        {
                              if (in != null) in.close();
                              if (out != null) out.close();
                        }
                        catch (Exception ex)
                        {
                               ex.printStackTrace();
                               System.exit(1);
                        }
                 }
            }
       }
  }
