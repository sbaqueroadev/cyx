package co.com.sbaqueroadev.cyxtera.model.implementation.operation;

import co.com.sbaqueroadev.cyxtera.exceptions.OperationException;

import javax.management.OperationsException;

public class AppOperationFactory {

    private static final String PLUS_SYMBOL = "s";
    private static final String MINUS_SYMBOL = "m";
    private static final String BY_SYMBOL = "mu";
    private static final String DIVIDE_SYMBOL = "d";
    private static final String POW_SYMBOL = "p";

    public static AppOperation createOperation(String symbol, String id) throws OperationException {
        switch (symbol){
            case PLUS_SYMBOL:
                return new Addition(id);
            case MINUS_SYMBOL:
                return new Substraction(id);
            case BY_SYMBOL:
                return new Multiplication(id);
            case DIVIDE_SYMBOL:
                return new Division(id);
            case POW_SYMBOL:
                return new Pow(id);
            default:
                throw new OperationException("Operation symbol doesn't exist (use s,m,mu,d,p)");
        }
    }

}
