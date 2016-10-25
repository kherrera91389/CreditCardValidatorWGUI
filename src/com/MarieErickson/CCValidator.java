package com.MarieErickson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by yd7581ku on 10/25/2016.
 */
public class CCValidator extends JFrame
{
    private JTextField creditCardNumberTextField;
    private JButton validateButton;
    private JButton quitButton;
    private JPanel rootPanel;
    private JLabel validMessageLabel;
    public CCValidator(){
        super("CreditCardValidatorWGUI");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        validateButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String ccNumber = creditCardNumberTextField.getText();
                boolean valid = isVisaCreditCardNumber(ccNumber);
                String isValid= valid ? "Credit card number is valid":
                        "Credit card number is not valid";
                validMessageLabel.setText(isValid);


            }
        });
        quitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                //verify user wants to close and potentially lose data/work
                int quit= JOptionPane.showConfirmDialog(CCValidator.this,
                        "Are you sure you want to quit?", "Quit?",
                        JOptionPane.OK_CANCEL_OPTION);
                //handle ok button click event in dialog box
                if(quit == JOptionPane.OK_OPTION){
                    //0 means no problems/errors
                    System.exit(0);
                }
            }
        });
    }
    private static boolean isVisaCreditCardNumber(String cc){


        //convert string of numbers in a char array
        char[] numbers = cc.toCharArray();
        int[] numbersInt = new int[numbers.length];
        //convert char array to integer array using a for loop
        for (int x = 0; x < numbers.length; x++) {
            //gets numeric value for char in array
            numbersInt[x] = Character.getNumericValue(numbers[x]);

        }
        //verify that the number entered is 16 characters long
        if (numbersInt.length == 16) {

            //verify that the first digit is 4, prefix for Visa
            if (numbersInt[0] == 4) {
                int sum = 0;
                int sum2 = 0;
                //add even numbers together
                for (int x = 1; x < numbersInt.length; x=x+2)
                    sum = sum + numbersInt[x];
                //multiply even digits to add to the sum of the odd digits
                for (int y = 0; y < numbersInt.length; y=y+2) {
                    int times2 = numbersInt[y] * 2;
                    int tsum = 0;
                    //if the sum of the digit multiplied by two is greater than 10,
                    // add the digits of the product
                    if ((times2) >= 10) {
                        while (times2 > 0) {
                            tsum = tsum + times2 % 10;
                            times2 = times2 / 10;

                        }
                        //add sum of product to sum of digits multiplied by two
                        sum2 += tsum;
                        //if the sum of the product is not more than ten, add the sum
                    } else sum2 += times2;
                }
                //sum the sums
                sum += sum2;
                //return true if the processed number is divisible by the check digit
                return sum % 10 == 0;
            } else {
                return false;
            }
        } else {
            System.out.println("Your entry is too short");
            return false;
        }
    }
}
