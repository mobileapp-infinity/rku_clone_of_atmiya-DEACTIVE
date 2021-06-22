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
import com.infinity.infoway.rkuniversity.rku_old_app.models.FDPDownloadDocumentModel;

import java.util.ArrayList;

public class FDPDownloadDocumentListAdapter extends RecyclerView.Adapter<FDPDownloadDocumentListAdapter.MyViewHolder>{

    Context context;
    ArrayList<FDPDownloadDocumentModel> fdpDownloadListDocumentModelArray;
    LayoutInflater layoutInflater;
    IFDPDownloadDocument ifdpDownloadDocument;

    public FDPDownloadDocumentListAdapter(Context context,ArrayList<FDPDownloadDocumentModel> fdpDownloadListDocumentModelArray){
        this.context = context;
        this.fdpDownloadListDocumentModelArray = fdpDownloadListDocumentModelArray;
        layoutInflater = LayoutInflater.from(context);
        ifdpDownloadDocument = (IFDPDownloadDocument) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.inflater_download_fdp_document_list,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        myViewHolder.tvFileNameFDPView.setText(fdpDownloadListDocumentModelArray.get(i).getFileName());
        myViewHolder.iv_download_fdp_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ifdpDownloadDocument.downloadFDPDocument(fdpDownloadListDocumentModelArray.get(i).getFileName(),
                        fdpDownloadListDocumentModelArray.get(i).getFileURL());
            }
        });

        myViewHolder.iv_view_fdp_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ifdpDownloadDocument.viewFDPDocument(fdpDownloadListDocumentModelArray.get(i).getFileName(),
                        fdpDownloadListDocumentModelArray.get(i).getFileURL());
            }
        });
    }

    @Override
    public int getItemCount() {
        return fdpDownloadListDocumentModelArray.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

//        LinearLayout llDownloadFDPDocumentFDPView;
        CustomTextView tvFileNameFDPView;
        ImageView iv_download_fdp_pdf,iv_view_fdp_pdf;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

//            llDownloadFDPDocumentFDPView = itemView.findViewById(R.id.llDownloadFDPDocumentFDPView);
            tvFileNameFDPView = itemView.findViewById(R.id.tvFileNameFDPView);
            iv_download_fdp_pdf = itemView.findViewById(R.id.iv_download_fdp_pdf);
            iv_view_fdp_pdf = itemView.findViewById(R.id.iv_view_fdp_pdf);
        }
    }

    public interface IFDPDownloadDocument{
        void downloadFDPDocument(String fileName,String fileURL);
        void viewFDPDocument(String fileName,String fileURL);
    }

}
