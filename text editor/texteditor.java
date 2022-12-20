// Java Program to create a text editor using java

//import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
//import javax.swing.text.*;

class editor extends JFrame implements ActionListener {
    JTextArea t;
    JFrame f;
    editor(){
        f = new JFrame("Editor");
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }

        catch (Exception e) {

        }
 
        t = new JTextArea();
        JMenuBar mb = new JMenuBar(); 
        JMenu m1 = new JMenu("File"); 
        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi2 = new JMenuItem("Open");
        JMenuItem mi3 = new JMenuItem("Save");
        JMenuItem mi8 = new JMenuItem("Print");
        JMenuItem mi12 = new JMenuItem("Exit");
 
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi8.addActionListener(this);
        mi12.addActionListener(this);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi8);
        m1.addSeparator();
        m1.add(mi12);
 
        JMenu m2 = new JMenu("Edit");
        JMenuItem mi4 = new JMenuItem("cut");
        JMenuItem mi5 = new JMenuItem("copy");
        JMenuItem mi6 = new JMenuItem("paste");
        JMenuItem mi7 = new JMenuItem("Delete all");

        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);
        mi7.addActionListener(this);
 
        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);
        m2.add(mi7);
 
        JMenu m4=new JMenu("View");
        JMenuItem mi9 = new JMenuItem("Zoom IN");
        JMenuItem mi10 = new JMenuItem("Zoom OUT");
        JMenuItem mi11 = new JMenuItem("FULL WINDOW");
        JMenuItem mi13 = new JMenuItem("ORIGINAL WINDOW");
        JMenu mi14 = new JMenu("Fonts");

        JMenuItem font1=new JMenuItem("BOLD");
        JMenuItem font2=new JMenuItem("style");

         font1.addActionListener(this);
         font2.addActionListener(this);

        mi9.addActionListener(this);
        mi10.addActionListener(this);
        mi11.addActionListener(this);
        mi13.addActionListener(this);
        mi14.addActionListener(this);

        mi14.add(font1);
        mi14.add(font2);

        m4.add(mi9);
        m4.add(mi10);
        m4.add(mi11);
        m4.add(mi13);
        m4.add(mi14);

        mb.add(m1);
        mb.add(m2);
        mb.add(m4);
        //mb.add(m3);
        f.setJMenuBar(mb);
        f.add(t);
        f.setSize(500, 500);
       // f.show();
        f.setVisible(true);

    }
 
    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();
        if (s.equals("cut")) {
            t.cut();
        }
        else if (s.equals("copy")) {
            t.copy();
        }
        else if (s.equals("paste")) {
            t.paste();
        }
        else if (s.equals("Save")) {
            JFileChooser j = new JFileChooser("f:");
            int r = j.showSaveDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                try {
                    FileWriter wr = new FileWriter(fi, false);
                    BufferedWriter w = new BufferedWriter(wr);
                    w.write(t.getText());
                    w.flush();
                    w.close();
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            else
                JOptionPane.showMessageDialog(f, "the user cancelled the operation");
        }

        else if (s.equals("Print")) {
            try {
                t.print();
            }
            catch (Exception evt) {
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
        }

        else if (s.equals("Open")) {
            JFileChooser j = new JFileChooser("f:");
            int r = j.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                try {
                    String s1 = "", sl = "";
                    FileReader fr = new FileReader(fi);
                    BufferedReader br = new BufferedReader(fr);
                    sl = br.readLine();
                    while ((s1 = br.readLine()) != null) {
                        sl = sl + "\n" + s1;
                    }
                    t.setText(sl);
                }

                catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }

            }
            else{
                JOptionPane.showMessageDialog(f, "the user cancelled the operation");
            }
        }

        else if (s.equals("New")) {
            t.setText("");
        }
        else if (s.equals("Delete all")) {
            t.setText("");
        }

        else if (s.equals("Exit")) {
            JOptionPane.showMessageDialog(f, "Save the file and exit or cancel and exit");
            JFileChooser j = new JFileChooser("f:");
            int r = j.showSaveDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                try {
                    FileWriter wr = new FileWriter(fi, false);
                    BufferedWriter w = new BufferedWriter(wr);
                    w.write(t.getText());
                    w.flush();
                    w.close();
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
                f.setVisible(false);
            }
            else{
                f.setVisible(false);
            }
            
        }
        else if (s.equals("Zoom IN")) {
           t.setFont(new java.awt.Font(t.getFont().getFontName(),t.getFont().getStyle(),t.getFont().getSize()+2));
           
        }
        else if (s.equals("Zoom OUT")) {
            t.setFont(new java.awt.Font(t.getFont().getFontName(),t.getFont().getStyle(),t.getFont().getSize()-2));
            
        }
        else if (s.equals("FULL WINDOW")) {
            f.setSize(2000, 2000);
        }
        else if (s.equals("ORIGINAL WINDOW")) {
            f.setSize(500, 500);
        }
        else if (s.equals("")) {
            t.setFont(new Font("BOLD",Font.PLAIN,15));
        }

    }
    public static void main(String args[])
    {
        editor e = new editor();
    }
}