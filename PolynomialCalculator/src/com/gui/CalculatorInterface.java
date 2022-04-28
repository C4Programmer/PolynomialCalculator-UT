/**
 * 	dataToTheFirstButton.addActionListener(e -> = Adds the values from the coefficient field and the degree field to the first polynomial
 * 	dataToTheSecondButton.addActionListener(e -> = Adds the values from the coefficient field and the degree field to the first polynomial
 * 	additionButton.addActionListener(e -> = Makes the Addition operation between the two polynomials
 * 	resetFirstButton.addActionListener(e -> = Resets the value of the first polynomial
 * 	resetSecondButton.addActionListener(e -> = Resets the value of the second polynomial
 * 	subtractionButton.addActionListener(e -> = Makes the Subtraction between the two polynomials
 * 	multiplicationButton.addActionListener(e -> = Makes the Multiplication between the two polynomials
 * 	divisionButton.addActionListener(e -> = Makes the Division between the two polynomials(first one divided to second one)
 * 	derivationButton.addActionListener(e -> = Derivate the first Polynomial
 * 	integrationButton.addActionListener(e -> = Integrate the first Polynomial
 * 	void printingPolynomial (Polynomial polynomial,JTextField coefficient, JTextField degree, JTextArea textArea, JScrollPane scrollPane) = Prints the first and second polynomial to the GUI specific fields
 * 	void printingResult(Polynomial polynomialResult,JScrollPane resultScrollPane) = Prints the result to the specific GUI field
 * 	void printingDivision(Polynomial polynomialResult,JScrollPane resultScrollPane) = Prints the division result
 * 	boolean checkErrors(Polynomial polynomial,JTextField degreeField, JTextField coefficientField) = Checks for Errors
 */

package com.gui;

import com.element.Monomial;
import com.calculator.Operation;
import com.element.Polynomial;

import javax.swing.*;

public class CalculatorInterface {
    public JPanel jpanel;
    private JButton dataToTheFirstButton;
    private JButton dataToTheSecondButton;
    private JButton additionButton;
    private JButton subtractionButton;
    private JButton multiplicationButton;
    private JButton divisionButton;
    private JButton derivationButton;
    private JButton integrationButton;
    private JScrollPane firstScrollPane;
    private JScrollPane secondScrollPane;
    private JTextField coefficientField;
    private JTextField degreeField;
    private JScrollPane resultScrollPane;
    private JScrollPane divisionScrollPane;
    private JButton resetFirstButton;
    private JButton resetSecondButton;

    private final Polynomial polynomial1 = new Polynomial();
    private final Polynomial polynomial2 = new Polynomial();

    private final JTextArea firstPolynomial = new JTextArea("");
    private final JTextArea secondPolynomial = new JTextArea("");

    public CalculatorInterface() {

        dataToTheFirstButton.addActionListener(e -> {
            if(CalculatorInterface.checkErrors(polynomial1,degreeField,coefficientField)){
                polynomial1.addMonomial(Double.parseDouble(coefficientField.getText()),Integer.parseInt(degreeField.getText()));
                CalculatorInterface.printingPolynomial(polynomial1,coefficientField,degreeField,firstPolynomial,firstScrollPane);
            }

        });
        dataToTheSecondButton.addActionListener(e -> {
            if(CalculatorInterface.checkErrors(polynomial2,degreeField,coefficientField)){
                polynomial2.addMonomial(Double.parseDouble(coefficientField.getText()),Integer.parseInt(degreeField.getText()));
                CalculatorInterface.printingPolynomial(polynomial2,coefficientField,degreeField,secondPolynomial,secondScrollPane);
            }
        });
        additionButton.addActionListener(e -> {
            Operation operation = new Operation();
            Polynomial polynomialResult = operation.addition(polynomial1,polynomial2);
            CalculatorInterface.printingResult(polynomialResult,resultScrollPane);

        });
        resetFirstButton.addActionListener(e -> {
            polynomial1.getMonomialList().clear();
            firstPolynomial.setText("");
            firstScrollPane.getViewport().add(firstPolynomial);
        });
        resetSecondButton.addActionListener(e -> {
            polynomial2.getMonomialList().clear();
            secondPolynomial.setText("");
            secondScrollPane.getViewport().add(secondPolynomial);
        });
        subtractionButton.addActionListener(e -> {
            Operation operation = new Operation();
            Polynomial polynomialResult = operation.subtraction(polynomial1,polynomial2);
            CalculatorInterface.printingResult(polynomialResult,resultScrollPane);
        });
        multiplicationButton.addActionListener(e -> {
            Operation operation = new Operation();
            Polynomial polynomialResult = operation.multiplication(polynomial1,polynomial2);
            CalculatorInterface.printingResult(polynomialResult,resultScrollPane);
        });
        divisionButton.addActionListener(e -> {
            Operation operation = new Operation();
            Polynomial polynomialResult = operation.divisionQuotient(polynomial1,polynomial2);
            Polynomial polynomialRest = operation.divisionRest(polynomial1,polynomial2);
            CalculatorInterface.printingDivision(polynomialResult,resultScrollPane);
            CalculatorInterface.printingDivision(polynomialRest,divisionScrollPane);
        });
        derivationButton.addActionListener(e -> {
            Operation operation = new Operation();
            Polynomial polynomialResult = operation.derivation(polynomial1);
            CalculatorInterface.printingResult(polynomialResult,resultScrollPane);
        });
        integrationButton.addActionListener(e -> {
            Operation operation = new Operation();
            Polynomial polynomialResult = operation.integration(polynomial1);
            CalculatorInterface.printingDivision(polynomialResult,resultScrollPane);
        });
    }

