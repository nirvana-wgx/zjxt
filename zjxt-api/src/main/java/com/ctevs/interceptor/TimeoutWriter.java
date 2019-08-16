package com.ctevs.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ctevs.common.Code;
import com.ctevs.common.Constants;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TimeoutWriter {

    private static Log LOG = LogFactory.getLog(TimeoutWriter.class);

    private static final String TIMEOUT_MSG = new StringBuilder()
            .append("{\"")
            .append(Constants.CODE)
            .append("\":")
            .append(Code.SESSION_TIME_OUT)
            .append(",\"")
            .append(Constants.MSG)
            .append("\":")
            .append("\"")
            .append(Constants.SESSION_TIME_OUT)
            .append("\"")
            .append("}").toString();

    protected void writeTimeOutMsg(HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        try {
            writer.write(TIMEOUT_MSG);
        } catch (Exception e) {
            LOG.error(e, e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
