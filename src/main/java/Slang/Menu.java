package Slang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Menu extends JFrame implements ActionListener {
    private JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10;
    SlangWord slangWord;

    Menu() throws Exception {
        slangWord = (SlangWord) SlangWord.getInstance();
        // Label
        JLabel label = new JLabel("Slang Word Program!");
        label.setForeground(Color.orange);
        label.setFont(new Font("Gill Sans MT", Font.ITALIC, 40));
        label.setAlignmentX(CENTER_ALIGNMENT);

        // A Grid
        btn1 = new JButton("1. Find slang word");
        btn1.addActionListener(this);
        btn1.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
        btn1.setFocusable(false);

        btn2 = new JButton("2. Find slang word with definition");
        btn2.addActionListener(this);
        btn2.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
        btn2.setFocusable(false);

        btn3 = new JButton("3. View history");
        btn3.addActionListener(this);
        btn3.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
        btn3.setFocusable(false);

        btn4 = new JButton("4. Add new slang word");
        btn4.addActionListener(this);
        btn4.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
        btn4.setFocusable(false);

        btn5 = new JButton("5. Edit slang word");
        btn5.addActionListener(this);
        btn5.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
        btn5.setFocusable(false);

        btn6 = new JButton("6. Delete slang word");
        btn6.addActionListener(this);
        btn6.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
        btn6.setFocusable(false);

        btn7 = new JButton("7. Reset slang word");
        btn7.addActionListener(this);
        btn7.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
        btn7.setFocusable(false);

        btn8 = new JButton("8. Random slang word");
        btn8.addActionListener(this);
        btn8.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
        btn8.setFocusable(false);

        btn9 = new JButton("9. Quiz random slang word");
        btn9.addActionListener(this);
        btn9.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
        btn9.setFocusable(false);

        btn10 = new JButton("10. Quiz random definition");
        btn10.addActionListener(this);
        btn10.setFont(new Font("Gill Sans MT", Font.PLAIN, 14));
        btn10.setFocusable(false);

        JPanel panelCenter = new JPanel();
        // panelCenter.setBackground(Color.gray);
        panelCenter.setLayout(new GridLayout(10, 1, 10, 10));
        panelCenter.add(btn1);
        panelCenter.add(btn2);
        panelCenter.add(btn3);
        panelCenter.add(btn4);
        panelCenter.add(btn5);
        panelCenter.add(btn6);
        panelCenter.add(btn7);
        panelCenter.add(btn8);
        panelCenter.add(btn9);
        panelCenter.add(btn10);

        Dimension size2 = new Dimension(600, 500);
        panelCenter.setMaximumSize(size2);
        panelCenter.setPreferredSize(size2);
        panelCenter.setMinimumSize(size2);
        Container con = this.getContentPane();
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(label);
        con.add(Box.createRigidArea(new Dimension(0, 30)));
        con.add(panelCenter);

        // Setting Frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Slang Words");
        this.setVisible(true);
        this.setSize(650, 650);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

    }

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == btn1) {
            this.dispose();
            try {
                new Find();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (e.getSource() == btn2) {
            this.dispose();
            try {
                new Find();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        } else if (e.getSource() == btn3) {
            this.dispose();
            try {
                new History();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        } else if (e.getSource() == btn4) {
            this.dispose();
            try {
                // Code
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        } else if (e.getSource() == btn5) {
            this.dispose();
            try {
                new Find();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        } else if (e.getSource() == btn6) {
            this.dispose();
            try {
                // Code
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (e.getSource() == btn7) {
            this.dispose();
            try {
                // Code
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (e.getSource() == btn8) {
            this.dispose();
            try {
                // Code
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (e.getSource() == btn9) {
            this.dispose();
            try {
                // Code
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else if (e.getSource() == btn10) {
            this.dispose();
            try {
                // Code
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}
