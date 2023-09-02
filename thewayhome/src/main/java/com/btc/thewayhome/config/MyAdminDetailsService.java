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
public class MyAdminDetailsService implements UserDetailsService {

    @Autowired
    IAdminMemberDaoMapper iAdminMemberDaoMapper;

    @Override
    public UserDetails loadUserByUsername(String adminname) throws UsernameNotFoundException {
        log.info("loadUserByUsername()");

        log.info("adminname: " + adminname);

        AdminMemberDto adminMemberDto = new AdminMemberDto();
        //Service에서 Dto로 넘겨주니 타입 안 맞으니까 넣어서
        adminMemberDto.setA_m_id(adminname);

        log.info("adminMemberDto.setA_m_id(adminname)------> {}", adminMemberDto.getA_m_id());


        AdminMemberDto selectedAdminMemberDto = iAdminMemberDaoMapper.selectAdminForLogin(adminMemberDto);
//        AdminMemberDto selectedAdminMemberDto = iAdminMemberDaoMapper.selectAdminForLogin(adminMemberDto.getA_m_id().toString());

//        if (selectedUserMemberDto == null) {
//            throw new UsernameNotFoundException("사용자 정보가 일치하지 않습니다.");
//
//        } else {
//            log.info("id: " + selectedUserMemberDto.getU_m_id());
//            log.info("pw: " + selectedUserMemberDto.getU_m_pw());
//
//        }

        return User.builder()
                .username(selectedAdminMemberDto.getA_m_id())
                .password(selectedAdminMemberDto.getA_m_pw())
                .roles("ADMIN")
                .build();
    }
}
