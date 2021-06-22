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
import com.infinity.infoway.rkuniversity.rku_old_app.models.PDApplicationModel;

import java.util.ArrayList;

public class DownloadDocumentListAdapterPDView extends RecyclerView.Adapter<DownloadDocumentListAdapterPDView.MyViewHolder>{

    Context context;
    ArrayList<PDApplicationModel> pdApplicationModelArrayList;
    LayoutInflater layoutInflater;
    DownloadDocumentListAdapterPDView.PDDownloadDocument pdDownloadDocument;

    public DownloadDocumentListAdapterPDView(Context context,ArrayList<PDApplicationModel> pdApplicationModelArrayList){
        this.context = context;
        this.pdApplicationModelArrayList = pdApplicationModelArrayList;
        layoutInflater = LayoutInflater.from(context);
        pdDownloadDocument = (PDDownloadDocument) context;
    }

    @NonNull
    @Override
    public DownloadDocumentListAdapterPDView.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.inflater_download_document_view_pd,viewGroup,false);
        return new DownloadDocumentListAdapterPDView.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DownloadDocumentListAdapterPDView.MyViewHolder myViewHolder, final int i) {

        myViewHolder.tvFileNameViewPD.setText(pdApplicationModelArrayList.get(i).getFileName());
        myViewHolder.iv_download_pd_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pdDownloadDocument.downloadPDDocument(pdApplicationModelArrayList.get(i).getFileName(),
                        pdApplicationModelArrayList.get(i).getFileURL());
            }
        });

        myViewHolder.iv_view_pd_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pdDownloadDocument.viewPDDocument(pdApplicationModelArrayList.get(i).getFileName(),
                        pdApplicationModelArrayList.get(i).getFileURL());
            }
        });

    }

    @Override
    public int getItemCount() {
        return pdApplicationModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

//        LinearLayout llDownloadDocumentViewPD;
        CustomTextView tvFileNameViewPD;
        ImageView iv_download_pd_doc,iv_view_pd_doc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

//            llDownloadDocumentViewPD = itemView.findViewById(R.id.llDownloadDocumentViewPD);
            tvFileNameViewPD = itemView.findViewById(R.id.tvFileNameViewPD);
            iv_download_pd_doc = itemView.findViewById(R.id.iv_download_pd_doc);
            iv_view_pd_doc = itemView.findViewById(R.id.iv_view_pd_doc);
        }
    }

    public interface PDDownloadDocument{
        void downloadPDDocument(String fileName,String fileURL);
        void viewPDDocument(String fileName,String fileURL);
    }

}
