package com.infinity.infoway.rkuniversity.rku_old_app.CommonCls;

public class FileAndFolderInfo {


    public static final String FOLDER_NAME = "Rku";


    public static final String getCustomeFileName(String empCode,String academicContributionType,
                                                  String currentDate,String nameOfFile){
        return empCode+ "_" + academicContributionType + "_" + currentDate + "_" + nameOfFile;
    }


}
