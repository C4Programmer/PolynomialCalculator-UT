package com.element;

public class Monomial{

    private double coefficientOfMonomial;
    private int degreeOfMonomial;

    public void setCoefficientOfMonomial(double coefficientOfMonomial) {
        this.coefficientOfMonomial = coefficientOfMonomial;
    }

    public double getCoefficientOfMonomial() {
        return coefficientOfMonomial;
    }

    public void setDegreeOfMonomial(int degreeOfMonomial) {
        this.degreeOfMonomial = degreeOfMonomial;
    }

    public int getDegreeOfMonomial() {
        return degreeOfMonomial;
    }
}
