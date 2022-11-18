package Slang;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

import static java.awt.Component.CENTER_ALIGNMENT;

public class History extends JFrame implements ActionListener, TableModelListener {
    private JLabel label_;
    private JButton btnBack, btnRefresh;
    private JTable table;
    private DefaultTableModel model;
    SlangWord slangword;
    History() throws Exception {

        slangword = (SlangWord) SlangWord.getInstance();
        // Title Label
        JLabel label = new JLabel();
        label.setText("History Slang Words");
        label.setForeground(Color.orange);
        label.setFont(new Font("Gill Sans MT", Font.ITALIC, 40));
        label.setAlignmentX(CENTER_ALIGNMENT);

        // Table result
        JPanel panelTable = new JPanel();
        panelTable.setBackground(Color.black);
        String column[] = { "Ordinal numbers", "Slag", "Meaning", "Date and Time" };
        table = new JTable(new DefaultTableModel(column, 0));
        table.setRowHeight(30);
        model = (DefaultTableModel) table.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getModel().addTableModelListener(this);
        JScrollPane sp = new JScrollPane(table);

        panelTable.setLayout(new GridLayout(1, 1));
        panelTable.add(sp);

        // Button Refresh
        JPanel topPanel = new JPanel();
        btnRefresh = new JButton("Refresh");
        btnRefresh.setFocusable(false);
        topPanel.add(btnRefresh);
        btnRefresh.addActionListener(this);
        btnRefresh.setAlignmentX(CENTER_ALIGNMENT);

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
        this.setTitle("History Slang Words");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(650,650);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRefresh) {
            String [][] temp = null;
            try {
                temp = slangword.getHistory();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            if (temp != null) {
                for (int i = 0; i < temp.length; i++) {
                    String rlt[] = temp[i];
                    model.addRow(rlt);
                }
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
