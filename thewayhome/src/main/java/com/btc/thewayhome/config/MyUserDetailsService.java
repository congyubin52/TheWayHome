package com.btc.thewayhome.config;

import com.btc.thewayhome.admin.member.AdminMemberDto;
import com.btc.thewayhome.admin.member.IAdminMemberDaoMapper;
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

//    @Autowired
//    IAdminMemberDaoMapper iAdminMemberDaoMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername()");

        UserMemberDto userMemberDto = new UserMemberDto();
        //Service에서 Dto로 넘겨주니 타입 안 맞으니까 넣어서
        userMemberDto.setU_m_id(username);

        UserMemberDto selectedUserMemberDto = iUserMemberDaoMapper.selectUserForLogin(userMemberDto);
//        AdminMemberDto adminMemberDto = new AdminMemberDto();
//        //Service에서 Dto로 넘겨주니 타입 안 맞으니까 넣어서
//        adminMemberDto.setA_m_id(username);
//
//        AdminMemberDto selectedAdminMemberDto = iAdminMemberDaoMapper.selectAdminForLogin(adminMemberDto);

//        if (selectedUserMemberDto == null) {
//            throw new UsernameNotFoundException("사용자 정보가 일치하지 않습니다.");
//
//        } else {
//            log.info("id: " + selectedUserMemberDto.getU_m_id());
//            log.info("pw: " + selectedUserMemberDto.getU_m_pw());
//
//        }

        return User.builder()
                .username(selectedUserMemberDto.getU_m_id())
                .password(selectedUserMemberDto.getU_m_pw())
                .roles("USER")
                .build();
    }
}
