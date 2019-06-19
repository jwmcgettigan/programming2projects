/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Primes;

import java.util.ArrayList;

/**
 *
 * @author Justin McGettigan
 */
public class Primes {

    private ArrayList<Integer> primes;
    private int mOfIndex; //multiple of

    public Primes() {
        primes = new ArrayList<>();
        for (int i = 2; i < 201; i++) {
            primes.add(i);
        }
        mOfIndex = 0;
    }

    public void sieveOfEratothenes() {
        int mOf, temp;
        boolean resume = true;
        
        System.out.printf(primes + "\n\n");
        for (int mOfIndex = 0; resume; mOfIndex++) {
            temp = primes.size();
            mOf = primes.get(mOfIndex);
            for (int i = 0; i < primes.size(); i++) {
                if (primes.get(i) != mOf && primes.get(i) % mOf == 0) {
                    primes.remove(i);
                }
            }
            resume = primes.size() != temp;
            if(resume){
                System.out.printf(primes + "\n\n");
            }
        }
    }
}
