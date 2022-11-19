package Slang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Question extends JFrame implements ActionListener {

    private JButton btn1, btn2, btn3, btn4, btnBack;
    private JLabel label_, labelQuestion;
    SlangWord slangword;
    int result = 0;

    Question (String type) throws Exception {
        slangword = (SlangWord) SlangWord.getInstance();
        String temp [] = null;
        if (type.equals("Slang")) {
            temp = slangword.questionSlang();
        } else if (type.equals("Definition")) {
            temp = slangword.questionDefinition();
        }
        slangword = (SlangWord) SlangWord.getInstance();
        // Title Label
        JLabel label = new JLabel();
        label.setText("Question Slang Words");
        label.setForeground(Color.orange);
        label.setFont(new Font("Gill Sans MT", Font.ITALIC, 40));
        label.setAlignmentX(CENTER_ALIGNMENT);

        // Result Label
        labelQuestion = new JLabel();
        labelQuestion.setForeground(Color.black);
        labelQuestion.setFont(new Font("Gill Sans MT", Font.PLAIN, 25));
        labelQuestion.setAlignmentX(CENTER_ALIGNMENT);

        // Result Label
        label_ = new JLabel();
        label_.setText("Choose the correct answer");
        label_.setForeground(Color.red);
        label_.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        label_.setAlignmentX(CENTER_ALIGNMENT);

        JPanel panel = new JPanel();
        btn1 = new JButton();
        btn1.setFocusable(false);
        btn1.addActionListener(this);
        btn1.setAlignmentX(CENTER_ALIGNMENT);

        btn2 = new JButton();
        btn2.setFocusable(false);
        btn2.addActionListener(this);
        btn2.setAlignmentX(CENTER_ALIGNMENT);

        btn3 = new JButton();
        btn3.setFocusable(false);
        btn3.addActionListener(this);
        btn3.setAlignmentX(CENTER_ALIGNMENT);

        btn4 = new JButton();
        btn4.setFocusable(false);
        btn4.addActionListener(this);
        btn4.setAlignmentX(CENTER_ALIGNMENT);

        panel.setLayout(new GridLayout(2, 2, 10, 10));
        panel.add(btn1);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);

        JPanel bottomPanel = new JPanel();
        btnBack = new JButton("Back");
        btnBack.setFocusable(false);
        bottomPanel.add(btnBack);
        btnBack.addActionListener(this);
        btnBack.setAlignmentX(CENTER_ALIGNMENT);

        Container con = this.getContentPane();
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(label);
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(labelQuestion);
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(label_);
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(panel);
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(bottomPanel);
        // Setting JFrame
        this.setTitle("Question Slang Words");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(650,650);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        if (temp != null) {
            labelQuestion.setText(temp[0]);
            btn1.setText(temp[1]);
            btn2.setText(temp[2]);
            btn3.setText(temp[3]);
            btn4.setText(temp[4]);
            result = Integer.parseInt(temp[5]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            if (result == 1) {
                label_.setText("Correct");
                JOptionPane.showMessageDialog(this, "EXCELLENT", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            } else {
                label_.setText("Incorrect");
            }
        }
        if (e.getSource() == btn2) {
            if (result == 2) {
                label_.setText("Correct");
                JOptionPane.showMessageDialog(this, "EXCELLENT", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            } else {
                label_.setText("Incorrect");
            }
        }
        if (e.getSource() == btn3) {
            if (result == 3) {
                label_.setText("Correct");
                JOptionPane.showMessageDialog(this, "EXCELLENT", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            } else {
                label_.setText("Incorrect");
            }
        }
        if (e.getSource() == btn4) {
            if (result == 4) {
                label_.setText("Correct");
                JOptionPane.showMessageDialog(this, "EXCELLENT", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            } else {
                label_.setText("Incorrect");
            }
        }
        if (e.getSource() == btnBack) {
            this.dispose();
            try {
                new Menu();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
