package com.ctevs.service;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ctevs.common.DateUtils;
import com.ctevs.common.MD5Util;

@Service
public class TokenService {
//	@Autowired
	public String getToken(String userId) {
		String token = createToken(userId);

		return token;
	}
	
	private String createToken(String userId) {
		String source = userId + DateUtils.getDateYMD();
		return MD5Util.MD5Encode(source, "UTF-8");
	}

	public boolean isValidToken(String token, String userId) {
		if ( !StringUtils.isEmpty(token) && !StringUtils.isEmpty(userId)) {
			String source = userId + DateUtils.getDateYMD();
			if(MD5Util.MD5Encode(source, "UTF-8").equals(token)) {
				return true;
			}
			return false;
		}
		return false;
	}
}
