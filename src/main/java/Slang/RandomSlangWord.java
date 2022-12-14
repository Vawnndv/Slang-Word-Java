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
    private JButton btnBack, btnRandom, btnClear;
    private JTable table;
    private DefaultTableModel model;
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
        model = (DefaultTableModel) table.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getModel().addTableModelListener(this);
        table.getModel().addTableModelListener(this);
        JScrollPane sp = new JScrollPane(table);

        panelTable.setLayout(new GridLayout(1, 1));
        panelTable.add(sp);

        // Button Random
        JPanel topPanel = new JPanel();
        btnRandom = new JButton("Random");
        btnRandom.setFocusable(false);
        btnRandom.addActionListener(this);
        btnRandom.setAlignmentX(CENTER_ALIGNMENT);

        btnClear = new JButton("Clear table");
        btnClear.setFocusable(false);
        btnClear.addActionListener(this);
        btnClear.setAlignmentX(CENTER_ALIGNMENT);
        topPanel.setLayout(new GridLayout(1,2));
        topPanel.add(btnRandom);
        topPanel.add(btnClear);

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
        if (e.getSource() == btnRandom) {
            String [][] temp = slangword.randomSlangWord();
            if (temp != null) {
                for (int i = 0; i < temp.length; i++) {
                    String rlt[] = temp[i];
                    model.addRow(rlt);
                }
            }
        }
        if (e.getSource() == btnClear) {
            clearTable();
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

    void clearTable() {
        int rowCount = model.getRowCount();
        // Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {

    }
}
