package com.ctevs.common;

import java.util.Map;

import javax.xml.ws.BindingProvider;

public class WsUtils {
//	private static final String WS_USER_NAME = Constants.GH_USERNAME;
//	private static final String WS_USER_PS = Constants.GH_PASSWOED;

    /**
     * 添加登录信息
     *
     * @param client
     */
    public static <T> T inputUserInfo(T client, String userName, String passwd) {
        BindingProvider bp = (BindingProvider) client;
        Map<String, Object> context = bp.getRequestContext();
        context.put(BindingProvider.USERNAME_PROPERTY, userName);
        context.put(BindingProvider.PASSWORD_PROPERTY, passwd);
        return client;
    }

}
