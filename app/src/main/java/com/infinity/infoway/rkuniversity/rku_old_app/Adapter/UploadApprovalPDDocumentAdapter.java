package com.infinity.infoway.rkuniversity.rku_old_app.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.models.UploadApprovalPDDocumentModel;

import java.util.ArrayList;

public class UploadApprovalPDDocumentAdapter extends RecyclerView.Adapter<UploadApprovalPDDocumentAdapter.MyViewHolder>{


    Context context;
    ArrayList<UploadApprovalPDDocumentModel> uploadApprovalPDDocumentModelArrayList;
    LayoutInflater layoutInflater;
    UploadApprovalPDDocumentAdapter.AddDeletePDApprovalFile addDeletePDApprovalFile;


    public UploadApprovalPDDocumentAdapter(Context context,ArrayList<UploadApprovalPDDocumentModel> uploadApprovalPDDocumentModelArrayList){
        this.context = context;
        this.uploadApprovalPDDocumentModelArrayList = uploadApprovalPDDocumentModelArrayList;
        layoutInflater = LayoutInflater.from(context);
        addDeletePDApprovalFile = (AddDeletePDApprovalFile) context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.inflater_upload_approval_pd_document,viewGroup,false);
        return new UploadApprovalPDDocumentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.tvFileNameUploadApprovalPDDoc.setText(uploadApprovalPDDocumentModelArrayList.get(i).getFileName());

        myViewHolder.tvChooseFileUploadApprovalPDDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDeletePDApprovalFile.selectFilePD(uploadApprovalPDDocumentModelArrayList,i);
            }
        });

        myViewHolder.imgRemoveFileUploadApprovalPDDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uploadApprovalPDDocumentModelArrayList.size() > 1){
                    addDeletePDApprovalFile.removeFilePD(uploadApprovalPDDocumentModelArrayList,i);
                }
            }
        });

        myViewHolder.imgAddMoreFileUploadApprovalPDDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDeletePDApprovalFile.addFilePD(uploadApprovalPDDocumentModelArrayList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return uploadApprovalPDDocumentModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        CustomTextView tvChooseFileUploadApprovalPDDoc,tvFileNameUploadApprovalPDDoc;
        ImageView imgAddMoreFileUploadApprovalPDDoc,imgRemoveFileUploadApprovalPDDoc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvChooseFileUploadApprovalPDDoc = itemView.findViewById(R.id.tvChooseFileUploadApprovalPDDoc);
            tvFileNameUploadApprovalPDDoc = itemView.findViewById(R.id.tvFileNameUploadApprovalPDDoc);

            imgAddMoreFileUploadApprovalPDDoc = itemView.findViewById(R.id.imgAddMoreFileUploadApprovalPDDoc);
            imgRemoveFileUploadApprovalPDDoc = itemView.findViewById(R.id.imgRemoveFileUploadApprovalPDDoc);

        }
    }


    public interface AddDeletePDApprovalFile{
        void addFilePD(ArrayList<UploadApprovalPDDocumentModel> uploadApprovalPDDocumentModelArrayList);
        void removeFilePD(ArrayList<UploadApprovalPDDocumentModel> uploadApprovalPDDocumentModelArrayList,int position);
        void selectFilePD(ArrayList<UploadApprovalPDDocumentModel> uploadApprovalPDDocumentModelArrayList,int position);
    }

}
