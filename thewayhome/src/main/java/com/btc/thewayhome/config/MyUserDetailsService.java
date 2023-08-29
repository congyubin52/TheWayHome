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

        UserMemberDto userMemberDto = new UserMemberDto();
        userMemberDto.setU_m_id(username);

        UserMemberDto selectedUserMemberDto = iUserMemberDaoMapper.selectUserMemberForLogin(userMemberDto);
        return User.builder()
                .username(selectedUserMemberDto.getU_m_id())
                .password(selectedUserMemberDto.getU_m_pw())
                .roles("USER")
                .build();
    }
}
