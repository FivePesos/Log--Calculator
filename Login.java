import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Login implements ActionListener {

    JTextField textFieldUsername;
    JPasswordField textFieldPassword;
    JButton buttonRegister;
    JButton buttonLogin;
    JLabel progressBarText;
    JFrame frame;
    JProgressBar bar;
    protected String username, password;

    public Login() {
        File usernameFile = new File("username.txt");
        File passwordFile = new File("password.txt");

        try {
            if (!usernameFile.exists()) {
                usernameFile.createNewFile();
            }
            if (!passwordFile.exists()) {
                passwordFile.createNewFile();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        frame = new JFrame();

        // JLabel for Username
        JLabel labelUsername = new JLabel();
        labelUsername.setText("Username");
        labelUsername.setBounds(210, 300, 75, 25);

        // JLabel for Password
        JLabel labelPassword = new JLabel();
        labelPassword.setText("Password");
        labelPassword.setBounds(210, 400, 75, 25);

        // JButton for Login
        buttonLogin = new JButton();
        buttonLogin.addActionListener(this);
        buttonLogin.setText("Log in");
        buttonLogin.setBounds(200, 550, 90, 25);

        // JButton for Register
        buttonRegister = new JButton();
        buttonRegister.addActionListener(this);
        buttonRegister.setText("Register");
        buttonRegister.setBounds(200, 600, 90, 25);

        // TextField for Username
        textFieldUsername = new JTextField();
        textFieldUsername.setBounds(140, 330, 200, 30);

        // TextField for Password
        textFieldPassword = new JPasswordField();
        textFieldPassword.setBounds(140, 430, 200, 30);

        frame.getContentPane().setBackground(new Color(179, 173, 173));
        frame.setResizable(false);
        frame.setSize(500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        frame.add(labelUsername);
        frame.add(labelPassword);
        frame.add(buttonLogin);
        frame.add(buttonRegister);
        frame.add(textFieldUsername);
        frame.add(textFieldPassword);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonRegister) {
            frame.dispose(); // Close the Login frame
            Register registerPage = new Register(); // Open Register form
        }

        if (e.getSource() == buttonLogin) {
            username = textFieldUsername.getText();
            password = String.valueOf(textFieldPassword.getPassword());

            if (!(credentialsExist(username, password))) {
                JOptionPane.showMessageDialog(null, "Wrong Username or Password", "WARNING", JOptionPane.ERROR_MESSAGE);
            } else {
                frame.dispose();
                Calculator calc = new Calculator();
            }
        }
    }

    private boolean credentialsExist(String username, String password) {
        File fileUsername = new File("username.txt");
        File filePassword = new File("password.txt");

        try {
            BufferedReader usernameReader = new BufferedReader(new FileReader(fileUsername));
            BufferedReader passwordReader = new BufferedReader(new FileReader(filePassword));
            String usernameCurrentLine;
            String passwordCurrentLine;

            while ((usernameCurrentLine = usernameReader.readLine()) != null
                    && (passwordCurrentLine = passwordReader.readLine()) != null) {
                if (usernameCurrentLine.equals(username) && passwordCurrentLine.equals(password))
                    return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

        return false;
    }

}
