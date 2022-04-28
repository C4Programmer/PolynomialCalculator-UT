/**
 * -	void addMonomial (double coefficient, int degree) = It adds a Monomial with the specific values to the list of the Polynomial
 */

package com.element;

import java.util.ArrayList;
import java.util.List;

public class Polynomial {

    private List<Monomial> monomialList = new ArrayList<>();

    public void addMonomial(double coefficient, int degree){
        Monomial monomial = new Monomial();
        monomial.setDegreeOfMonomial(degree);
        monomial.setCoefficientOfMonomial(coefficient);
        this.monomialList.add(monomial);
    }

    public void setMonomialList(List<Monomial> monomialList) {
        this.monomialList = monomialList;
    }

    public List<Monomial> getMonomialList() {
        return monomialList;
    }
}
