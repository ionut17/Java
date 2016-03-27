package lab6.controller;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

/**
 *
 * @author Anca
 */
public class Function {

    private String function;
    
    public Function(){}
    
    public Function(String function){
        this.function=function;
    }

    public Object getValueOf(Integer value) {
        JexlEngine jexl = new JexlEngine();
        Expression e = jexl.createExpression(function);
        MapContext mc = new MapContext();
        mc.set("x", value);
        System.out.println("** " + e.evaluate(mc) + " *");
        return e.evaluate(mc);
    }

    /**
     * @param function the function to set
     */
    public void setFunction(String function) {
        this.function = function;
    }
}
