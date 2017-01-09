package org.midagecrisis.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController{
	public static Map<String, String> user = new HashMap();
	{
		user.put("1", "{id:1, name:a}");
		user.put("2", "{id:2, name:b}");
		user.put("3", "{id:3, name:c}");
	}
	
	@RequestMapping(value="/findUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String findUser(
			@RequestParam String userId
			) throws Exception {
		return user.get(userId);
	}
}
