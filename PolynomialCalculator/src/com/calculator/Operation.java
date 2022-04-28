/**
 * -	Polynomial addition (Polynomial polynomial1, Polynomial polynomial2) = It returns the addition between two polynomials. The method involves the principle of Merge Sort (merging 2 sorted list). The degrees are in descending order and by applying this principle, we can efficiently find the resulting polynomial.
 * -	Polynomial subtraction (Polynomial polynomial1, Polynomial polynomial2) = It involves the same principles as the addition
 * -	Polynomial multiplication (Polynomial polynomial1, Polynomial polynomial2) = Each element of one polynomial is multiplied with all the elements of the other polynomial obtaining an auxiliary polynomial. The multiplication result will be represented by using addition on all the auxiliary polynomials the results.
 * -	Polynomial divisionQuotient (Polynomial polynomial1, Polynomial polynomial2) = It returns the quotient of the division between the first polynomial to the second one.
 * -	Polynomial divisionRest (Polynomial polynomial1, Polynomial polynomial2) = It is the same method as the other division method, the only difference is that it returns the Rest of the division.
 * -	Polynomial derivation (Polynomial polynomial1) = It returns the value of the derivative of the polynomial
 * -	Polynomial integration (Polynomial polynomial1) = It returns the value of the integral of the polynomial
 */

package com.calculator;

import com.element.Monomial;
import com.element.Polynomial;

public class Operation {

    public Polynomial addition(Polynomial polynomial1, Polynomial polynomial2){
        Polynomial polynomialResult = new Polynomial();
        int i=0,j=0;
        while(i < polynomial1.getMonomialList().size() && j < polynomial2.getMonomialList().size()){
            if(polynomial1.getMonomialList().get(i).getDegreeOfMonomial()<polynomial2.getMonomialList().get(j).getDegreeOfMonomial()){
                polynomialResult.addMonomial(polynomial2.getMonomialList().get(j).getCoefficientOfMonomial(),polynomial2.getMonomialList().get(j).getDegreeOfMonomial());
                j++;
            }else if(polynomial1.getMonomialList().get(i).getDegreeOfMonomial()>polynomial2.getMonomialList().get(j).getDegreeOfMonomial()){
                polynomialResult.addMonomial(polynomial1.getMonomialList().get(i).getCoefficientOfMonomial(),polynomial1.getMonomialList().get(i).getDegreeOfMonomial());
                i++;
            }else{
                polynomialResult.addMonomial(polynomial1.getMonomialList().get(i).getCoefficientOfMonomial() +
                        polynomial2.getMonomialList().get(j).getCoefficientOfMonomial(),polynomial1.getMonomialList().get(i).getDegreeOfMonomial());
                i++;
                j++;
            }
        }
        while(i < polynomial1.getMonomialList().size()){
            polynomialResult.addMonomial(polynomial1.getMonomialList().get(i).getCoefficientOfMonomial(),polynomial1.getMonomialList().get(i).getDegreeOfMonomial());
            i++;
        }
        while(j < polynomial2.getMonomialList().size()){
            polynomialResult.addMonomial(polynomial2.getMonomialList().get(j).getCoefficientOfMonomial(),polynomial2.getMonomialList().get(j).getDegreeOfMonomial());
            j++;
        }
        return polynomialResult;
    }

    public Polynomial subtraction(Polynomial polynomial1, Polynomial polynomial2){
        Polynomial polynomialResult = new Polynomial();
        int i=0,j=0;
        while(i < polynomial1.getMonomialList().size() && j < polynomial2.getMonomialList().size()){
            if(polynomial1.getMonomialList().get(i).getDegreeOfMonomial()<polynomial2.getMonomialList().get(j).getDegreeOfMonomial()){
                polynomialResult.addMonomial((-1)*polynomial2.getMonomialList().get(j).getCoefficientOfMonomial(),polynomial2.getMonomialList().get(j).getDegreeOfMonomial());
                j++;
            }else if(polynomial1.getMonomialList().get(i).getDegreeOfMonomial()>polynomial2.getMonomialList().get(j).getDegreeOfMonomial()){
                polynomialResult.addMonomial(polynomial1.getMonomialList().get(i).getCoefficientOfMonomial(),polynomial1.getMonomialList().get(i).getDegreeOfMonomial());
                i++;
            }else{
                if(polynomial1.getMonomialList().get(i).getCoefficientOfMonomial() - polynomial2.getMonomialList().get(j).getCoefficientOfMonomial()!=0){
                    polynomialResult.addMonomial(polynomial1.getMonomialList().get(i).getCoefficientOfMonomial() -
                            polynomial2.getMonomialList().get(j).getCoefficientOfMonomial(),polynomial1.getMonomialList().get(i).getDegreeOfMonomial());
                }
                i++;
                j++;
            }
        }
        while(i < polynomial1.getMonomialList().size()){
            polynomialResult.addMonomial(polynomial1.getMonomialList().get(i).getCoefficientOfMonomial(),polynomial1.getMonomialList().get(i).getDegreeOfMonomial());
            i++;
        }
        while(j < polynomial2.getMonomialList().size()){
            polynomialResult.addMonomial((-1)*polynomial2.getMonomialList().get(j).getCoefficientOfMonomial(),polynomial2.getMonomialList().get(j).getDegreeOfMonomial());
            j++;
        }
        return polynomialResult;
    }

