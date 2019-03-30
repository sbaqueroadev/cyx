package co.com.sbaqueroadev.cyxtera.webservices;

import co.com.sbaqueroadev.cyxtera.dao.*;
import co.com.sbaqueroadev.cyxtera.dto.MeasureDTO;
import co.com.sbaqueroadev.cyxtera.dto.PacientDTO;
import co.com.sbaqueroadev.cyxtera.exceptions.UsernameExistsException;
import co.com.sbaqueroadev.cyxtera.model.implementation.*;
import co.com.sbaqueroadev.cyxtera.model.implementation.operation.AppOperation;
import co.com.sbaqueroadev.cyxtera.services.ApplicationUserServiceImpl;
import co.com.sbaqueroadev.cyxtera.services.CalculationService;
import co.com.sbaqueroadev.cyxtera.services.RoleServiceImpl;
import co.com.sbaqueroadev.cyxtera.services.SessionCalculationService;
import org.apache.commons.lang3.ArrayUtils;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.SimpleDateFormat;
import java.util.*;

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
