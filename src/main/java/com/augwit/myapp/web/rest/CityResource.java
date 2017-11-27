package com.augwit.myapp.web.rest;

import com.augwit.myapp.domain.City;
import com.augwit.myapp.service.CityService;
import com.augwit.myapp.web.rest.util.ExcelTranslateDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CityResource {

    private final CityService cityService;


    public CityResource(CityService cityService) {
        this.cityService = cityService;
    }

    @RequestMapping("/uploadExcel")
    @ResponseBody
    public String  upLoadExcel(MultipartFile file){

        ExcelTranslateDateUtil eTDUtil = new ExcelTranslateDateUtil();
        List<HSSFSheet>sheets = eTDUtil.getSheets(file);
        HSSFSheet sheet = sheets.get(0);

        Map<String,Integer> titles = eTDUtil.getFirstRowTitle(sheet);
        List<HSSFRow> rows = eTDUtil.getRows(sheet);
        int rowNum = rows.size();

        for(int i = 0; i < rowNum; i++){

            City city=new City();

            Integer colCityId = titles.get("城市id");
            Object valueCityId = eTDUtil.getCellValue(rows.get(i),colCityId);
            Double doubleCityId = (Double) valueCityId;
            city.setCityId(doubleCityId.longValue());

            Integer colCityName = titles.get("城市名称");
            Object valueCityName = eTDUtil.getCellValue(rows.get(i), colCityName);
            city.setCityName((String) valueCityName);

            Integer colCityArea = titles.get("城市面积");
            Object valueCityArea = eTDUtil.getCellValue(rows.get(i), colCityArea);
            city.setCityArea((Double) valueCityArea);

            Integer colProvince = titles.get("所属省份");
            Object valueProvince = eTDUtil.getCellValue(rows.get(i), colProvince);
            city.setProvince((String) valueProvince);

            Integer colPostalCode = titles.get("邮编");
            Object valuePostalCode = eTDUtil.getCellValue(rows.get(i), colPostalCode);
            city.setPostalCode((String) valuePostalCode);

            cityService.save(city);
        }
        return "Success Uploaded !";
    }

}
