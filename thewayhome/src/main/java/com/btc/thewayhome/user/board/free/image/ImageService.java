package com.btc.thewayhome.user.board.free.image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {



    public String imageUpload(MultipartRequest request) throws IOException {


//        // request 인자에서 이미지 파일을 뽑아냄
//        MultipartFile file = request.getFile("upload");
//
//        // 뽑아낸 이미지 파일에서 이름 및 확장자 추출
//        String fileOriName = file.getOriginalFilename();
//        String fileExtension = fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length());
//
//        // 이미지 파일 이름 유일성을 위해 uuid 생성
//        UUID uuid = UUID.randomUUID();
//        String uniqueName = uuid.toString().replace("-", "");
//
//        File saveFile = new File(uploadDir + "\\" + uniqueName + fileExtension);
//        file.transferTo(saveFile);

        return null;

    }

}