    public Polynomial multiplication(Polynomial polynomial1, Polynomial polynomial2){

        Polynomial polynomialResult = new Polynomial();
        polynomialResult.addMonomial(0,0);
        Operation operation = new Operation();

        for(Monomial monomial1: polynomial1.getMonomialList()){
            Polynomial polynomialAuxiliary = new Polynomial();
            for(Monomial monomial2: polynomial2.getMonomialList()){
                polynomialAuxiliary.addMonomial(monomial1.getCoefficientOfMonomial()*monomial2.getCoefficientOfMonomial()
                        ,monomial1.getDegreeOfMonomial()+monomial2.getDegreeOfMonomial());
            }
            polynomialResult = operation.addition(polynomialResult,polynomialAuxiliary);
        }

        return polynomialResult;
    }

    public Polynomial divisionQuotient(Polynomial polynomial1, Polynomial polynomial2){

        Polynomial polynomialResult = new Polynomial();
        Operation operation = new Operation();
        Polynomial polynomialReminder = new Polynomial();

        polynomialReminder.setMonomialList(polynomial1.getMonomialList());

        while(polynomialReminder.getMonomialList().get(0).getDegreeOfMonomial() >= polynomial2.getMonomialList().get(0).getDegreeOfMonomial()){
            polynomialResult.addMonomial(polynomialReminder.getMonomialList().get(0).getCoefficientOfMonomial()/polynomial2.getMonomialList().get(0).getCoefficientOfMonomial(),
                    polynomialReminder.getMonomialList().get(0).getDegreeOfMonomial()-polynomial2.getMonomialList().get(0).getDegreeOfMonomial());
            Polynomial polynomialAuxiliary1 = new Polynomial();
            polynomialAuxiliary1.addMonomial(
                    polynomialReminder.getMonomialList().get(0).getCoefficientOfMonomial()/polynomial2.getMonomialList().get(0).getCoefficientOfMonomial(),
                    polynomialReminder.getMonomialList().get(0).getDegreeOfMonomial()-polynomial2.getMonomialList().get(0).getDegreeOfMonomial());

            Polynomial polynomialAuxiliary2 = operation.multiplication(polynomial2,polynomialAuxiliary1);
            polynomialReminder = operation.subtraction(polynomialReminder,polynomialAuxiliary2);
        }
        return polynomialResult;
    }

    public Polynomial divisionRest(Polynomial polynomial1, Polynomial polynomial2){

        Polynomial polynomialResult = new Polynomial();
        Operation operation = new Operation();
        Polynomial polynomialReminder = new Polynomial();

        polynomialReminder.setMonomialList(polynomial1.getMonomialList());

        while(polynomialReminder.getMonomialList().get(0).getDegreeOfMonomial() >= polynomial2.getMonomialList().get(0).getDegreeOfMonomial()){
            polynomialResult.addMonomial(polynomialReminder.getMonomialList().get(0).getCoefficientOfMonomial()/polynomial2.getMonomialList().get(0).getCoefficientOfMonomial(),
                    polynomialReminder.getMonomialList().get(0).getDegreeOfMonomial()-polynomial2.getMonomialList().get(0).getDegreeOfMonomial());
            Polynomial polynomialAuxiliary1 = new Polynomial();
            polynomialAuxiliary1.addMonomial(
                    polynomialReminder.getMonomialList().get(0).getCoefficientOfMonomial()/polynomial2.getMonomialList().get(0).getCoefficientOfMonomial(),
                    polynomialReminder.getMonomialList().get(0).getDegreeOfMonomial()-polynomial2.getMonomialList().get(0).getDegreeOfMonomial());

            Polynomial polynomialAuxiliary2 = operation.multiplication(polynomial2,polynomialAuxiliary1);
            polynomialReminder = operation.subtraction(polynomialReminder,polynomialAuxiliary2);
        }
        return polynomialReminder;
    }

    public Polynomial derivation(Polynomial polynomial1){
        Polynomial polynomialResult = new Polynomial();

        for(Monomial monomial: polynomial1.getMonomialList()){
            if(monomial.getDegreeOfMonomial()!=0){
                polynomialResult.addMonomial(monomial.getCoefficientOfMonomial()*monomial.getDegreeOfMonomial(),monomial.getDegreeOfMonomial()-1);
            }
        }

        return polynomialResult;
    }

    public Polynomial integration(Polynomial polynomial1){
        Polynomial polynomialResult = new Polynomial();

        for(Monomial monomial: polynomial1.getMonomialList()){
            polynomialResult.addMonomial(monomial.getCoefficientOfMonomial()/(monomial.getDegreeOfMonomial()+1),monomial.getDegreeOfMonomial()+1);
        }

        return polynomialResult;
    }
}
