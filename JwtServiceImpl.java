package com.boe.demo.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.boe.demo.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService {
	// generatekey
	// getRequestMapByKey
	// CreateJwtString
	// isOk
   // application之间使用Token通信(标记)
	private static final String SALT ="luvooSecret";
	private  String key ="aaabcd";
	private byte[]  generateKey() {
		byte[] key = null;
		try {
			key = SALT.getBytes("UTF-8");
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		
		return key;
	}
	public Map<String,Object> get(String key){
		HttpServletRequest request =
			(	(ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String jwt = null ; //request.getHeader(("Authorization"));
		// [ˌɔ:θərəˈzeɪʃn] n 授权 批准 证书
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie :cookies) { //遍历一遍
			if(cookie.getName().equals("token")) {
				jwt = cookie.getValue();
			}
		}
		Jws<Claims>claims = null;
		try {
			claims = Jwts.parser()
					.setSigningKey(SALT.getBytes("UTF-8"))
					.parseClaimsJws(jwt);
					
		}catch(Exception e)
		{
			 e.printStackTrace();
		}
		Map<String,Object>  map =  ///返回加密过的信息
				(LinkedHashMap<String,Object>)claims.getBody().get(key);
		return map;
	}
	
	public String create(Map<String,String> data, String subject) {
		String jwt = Jwts.builder()
					.setHeaderParam("typ", "JWT") ///键值对  
					.setHeaderParam("regDate", System.currentTimeMillis()) //键值对
					.setSubject(subject)//???
					.claim(this.key, data) //将收集的信息放到claim 什么是claim????
					.signWith(SignatureAlgorithm.HS256,  this.generateKey())  //HS256 加密方式
					.compact(); 
		return jwt;
//////////////////////////////// Header////////////////////
//		{
//			  "alg": "HS256",
//			  "typ": "JWT"
//		}
/////////////////////////// PayLoad Data////////////////////
//{
//"sub": "1234567890",
//  "name": "John Doe",
//  "iat": 1516239022  // 日期心事
//}
///////////////////VERIFY SIGNATURE//////////////////////////
//		HMACSHA256(
//				  base64UrlEncode(header) + "." +
//				  base64UrlEncode(payload),
//				  
//				  your-256-bit-secret
//
//				) secret base64 encoded
	}

	public boolean isUsable(String jwt) {
		// Check Token是否有效
		Jws<Claims> claims = Jwts.parser()
				.setSigningKey(this.generateKey())
				.parseClaimsJws(jwt);
		return true;
	}


}
