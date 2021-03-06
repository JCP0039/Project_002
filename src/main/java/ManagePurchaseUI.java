import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagePurchaseUI {

    public JFrame view;

    public JButton btnLoad = new JButton("Load Purchase");
    public JButton btnSave = new JButton("Save Purchase");

    public JTextField txtProductID = new JTextField(20);
    public JTextField txtPurchaseID = new JTextField(20);
    public JTextField txtCustomerID = new JTextField(20);
    public JTextField txtPrice = new JTextField(20);
    public JTextField txtQuantity = new JTextField(20);
    public JTextField txtTax = new JTextField(20);
    public JTextField txtCost = new JTextField(20);
    public JTextField txtTotal = new JTextField(20);
    public JTextField txtDate = new JTextField(20);

    public ManagePurchaseUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Update Purchase Information");
        view.setSize(800, 800);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnLoad);
        panelButtons.add(btnSave);
        view.getContentPane().add(panelButtons);

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("PurchaseID "));
        line1.add(txtPurchaseID);
        view.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("CustomerID "));
        line2.add(txtCustomerID);
        view.getContentPane().add(line2);

        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("ProductID "));
        line3.add(txtProductID);
        view.getContentPane().add(line3);

        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Price "));
        line4.add(txtPrice);
        view.getContentPane().add(line4);

        JPanel line5 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Quantity "));
        line4.add(txtQuantity);
        view.getContentPane().add(line5);

        JPanel line6 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Cost "));
        line4.add(txtCost);
        view.getContentPane().add(line6);

        JPanel line7 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Tax "));
        line4.add(txtTax);
        view.getContentPane().add(line7);

        JPanel line8 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Total "));
        line4.add(txtTotal);
        view.getContentPane().add(line8);

        JPanel line9 = new JPanel(new FlowLayout());
        line4.add(new JLabel("Date "));
        line4.add(txtDate);
        view.getContentPane().add(line9);


        btnLoad.addActionListener(new LoadButtonListerner());

        btnSave.addActionListener(new SaveButtonListener());

    }

    public void run() {
        view.setVisible(true);
    }

    class LoadButtonListerner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PurchaseModel purchase = new PurchaseModel();
            String id = txtPurchaseID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "PurchaseID cannot be null!");
                return;
            }

            try {
                purchase.mPurchaseID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "PurchaseID is invalid!");
                return;
            }

            // call data access!

            purchase = StoreManager.getInstance().getDataAdapter().loadPurchase(purchase.mPurchaseID);

            if (purchase == null) {
                JOptionPane.showMessageDialog(null, "Purchase does NOT exists!");
            } else {
                txtCustomerID.setText(Integer.toString(purchase.mCustomerID));
                txtProductID.setText(Integer.toString(purchase.mProductID));
                txtPrice.setText(Double.toString(purchase.mPrice));
                txtQuantity.setText(Double.toString(purchase.mQuantity));
                txtCost.setText(Double.toString(purchase.mCost));
                txtTax.setText(Double.toString(purchase.mTax));
                txtTotal.setText(Double.toString(purchase.mTotal));
                txtDate.setText(purchase.mDate);
            }
        }
    }

    class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            PurchaseModel purchase = new PurchaseModel();
            String id = txtPurchaseID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "PurchaseID cannot be null!");
                return;
            }

            try {
                purchase.mPurchaseID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "PurchaseID is invalid!");
                return;
            }

            String customerID = txtCustomerID.getText();
            if (customerID.length() == 0) {
                JOptionPane.showMessageDialog(null, "CustomerID cannot be empty!");
                return;
            }

            purchase.mCustomerID = Integer.parseInt(customerID);


            String productID = txtProductID.getText();
            if (customerID.length() == 0) {
                JOptionPane.showMessageDialog(null, "ProductID cannot be empty!");
                return;
            }

            purchase.mProductID = Integer.parseInt(productID);

            String price = txtPrice.getText();
            try {
                purchase.mPrice = Double.parseDouble(price);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Price is invalid!");
                return;
            }

            String quant = txtQuantity.getText();
            try {
                purchase.mQuantity = Double.parseDouble(quant);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Quantity is invalid!");
                return;
            }

            String cost = txtCost.getText();
            try {
                purchase.mCost = Double.parseDouble(cost);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Cost is invalid!");
                return;
            }

            String tax = txtTax.getText();
            try {
                purchase.mTax = Double.parseDouble(tax);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Tax is invalid!");
                return;
            }

            String Total = txtTotal.getText();
            try {
                purchase.mTotal = Double.parseDouble(Total);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Total is invalid!");
                return;
            }

            String date = txtDate.getText();
            if (date.length() == 0) {
                JOptionPane.showMessageDialog(null, "Date cannot be empty!");
                return;
            }

            purchase.mDate = date;


            int  res = StoreManager.getInstance().getDataAdapter().savePurchase(purchase);

            if (res == IDataAdapter.PRODUCT_SAVE_FAILED)
                JOptionPane.showMessageDialog(null, "Purchase is NOT saved successfully!");
            else
                JOptionPane.showMessageDialog(null, "Purchase is SAVED successfully!");
        }
    }
}
