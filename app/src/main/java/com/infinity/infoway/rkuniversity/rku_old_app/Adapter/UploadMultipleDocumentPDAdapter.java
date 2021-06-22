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
import com.infinity.infoway.rkuniversity.rku_old_app.models.AddMultipleFileModelPD;

import java.util.ArrayList;

public class UploadMultipleDocumentPDAdapter extends RecyclerView.Adapter<UploadMultipleDocumentPDAdapter.MyViewHolder>{

    Context context;
    ArrayList<AddMultipleFileModelPD> addMultipleFileModelPDPArrayList;
    LayoutInflater layoutInflater;
    UploadMultipleDocumentPDAdapter.AddDeletePDFile addFile;

    public UploadMultipleDocumentPDAdapter(Context context,ArrayList<AddMultipleFileModelPD> addMultipleFileModelPDPArrayList){
        this.context = context;
        this.addMultipleFileModelPDPArrayList = addMultipleFileModelPDPArrayList;
        layoutInflater = LayoutInflater.from(context);
        addFile = (UploadMultipleDocumentPDAdapter.AddDeletePDFile) context;
    }


    @Override
    public UploadMultipleDocumentPDAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.inflater_upload_multiple_document_pd,viewGroup,false);
        return new UploadMultipleDocumentPDAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UploadMultipleDocumentPDAdapter.MyViewHolder myViewHolder, final int i) {


        myViewHolder.tvFileNameFDPRv.setText(addMultipleFileModelPDPArrayList.get(i).getFileName());

        myViewHolder.tvChooseFileFDPRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFile.selectFilePD(addMultipleFileModelPDPArrayList,i);
            }
        });

        myViewHolder.imgRemoveFileFDPRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addMultipleFileModelPDPArrayList.size() > 1){
                    addFile.removeFilePD(addMultipleFileModelPDPArrayList,i);
                }
            }
        });

        myViewHolder.imgAddMoreFileFDPRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFile.addFilePD(addMultipleFileModelPDPArrayList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return addMultipleFileModelPDPArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        CustomTextView tvChooseFileFDPRv,tvFileNameFDPRv;
        ImageView imgAddMoreFileFDPRv,imgRemoveFileFDPRv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvChooseFileFDPRv = itemView.findViewById(R.id.tvChooseFileFDPRv);
            tvFileNameFDPRv = itemView.findViewById(R.id.tvFileNameFDPRv);

            imgAddMoreFileFDPRv = itemView.findViewById(R.id.imgAddMoreFileFDPRv);
            imgRemoveFileFDPRv = itemView.findViewById(R.id.imgRemoveFileFDPRv);

        }
    }


    public interface AddDeletePDFile{
        void addFilePD(ArrayList<AddMultipleFileModelPD> addMultipleFileModelPDArrayList);
        void removeFilePD(ArrayList<AddMultipleFileModelPD> addMultipleFileModelPDArrayList,int position);
        void selectFilePD(ArrayList<AddMultipleFileModelPD> addMultipleFileModelPDArrayList,int position);
    }

}

