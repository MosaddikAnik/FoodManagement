import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Login extends JFrame implements ActionListener {

    JLabel userLabel, passLabel, headerLogin, headerBBQ, headerFried, headerBurger, headerPops;
    JTextField userTX;
    JPasswordField passPF;
    JButton loginBtn, exitBtn, clearcartBtn, cartBtn, cartBtn1, cartBtn2, cartBtn3, cartBtn4;
    JPanel panel;
    Font front;
    FileWriter writer;
    double totalPrice = 0.0;
    String customerName = null;
    String customerId = null;
    JTextArea cartDisplay;

    void setupFileWriter() {
        try {
            File file = new File("calculator_history.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            writer = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Login() {
        super("Food Panda");
        this.setSize(1400, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        front = new Font("Arial", Font.PLAIN, 16);

        panel = new JPanel();
        panel.setLayout(null);

        headerLogin = new JLabel("LOGIN");
        headerLogin.setBounds(100, 30, 300, 50);
        headerLogin.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(headerLogin);

        userLabel = new JLabel("User Name : ");
        userLabel.setBounds(100, 100, 100, 30);
        panel.add(userLabel);

        passLabel = new JLabel("Password : ");
        passLabel.setBounds(100, 150, 100, 30);
        panel.add(passLabel);

        userTX = new JTextField();
        userTX.setBounds(200, 100, 140, 30);
        panel.add(userTX);

        passPF = new JPasswordField();
        passPF.setBounds(200, 150, 140, 30);
        panel.add(passPF);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(100, 200, 100, 30);
        panel.add(loginBtn);

        exitBtn = new JButton("Exit");
        exitBtn.setBounds(220, 200, 100, 30);
        panel.add(exitBtn);

        cartBtn = new JButton("View Cart");
        cartBtn.setBounds(100, 250, 100, 30);
        panel.add(cartBtn);

        clearcartBtn = new JButton("Clear Cart");
        clearcartBtn.setBounds(220, 250, 100, 30);
        panel.add(clearcartBtn);

        cartDisplay = new JTextArea();
        cartDisplay.setEditable(false);
        cartDisplay.setFont(new Font("Monospaced", Font.PLAIN, 14));
        cartDisplay.setBounds(400, 100, 400, 300);
        cartDisplay.setBorder(BorderFactory.createTitledBorder("Cart Summary"));
        panel.add(cartDisplay);

        headerBBQ = new JLabel("BBQ Juicy Chicken - $150");
        headerBBQ.setBounds(100, 400, 200, 30);
        headerBBQ.setFont(front);
        panel.add(headerBBQ);

        cartBtn1 = new JButton("Add To Cart");
        cartBtn1.setBounds(100, 440, 200, 30);
        panel.add(cartBtn1);

        headerFried = new JLabel("Fried Chicken - $150");
        headerFried.setBounds(350, 400, 200, 30);
        headerFried.setFont(front);
        panel.add(headerFried);

        cartBtn2 = new JButton("Add To Cart");
        cartBtn2.setBounds(350, 440, 200, 30);
        panel.add(cartBtn2);

        headerBurger = new JLabel("Crispy Chicken Burger - Large - $250");
        headerBurger.setBounds(600, 400, 300, 30);
        headerBurger.setFont(front);
        panel.add(headerBurger);

        cartBtn3 = new JButton("Add To Cart");
        cartBtn3.setBounds(600, 440, 200, 30);
        panel.add(cartBtn3);

        headerPops = new JLabel("Crispy Chicken Pops - $130");
        headerPops.setBounds(850, 400, 200, 30);
        headerPops.setFont(front);
        panel.add(headerPops);

        cartBtn4 = new JButton("Add To Cart");
        cartBtn4.setBounds(850, 440, 200, 30);
        panel.add(cartBtn4);

        setupFileWriter();
        this.add(panel);
    }


    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();

        if (source == loginBtn) {
            String username = userTX.getText().trim();
            String password = new String(passPF.getPassword()).trim();
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username and password cannot be empty.");
            } else {
                JOptionPane.showMessageDialog(this, "Login Successful");
            }
        } else if (source == exitBtn) {
            System.exit(0);
        } else if (source == cartBtn1) {
            addToCart("BBQ Juicy Chicken - $150", 150.0);
        } else if (source == cartBtn2) {
            addToCart("Fried Chicken - $150", 150.0);
        } else if (source == cartBtn3) {
            addToCart("Crispy Chicken Burger - Large - $250", 250.0);
        } else if (source == cartBtn4) {
            addToCart("Crispy Chicken Pops - $130", 130.0);
        } else if (source == cartBtn) {
            if (customerName == null || customerId == null) {
                cartDisplay.setText("Customer info missing. Add an item first.");
                return;
            }

            StringBuilder message = new StringBuilder();
            message.append("Customer Name: ").append(customerName).append("\n");
            message.append("Customer ID: ").append(customerId).append("\n\n");
            message.append("Items in Cart:\n");

            message.append("\nTotal Price: $").append(totalPrice);
            cartDisplay.setText(message.toString());

            try {
                writer.write("Customer Name: " + customerName + "\n");
                writer.write("Customer ID: " + customerId + "\n");
                writer.write("New Cart Session:\n");
                writer.write("Total: $" + totalPrice + "\n");
                writer.write("----------\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (source == clearcartBtn) {
            totalPrice = 0.0;
            customerName = null;
            customerId = null;
            cartDisplay.setText("");
            JOptionPane.showMessageDialog(this, "Cart has been cleared.");
        }
    }
}
