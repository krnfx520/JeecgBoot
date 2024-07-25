package org.jeecg.modules.systemApiTest.web;

import org.jeecg.common.util.QueryCriteria;
import org.jeecg.modules.systemApiTest.service.SystemTestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/testSystem")
public class SystemTestController {
    @Autowired
    private SystemTestService systemTestService;

    @ResponseBody
    @RequestMapping(value = "/testNacos",method = { RequestMethod.POST, RequestMethod.GET })
    public Object testNacos(HttpServletRequest request) {
        QueryCriteria criteria = new QueryCriteria();
        Map<String, Object> query = WebUtils.getParametersStartingWith(request, "");
        criteria.getCondition().putAll(query);
        criteria.put("apiCondition",request.getParameter("apiCondition"));
        return systemTestService.testNacos();
    }


}
