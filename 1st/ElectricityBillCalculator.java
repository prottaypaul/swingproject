/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package me.electricitybillcalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElectricityBillCalculator extends JFrame implements ActionListener {

    private JTextField unitsTextField;
    private JButton calculateButton;
    private JLabel resultLabel;
    private JComboBox<String> tariffComboBox;
    private JRadioButton residentialRadioButton;
    private JRadioButton commercialRadioButton;
    private JCheckBox discountCheckBox;

    public ElectricityBillCalculator() {
        setTitle("Electricity Bill Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel unitsLabel = new JLabel("Enter the number of units:");
        unitsTextField = new JTextField();

        JLabel tariffLabel = new JLabel("Select tariff category:");
        tariffComboBox = new JComboBox<>();
        tariffComboBox.addItem("Residential");
        tariffComboBox.addItem("Commercial");

        JLabel categoryLabel = new JLabel("Select category:");
        residentialRadioButton = new JRadioButton("Residential");
        commercialRadioButton = new JRadioButton("Commercial");
        ButtonGroup categoryButtonGroup = new ButtonGroup();
        categoryButtonGroup.add(residentialRadioButton);
        categoryButtonGroup.add(commercialRadioButton);
        residentialRadioButton.setSelected(true);

        discountCheckBox = new JCheckBox("Apply discount");

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);

        resultLabel = new JLabel();

        panel.add(unitsLabel);
        panel.add(unitsTextField);
        panel.add(tariffLabel);
        panel.add(tariffComboBox);
        panel.add(categoryLabel);
        panel.add(residentialRadioButton);
        panel.add(new JLabel()); // Empty cell for spacing
        panel.add(commercialRadioButton);
        panel.add(discountCheckBox);
        panel.add(calculateButton);
        panel.add(resultLabel);

        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            try {
                int units = Integer.parseInt(unitsTextField.getText());
                String tariffCategory = (String) tariffComboBox.getSelectedItem();
                boolean applyDiscount = discountCheckBox.isSelected();
                double billAmount = calculateBill(units, tariffCategory, applyDiscount);
                resultLabel.setText("Bill amount: $" + billAmount);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a number.");
            }
        }
    }

    private double calculateBill(int units, String tariffCategory, boolean applyDiscount) {
        double billAmount;
        double rate;
        if (tariffCategory.equals("Residential")) {
            rate = 1.20;
        } else {
            rate = 2.50;
        }

        if (units <= 100) {
            billAmount = units * rate;
        } else if (units <= 300) {
            billAmount = 100 * rate + (units - 100) * (rate + 1);
        } else {
            billAmount = 100 * rate + 200 * (rate + 1) + (units - 300) * (rate + 2);
        }

        if (applyDiscount) {
            billAmount *= 0.9; // 10% discount
        }

        return billAmount;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ElectricityBillCalculator().setVisible(true);
            }
        });
    }
}