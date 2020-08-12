package com.jetbet.auth.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jetbet.auth.bean.JwtTokenRequest;
import com.jetbet.auth.bean.ResponseBean;
import com.jetbet.auth.bean.UserBean;
import com.jetbet.auth.repository.UserRepository;
import com.jetbet.auth.util.ApplicationConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserAuthDao {
	
	@Autowired
	UserRepository userRepository;
	
	public ResponseBean authUser(JwtTokenRequest userRequest,String transactionId) {
		log.info("["+transactionId+"]************authUserDAO*************************");
		boolean isValid=true;
		
		String user=userRequest.getUsername().toUpperCase();
		String password=userRequest.getPassword();
		
		log.info("["+transactionId+"] UserName:  "+user);
		log.info("["+transactionId+"] Password:  "+password);
		
		ResponseBean responseBean = new ResponseBean();
		UserBean userBean=userRepository.findByUserIdAndPassword(user, password);
		if(userBean!=null) {
			if(userBean.getUserId().equalsIgnoreCase(user) && userBean.getPassword().equals(password)) {
				if(userBean.getIsActive().equalsIgnoreCase("N")) {
					isValid=false;
					responseBean.setStatus(ApplicationConstants.FAILED);
					responseBean.setErrorCode(ApplicationConstants.ERROR_CODE_003);
					responseBean.setErrorMsg(ApplicationConstants.ERROR_MSG_003);
				}
				if(userBean.getIsUserLock().equalsIgnoreCase("Y")){
					isValid=false;
					responseBean.setStatus(ApplicationConstants.FAILED);
					responseBean.setErrorCode(ApplicationConstants.ERROR_CODE_004);
					responseBean.setErrorMsg(ApplicationConstants.ERROR_MSG_004);
				}
				if(isValid) {
					responseBean.setStatus(ApplicationConstants.SUCCESS);
					responseBean.setErrorMsg("call Authorization API to fetch token");
				}
			}else {
				responseBean.setStatus(ApplicationConstants.FAILED);
				responseBean.setErrorCode(ApplicationConstants.ERROR_CODE_002);
				responseBean.setErrorMsg(ApplicationConstants.ERROR_MSG_002);
			}
		}else {
			responseBean.setStatus(ApplicationConstants.FAILED);
			responseBean.setErrorCode(ApplicationConstants.ERROR_CODE_001);
			responseBean.setErrorMsg(ApplicationConstants.ERROR_MSG_001);
		}
		log.info("responseBean:: "+responseBean);
		return responseBean;
	}


}
