package lab6.controller;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

/**
 *
 * @author Anca
 */
public class Function {

    private String function;

    public Object getValueOf(Integer value) {
        Map<String, Object> functions = new HashMap<String, Object>();
        functions.put("math", Math.class);
        JexlEngine jexl = new JexlEngine();
        jexl.setFunctions(functions);
        Expression e = jexl.createExpression(getFunction());
        MapContext mc = new MapContext();
        mc.set("x", value);
        //        System.out.println("** " + e.evaluate(mc) + " *");
        String returnValue=e.evaluate(mc).toString().split("\\.")[0];
        return Integer.valueOf(returnValue);
    }

    /**
     * @return the function
     */
    public String getFunction() {
        return function;
    }

    /**
     * @param function the function to set
     */
    public void setFunction(String function) {
        this.function = function;
    }

}
