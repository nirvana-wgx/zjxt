package com.ctevs.api.controller;

import static com.ctevs.common.Code.SERVER_ERR;
import static com.ctevs.common.Code.SUCCESS;
import static com.ctevs.common.Constants.CODE;
import static com.ctevs.common.Constants.MSG;
import static com.ctevs.common.Constants.MSG_SERVER_ERR;
import static com.ctevs.common.Constants.MSG_SUCCESS;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ctevs.api.exception.AppException;

/**
 * 所有controller的基类,成功处理,成功的code为1,异常统一处理
 */
public class ControllerSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerSupport.class);

    /**
     * 异常处理
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     * @param exp  异常
     * @return 异常结果code和msg
     * @throws Exception
     */
    @ExceptionHandler
    @ResponseBody
    public Map<String, Object> exp(HttpServletRequest req,
                                   HttpServletResponse resp, Exception exp) throws Exception {
//		LOG.error(exp, exp);
        LOGGER.error(exp.getMessage(), exp);
        Map<String, Object> map = new HashMap<String, Object>(2);
        if (exp instanceof AppException) {
            map.put(CODE, SERVER_ERR);
            map.put(MSG, exp.getMessage());
        } else {
            map.put(CODE, SERVER_ERR);
            map.put(MSG, MSG_SERVER_ERR);
            //add by xue temp
            map.put("ERROR", exp.getMessage());
        }

        return map;
    }

    /**
     * 处理成功的方法,返回map里加入成功的code和msg
     *
     * @param map 相应参数map
     */
    protected void success(Map<String, Object> map) {
        map.put(CODE, SUCCESS);
        map.put(MSG, MSG_SUCCESS);
    }

}
