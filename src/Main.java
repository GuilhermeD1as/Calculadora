package calc;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import javax.swing.border.*;

class Calc extends JFrame implements ActionListener {

    private final static String MAIS = "+";
    private final static String MENOS = "-";
    private final static String MULT = "*";
    private final static String DIV = "/";

    private JTextField num1TF;
    private JTextField num2TF;
    private JComboBox operCBB;
    private JButton calcBT;
    private JTextField resultadoTF;

    private DecimalFormat df = new DecimalFormat("0.00");

    public Calc() {
        num1TF = new JTextField(5);
        num2TF = new JTextField(5);

        operCBB = new JComboBox();
        operCBB.addItem(MAIS);
        operCBB.addItem(MENOS);
        operCBB.addItem(MULT);
        operCBB.addItem(DIV);

        calcBT = new JButton("Calcular");

        resultadoTF = new JTextField();
        resultadoTF.setEditable( false );

        JPanel calcPNL = new JPanel();
        calcPNL.setLayout(new FlowLayout(FlowLayout.LEFT));
        calcPNL.add(num1TF);
        calcPNL.add(operCBB);
        calcPNL.add(num2TF);
        calcPNL.add(calcBT);

        JPanel conteudoPNl = new JPanel();
        conteudoPNl.setBorder(new TitledBorder("Calculadora aritmética"));
        conteudoPNl.setLayout(new BorderLayout());
        conteudoPNl.add(BorderLayout.CENTER, resultadoTF);
        conteudoPNl.add(BorderLayout.SOUTH, calcPNL);

        super.setTitle("calculadora");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setContentPane(conteudoPNl);
        super.pack();
        super.setLocationRelativeTo(this);
        calcBT.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        boolean num10k = true;
        boolean num20k = true;

        double num1 = 0;
        try {
            num1 = df.parse(num1TF.getText()).doubleValue();
        } catch (ParseException ex) {
            num10k = false;
        }
        double num2 = 0;
        try {
            num2 = df.parse(num2TF.getText()).doubleValue();
        } catch (ParseException ex) {
            num20k = false;
        }
        if (num10k && num20k) {
            boolean divZero = false;
            double result = 0;
            Object seLItem = operCBB.getSelectedItem();
            if (seLItem.equals(MAIS)) {
                result = num1 + num2;
            } else if (seLItem.equals(MENOS)) {
                result = num1 - num2;
            } else if (seLItem.equals(MULT)) {
                result = num1 * num2;
            } else if (seLItem.equals(DIV)) {
                if (num2 == 0) {
                    divZero = true;
                } else {
                    result = num1 / num2;
                }
            }
            if (divZero) {
                String msg = "Operação inválida - divisão por zero!";
                JOptionPane.showMessageDialog(null, msg, " Calculadora", JOptionPane.WARNING_MESSAGE);
            } else {
                resultadoTF.setText(df.format(result));
            }
        } else {
            if (num10k == false) {
                String msg = " O valor \"" + num1TF.getText() + "\" não e um numero válido.";
                JOptionPane.showMessageDialog(this, msg, "Calculadora", JOptionPane.WARNING_MESSAGE);
            } else if (num20k == false) {
                String msg = "O valor \"" + num2TF.getText() + "\" não e um numero valido.";
                JOptionPane.showMessageDialog(this, msg, " Calculadora", JOptionPane.WARNING_MESSAGE);
            }

        }
    }
    public static void main(String[]args) {
                Calc calc = new Calc();
                calc.setVisible(true);
         }

}









