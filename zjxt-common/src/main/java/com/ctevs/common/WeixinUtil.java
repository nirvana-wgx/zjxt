package com.ctevs.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctevs.common.vo.AccessToken;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;


public class WeixinUtil {
    private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);
    private static final String appId = "wxf404a3753d483c81";
    private static final String appSceret = "44daaeeb7da875e11eee7c053befa2b1";
    private static AccessToken accessToken = null;

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        } catch (ConnectException ce) {
            log.error("Weixin server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:{}", e);
        }
        return jsonObject;
    }

    // 获取access_token的接口地址（GET） 限200（次/天）
    // public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 获取access_token
     *
     * @param appid     凭证
     * @param appsecret 密钥
     * @return
     */
    public static AccessToken getAccessToken(String appid, String appsecret) {
        //  AccessToken accessToken = null;
        if (accessToken == null) {
            String requestUrl = WeChatURL.access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
            accessToken = new AccessToken();
            JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
            // 如果请求成功
            if (null != jsonObject) {
                try {
                    accessToken = new AccessToken();
                    accessToken.setToken(jsonObject.getString("access_token"));
                    accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
                    accessToken.setCreateTime(new Date().getTime());
                } catch (JSONException e) {
                    accessToken = null;
                    // 获取token失败
                    log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
                }
            }
        }else{
            if ((new Date().getTime()-accessToken.getCreateTime())>accessToken.getCreateTime()*1000){
                String requestUrl = WeChatURL.access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
                JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
                // 如果请求成功
                if (null != jsonObject) {
                    try {
                        accessToken = new AccessToken();
                        accessToken.setToken(jsonObject.getString("access_token"));
                        accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
                        accessToken.setCreateTime(new Date().getTime());
                    } catch (JSONException e) {
                        accessToken = null;
                        // 获取token失败
                        log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
                    }
                }
            }

        }




        return accessToken;
    }

    /**
     * 获取access_token
     *
     * @return
     */
    public static AccessToken getAccessToken() {
        //  AccessToken accessToken = null;
        if (accessToken == null) {
            String requestUrl = WeChatURL.access_token_url.replace("APPID", appId).replace("APPSECRET", appSceret);
            accessToken = new AccessToken();
            JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
            // 如果请求成功
            if (null != jsonObject) {
                try {
                    accessToken = new AccessToken();
                    accessToken.setToken(jsonObject.getString("access_token"));
                    accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
                    accessToken.setCreateTime(new Date().getTime());
                } catch (Exception e) {
                    accessToken = null;
                    // 获取token失败
                    log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
                }
            }
        }else{
            if ((new Date().getTime()-accessToken.getCreateTime())>accessToken.getCreateTime()*1000){
                String requestUrl = WeChatURL.access_token_url.replace("APPID", appId).replace("APPSECRET", appSceret);
                JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
                // 如果请求成功
                if (null != jsonObject) {
                    try {
                        accessToken = new AccessToken();
                        accessToken.setToken(jsonObject.getString("access_token"));
                        accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
                        accessToken.setCreateTime(new Date().getTime());
                    } catch (Exception e) {
                        accessToken = null;
                        // 获取token失败
                        log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
                    }
                }
            }

        }

        return accessToken;
    }

public static  void main(String args[]){
    AccessToken at = getAccessToken();
    System.out.println(at.getToken());

}

}