    static void printingPolynomial(Polynomial polynomial,JTextField coefficient, JTextField degree, JTextArea textArea, JScrollPane scrollPane){
        if(polynomial.getMonomialList().size() == 1){
            if(Integer.parseInt(coefficient.getText())<0){
                textArea.setText("("+coefficient.getText()+")"+"x^"+degree.getText());
            }else{
                textArea.setText(coefficient.getText()+"x^"+degree.getText());
            }
        }else{
            if(Integer.parseInt(coefficient.getText())<0){
                textArea.setText(textArea.getText()+" + "+"("+coefficient.getText()+")"+"x^"+degree.getText());
            }else{
                textArea.setText(textArea.getText()+" + "+coefficient.getText()+"x^"+degree.getText());
            }
        }
        scrollPane.getViewport().add(textArea);
    }

    static void printingResult(Polynomial polynomialResult,JScrollPane resultScrollPane){
        JTextArea resultArea = new JTextArea("");
        for(Monomial monomial: polynomialResult.getMonomialList()){
            if(polynomialResult.getMonomialList().get(0) == monomial){
                if(monomial.getCoefficientOfMonomial()<0){
                    resultArea.setText("("+(int)monomial.getCoefficientOfMonomial()+")x^"+monomial.getDegreeOfMonomial());
                }else{
                    resultArea.setText((int)monomial.getCoefficientOfMonomial()+"x^"+monomial.getDegreeOfMonomial());
                }
            }else{
                if(monomial.getCoefficientOfMonomial()<0){
                    resultArea.setText(resultArea.getText()+" + ("+(int)monomial.getCoefficientOfMonomial()+")x^"+monomial.getDegreeOfMonomial());
                }else{
                    resultArea.setText(resultArea.getText()+" + "+(int)monomial.getCoefficientOfMonomial()+"x^"+monomial.getDegreeOfMonomial());
                }
            }
        }
        resultScrollPane.getViewport().add(resultArea);
    }
    static void printingDivision(Polynomial polynomialResult,JScrollPane resultScrollPane){
        JTextArea resultArea = new JTextArea("");
        for(Monomial monomial: polynomialResult.getMonomialList()){
            if(polynomialResult.getMonomialList().get(0) == monomial){
                if(monomial.getCoefficientOfMonomial()<0){
                    resultArea.setText("("+monomial.getCoefficientOfMonomial()+")x^"+monomial.getDegreeOfMonomial());
                }else{
                    resultArea.setText(monomial.getCoefficientOfMonomial()+"x^"+monomial.getDegreeOfMonomial());
                }
            }else{
                if(monomial.getCoefficientOfMonomial()<0){
                    resultArea.setText(resultArea.getText()+" + ("+monomial.getCoefficientOfMonomial()+")x^"+monomial.getDegreeOfMonomial());
                }else{
                    resultArea.setText(resultArea.getText()+" + "+monomial.getCoefficientOfMonomial()+"x^"+monomial.getDegreeOfMonomial());
                }
            }
        }
        resultScrollPane.getViewport().add(resultArea);
    }

    static boolean checkErrors(Polynomial polynomial,JTextField degreeField, JTextField coefficientField){

        try {
            int check = Integer.parseInt(coefficientField.getText());
            for(Monomial monomial: polynomial.getMonomialList()){
                if(monomial.getDegreeOfMonomial()<=Integer.parseInt(degreeField.getText())) {
                    JOptionPane.showMessageDialog(null, "Incorrect degree introduced ! Please make sure that you introduce the degrees in a strictly descending order");
                    return false;
                }
            }
            if(Integer.parseInt(degreeField.getText())<0){
                JOptionPane.showMessageDialog(null, "Incorrect degree introduced ! Please make sure that the degrees are positive values");
                return false;
            }
            return true;
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Incorrect coefficient introduced ! Please make sure the coefficients or the degrees are Integer values");
            return false;
        }
    }
}

