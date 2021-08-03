package com.card.shuttle.common.springsecurity;

import com.card.shuttle.common.dao.CmmnDao;
import com.card.shuttle.common.module.util.CmmnUtil;
import com.card.shuttle.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	CmmnDao cmmnDao;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String userId = authentication.getName();
        String userPw = authentication.getCredentials().toString();

        // 사용자 정보 가져오기
		UserVO userVO = new UserVO();
		try {
        	userVO = cmmnDao.selectOne("sellerweb.common.selectUserInfo", userId);
        } catch(RuntimeException ex) {
        	log.error(CmmnUtil.getExceptionMsg(ex));
        	throw new BadCredentialsException("로그 확인이 필요합니다.");
        }
        if(userVO == null) {
        	throw new BadCredentialsException("로그인 실패");
        }

        userVO.setInputPwdNo(userPw);
        
        // 사용자 role(auth) 가져오기
        // List<String> authList = cmmnDao.selectList("com.pentas.member.getUserAuth", userId);
        // System.out.println(authList);
        
        // Collection<SimpleGrantedAuthority> auths = new ArrayList<SimpleGrantedAuthority>();
        // for(String auth : authList) {
        // 	auths.add(new SimpleGrantedAuthority(auth));
        // }
        // userVO.setAuthorities(auths);

		return new UsernamePasswordAuthenticationToken(userVO, userPw);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
