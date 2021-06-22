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
import com.infinity.infoway.rkuniversity.rku_old_app.models.AddMultipleFileModelFDP;

import java.util.ArrayList;

public class UploadMultipleFileAdapter extends RecyclerView.Adapter<UploadMultipleFileAdapter.MyViewHolder>{

    Context context;
    ArrayList<AddMultipleFileModelFDP> addMultipleFileModelFDPArrayList;
    LayoutInflater layoutInflater;
    AddFile addFile;

    public UploadMultipleFileAdapter(Context context,ArrayList<AddMultipleFileModelFDP> addMultipleFileModelFDPArrayList){
        this.context = context;
        this.addMultipleFileModelFDPArrayList = addMultipleFileModelFDPArrayList;
        layoutInflater = LayoutInflater.from(context);
        addFile = (AddFile) context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.inflater_multiple_file_adapter,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {


        myViewHolder.tvFileNameFDPRv.setText(addMultipleFileModelFDPArrayList.get(i).getFileName());

        myViewHolder.tvChooseFileFDPRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFile.selectFile(addMultipleFileModelFDPArrayList,i);
            }
        });

        myViewHolder.imgRemoveFileFDPRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addMultipleFileModelFDPArrayList.size() > 1){
                    addFile.removeFile(addMultipleFileModelFDPArrayList,i);
                }
            }
        });

        myViewHolder.imgAddMoreFileFDPRv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addFile.addFile(addMultipleFileModelFDPArrayList);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return addMultipleFileModelFDPArrayList.size();
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


    public interface AddFile{
        void addFile(ArrayList<AddMultipleFileModelFDP> addMultipleFileModelFDPArrayList);
        void removeFile(ArrayList<AddMultipleFileModelFDP> addMultipleFileModelFDPArrayList,int position);
        void selectFile(ArrayList<AddMultipleFileModelFDP> addMultipleFileModelFDPArrayList,int position);
    }

}
