package Slang;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RandomSlangWord extends JFrame implements ActionListener, TableModelListener {
    private JButton btnBack, btnRandom;
    private JTable table;
    SlangWord slangword;

    RandomSlangWord () throws Exception {
        slangword = (SlangWord) SlangWord.getInstance();
        // Title Label
        JLabel label = new JLabel();
        label.setText("Random Slang Words");
        label.setForeground(Color.orange);
        label.setFont(new Font("Gill Sans MT", Font.ITALIC, 40));
        label.setAlignmentX(CENTER_ALIGNMENT);

        // Table result
        JPanel panelTable = new JPanel();
        panelTable.setBackground(Color.black);
        String column[] = { "Slag", "Meaning"};
        table = new JTable(new DefaultTableModel(column, 0));
        table.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getModel().addTableModelListener(this);
        JScrollPane sp = new JScrollPane(table);

        panelTable.setLayout(new GridLayout(1, 1));
        panelTable.add(sp);

        // Button Random
        JPanel topPanel = new JPanel();
        btnRandom = new JButton("Random");
        btnRandom.setFocusable(false);
        topPanel.add(btnRandom);
        btnRandom.addActionListener(this);
        btnRandom.setAlignmentX(CENTER_ALIGNMENT);

        // Button Back
        JPanel bottomPanel = new JPanel();
        btnBack = new JButton("Back");
        btnBack.setFocusable(false);
        bottomPanel.add(btnBack);
        btnBack.addActionListener(this);
        btnBack.setAlignmentX(CENTER_ALIGNMENT);

        // Setting Content
        Container con = this.getContentPane();
        con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(label);
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(topPanel);
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(panelTable);
        con.add(Box.createRigidArea(new Dimension(0, 20)));
        con.add(btnBack);
        // Setting JFrame
        this.setTitle("Random Slang Words");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(650,650);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void tableChanged(TableModelEvent e) {

    }
}
