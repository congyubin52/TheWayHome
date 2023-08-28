package com.btc.thewayhome.admin.member;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AdminMemberDto {

    private int a_m_no;
    private String use_yn;
    private String a_m_id;
    private String a_m_pw;
    private int s_no;
    private String s_name;
    private String s_address;
    private int a_m_approval;
    private String a_m_reg_date;
    private String a_m_mod_date;

}
