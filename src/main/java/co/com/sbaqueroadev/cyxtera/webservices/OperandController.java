package co.com.sbaqueroadev.cyxtera.webservices;

import co.com.sbaqueroadev.cyxtera.model.implementation.*;
import co.com.sbaqueroadev.cyxtera.services.CalculationService;
import co.com.sbaqueroadev.cyxtera.services.SessionCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OperandController {

    @Autowired
    SessionCalculationService sessionCalculationService;

    @Autowired
    CalculationService calculationService;

    @Autowired
    SessionCalculationData sessionData;

    @Autowired
    CalculationData calculationData;

    @RequestMapping(value = "/operands", method = RequestMethod.POST)
    @ResponseBody
    public String addOperands(@RequestBody List<Integer> numbers){
        calculationService.addNumbers(calculationData, numbers);
        return "OK";
    }

}
