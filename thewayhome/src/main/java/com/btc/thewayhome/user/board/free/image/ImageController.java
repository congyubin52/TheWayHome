package com.btc.thewayhome.user.board.free.image;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.util.UUID;

@Log4j2
@Controller
public class ImageController {

    private String uploadDir = "C:\\localImage\\";

    @PostMapping("/image/upload")
    @ResponseBody
    public String imageUpload(MultipartRequest request) throws Exception {
       log.info("imageUpload()");

        // request 인자에서 이미지 파일을 뽑아냄
        MultipartFile file = request.getFile("upload");

        // 뽑아낸 이미지 파일에서 이름 및 확장자 추출
        String fileOriName = file.getOriginalFilename();
        String fileExtension = fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length());

        // 이미지 파일 이름 유일성을 위해 uuid 생성
        UUID uuid = UUID.randomUUID();
        String uniqueName = uuid.toString().replace("-", "");

        File saveFile = new File(uploadDir + "\\" + uniqueName + fileExtension);
        file.transferTo(saveFile);

        return uniqueName + fileExtension;

    }

}
