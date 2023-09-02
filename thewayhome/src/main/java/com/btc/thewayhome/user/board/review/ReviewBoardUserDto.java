package com.btc.thewayhome.user.board.review;

import lombok.Data;

@Data
public class ReviewBoardUserDto {

    private int b_no; //'게시물 번호'
    private String use_yn;  //'사용유무(Y: 사용, N: 비사용)' default 'Y'
    private String u_m_id; //'아이디' db에서 수정해야함
    private String b_category; //'카테고리(실종/제보, 입양후기)'
    private String b_image; //'이미지'
    private String b_title; //'제목'
    private String b_content; //'내용'
    private String b_tag; //'태그'
    private String b_reg_date; //'등록일'
    private String b_mod_date; //'수정일'

}
