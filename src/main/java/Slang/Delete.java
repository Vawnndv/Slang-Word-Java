package Slang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Delete  extends JFrame implements ActionListener {
    private JLabel label_;
    private JButton btnDelete, btnBack;
    private JTextField text;
    SlangWord slangword;

    Delete () throws Exception {
        slangword = (SlangWord) SlangWord.getInstance();
        // Title Label
        JLabel label = new JLabel();
        label.setText("Delete Slang Words");
        label.setForeground(Color.orange);
        label.setFont(new Font("Gill Sans MT", Font.ITALIC, 40));
        label.setAlignmentX(CENTER_ALIGNMENT);

        // Result Label
        label_ = new JLabel();
        label_.setText("Delete slang word");
        label_.setForeground(Color.red);
        label_.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        label_.setAlignmentX(CENTER_ALIGNMENT);

        JPanel form = new JPanel();
        JLabel enterKey = new JLabel("Enter Slang word please");
        text = new JTextField();
        form.setLayout(new GridLayout(2, 1));
        form.add(enterKey);
        form.add(text);

        btnDelete = new JButton("Delete");
        btnDelete.setFocusable(false);
        btnDelete.addActionListener(this);
        btnDelete.setAlignmentX(CENTER_ALIGNMENT);

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
        con.add(form);
        con.add(Box.createRigidArea(new Dimension(0, 30)));
        con.add(btnDelete);
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(bottomPanel);
        // Setting JFrame
        this.setTitle("Delete Slang Words");
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
