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
        ExpressionEvalFactory factoryEval = new ExpressionEvalFactory();
        Map<String, Object> functions = new HashMap<String, Object>();
        functions.put("math", Math.class);
        JexlEngine jexl = factoryEval.getInstance();
        jexl.setFunctions(functions);
        Expression e = jexl.createExpression(getFunction());
        MapContext mc = new MapContext();
        mc.set("x", value);

        System.out.println("expression: *" + e + "*");
//        Expression express=jexl.createExpression("sin(30)");
//        System.out.println("** " + express.evaluate(null) + " *");
        String returnValue = e.evaluate(mc).toString().split("\\.")[0];
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
    public void setFunction(String myFunction) {
        String[] mathFunctions = {"sin", "toRadians", "cos", "abs", "acos", "asin", "atan", "ceil", "floor", "log", "log10", "max", "min", "pow", "random", "round", "sqrt", "tan", "toDegrees"};
        for (String f: mathFunctions) {
            myFunction = parseFunction(myFunction, f);
        }
        this.function = myFunction;
    }

    private String parseFunction(String myFunction, String math) {
        if (!myFunction.contains("math:" + math)) {
            if (myFunction.contains(math)) {
                String[] functionPieces = myFunction.split(math);
                myFunction = "";
                for (int i = 0; i < functionPieces.length - 1; i++) {
                    myFunction += functionPieces[i] + "math:" + math;
                }
                myFunction += functionPieces[functionPieces.length - 1];
            }
        }
        return myFunction;
    }
}
