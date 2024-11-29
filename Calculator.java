import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

class Calculator extends JFrame implements ActionListener {
    
    static JFrame frame;
    static JTextField display;

 
    String firstOperand, operator, secondOperand;


    public Calculator() {
        firstOperand = operator = secondOperand = "";

      
        frame = new JFrame("Calculator");

        display = new JTextField(20);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 50));
        display.setEditable(false);

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5)); 

        
        String[] buttonLabels = {
            "C", "%", "/", "*",
            "7", "8", "9", "-",
            "4", "5", "6", "+",
            "1", "2", "3", "=",
            "0", ".", "", ""
        };

        
        JButton[] buttons = new JButton[20]; 
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 20));
            buttons[i].addActionListener(this);
            if (!buttonLabels[i].isEmpty()) { 
                buttonPanel.add(buttons[i]);
            }
        }

        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.add(display, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

       
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        frame.add(mainPanel);

        
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
    
        if (command.matches("[0-9]") || command.equals(".")) {
            
            if (!operator.isEmpty()) {
                secondOperand += command;
            } else {
                firstOperand += command;
            }
            display.setText(firstOperand + operator + secondOperand);
        } else if (command.equals("C")) {
            
            firstOperand = operator = secondOperand = "";
            display.setText("");
        } else if (command.equals("=")) {
            
            if (!firstOperand.isEmpty() && !secondOperand.isEmpty()) {
                double result = 0.0;
                double num1 = Double.parseDouble(firstOperand);
                double num2 = Double.parseDouble(secondOperand);
    
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                }
    
                
                if (result == (int) result) {
                    display.setText(String.valueOf((int) result));
                } else {
                    display.setText(String.valueOf(result));
                }
    
                
                firstOperand = String.valueOf(result);
                operator = secondOperand = "";
            }
        } else if (command.matches("[+\\-*/]")) {
            
            if (!firstOperand.isEmpty() && secondOperand.isEmpty()) {
                operator = command;
                display.setText(firstOperand + operator);
            }
        } else if (command.equals("%")) {
            
            if (!secondOperand.isEmpty()) {
                secondOperand = String.valueOf(Double.parseDouble(secondOperand) / 100);
            } else if (!firstOperand.isEmpty()) {
                firstOperand = String.valueOf(Double.parseDouble(firstOperand) / 100);
            }
            display.setText(firstOperand + operator + secondOperand);
        }
    }
    
}
