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
    public String shelterList(Model model, AdminMemberDto adminMemberDto, HttpSession session) {
        log.info("shelterList()");

        AdminMemberDto loginedAdminMemberDto = (AdminMemberDto) session.getAttribute("loginedAdminMemberDto");

        List<AdminShelterListInfoDto> adminShelterListInfoDtos = petsAdminService.searchShelterList(loginedAdminMemberDto);

        log.info("---------------------->{}", adminShelterListInfoDtos);

        model.addAttribute("adminShelterListInfoDtos", adminShelterListInfoDtos);

        String nextPage = "admin/pets/admin/admin_shelter_list";

        return nextPage;

    }

    //보호 동물 전체 리스트(보호소 리스트 상세 페이지) - SUPER
    @GetMapping("/pets_list")
    public String petsList(Model model, @RequestParam("s_no") String s_no, PetsAdminDto petsAdminDto) {
        log.info("petsList()");

        List<PetsAdminDto> petsAdminDtos = petsAdminService.searchPetsList(s_no);

        log.info("petsAdminDtos---------------->{}",petsAdminDtos);

        model.addAttribute("petsAdminDtos", petsAdminDtos);

        String nextPage = "admin/pets/admin/admin_pets_list";



        log.info("->>>>>>>>>>>>> " + petsAdminDtos.get(0).getAn_thumbnail());

        return nextPage;

    }

    ////보호 동물 전체 리스트() - 일반 admin
    @GetMapping("/all_pets_list")
    public String allPetsList(Model model, HttpSession session) {
        log.info("allPetsList()");

        AdminMemberDto loginedAdminMemberDto = (AdminMemberDto) session.getAttribute("loginedAdminMemberDto");

        List<PetsAdminDto> petsAdminDtos = petsAdminService.searchAllPetsList(loginedAdminMemberDto);
        model.addAttribute("petsAdminDtos", petsAdminDtos);

        String nextPage = "admin/pets/admin/admin_pets_list";

        return nextPage;

    }

    //보호 동물 상세 페이지(보호 동물 전체 리스트 클릭시)
    @GetMapping("/pets_list_detail")
    public String petsListDetail(Model model, PetsAdminDto petsAdminDto, HttpSession session, @RequestParam("an_no") String an_no) {
        log.info("petsListDetail()");

        String nextPage = "admin/pets/admin/admin_pets_list_detail";

        petsAdminDto = petsAdminService.searchPetsListDetail(an_no);

        session.setAttribute("petsAdminDto", petsAdminDto);

        model.addAttribute("petsAdminDto", petsAdminDto);
        log.info("petsAdminDto-------->", petsAdminDto);

        log.info("----------->{}", petsAdminDto.getAn_image());

        return nextPage;

    }


    // 보호 동물 등록 페이지()
    @RequestMapping("/admin_regist_pets_form")
    public String createRegistPetsForm(HttpSession session) {
        log.info("createRegistPetsForm()");

        AdminMemberDto loginedAdminMemberDto = (AdminMemberDto) session.getAttribute("loginedAdminMemberDto");

        String nextPage = "redirect:/admin/member/member_login_form";

        if(loginedAdminMemberDto != null) {

            nextPage = "admin/pets/admin/admin_regist_pets_form";
        }

        return nextPage;

    }

    // 보호 동물 등록 성공 or 실패
    @RequestMapping("/admin_regist_pets_confirm")
    public String petsRegistConfirm(PetsApiDto petsApiDto, @RequestParam("file") MultipartFile file,
                                    @RequestParam("an_no") String an_no){
        log.info("createRegistConfirm()");

        // 파일을 바로 쓸수는 없고 객체로 만들어서 써야한다.
        // SAVE FILE (바이너리 파일을 서버로 저장하는 방법)
        String saveFileName = uploadFileService.upload(file);
        petsApiDto.setAn_no(an_no);
        String nextPage = "admin/pets/admin/admin_regist_pets_success";

        if(saveFileName != null){
            petsApiDto.setAn_thumbnail(saveFileName);
            petsApiDto.setAn_image(saveFileName);

            int result = petsAdminService.petsRegistConfirm(petsApiDto);

            if(result <= 0){
                nextPage = "admin/pets/admin/admin_regist_pets_fail";
            }

        }

        return nextPage;
    }


    }

    //보호동물 삭제
 /*   @GetMapping("/pet_delete_confirm")
    @ResponseBody
        public Object petDeleteConfirm(PetsAdminDto petsAdminDto) {
            log.info("petDeleteConfirm()");

            Map<String, Object> map = petsAdminService.petsDeleteConfirm(Integer.parseInt(petsAdminDto.getAn_no()));

            log.info("an_no---=============>{}", petsAdminDto.getAn_no());

            return map;
        }*/

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

    }




