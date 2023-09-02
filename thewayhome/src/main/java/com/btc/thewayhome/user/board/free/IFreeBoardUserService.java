package com.btc.thewayhome.user.board.free;

import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;

public interface IFreeBoardUserService {
    public int freeBoardWriteConfirm(String u_m_id, String b_imgage, FreeBoardUserDto freeBoardUserDto);
}
