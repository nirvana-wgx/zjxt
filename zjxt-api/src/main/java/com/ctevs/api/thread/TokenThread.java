package com.ctevs.api.thread;

 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ctevs.common.WeixinUtil;
import com.ctevs.common.vo.AccessToken;
 
/**
 * 定时获取微信access_token的线程
 *在WechatMpDemoApplication中注解@EnableScheduling，在程序启动时就开启定时任务。
 * 每7200秒执行一次
 */
@Component
public class TokenThread  {
    private static Logger log = LoggerFactory.getLogger(TokenThread.class);
 
    // 第三方用户唯一凭证
    public static String appid = "wx3a41c0eb2cbffadf";
    // 第三方用户唯一凭证密钥
 
    public static String appsecret = "87ae9d8125e22e8622834fedc2aa6ad3";
    // 第三方用户唯一凭证
    public static AccessToken accessToken = null;
 
//    @Scheduled(fixedDelay = 7200000)
    //7200秒执行一次
    public void gettoken(){
        accessToken=WeixinUtil.getAccessToken(appid,appsecret);
        if(null!=accessToken){
            log.info("获取成功，accessToken:"+accessToken.getToken());
        }else {
            log.error("获取token失败");
        }
    }
}