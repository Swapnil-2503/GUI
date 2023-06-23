import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTrackerApp extends JFrame {
    private JTextField expenseTextField;
    private JComboBox<String> categoryComboBox;
    private JTextArea expenseListTextArea;
    private JLabel totalExpenseLabel;

    private List<Expense> expenseList;
    private double totalExpense;

    public ExpenseTrackerApp() {
        expenseList = new ArrayList<>();
        totalExpense = 0.0;

        setTitle("Expense Tracker");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Expense entry panel
        JPanel expenseEntryPanel = new JPanel();
        expenseEntryPanel.setLayout(new FlowLayout());

        JLabel expenseLabel = new JLabel("Expense:");
        expenseTextField = new JTextField(10);

        JLabel categoryLabel = new JLabel("Category:");
        categoryComboBox = new JComboBox<>(new String[]{"Food", "Transportation", "Entertainment", "Utilities"});

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new AddButtonListener());

        expenseEntryPanel.add(expenseLabel);
        expenseEntryPanel.add(expenseTextField);
        expenseEntryPanel.add(categoryLabel);
        expenseEntryPanel.add(categoryComboBox);
        expenseEntryPanel.add(addButton);

        // Expense list panel
        JPanel expenseListPanel = new JPanel();
        expenseListPanel.setLayout(new BorderLayout());

        expenseListTextArea = new JTextArea();
        expenseListTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(expenseListTextArea);

        expenseListPanel.add(scrollPane, BorderLayout.CENTER);

        // Total expense panel
        JPanel totalExpensePanel = new JPanel();
        totalExpensePanel.setLayout(new FlowLayout());

        JLabel totalLabel = new JLabel("Total Expense: INR");
        totalExpenseLabel = new JLabel("0.0");

        totalExpensePanel.add(totalLabel);
        totalExpensePanel.add(totalExpenseLabel);

        // Add panels to the main frame
        add(expenseEntryPanel, BorderLayout.NORTH);
        add(expenseListPanel, BorderLayout.CENTER);
        add(totalExpensePanel, BorderLayout.SOUTH);
    }

    private void updateExpenseListTextArea() {
        StringBuilder sb = new StringBuilder();
        for (Expense expense : expenseList) {
            sb.append(expense).append("\n");
        }
        expenseListTextArea.setText(sb.toString());
    }

    private void updateTotalExpenseLabel() {
        totalExpenseLabel.setText(String.format("%.2f", totalExpense));
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String expenseText = expenseTextField.getText();
            String category = (String) categoryComboBox.getSelectedItem();
            double amount = Double.parseDouble(expenseText);
            Expense expense = new Expense(amount, category);
            expenseList.add(expense);
            totalExpense += amount;
            updateExpenseListTextArea();
            updateTotalExpenseLabel();
            expenseTextField.setText("");
        }
    }

    private class Expense {
        private double amount;
        private String category;

        public Expense(double amount, String category) {
            this.amount = amount;
            this.category = category;
        }

        public double getAmount() {
            return amount;
        }

        public String getCategory() {
            return category;
        }

        @Override
        public String toString() {
            return category + ": INR" + String.format("%.2f", amount);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ExpenseTrackerApp app = new ExpenseTrackerApp();
                app.setVisible(true);
            }
        });
    }
}
