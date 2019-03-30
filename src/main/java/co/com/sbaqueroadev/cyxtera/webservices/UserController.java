package co.com.sbaqueroadev.cyxtera.webservices;

import co.com.sbaqueroadev.cyxtera.model.implementation.CalculationData;
import co.com.sbaqueroadev.cyxtera.model.implementation.SessionCalculationData;
import co.com.sbaqueroadev.cyxtera.services.CalculationService;
import co.com.sbaqueroadev.cyxtera.services.SessionCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;

@Controller
@Scope("session")
public class UserController {

	@Autowired
	SessionCalculationService sessionCalculationService;

	@Autowired
	CalculationService calculationService;

	@Autowired
	SessionCalculationData sessionData;

	@Autowired
	CalculationData calculationData;

	@RequestMapping(value = "/new",method = RequestMethod.GET)
	@ResponseBody
	public String newSession(){
		sessionData = new SessionCalculationData();
		sessionData.setId(RequestContextHolder.currentRequestAttributes().getSessionId());
		calculationData = new CalculationData();
		return sessionData.getId();
	}
}
