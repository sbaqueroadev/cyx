package co.com.sbaqueroadev.cyxtera.webservices;

import co.com.sbaqueroadev.cyxtera.exceptions.OperandsException;
import co.com.sbaqueroadev.cyxtera.exceptions.OperationException;
import co.com.sbaqueroadev.cyxtera.model.implementation.Calculation;
import co.com.sbaqueroadev.cyxtera.model.implementation.CalculationData;
import co.com.sbaqueroadev.cyxtera.model.implementation.SessionCalculationData;
import co.com.sbaqueroadev.cyxtera.model.implementation.operation.AppOperationFactory;
import co.com.sbaqueroadev.cyxtera.services.CalculationService;
import co.com.sbaqueroadev.cyxtera.services.SessionCalculationService;
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

    //@Autowired
    //private ApplicationUserServiceImpl applicationUserServiceImpl;


    @RequestMapping(value = "/operation/{symbol}", method = RequestMethod.POST)
    @ResponseBody
    public String addOperation(@PathVariable(value = "symbol", required = true) String operation) throws OperationException {
        if(calculationData == null){
            throw new OperationException("You need to add operands first");
        }
        System.out.println("Numbers:" + calculationData.getNumbers().size());
        //System.out.println("Operation:" + calculationData.getAppOperation()!=null?calculationData.getAppOperation().getClass().getName():"No Op");
        calculationData = (CalculationData) calculationService.addOperation(calculationData, AppOperationFactory.createOperation(operation, calculationData.getId() + "op" ));
        System.out.println("OPERATION:   " + calculationData.getAppOperation().getClass().getName());
        sessionData = (SessionCalculationData) sessionCalculationService.updateLastCalculation(sessionData, calculationData);
        calculationService.update(calculationData);
        sessionCalculationService.update(sessionData);
        calculationData = null;
        System.out.println("Numbers: null" );
        //System.out.println("Operation:" + calculationData.getAppOperation()!=null?calculationData.getAppOperation().getClass().getName():"No Op");
        return "OK";
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    @ResponseBody
    public SessionCalculationData calculate() throws OperationException, OperandsException {
        sessionData.setResult(sessionCalculationService.calculateResult(sessionData));
        return sessionData;
    }

    @RequestMapping(value = "/operands", method = RequestMethod.POST)
    @ResponseBody
    public String addOperands(@RequestBody Integer[] numbers){
        if(calculationData == null){
            calculationData = (CalculationData) new CalculationData();
            calculationData.setId(sessionData.getId()+"-"+( sessionData.getCalculations().size()+1));
            sessionData = (SessionCalculationData) sessionCalculationService.addCalculation(sessionData, calculationData);
            System.out.println("Numbers:" + calculationData.getNumbers().size());
            //System.out.println("Operation:" + calculationData.getAppOperation()!=null?calculationData.getAppOperation().getClass().getName():"No Op");
        }
        System.out.println("Numbers:" + calculationData.getNumbers().size());
        //System.out.println("Operation:" + calculationData.getAppOperation()!=null?calculationData.getAppOperation().getClass().getName():"No Op");
        calculationData = (CalculationData) calculationService.addNumbers(calculationData, Arrays.asList(numbers));
        System.out.println("Numbers:" + calculationData.getNumbers().size());
        // System.out.println("Operation:" + calculationData.getAppOperation()!=null?calculationData.getAppOperation().getClass().getName():"No Op");
        return "OK";
    }

    @RequestMapping(value = "/new",method = RequestMethod.GET)
    @ResponseBody
    public String newSession(){
        sessionData = new SessionCalculationData();
        sessionData.setId(RequestContextHolder.currentRequestAttributes().getSessionId());
        calculationData = null;
        return sessionData.getId();
    }

}
