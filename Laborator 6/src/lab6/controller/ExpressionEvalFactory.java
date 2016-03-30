/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6.controller;

/**
 *
 * @author Anca
 */
 public class WithPow extends org.apache.commons.jexl2.JexlArithmetic { 
        public WithPow(boolean lenient) { 
            super(lenient); 
        } 

        public Object bitwiseXor(Object left, Object right) { 
            double l = toDouble(left); 
            double r = toDouble(right); 
            return Math.pow(l, r); 
        } 
    } 