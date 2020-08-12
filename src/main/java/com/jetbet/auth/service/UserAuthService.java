package com.jetbet.auth.service;

import org.springframework.stereotype.Service;

import com.jetbet.auth.bean.JwtTokenRequest;
import com.jetbet.auth.bean.ResponseBean;


@Service
public interface UserAuthService {

	ResponseBean authUser(JwtTokenRequest userRequest, String transactionId);
}
