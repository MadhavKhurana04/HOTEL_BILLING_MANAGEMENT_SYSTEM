package hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class HotelGUI extends JFrame {
    // Components
    private JPanel menuPanel, billPanel;
    private JComboBox<String> categoryCombo, itemCombo;
    private JButton addButton, generateBillButton;
    private JTextArea billTextArea;
    private JLabel totalLabel;
    
    // Data
    private ArrayList<MenuItem> orderedItems;
    private double total = 0.0;
    
    // Menu items and prices
    private String[][] menuItems = {
        {"Starter1", "Starter2", "Starter3"},
        {"Veg1", "Veg2", "Veg3"},
        {"NonVeg1", "NonVeg2", "NonVeg3"}
    };
    
    private double[][] prices = {
        {200, 220, 300},
        {200, 220, 300},
        {200, 220, 300}
    };
    
    public HotelGUI() {
        // Initialize
        orderedItems = new ArrayList<>();
        
        // Setup window
        setTitle("Hotel Management System");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Create menu panel
        createMenuPanel();
        
        // Create bill panel
        createBillPanel();
        
        // Add panels to frame
        add(menuPanel, BorderLayout.NORTH);
        add(billPanel, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    private void createMenuPanel() {
        menuPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        menuPanel.setBorder(BorderFactory.createTitledBorder("Menu Selection"));
        
        // Category selection
        menuPanel.add(new JLabel("Select Category:"));
        String[] categories = {"Starters", "Veg Menu", "Non-Veg Menu"};
        categoryCombo = new JComboBox<>(categories);
        menuPanel.add(categoryCombo);
        
        // Item selection
        menuPanel.add(new JLabel("Select Item:"));
        itemCombo = new JComboBox<>(menuItems[0]); // Initially showing starters
        menuPanel.add(itemCombo);
        
        // Category change listener
        categoryCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = categoryCombo.getSelectedIndex();
                itemCombo.removeAllItems();
                for (String item : menuItems[index]) {
                    itemCombo.addItem(item);
                }
            }
        });
        
        // Add button
        menuPanel.add(new JLabel("")); // Empty label for spacing
        addButton = new JButton("Add to Order");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemToOrder();
            }
        });
        menuPanel.add(addButton);
    }
    
    private void createBillPanel() {
        billPanel = new JPanel(new BorderLayout(5, 5));
        billPanel.setBorder(BorderFactory.createTitledBorder("Bill"));
        
        // Bill display area
        billTextArea = new JTextArea();
        billTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(billTextArea);
        billPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Total and generate bill button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        totalLabel = new JLabel("Total Amount: Rs. 0.00");
        bottomPanel.add(totalLabel, BorderLayout.WEST);
        
        generateBillButton = new JButton("Generate Bill");
        generateBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateBill();
            }
        });
        bottomPanel.add(generateBillButton, BorderLayout.EAST);
        
        billPanel.add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private void addItemToOrder() {
        int categoryIndex = categoryCombo.getSelectedIndex();
        int itemIndex = itemCombo.getSelectedIndex();
        
        String itemName = menuItems[categoryIndex][itemIndex];
        double price = prices[categoryIndex][itemIndex];
        
        // Add to order list
        MenuItem item = new MenuItem(itemName, price);
        orderedItems.add(item);
        
        // Update bill area
        billTextArea.append(itemName + "\t\tRs. " + price + "\n");
        
        // Update total
        total += price;
        totalLabel.setText("Total Amount: Rs. " + String.format("%.2f", total));
        
        JOptionPane.showMessageDialog(this, itemName + " added to your order!");
    }
    
    private void generateBill() {
        if (orderedItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items in your order!", "Empty Order", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Calculate taxes
        double cgst = total * 0.06;
        double sgst = total * 0.06;
        double finalTotal = total + cgst + sgst;
        
        // Create bill content
        StringBuilder bill = new StringBuilder();
        bill.append("\n---------------------Hotel Bill----------------------\n\n");
        bill.append("Items Ordered:\n");
        bill.append("------------------------------------------------\n");
        
        for (MenuItem item : orderedItems) {
            bill.append(item.getName()).append("\t\tRs. ").append(String.format("%.2f", item.getPrice())).append("\n");
        }
        
        bill.append("------------------------------------------------\n");
        bill.append("Total:\t\tRs. ").append(String.format("%.2f", total)).append("\n");
        bill.append("CGST (6%):\t\tRs. ").append(String.format("%.2f", cgst)).append("\n");
        bill.append("SGST (6%):\t\tRs. ").append(String.format("%.2f", sgst)).append("\n");
        bill.append("Final Total:\t\tRs. ").append(String.format("%.2f", finalTotal)).append("\n");
        bill.append("---------------------Thank You----------------------\n");
        
        // Display in bill area
        billTextArea.setText(bill.toString());
        
        // Save to database (simplified)
        Database db = new Database();
        boolean saved = db.saveBill(total, cgst, sgst, finalTotal, orderedItems);
        
        if (saved) {
            JOptionPane.showMessageDialog(this, "Bill generated and saved to database!");
        } else {
            JOptionPane.showMessageDialog(this, "Bill generated but could not save to database.");
        }
    }
}