package com.infinity.infoway.rkuniversity.rku_old_app.Adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.AddMissPunchActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.MissPunchApproval;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.MyMissPunchActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.MySharedPreferecesRKUOLD;

public class MissPunchAdapter extends BaseAdapter
{
    MySharedPreferecesRKUOLD mySharedPreferecesRKUOLD;

    Context ctx;

    public MissPunchAdapter(Context ctx) {
        this.ctx = ctx;
        mySharedPreferecesRKUOLD = new MySharedPreferecesRKUOLD(ctx);


    }

    class ViewHolder {


        TextView tv;

    }

    ViewHolder viewHolder;

    @Override
    public int getCount() {

        String IS_Parent = mySharedPreferecesRKUOLD.getIsPatrent();
        // return college.getTable().size();

        if (IS_Parent.contentEquals("1"))
        {
            return 3;
        }
        else {
            return 2;
        }
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater mInflater = LayoutInflater.from(ctx);
        if (view == null) {
            view = mInflater.inflate(R.layout.adapter_view_miss_punch, viewGroup, false);

            viewHolder = new ViewHolder();
            initView(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        String IS_Parent = mySharedPreferecesRKUOLD.getIsPatrent();

        System.out.println("IS_Parent IN miss punch Adapter :::::: "+IS_Parent);
        if (IS_Parent.contentEquals("1"))
        {
            if (i == 0) {
                viewHolder.tv.setText("View \nMiss Punch");

            } else if (i == 1) {
                viewHolder.tv.setText("Add \nMiss Punch");

            } else if (i == 2) {
                viewHolder.tv.setText("Miss Punch \nApproval");

            }
        }

        else
        {
            if (i == 0) {
                viewHolder.tv.setText("View \nMiss Punch");

            } else if (i == 1) {
                viewHolder.tv.setText("Add \nMiss Punch");

            }
//            else if (i == 2) {
//                viewHolder.tv.setText("Miss Punch \nApproval");
//
//            }
        }

        viewHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
//                    viewHolder.tv.setBackground(ctx.getResources().getDrawable(R.drawable.border_signup));

                    Intent intent = new Intent(ctx, MyMissPunchActivity.class);
                    ctx.startActivity(intent);
                } else {
//                    viewHolder.tv.setBackground(ctx.getResources().getDrawable(R.drawable.view_leave_border));

                }
                if (i == 1) {
//                    viewHolder.tv.setBackground(ctx.getResources().getDrawable(R.drawable.border_signup));
                    Intent intent = new Intent(ctx, AddMissPunchActivity.class);
                    ctx.startActivity(intent);

                } else {
//                    viewHolder.tv.setBackground(ctx.getResources().getDrawable(R.drawable.view_leave_border));


                }
                if (i == 2) {
//                    viewHolder.tv.setBackground(ctx.getResources().getDrawable(R.drawable.border_signup));
                    Intent intent = new Intent(ctx, MissPunchApproval.class);
                    ctx.startActivity(intent);

                } else {
//                    viewHolder.tv.setBackground(ctx.getResources().getDrawable(R.drawable.view_leave_border));

                }

                if (i == 3) {
                    Intent intent =new Intent(ctx,MissPunchApproval.class);
                    ctx.startActivity(intent);
//                    viewHolder.tv.setBackground(ctx.getResources().getDrawable(R.drawable.border_signup));

                } else {
//                    viewHolder.tv.setBackground(ctx.getResources().getDrawable(R.drawable.view_leave_border));

                }
            }
        });
        return view;
    }

    private void initView(@NonNull final View itemView)
    {
        viewHolder.tv = (TextView) itemView.findViewById(R.id.tv);

    }
}
