package co.com.sbaqueroadev.cyxtera.webservices;

import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import co.com.sbaqueroadev.cyxtera.exceptions.OperationException;
import co.com.sbaqueroadev.cyxtera.model.implementation.CalculationData;
import co.com.sbaqueroadev.cyxtera.model.implementation.SessionCalculationData;
import co.com.sbaqueroadev.cyxtera.model.implementation.operation.AppOperationFactory;
import co.com.sbaqueroadev.cyxtera.services.CalculationService;
import co.com.sbaqueroadev.cyxtera.services.SessionCalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Arrays;

@Controller
@Scope("session")
public class OperationController {

    @Autowired
    SessionCalculationService sessionCalculationService;

    @Autowired
    CalculationService calculationService;

    @Autowired
    SessionCalculationData sessionData;

    @Autowired
    CalculationData calculationData;

    private static final Logger logger = LoggerFactory.getLogger(OperationController.class);

    @RequestMapping(value = "/new",method = RequestMethod.GET)
    @ResponseBody
    public String newSession(){
        sessionData = new SessionCalculationData();
        sessionData.setId(RequestContextHolder.currentRequestAttributes().getSessionId());
        calculationData = null;
        logger.info("New Session created for the ID: " + sessionData.getId());
        return sessionData.getId();
    }

    @RequestMapping(value = "/operands", method = RequestMethod.POST)
    @ResponseBody
    public String addOperands(@RequestBody Integer[] numbers){
        if(calculationData == null){
            calculationData = (CalculationData) new CalculationData();
            calculationData.setId(sessionData.getId()+"-"+( sessionData.getCalculations().size()+1));
            logger.info("New Calculation created : " + calculationData.getId());
            sessionData = (SessionCalculationData) sessionCalculationService.addCalculation(sessionData, calculationData);
            logger.info("Calculation "+ calculationData.getId() +" added to session " + sessionData.getId());

        }
        calculationData = (CalculationData) calculationService.addNumbers(calculationData, Arrays.asList(numbers));
        logger.info("Operands added to temporal calculation: " + calculationData.getId());
        return "OK";
    }

    @RequestMapping(value = "/operation/{symbol}", method = RequestMethod.POST)
    @ResponseBody
    public String addOperation(@PathVariable(value = "symbol", required = true) String operation) throws OperationException {
        if(calculationData == null){
            OperationException e = new OperationException("You need to add operands first");
            logger.error("Error adding operation: ",e);
            throw e;
        }
        calculationData = (CalculationData) calculationService.addOperation(calculationData, AppOperationFactory.createOperation(operation, calculationData.getId() + "op" ));
        logger.info("Operation " + calculationData.getAppOperation().getName() + " to calculation " + calculationData.getId());
        sessionData = (SessionCalculationData) sessionCalculationService.updateLastCalculation(sessionData, calculationData);
        logger.info("Calculation " + calculationData.getId() + " updated in session " + sessionData.getId());
        calculationService.update(calculationData);
        sessionCalculationService.update(sessionData);
        logger.info("Calculation " + calculationData.getId() + " and session " + sessionData.getId() + " persisted.");
        calculationData = null;
        return "OK";
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    @ResponseBody
    public SessionCalculationData calculate() throws OperationException, OperandsException {
        logger.info("Calculating result for session " + sessionData.getId());
        sessionData.setResult(sessionCalculationService.calculateResult(sessionData));
        return sessionData;
    }

}
