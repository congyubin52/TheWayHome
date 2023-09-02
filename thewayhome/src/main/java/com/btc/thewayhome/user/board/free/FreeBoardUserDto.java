package com.btc.thewayhome.user.board.free;

import lombok.Data;

@Data
public class FreeBoardUserDto {

    private int b_no;
    private String use_yn;
    private String u_m_id;
    private String b_category;
    private String b_image;
    private String b_title;
    private String b_content;
    private String b_tag;
    private String b_reg_date;
    private String b_mod_date;

}
