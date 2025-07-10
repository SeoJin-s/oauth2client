package com.sakila.oauth2client.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2User implements OAuth2User{ // OAuth2UserService 반환할 DTO

	private Map<String, Object> attributes; // service attributes값을 주입(setter, constructor, ...)
	private String role;
	private String registrationId;
	
	public CustomOAuth2User(Map<String, Object> attributes, String role, String registrationId) {
		this.attributes = attributes;
		this.role = role;
		this.registrationId = registrationId;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
		//roleList.add(() -> this.role);
		roleList.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return CustomOAuth2User.this.role;
			}
		});
		
		return roleList;
	}

	@Override
	public String getName() {
	    if (registrationId.equals("naver")) {
	        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
	        return response.get("id").toString(); // 네이버: response.id
	    } else if (registrationId.equals("kakao")) {
	        return attributes.get("id").toString(); // 카카오: id
	    } else if (registrationId.equals("google")) {
	        return attributes.get("sub").toString(); // 구글: sub
	    }
	    return "anonymous"; // 
	}

}
