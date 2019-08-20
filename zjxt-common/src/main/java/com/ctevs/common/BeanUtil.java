package com.ctevs.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;

import com.ctevs.common.query.QueryBean;
import com.ctevs.common.result.ResultPOListBean;

public class BeanUtil extends BeanUtils {
    private static final Log log = LogFactory.getLog("BeanUtil");

    public static void copyProperties(Object orig, Object dest) {
        BeanUtils.copyProperties(orig, dest);
    }
    
    public static void copyProperties2(QueryBean orig, ResultPOListBean<Po> dest) {
        dest.setTotalPages(orig.getTotalPages());
        dest.setTotalCount(orig.getTotalCount());
    }

    /*
     * 
     * 扩展
     * 
     * @author xiaohb@xcgoo.net
     * 
     * @param orig
     * 
     * @param dest
     * 
     * @param editable 拷贝属性
     */

    public static void copyProperties(Object orig, Object dest, Class<?> editable) {
        BeanUtils.copyProperties(orig, dest, editable);
    }

    /*
     * 
     * 扩展
     * 
     * @author xiaohb@xcgoo.net
     * 
     * @param orig
     * 
     * @param dest
     * 
     * @param editable 拷贝属性
     */

    public static void copyProperties(Object orig, Object dest, String... ignoreProperties) {
        BeanUtils.copyProperties(orig, dest, ignoreProperties);
    }

    public static ByteArrayOutputStream objToByte(Object obj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
        } catch (IOException e) {
            log.error(e);
        } finally {
            try {
              if(oos!=null)  oos.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return bos;
    }

    public static Object byteToObj(byte[] byt) {
        ObjectInputStream ois = null;
        Object obj = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(byt, 0, byt.length));
            obj = ois.readObject();
        } catch (IOException e) {
            log.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
        } finally {
            try {
              if(ois!=null)  ois.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return obj;
    }

    public static String getLogString(Object obj) {
        if (obj == null) {
            return "";
        }
        StringBuffer str = new StringBuffer();
        try {
            Method[] sourceMethods = obj.getClass().getMethods();
            for (int i = 0; i < sourceMethods.length; i++)
                if (sourceMethods[i].getName().startsWith("get")) {
                    String typeString = sourceMethods[i].getReturnType().getName();
                    String type = getReturnType(typeString);
                    String filed = sourceMethods[i].getName().substring(3);
                    if ((filed != null) && (filed.toLowerCase().equals("serialversionuid"))) {
                        continue;
                    }
                    String value = "";
                    Object object = sourceMethods[i].invoke(obj, (Object[]) null);
                    if (object != null) {
                        value = sourceMethods[i].invoke(obj, (Object[]) null).toString();
                    }
                    str.append(type + " " + filed + "=" + value);
                    str.append("\n");
                }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    private static String getReturnType(Object type) {
        String string = type.toString();
        int index = string.lastIndexOf(".") + 1;
        return string.substring(index);
    }
}
