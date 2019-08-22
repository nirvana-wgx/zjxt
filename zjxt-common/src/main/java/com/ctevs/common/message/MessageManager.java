package com.ctevs.common.message;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MessageManager {
    private final Log log = LogFactory.getLog(getClass());
    private String basename;
    private static Properties props;

    public void setBasename(String basename) {
        this.basename = basename;
    }

    public void init() {
        try {
            if(!basename.endsWith(".properties")) 
                throw new IllegalArgumentException("仅支持properties！");
            InputStream is =MessageManager.class.getClassLoader().getResourceAsStream(basename);
            if(is==null) throw new NullPointerException("properties文件加载不正确！");
            props = getProperties(is);
        } catch (IllegalArgumentException e) {
            log.error("错误,clause={}", e.getCause());
        } catch (IOException e) {
            log.error("错误,clause={}", e.getCause());
        }

    }

    private static Properties getProperties(InputStream inputStream) throws IOException {
        Properties prop = new Properties();
        prop.load(inputStream);
        return prop;
    }

    public   String getMessage(String code) {
        return props.getProperty(code);
    }
    public   String get(String code) {
        return props.getProperty(code);
    }
}
