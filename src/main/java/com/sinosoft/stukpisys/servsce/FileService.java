package com.sinosoft.stukpisys.servsce;

import org.apache.ibatis.annotations.Select;

import java.io.File;

public interface FileService {
    String readExcel(File excel,String type);

}
