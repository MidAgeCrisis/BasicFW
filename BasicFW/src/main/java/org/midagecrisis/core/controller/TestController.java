package org.midagecrisis.core.controller;

import org.midagecrisis.core.data.ResultType;
import org.midagecrisis.core.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/spring4", method = RequestMethod.GET)
public class TestController {
    @Autowired
    TestService testServiceImpl;

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @ResponseBody
    public ResultType test1(@RequestParam(value = "test", required = true) String test) {
        ResultType r = new ResultType();
        r.setSuccess("1");
        r.setMessage("Success");
        r.setData(testServiceImpl.test());
        return r;
    }
}