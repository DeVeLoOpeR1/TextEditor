import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menubar;
    JMenu file,edit;
    JMenuItem newFile,openFile,saveFile;
    JMenuItem cut,copy,paste,selectAll,close;
    JTextArea textArea;
    TextEditor()
    {
        //initialise JFrame
        frame=new JFrame();
        //initialise menubar
        menubar=new JMenuBar();

        //initialise menu
        file=new JMenu("File");
        edit=new JMenu("Edit");

        // add menu to menubar
        menubar.add(file);
        menubar.add(edit);


        //initialise textarea
        textArea=new JTextArea();

        //initialise menuitems for file and add to it
        newFile=new JMenuItem("New File");
        openFile=new JMenuItem("Open File");
        saveFile=new JMenuItem("Save File");

        //add  actionlistner
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);


        //initialise menuitems for edit and add to edit
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");

        //add  actionlistner
        cut.addActionListener(this);
        copy.addActionListener(this);
        cut.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //add scroll panel
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel panel=new JPanel();

        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        //add to frame
        panel.add(scrollPane);
        frame.add(panel);


        //add to jframe
        //frame.add(textArea); we are addint text area to panel and adding that panel to frame
        frame.setJMenuBar(menubar);
        frame.setBounds(50,50,900,400);
        frame.setVisible(true);
    }
    public static void main(String args[])
    {
        TextEditor textEditor=new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==newFile)
        {
            TextEditor textEditor1=new TextEditor();
        }
        if(e.getSource()==openFile)
        {
            JFileChooser filechooser=new JFileChooser("C:");
            int chooseOption=filechooser.showOpenDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION)
            {
                File file=filechooser.getSelectedFile();
                String filepath= file.getPath();
                try{
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(filepath));
                    String intermediate="",output="";
                    while((intermediate=bufferedReader.readLine())!=null)
                    {
                        output+=intermediate+"\n";
                    }
                    textArea.setText(output);

                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
        if(e.getSource()==saveFile)
        {
            JFileChooser filechooser =new JFileChooser("C:");
            filechooser.setApproveButtonText("Save");
            int chooseOption=filechooser.showSaveDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION)
            {
                File file=new File(filechooser.getSelectedFile().getAbsolutePath()+".txt");
                //String filePath= file.getPath();
                try{
                    BufferedWriter outfile=null;
                    outfile=new BufferedWriter(new FileWriter(file));
                    textArea.write(outfile);
                    outfile.close();
                }
                catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
        if(e.getSource()==cut)
        {
            textArea.cut();
        }
        if(e.getSource()==copy)
        {
            textArea.copy();
        }
        if(e.getSource()==paste)
        {
            textArea.paste();
        }
        if(e.getSource()==selectAll)
        {
            textArea.selectAll();
        }
        if(e.getSource()==close)
        {
            System.exit(0);
        }

    }
}