package Slang;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class Find extends JFrame implements ActionListener, TableModelListener {

    private JLabel label_;
    private JTextField textField;
    private JButton btnBack, btnFindSW, btnFindD;
    private JTable table;
    private DefaultTableModel model;
    SlangWord slangword;
    private String[][] result;
    private String data[][] = { { "", "", "" } };

    Find() throws Exception {

        slangword = (SlangWord) SlangWord.getInstance();
        // Title Label
        JLabel label = new JLabel();
        label.setText("Find Slang Words");
        label.setForeground(Color.orange);
        label.setFont(new Font("Gill Sans MT", Font.ITALIC, 40));
        label.setAlignmentX(CENTER_ALIGNMENT);

        // Result Label
        label_ = new JLabel();
        label_.setText("Enter and search by slang word");
        label_.setForeground(Color.black);
        label_.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
        label_.setAlignmentX(CENTER_ALIGNMENT);

        // Form
        JPanel form = new JPanel();
        JLabel formLabel = new JLabel("  Enter please");
        textField = new JTextField();
        btnFindSW = new JButton("Find flow Slang Word ");
        btnFindSW.addActionListener(this);
        btnFindSW.setMnemonic(KeyEvent.VK_ENTER);

        btnFindD = new JButton("Find flow Definition ");
        btnFindD.addActionListener(this);
        btnFindD.setMnemonic(KeyEvent.VK_ENTER);

        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(2, 1, 10, 10));
        jp.add(btnFindSW);
        jp.add(btnFindD);

        form.setLayout(new BorderLayout(10, 10));
        form.add(formLabel, BorderLayout.LINE_START);
        form.add(textField, BorderLayout.CENTER);
        form.add(jp, BorderLayout.LINE_END);
        Dimension size = new Dimension(700, 50);
        form.setMaximumSize(size);
        form.setPreferredSize(size);
        form.setMinimumSize(size);
        // Table result
        JPanel panelTable = new JPanel();
        panelTable.setBackground(Color.black);

        String column[] = { "Ordinal numbers", "Slag", "Meaning" };

        table = new JTable(new DefaultTableModel(column, 0));
        table.setRowHeight(30);
        model = (DefaultTableModel) table.getModel();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getModel().addTableModelListener(this);
        JScrollPane sp = new JScrollPane(table);

        panelTable.setLayout(new GridLayout(1, 1));
        panelTable.add(sp);

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
        con.add(label);
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(form);
        con.add(Box.createRigidArea(new Dimension(0, 10)));
        con.add(panelTable);
        con.add(Box.createRigidArea(new Dimension(0, 20)));
        con.add(btnBack);
        // Setting JFrame
        this.setTitle("Find Slang Words");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(650,650);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnFindD || e.getSource() == btnFindSW) {
            String key = textField.getText();
            if (key.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter word you want to find", "Inane error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String[][] temp = null;
            if (e.getSource() == btnFindSW) {
                this.clearTable();
                temp = slangword.getMeaning(key);

            } else if (e.getSource() == btnFindD) {
                this.clearTable();
                temp = slangword.getKey(key);
            }
            if (temp != null)
                label_.setText(temp.length + " results were found");
            else {
                label_.setText("Can't not find");
                return;
            }
            result = temp;
            for (int i = 0; i < temp.length; i++) {
                String rlt[] = temp[i];
                model.addRow(rlt);
            }

            try {
                for (int index = 0; index < temp.length; index++)
                    slangword.saveHistory(temp[index][1], temp[index][2]);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == btnBack) {
            this.dispose();
            try {
                new Menu();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void tableChanged(TableModelEvent e) {
        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();
        if (row == col && row == -1)
            return;
        String Data = (String) table.getValueAt(row, col);
        System.out.println("Table element selected is: " + row + col + " : " + Data);
        if (col == 2) {
            // edit meaning
            slangword.setMeaning((String) table.getValueAt(row, 1), result[row][2], (String) table.getValueAt(row, 2));
            JOptionPane.showMessageDialog(this, "Updated a row.");
        }
        table.setFocusable(false);
    }

    void clearTable() {
        int rowCount = model.getRowCount();
        // Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
}
