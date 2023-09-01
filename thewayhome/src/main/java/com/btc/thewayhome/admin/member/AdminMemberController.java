package com.btc.thewayhome.admin.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {

    @Autowired
    AdminMemberService adminMemberService;

    // 보호소 api db에 삽입
    @RequestMapping("/")
    public Object shelterRegistNum() {
        log.info("shelterRegist()");
//        StringBuilder result = new StringBuilder();
        while (true) {
            List<String> result = new ArrayList<>();
            String responseString = "";
            String orgCd = "";
            String uprCd = "";
            List<String> orgCdList = new ArrayList<>();
            ShelterNumDto shelterNumDto = new ShelterNumDto();
//        StringBuilder response = new StringBuilder();

            try {
                String apiUrl = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/sido?" +
                        "serviceKey=IyQg8I2dXbv8kkUs2Gki35cm64Cu%2BxaUWkNCsFipH3WWV6%2FiZD4HHrq4v%2Bykezvft92l9H5S0zULIYrQonfaUA%3D%3D" +
                        "&_type=json" +
                        "&pageNo=1" +
                        "&numOfRows=5";
                System.out.println(">>url: " + apiUrl);
                URL url = new URL(apiUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                BufferedReader br;

                br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                String returnLine;

                StringBuilder response = new StringBuilder();

                while ((returnLine = br.readLine()) != null) {
                    result.add(returnLine);
                    response.append(returnLine);
                }
                urlConnection.disconnect();

                responseString = response.toString();


                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObj = (JSONObject) jsonParser.parse(responseString);
                JSONObject parseResponse = (JSONObject) jsonObj.get("response");
                JSONObject parseBody = (JSONObject) parseResponse.get("body");
                JSONObject items = (JSONObject) parseBody.get("items"); // items is a JSONObject
                JSONArray array = (JSONArray) items.get("item");


                for (int i = 0; i < array.size(); i++) {
                    JSONObject jObj = (JSONObject) array.get(i);
                    orgCd = jObj.get("orgCd").toString();
                    System.out.println("orgCd : " + orgCd);
                    orgCdList.add(jObj.get("orgCd").toString());
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("---------> " + orgCdList);
            // result 리스트에 "orgCd" 값들이 저장되어 있음
//
//        log.info(result.size());

            StringBuilder sigungu = new StringBuilder();
            try {
                String apiUrl = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/sigungu?" +
                        "serviceKey=IyQg8I2dXbv8kkUs2Gki35cm64Cu%2BxaUWkNCsFipH3WWV6%2FiZD4HHrq4v%2Bykezvft92l9H5S0zULIYrQonfaUA%3D%3D" +
                        "&upr_cd=" + orgCd +
                        "&_type=json" +
                        "&pageNo=1" +
                        "&numOfRows=5";
                System.out.println(">>url: " + apiUrl);
                URL url = new URL(apiUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                BufferedReader br;

                br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                String returnLine;

                StringBuilder response = new StringBuilder();

                while ((returnLine = br.readLine()) != null) {
                    result.add(returnLine);
                    response.append(returnLine);
                }
                urlConnection.disconnect();


                responseString = response.toString();
                System.out.println("response : " + response);
                System.out.println("response : " + response);

                System.out.println("responseString : " + responseString);

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObj = (JSONObject) jsonParser.parse(responseString);
                JSONObject parseResponse = (JSONObject) jsonObj.get("response");
                JSONObject parseBody = (JSONObject) parseResponse.get("body");

                JSONObject items = (JSONObject) parseBody.get("items"); // items is a JSONObject

                System.out.println("items : " + items);

                JSONArray array = (JSONArray) items.get("item");

                for (int i = 0; i < array.size(); i++) {
                    JSONObject jObj = (JSONObject) array.get(i);
                    uprCd = jObj.get("uprCd").toString();
                    orgCd = jObj.get("orgCd").toString();
                    System.out.println("uprCd : " + uprCd);
                    System.out.println("orgCd : " + sigungu);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

//쉘터
            try {
                String apiUrl = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/shelter?" +
                        "serviceKey=IyQg8I2dXbv8kkUs2Gki35cm64Cu%2BxaUWkNCsFipH3WWV6%2FiZD4HHrq4v%2Bykezvft92l9H5S0zULIYrQonfaUA%3D%3D" +
                        "&upr_cd=" + uprCd +
                        "&org_cd=" + orgCd +
                        "&_type=json" +
                        "&pageNo=1" +
                        "&numOfRows=5";
                System.out.println(">>url: " + apiUrl);
                URL url = new URL(apiUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                BufferedReader br;

                br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                String returnLine;
                StringBuilder response = new StringBuilder();

                while ((returnLine = br.readLine()) != null) {
                    result.add(returnLine);
                    response.append(returnLine);
                }
                urlConnection.disconnect();
                responseString = response.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            adminMemberService.shelterRegistNum(responseString, shelterNumDto);

            ShelterInfoDto shelterInfoDto = new ShelterInfoDto();

            try {
                String apiUrl = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?" +
                        "serviceKey=IyQg8I2dXbv8kkUs2Gki35cm64Cu%2BxaUWkNCsFipH3WWV6%2FiZD4HHrq4v%2Bykezvft92l9H5S0zULIYrQonfaUA%3D%3D" +
                        "&_type=json" +
                        "&pageNo=1" +
                        "&numOfRows=1000";
//            System.out.println(">>url: " + apiUrl);
                URL url = new URL(apiUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                BufferedReader br;

                br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
                String returnLine;
                StringBuilder response = new StringBuilder();

                while ((returnLine = br.readLine()) != null) {
                    result.add(returnLine);
                    response.append(returnLine);
                }
                urlConnection.disconnect();
                responseString = response.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            adminMemberService.shelterRegistInfo(responseString, shelterInfoDto);


            return null;
        }
    }

    @GetMapping("/create_account_form")
    public String createAccountForm() {
        log.info("createAccountForm()");

        String nextPage = "admin/member/create_account_form";

//            Map<String, Object> msgMap = adminMemberService.ShelterList();
//            model.addAttribute("shelterNumDtos", msgMap.get("shelterNumDtos"));
//            model.addAttribute("shleterInfoDtos", msgMap.get("shleterInfoDtos"));

        return nextPage;

    }

//        @PostMapping ("/create_account_confirm")
//        public String createAccountConfirm(AdminMemberDto adminMemberDto, Model model){
//            log.info("createAccountConfirm()");
//
////            List<AdminMemberDto> shelterNameJoinDtos = adminMemberService.ShelterNameJoin(adminMemberDto);
//
//
////            log.info("[AdminMemberController] shelterNameJoinDto" + shelterNameJoinDtos);
//
//            String nextPage = "/admin/member/create_account_success";
//
////            int result = adminMemberService.createAccountConfirm(adminMemberDto);
////            if(result <= 0){
////                nextPage = "/admin/member/create_account_fail";
////            }
//
//            return nextPage;
//        }

    // 회원가입할 때 DB에 보호소명으로 조인된 테이블 데이터를 비동기로 출력을 위한 것
//    @PostMapping("/searchShelterName")
//    @ResponseBody
//    public Object searchShelterName(@RequestBody Map<String, String> msgMap){
//        Map<String, Object> map = adminMemberService.searchShelterName(msgMap);
//        List<String>
//        return map;
//    }





    // 로그인
    @GetMapping("/member_login_form")
    public String memberLoginForm() {
        log.info("[AdminMemberController] memberLoginForm()");

        String nextPage = "admin/member/member_login_form";

        return nextPage;

    }

//    @PostMapping("/member_login_confirm")
//
//    public int memberLoginConfirm(AdminMemberDto adminMemberDto, HttpSession session) {
//        log.info("[AdminMemberController] memberLoginConfirm()");
//
//        // String nextPage = "redirect:/"; 쏴리
//
//        AdminMemberDto loginedAdminMemberDto = adminMemberService.loginConfirm(adminMemberDto);
//
//        if(loginedAdminMemberDto != null) {
//            session.setAttribute("loginedAdminMemberDto", loginedAdminMemberDto);
//            session.setMaxInactiveInterval(60 * 30);
//            return 1;
//        }
//            log.info("loginedAdminMemberDto" + loginedAdminMemberDto.getA_m_id());
//
//        return 0;
//
//    }

    @PostMapping("/member_login_confirm")
    @ResponseBody
    public Object memberLoginConfirm(@RequestBody Map<String, String> msgMap, HttpSession session, Model model) {
        log.info("[AdminMemberController] memberLoginConfirm()");

        Map<String, Object> map = adminMemberService.loginConfirm(msgMap);

        AdminMemberDto loginedAdminMemberDto = null;
        if (map != null) {
            loginedAdminMemberDto = (AdminMemberDto) map.get("adminMemberDto");
            session.setAttribute("loginedAdminMemberDto", loginedAdminMemberDto);
            session.setMaxInactiveInterval(60 * 30);
            return map;

        }

        model.addAttribute("loginedAdminMemberDto", loginedAdminMemberDto);

        return null;

    }

    //로그아웃
    @GetMapping("/member_logout_comfirm")
    public String memberLogoutConfirm(HttpSession session) {
        log.info("[AdminMemberController] memberLogoutConfirm()");

        String nextPage = "redirect:/admin";

        session.removeAttribute("loginedAdminMemberDto");


        return nextPage;

    }

    //회원정보 수정
    @GetMapping("/member_modify_form")
    public String memberModifyForm() {
        log.info("[AdminMemberController] memberModifyForm()");

        String nextPage = "admin/member/member_modify_form";

        return nextPage;

    }

    @PostMapping("/member_modify_confirm")
    @ResponseBody
    public AdminMemberDto memberModifyConfirm(@RequestBody Map<String, String> msgMap, HttpSession session) {
        log.info("[AdminMemberController] memberModifyConfirm()");

        //service에서 adminMemberDto로 받으므로 dto로 변환해서 들고 가야 함
        AdminMemberDto adminMemberDto = new AdminMemberDto();
        adminMemberDto.setA_m_no(Integer.parseInt(msgMap.get("a_m_no")));
        adminMemberDto.setA_m_id(msgMap.get("a_m_id"));
        adminMemberDto.setA_m_pw(msgMap.get("a_m_pw"));

        adminMemberDto = adminMemberService.memberModifyConfirm(adminMemberDto);

        if (adminMemberDto != null) {
            AdminMemberDto loginedAdminMemberDto = adminMemberDto;
            session.setAttribute("loginedAdminMemberDto", loginedAdminMemberDto);
            session.setMaxInactiveInterval(60 * 30);

            return adminMemberDto;

        }
        return null;
    }

  /* @PostMapping("/member_modify_confirm")
   @ResponseBody
   public Map<String, Object> memberModifyConfirm(@RequestParam(value = "a_m_pw", required = false) String a_m_pw, HttpSession session, AdminMemberDto adminMemberDto) {
       log.info("[AdminMemberController] memberModifyConfirm()");

       Map<String, Object> resultMap = adminMemberService.memberModifyConfirm(a_m_pw, adminMemberDto);

        if(loginedAdminMemberDto != null) {
            session.setAttribute("loginedAdminMemberDto", loginedAdminMemberDto);
            session.setMaxInactiveInterval(60*30);
        }
       return resultMap;

   }*/

   /* @PostMapping("/member_modify_confirm")
    public Object memberModifyConfirm(HttpSession session, AdminMemberDto adminMemberDto) {
        log.info("[AdminMemberController] memberModifyConfirm()");

        String nextPage = "admin/member/member_modify_form";

        AdminMemberDto loginedAdminMemberDto = adminMemberService.memberModifyConfirm(adminMemberDto);

        if(loginedAdminMemberDto != null) {

            session.setAttribute("loginedAdminMemberDto", loginedAdminMemberDto);
            session.setMaxInactiveInterval(60*30);
        }
        else {
            nextPage = "admin/member/member_modify_fail";

        }

        return nextPage;
    }*/

/*    @PostMapping("/member_modify_confirm")
    public void memberModifyConfirm(HttpSession session, AdminMemberDto adminMemberDto, HttpServletResponse response) throws IOException {
        log.info("[AdminMemberController] memberModifyConfirm()");

//        String nextPage = "admin/member/member_modify_form";

        AdminMemberDto loginedAdminMemberDto = adminMemberService.memberModifyConfirm(adminMemberDto);

        if (loginedAdminMemberDto != null) {
            session.setAttribute("loginedAdminMemberDto", loginedAdminMemberDto);
            session.setMaxInactiveInterval(60 * 30);

            // contentType을 먼저하지 않으면, 한글이 깨질 수 있음
            response.setContentType("text/html; charset=euc-kr");

            PrintWriter out = response.getWriter();
            // 성공 시 alert 메시지를 띄우고 페이지를 이동
                out.println("<script>alert('수정이 완료되었습니다.'); location.href = \"/admin/member/member_modify_form\";</script>");
                out.flush();
        } else {
            // 실패 시 alert 메시지를 띄우고 페이지를 이동
            PrintWriter out = response.getWriter();
            out.println("<script>alert(''); location.href = \"/admin/member/member_modify_form\";</script>");
            out.flush();
        }
    }*/


    //회원 탈퇴
    @GetMapping("/member_delete_confirm")
    @ResponseBody
    public Map<String, Object> memberDeleteConfirm(@RequestBody Map<String, String> msgMap, HttpSession session) {
        log.info("[AdminMemberController] memberDeleteConfirm()");

        Map<String, Object> map = adminMemberService.memberDeleteConfirm(Integer.parseInt(msgMap.get("a_m_no")));

        if ((int) map.get("result") > 0) {
            session.removeAttribute("loginedAdminMemberDto");

        }
        return map;
    }

    //관리자 정보 리스트
    @GetMapping("/search_admin_list")
    public String searchAdminList(Model model, HttpSession session) {
        log.info("[AdminMemberController] searchAdminList()");

        List<AdminMemberDto> adminMemberDtos = adminMemberService.searchAdminList();

        model.addAttribute("adminMemberDtos", adminMemberDtos);

        String nextPage = "/admin/member/search_admin_list";

        AdminMemberDto loginedAdminMemberDto = (AdminMemberDto) session.getAttribute("loginedAdminMemberDto");

        if (loginedAdminMemberDto == null) {
            return "redirect:/admin";

        }

        return nextPage;

    }

    //(로그인을 위한) 관리자 승인 처리
    @PostMapping("/member_approval_confirm")
    @ResponseBody
//    public String memberApprovalConfirm(Model model, @RequestParam("a_m_no") int a_m_no) {
    public Map<String, Object> memberApprovalConfirm(Model model, @RequestParam Map<String, String> msgMap) {
        log.info("[AdminMemberController] memberApprovalConfirm()");

        Map<String, Object> map = new HashMap<>();

        log.info(msgMap.get("a_m_no"));
        log.info(msgMap.get("a_m_id"));
        log.info(msgMap.get("s_no"));
        log.info(msgMap.get("s_name"));
        log.info(msgMap.get("s_address"));

//       String nextPage = "redirect:/admin/member/search_admin_list";
//       log.info("a_m_no--------->" + request.getParameter("a_m_no"));
//       adminMemberService.memberApprovalConfirm(Integer.parseInt(request.getParameter("a_m_no")));
//       int result = adminMemberService.memberApprovalConfirm(Integer.parseInt(msgMap.get("a_m_no")));
         List<AdminMemberDto> adminMemberDtos = adminMemberService.memberApprovalConfirm(msgMap.get("a_m_no"));

        /*if(result >= 0) {
            log.info("msgMap no: " + msgMap.get("a_m_id"))0909090;
            List<AdminMemberDto> adminMemberDtos = adminMemberService.searchAdminInfo(msgMap);

            log.info("adminMemberDtos", adminMemberDtos.get(0));
            map.put("adminMemberDtos", adminMemberDtos);
            return map;

        }
            return null;
    }*/
return null;
    }

}
