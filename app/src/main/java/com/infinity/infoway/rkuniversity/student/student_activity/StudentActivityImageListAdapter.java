package com.infinity.infoway.rkuniversity.student.student_activity;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;

import java.util.ArrayList;

public class StudentActivityImageListAdapter extends RecyclerView.Adapter<StudentActivityImageListAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<StudentActivityPojo.ActivityFile> activityFileArrayList;
    Dialog dialog;

    public StudentActivityImageListAdapter(Context context, ArrayList<StudentActivityPojo.ActivityFile> activityFileArrayList) {
        this.context = context;
        this.activityFileArrayList = activityFileArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_student_activity_image_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (!CommonUtil.checkIsEmptyOrNullCommon(activityFileArrayList.get(position).getActivityFile())) {
            String imgUrl = activityFileArrayList.get(position).getActivityFile();

            Glide.with(context)
                    .load(imgUrl)
                    .placeholder(R.drawable.ic_no_data_found)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_no_data_found)
                    .fitCenter()
                    .into(holder.imgStudentActivityImage);

            holder.imgStudentActivityImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog = new Dialog(context);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.show();

                    dialog.setContentView(R.layout.action_popup);
                    AppCompatImageView imageView = dialog.findViewById(R.id.imgZoomable);

                    Glide.with(context)
                            .load(activityFileArrayList.get(position).getActivityFile())
                            .placeholder(R.drawable.ic_no_data_found)
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .error(R.drawable.ic_no_data_found)
                            .into(imageView);

//                    Glide.with(context).load(activityFileArrayList.get(position).getActivityFile()).fitCenter().error(R.drawable.ic_no_data_found).into(imageView);
                    //Log.d("final_img", string);
                    WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
                    lp.dimAmount = 0f;
                    dialog.getWindow().setAttributes(lp);
                    dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return activityFileArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView imgStudentActivityImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgStudentActivityImage = itemView.findViewById(R.id.imgStudentActivityImage);
        }
    }

}
