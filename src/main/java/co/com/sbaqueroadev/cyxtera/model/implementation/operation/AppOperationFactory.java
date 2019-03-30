package co.com.sbaqueroadev.cyxtera.model.implementation.operation;

import co.com.sbaqueroadev.cyxtera.exceptions.OperationException;

import javax.management.OperationsException;

public class AppOperationFactory {

    private static final String PLUS_SYMBOL = "+";
    private static final String MINUS_SYMBOL = "-";
    private static final String BY_SYMBOL = "*";
    private static final String DIVIDE_SYMBOL = "/";
    private static final String POW_SYMBOL = "";

    public static AppOperation createOperation(String symbol) throws OperationException {
        switch (symbol){
            case PLUS_SYMBOL:
                return new Addition();
            case MINUS_SYMBOL:
                return new Substraction();
            case BY_SYMBOL:
                return new Multiplication();
            case DIVIDE_SYMBOL:
                return new Division();
            case POW_SYMBOL:
                return new Pow();
            default:
                throw new OperationException("Operation symbol doesn't exist (use +,-,*,/,^)");
        }
    }

}
