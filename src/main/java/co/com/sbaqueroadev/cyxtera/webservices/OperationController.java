package co.com.sbaqueroadev.cyxtera.webservices;

import co.com.sbaqueroadev.cyxtera.dao.SessionCalculationRepository;
import co.com.sbaqueroadev.cyxtera.dto.MeasureAllDay;
import co.com.sbaqueroadev.cyxtera.dto.MeasureAverageDay;
import co.com.sbaqueroadev.cyxtera.exceptions.OperationException;
import co.com.sbaqueroadev.cyxtera.model.implementation.*;
import co.com.sbaqueroadev.cyxtera.model.implementation.operation.AppOperation;
import co.com.sbaqueroadev.cyxtera.model.implementation.operation.AppOperationFactory;
import co.com.sbaqueroadev.cyxtera.services.ApplicationUserServiceImpl;
import co.com.sbaqueroadev.cyxtera.services.CalculationService;
import co.com.sbaqueroadev.cyxtera.services.SessionCalculationService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        calculationData = (CalculationData) calculationService.addOperation(calculationData, AppOperationFactory.createOperation(operation));
        sessionData = (SessionCalculationData) sessionCalculationService.addCalculation(sessionData, calculationData);
        calculationService.update(calculationData);
        sessionCalculationService.update(sessionData);
        calculationData = (CalculationData) new Calculation();
        return "OK";
    }

}
