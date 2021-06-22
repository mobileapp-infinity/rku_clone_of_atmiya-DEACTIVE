package com.infinity.infoway.rkuniversity.rku_old_app.CommonCls;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;


/*
* Created by remish varsani to upload document on 16-03-2020
* */


public class UploadDocumentUtility {


    public static void uploadDocument(Context context,String mimeType,String message,int requestCode){
        Intent intent = new Intent();
        intent.setType(mimeType);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        ((Activity)context).startActivityForResult(Intent.createChooser(intent, message), requestCode);
    }

}
