package com.btc.thewayhome.config;

import com.btc.thewayhome.user.member.IUserMemberDaoMapper;
import com.btc.thewayhome.user.member.UserMemberDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    IUserMemberDaoMapper iUserMemberDaoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername()");

        log.info("userName: " + username);

        UserMemberDto userMemberDto = new UserMemberDto();
        //Service에서 Dto로 넘겨주니 타입 안 맞으니까 넣어서
        userMemberDto.setU_m_id(username);

        UserMemberDto selectedUserMemberDto = iUserMemberDaoMapper.selectUserForLogin(userMemberDto);

        return User.builder()
                .username(selectedUserMemberDto.getU_m_id())
                .password(selectedUserMemberDto.getU_m_pw())
                .roles("USER")  //권한 사용 하지 않아서 필요 없음
                .build();
    }
}
