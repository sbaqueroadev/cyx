package co.com.sbaqueroadev.cyxtera.model.implementation.operation;

import co.com.sbaqueroadev.cyxtera.exceptions.OperationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.OperationsException;

public class AppOperationFactory {

    private static final Logger logger = LoggerFactory.getLogger(AppOperationFactory.class);

    private static final String PLUS_SYMBOL = "s";
    private static final String MINUS_SYMBOL = "m";
    private static final String BY_SYMBOL = "mu";
    private static final String DIVIDE_SYMBOL = "d";
    private static final String POW_SYMBOL = "p";

    public static AppOperation createOperation(String symbol, String id) throws OperationException {
        switch (symbol){
            case PLUS_SYMBOL:
                logger.debug("Creating an addition operation: ");
                return new Addition(id);
            case MINUS_SYMBOL:
                logger.debug("Creating a substraction operation: ");
                return new Substraction(id);
            case BY_SYMBOL:
                logger.debug("Creating a multiplication operation: ");
                return new Multiplication(id);
            case DIVIDE_SYMBOL:
                logger.debug("Creating a division operation: ");
                return new Division(id);
            case POW_SYMBOL:
                logger.debug("Creating a pow operation: ");
                return new Pow(id);
            default:
                OperationException e = new OperationException("Operation symbol doesn't exist (use s,m,mu,d,p)");
                logger.error("Error adding operation: ",e);
                throw e;
        }
    }

}
