package com.sinosoft.stukpisys.servsce.impl;

import com.sinosoft.stukpisys.servsce.FileService;
import com.sinosoft.stukpisys.untils.ExcelReader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class FileServiceImpl implements FileService{

    @Override
    public String readExcel(File excel, String type) {
        List<List<Object>> data = null;
        try {
            data = ExcelReader.get(excel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if("user".equals(type)) {
            return saveUser(data);
        }
        if("courses".equals(type)) {
            return saveCourses(data);
        }
        if("score".equals(type)) {
            return saveScore(data);
        }
        return null;
    }

    private String saveUser(List<List<Object>> excel){
        //todo
        return null;
    }

    private String saveCourses(List<List<Object>> excel){
        //todo
        return null;
    }

    private String saveScore(List<List<Object>> excel){
        //todo
        return null;
    }

    
}
