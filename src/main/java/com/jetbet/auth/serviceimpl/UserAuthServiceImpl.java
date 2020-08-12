package com.jetbet.auth.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jetbet.auth.bean.JwtTokenRequest;
import com.jetbet.auth.bean.ResponseBean;
import com.jetbet.auth.dao.UserAuthDao;
import com.jetbet.auth.service.UserAuthService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserAuthServiceImpl implements UserAuthService{

	@Autowired
	UserAuthDao userAuthDao;
	
	@Override
	public ResponseBean authUser(JwtTokenRequest userRequest, String transactionId) {
		ResponseBean response=userAuthDao.authUser(userRequest,transactionId);
		return response;
	}
}
