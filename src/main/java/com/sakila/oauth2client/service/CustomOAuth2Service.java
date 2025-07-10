package com.sakila.oauth2client.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.sakila.oauth2client.dto.CustomOAuth2User;

@Service
public class CustomOAuth2Service extends DefaultOAuth2UserService{
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// 인증서버 아이디값 디버깅
		//System.out.println(userRequest.getClientRegistration().getRegistrationId()); // naver
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		System.out.println(registrationId);
		
		// attributes 속성 디버깅
		OAuth2User oAuth2User = super.loadUser(userRequest);
		System.out.println(oAuth2User.getAttributes());
		
		String role;
		
		if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			role = "ROLE_NAVER";
		} else if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
			role = "ROLE_KAKAO";
		} else if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			role = "ROLE_GOOGLE";
		} else {
			System.out.println("인증서브 오류!");
			role = "ROLE_USER";
		}
		
		CustomOAuth2User customOAuth2User = new CustomOAuth2User(oAuth2User.getAttributes(), role, registrationId);
		return customOAuth2User;
	}
}