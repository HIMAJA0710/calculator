import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Double.parseDouble;

public class Calculator {
    JFrame frame;
    JTextField tf;

    public Calculator() {
        createCalcGUI();
    }

    public void createCalcGUI() {
        frame = new JFrame("Calculator");
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5)); 

        tf = new JTextField(20);
        frame.add(tf, BorderLayout.NORTH);

        JButton b0 = new JButton("0");
        panel.add(b0);
        JButton b1 = new JButton("1");
        panel.add(b1);
        JButton button2 = new JButton("2");
        panel.add(button2);
        JButton button3 = new JButton("3");
        panel.add(button3);
        JButton button4 = new JButton("4");
        panel.add(button4);
        JButton button5 = new JButton("5");
        panel.add(button5);
        JButton button6 = new JButton("6");
        panel.add(button6);
        JButton button7 = new JButton("7");
        panel.add(button7);
        JButton button8 = new JButton("8");
        panel.add(button8);
        JButton button9 = new JButton("9");
        panel.add(button9);
        JButton button10 = new JButton("*");
        panel.add(button10);
        JButton button11 = new JButton("-");
        panel.add(button11);
        JButton button12 = new JButton("+");
        panel.add(button12);
        JButton button13 = new JButton("=");
        panel.add(button13);
        JButton button14 = new JButton("/");
        panel.add(button14);
        JButton button15 = new JButton("C");
        panel.add(button15);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

        // Adding listeners
        b0.addActionListener(new NumberButtonListener());
        b1.addActionListener(new NumberButtonListener());
        button2.addActionListener(new NumberButtonListener());
        button3.addActionListener(new NumberButtonListener());
        button4.addActionListener(new NumberButtonListener());
        button5.addActionListener(new NumberButtonListener());
        button6.addActionListener(new NumberButtonListener());
        button7.addActionListener(new NumberButtonListener());
        button8.addActionListener(new NumberButtonListener());
        button9.addActionListener(new NumberButtonListener());

        button10.addActionListener(new OperationButtonListener());
        button11.addActionListener(new OperationButtonListener());
        button12.addActionListener(new OperationButtonListener());
        button14.addActionListener(new OperationButtonListener());

        button13.addActionListener(new EqualButtonListener());
        button15.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tf.setText("");
            }
        });
    }

    public static void main(String[] args) {
        new Calculator();
    }

    private class NumberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            tf.setText(tf.getText() + ((JButton) e.getSource()).getText());
        }
    }

    private class OperationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            tf.setText(tf.getText() + " " + ((JButton) e.getSource()).getText() + " ");
        }
    }

    private class EqualButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String exp = tf.getText();
                String[] parts = exp.split(" ");
                if (parts.length != 5) { 
                    tf.setText("Error");
                    return;
                }

                double n1 = parseDouble(parts[0]);
                String op1 = parts[1];
                double n2 = parseDouble(parts[2]);
                String op2 = parts[3];
                double n3 = parseDouble(parts[4]);
                double result = 0;

                switch (op1) {
                    case "+":
                        result = n1 + n2;
                        break;
                    case "-":
                        result = n1 - n2;
                        break;
                    case "*":
                        result = n1 * n2;
                        break;
                    case "/":
                        if (n2 != 0) {
                            result = n1 / n2;
                        } else {
                            tf.setText("Error");
                            return;
                        }
                        break;
                    default:
                        tf.setText("Error");
                        return;
                }
                switch (op2) {
                    case "+":
                        result = result + n3;
                        break;
                    case "-":
                        result = result - n3;
                        break;
                    case "*":
                        result = result * n3;
                        break;
                    case "/":
                        if (n3 != 0) {
                            result = result / n3;
                        } else {
                            tf.setText("Error");
                            return;
                        }
                        break;
                    default:
                        tf.setText("Error");
                        return;
                }

                tf.setText(String.valueOf(result));
            } catch (Exception ex) {
                tf.setText("Error");
            }
        }
    }
}
