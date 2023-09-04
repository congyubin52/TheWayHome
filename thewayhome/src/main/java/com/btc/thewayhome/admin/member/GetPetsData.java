package com.btc.thewayhome.admin.member;

import com.btc.thewayhome.admin.pets.PetsAdminController;
import com.btc.thewayhome.admin.pets.PetsAdminDto;
import com.btc.thewayhome.admin.pets.PetsAdminService;
import com.btc.thewayhome.admin.pets.PetsApiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetPetsData {

    @Autowired
    PetsAdminService petsAdminService;

    @Autowired
    AdminMemberService adminMemberService;

    public void getpets(){

        List<String> result = new ArrayList<>();
        String responseString = "";

        ShelterInfoDto shelterInfoDto = new ShelterInfoDto();
        PetsApiDto petsApiDto = new PetsApiDto();

        try {
            String apiUrl = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic?" +
                    "serviceKey=IyQg8I2dXbv8kkUs2Gki35cm64Cu%2BxaUWkNCsFipH3WWV6%2FiZD4HHrq4v%2Bykezvft92l9H5S0zULIYrQonfaUA%3D%3D" +
                    "&_type=json" +
                    "&pageNo=3" +
                    "&numOfRows=100";
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

//        adminMemberService.shelterRegistInfo(responseString, shelterInfoDto);

        petsAdminService.petsRegistInfo(responseString, petsApiDto);
    }
}
