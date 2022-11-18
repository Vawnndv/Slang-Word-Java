package Slang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewSlang extends JFrame implements ActionListener {

    private JLabel label_;
    private JTextField textKey, textMeaning;
    private JButton btnAdd, btnBack;
    SlangWord slangword;

    NewSlang () throws Exception {
        slangword = (SlangWord) SlangWord.getInstance();

        // Title Label
        JLabel label = new JLabel();
        label.setText("New Slang Words");
        label.setForeground(Color.orange);
        label.setFont(new Font("Gill Sans MT", Font.ITALIC, 40));
        label.setAlignmentX(CENTER_ALIGNMENT);

        // Result Label
        label_ = new JLabel();
        label_.setText("Enter new slang word");
        label_.setForeground(Color.red);
        label_.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        label_.setAlignmentX(CENTER_ALIGNMENT);

        JPanel form1 = new JPanel();
        JLabel enterKey = new JLabel("Enter key please");
        textKey = new JTextField();
        form1.setLayout(new GridLayout(2, 1));
        form1.add(enterKey);
        form1.add(textKey);

        JPanel form2 = new JPanel();
        JLabel enterMeaning = new JLabel("Enter meaning please");
        textMeaning = new JTextField();
        form2.setLayout(new GridLayout(2,1));
        form2.add(enterMeaning);
        form2.add(textMeaning);

        btnAdd = new JButton("Add new slang");
        btnAdd.setFocusable(false);
        btnAdd.addActionListener(this);
        btnAdd.setAlignmentX(CENTER_ALIGNMENT);

        // Bottom pannel
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
        con.add(label_);
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(form1);
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(form2);
        con.add(Box.createRigidArea(new Dimension(0, 30)));
        con.add(btnAdd);
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(bottomPanel);
        // Setting JFrame
        this.setTitle("New Slang Words");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(650,650);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
