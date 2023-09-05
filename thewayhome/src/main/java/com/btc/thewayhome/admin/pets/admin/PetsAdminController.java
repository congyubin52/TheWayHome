package com.btc.thewayhome.admin.pets.admin;


import com.btc.thewayhome.admin.member.AdminMemberDto;
import com.btc.thewayhome.user.board.config.UploadFileService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Log4j2
@RequestMapping("/admin/pets")
@Controller
public class PetsAdminController {

    @Autowired
    PetsAdminService petsAdminService;

    @Autowired
    UploadFileService uploadFileService;

    /*
     * 관리자(ADMIN)에게 보이는 페이지
     */
    //보호소 전체 리스트
    @GetMapping("/shelter_list")
    public String shelterList(Model model, HttpSession session) {
        log.info("shelterList()");

        String nextPage = "admin/pets/admin/admin_shelter_list";

        // 로그인된 관리자 계정으로 볼 수 있도록 하기 위해 session에 담아놨던 정보 가지고 옴 
        AdminMemberDto loginedAdminMemberDto = (AdminMemberDto) session.getAttribute("loginedAdminMemberDto");

        // 여러 개의 보호소들을 담아주기 위해 List 사용
        List<AdminShelterListInfoDto> adminShelterListInfoDtos = petsAdminService.searchShelterList(loginedAdminMemberDto);
        model.addAttribute("adminShelterListInfoDtos", adminShelterListInfoDtos);

        return nextPage;

    }

    //보호 동물 리스트 -> 보호소 리스트에서 보호소명을 클릭 시 나타나는 페이지
    @GetMapping("/pets_list")
    public String petsList(Model model, @RequestParam("s_no") String s_no) {
        log.info("petsList()");

        String nextPage = "admin/pets/admin/admin_pets_list";

        List<PetsAdminDto> petsAdminDtos = petsAdminService.searchPetsList(s_no);
        model.addAttribute("petsAdminDtos", petsAdminDtos);

        return nextPage;

    }

    //보호 동물 리스트 -> 메뉴바에서 보호동물 클릭 시 나타나는 페이지
    @GetMapping("/all_pets_list")
    public String allPetsList(Model model, HttpSession session) {
        log.info("allPetsList()");

        String nextPage = "admin/pets/admin/admin_pets_list";

        AdminMemberDto loginedAdminMemberDto = (AdminMemberDto) session.getAttribute("loginedAdminMemberDto");

        List<PetsAdminDto> petsAdminDtos = petsAdminService.searchAllPetsList(loginedAdminMemberDto);
        model.addAttribute("petsAdminDtos", petsAdminDtos);

        return nextPage;

    }

    //보호 동물 상세 페이지
    @GetMapping("/pets_list_detail")
    public String petsListDetail(Model model, PetsAdminDto petsAdminDto, HttpSession session, @RequestParam("an_no") String an_no) {
        log.info("petsListDetail()");

        String nextPage = "admin/pets/admin/admin_pets_list_detail";

        petsAdminDto = petsAdminService.searchPetsListDetail(an_no);

        session.setAttribute("petsAdminDto", petsAdminDto);
        model.addAttribute("petsAdminDto", petsAdminDto);

        return nextPage;

    }


    // 보호 동물 등록 페이지()
    @RequestMapping("/admin_regist_pets_form")
    public String createRegistPetsForm(HttpSession session) {
        log.info("createRegistPetsForm()");

        // loginedAdminMemberDto가 null인 경우, 로그인 페이지로 이동
        String nextPage = "redirect:/admin/member/member_login_form";

        // 보호 동물 등록 시 로그인 된 관리자만 사용하기 위함
        AdminMemberDto loginedAdminMemberDto = (AdminMemberDto) session.getAttribute("loginedAdminMemberDto");

        if (loginedAdminMemberDto != null) {
            nextPage = "admin/pets/admin/admin_regist_pets_form";

        }
        return nextPage;

    }

    // 보호 동물 등록 성공 or 실패
    @RequestMapping("/admin_regist_pets_confirm")
    public String petsRegistConfirm(PetsApiDto petsApiDto,
                                    @RequestParam("file") MultipartFile file,
                                    @RequestParam("an_no") String an_no) {
        log.info("createRegistConfirm()");

        String nextPage = "admin/pets/admin/admin_regist_pets_success";


        // 파일을 바로 쓸수는 없고 객체로 만들어서 써야한다.
        // SAVE FILE (바이너리 파일을 서버로 저장하는 방법)
        String saveFileName = uploadFileService.upload(file);

        petsApiDto.setAn_no(an_no);

        if (saveFileName != null) {
            petsApiDto.setAn_thumbnail(saveFileName);
            petsApiDto.setAn_image(saveFileName);

            int result = petsAdminService.petsRegistConfirm(petsApiDto);

            if (result <= 0) {
                nextPage = "admin/pets/admin/admin_regist_pets_fail";

            }

        }
        return nextPage;
    }


    //보호동물 삭제
    /*@GetMapping("/pet_delete_confirm")
    @ResponseBody
    public Object petDeleteConfirm(PetsAdminDto petsAdminDto) {
        log.info("petDeleteConfirm()");

        Map<String, Object> map = petsAdminService.petsDeleteConfirm(petsAdminDto.getAn_no());

        return map;
    }*/

    //회원 탈퇴
    @PostMapping("/pet_delete_confirm")
    public int petDeleteConfirm(@RequestParam("className") String className) {
        log.info("petDeleteConfirm()");

        int result = petsAdminService.petsDeleteConfirm(className);

        return result;

    }
}

    /*@GetMapping("/pet_delete_confirm")
    public String petDeleteConfirm(HttpSession session) {
        log.info("petDeleteConfirm()");

        String nextPage = "redirect:/admin/pets/admin/admin_pets_list_detail";

        PetsAdminDto petsAdminDto = (PetsAdminDto) session.getAttribute("petsAdminDto");

        log.info("petsAdminDto mo : " + petsAdminDto.getAn_no());

        int result = petsAdminService.petsDeleteConfirm(petsAdminDto);

        log.info("an_no----------------->{}", petsAdminDto.getAn_no());

        if (result > 0) {
            session.removeAttribute("petsAdminDto");

        } else {
//            nextPage = "admin/delete_fail";
        }

        return nextPage;

    }*/







