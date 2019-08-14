package com.ctevs.common;

import java.util.UUID;

public class UUIDUtils {
	 /**  
     * 自动生成32位的UUid（无-）  如：eb74a242074d41778112202e48d81845
     * @return  
     */  
    public static String getUUID() {  
        return UUID.randomUUID().toString().replace("-", "");  
    } 
}
