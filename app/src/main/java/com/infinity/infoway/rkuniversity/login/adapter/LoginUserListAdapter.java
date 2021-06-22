package com.infinity.infoway.rkuniversity.login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.login.pojo.RegisterStudentDetailsModel;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.infinity.infoway.rkuniversity.utils.MySharedPreferences;

import java.util.ArrayList;

public class LoginUserListAdapter extends RecyclerView.Adapter<LoginUserListAdapter.MyViewHolder> {

    Context context;
    ArrayList<RegisterStudentDetailsModel> registerStudentDetailsModelArrayList;
    LayoutInflater layoutInflater;
    IOnLoggedInStudentItemClicked iOnLoggedInStudentItemClicked;
    MySharedPreferences mySharedPreferences;

    public LoginUserListAdapter(Context context, ArrayList<RegisterStudentDetailsModel> registerStudentDetailsModelArrayList) {
        this.context = context;
        this.registerStudentDetailsModelArrayList = registerStudentDetailsModelArrayList;
        layoutInflater = LayoutInflater.from(context);
        iOnLoggedInStudentItemClicked = (IOnLoggedInStudentItemClicked) context;
        mySharedPreferences = new MySharedPreferences(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.inflater_login_user_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (!CommonUtil.checkIsEmptyOrNullCommon(registerStudentDetailsModelArrayList.get(position).getStuEnrollmentNo()) &&
                !CommonUtil.checkIsEmptyOrNullCommon(registerStudentDetailsModelArrayList.get(position).getStuPassword())) {
            holder.tvLoggedInStudentName.setText(registerStudentDetailsModelArrayList.get(position).getStudentName());
        }
        holder.llLoggedInStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CommonUtil.checkIsEmptyOrNullCommon(registerStudentDetailsModelArrayList.get(position).getStuEnrollmentNo()) &&
                        !CommonUtil.checkIsEmptyOrNullCommon(registerStudentDetailsModelArrayList.get(position).getStuPassword())) {
                    iOnLoggedInStudentItemClicked.onStudentItemClick(registerStudentDetailsModelArrayList.get(position).getStudentName(),
                            registerStudentDetailsModelArrayList.get(position).getStuEnrollmentNo(),
                            registerStudentDetailsModelArrayList.get(position).getStuPassword());
                }
            }
        });

        holder.imgDeleteLoggedInStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CommonUtil.checkIsEmptyOrNullCommon(registerStudentDetailsModelArrayList.get(position).getStuEnrollmentNo()) &&
                        !CommonUtil.checkIsEmptyOrNullCommon(registerStudentDetailsModelArrayList.get(position).getStuPassword())) {
                    iOnLoggedInStudentItemClicked.onDeleteLoggedInStudentClick(registerStudentDetailsModelArrayList.get(position).getStudentName(),
                            registerStudentDetailsModelArrayList.get(position).getStuEnrollmentNo(),
                            registerStudentDetailsModelArrayList.get(position).getStuPassword());

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return registerStudentDetailsModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextViewRegularFont tvLoggedInStudentName;
        private LinearLayout llLoggedInStudent;
        private AppCompatImageView imgDeleteLoggedInStudent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLoggedInStudentName = itemView.findViewById(R.id.tvLoggedInStudentName);
            llLoggedInStudent = itemView.findViewById(R.id.llLoggedInStudent);
            imgDeleteLoggedInStudent = itemView.findViewById(R.id.imgDeleteLoggedInStudent);
        }
    }

    public interface IOnLoggedInStudentItemClicked {
        void onStudentItemClick(String studentName, String studentUserName, String studentPassword);

        void onDeleteLoggedInStudentClick(String studentName, String studentUserName, String studentPassword);
    }

}


//package com.infinity.infoway.rku.login.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.infinity.infoway.rku.R;
//import com.infinity.infoway.rku.custom_class.TextViewRegularFont;
//import com.infinity.infoway.rku.login.pojo.RegisterStudentDetailsModel;
//import com.infinity.infoway.rku.utils.CommonUtil;
//
//import java.util.ArrayList;
//
//public class LoginUserListAdapter extends RecyclerView.Adapter<LoginUserListAdapter.MyViewHolder> {
//
//    Context context;
//    ArrayList<RegisterStudentDetailsModel> registerStudentDetailsModelArrayList;
//    LayoutInflater layoutInflater;
//    IOnLoggedInStudentItemClicked iOnLoggedInStudentItemClicked;
//
//    public LoginUserListAdapter(Context context, ArrayList<RegisterStudentDetailsModel> registerStudentDetailsModelArrayList) {
//        this.context = context;
//        this.registerStudentDetailsModelArrayList = registerStudentDetailsModelArrayList;
//        layoutInflater = LayoutInflater.from(context);
//        iOnLoggedInStudentItemClicked = (IOnLoggedInStudentItemClicked) context;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        int layoutId;
//        switch (viewType) {
//            case 0:
//                layoutId = R.layout.test_left_right;
//                break;
//
//            case 1:
//                layoutId = R.layout.test_left;
//                break;
//
//            case 2:
//                layoutId = R.layout.test_right;
//                break;
//
//            default:
//                throw new IllegalArgumentException();
//        }
//        View view = layoutInflater.inflate(layoutId, parent, false);
//        return new MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
////        if (!CommonUtil.checkIsEmptyOrNullCommon(registerStudentDetailsModelArrayList.get(position).getStuEnrollmentNo()) &&
////                !CommonUtil.checkIsEmptyOrNullCommon(registerStudentDetailsModelArrayList.get(position).getStuPassword())) {
////            holder.tvLoggedInStudentName.setText(registerStudentDetailsModelArrayList.get(position).getStudentName());
////        }
////        holder.itemView.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if (!CommonUtil.checkIsEmptyOrNullCommon(registerStudentDetailsModelArrayList.get(position).getStuEnrollmentNo()) &&
////                        !CommonUtil.checkIsEmptyOrNullCommon(registerStudentDetailsModelArrayList.get(position).getStuPassword())) {
////                    iOnLoggedInStudentItemClicked.onStudentItemClick(registerStudentDetailsModelArrayList.get(position).getStudentName(),
////                            registerStudentDetailsModelArrayList.get(position).getStuEnrollmentNo(),
////                            registerStudentDetailsModelArrayList.get(position).getStuPassword());
////                }
////            }
////        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return registerStudentDetailsModelArrayList.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//
//        private TextViewRegularFont tvLoggedInStudentName;
//        public LinearLayout view_foreground;
////        LinearLayout view_background;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
////            view_background = itemView.findViewById(R.id.view_background);
////            view_foreground = itemView.findViewById(R.id.view_foreground);
////            tvLoggedInStudentName = itemView.findViewById(R.id.tvLoggedInStudentName);
//        }
//    }
//
//    public interface IOnLoggedInStudentItemClicked {
//        void onStudentItemClick(String studentName, String studentUserName, String studentPassword);
//    }
//
//}
