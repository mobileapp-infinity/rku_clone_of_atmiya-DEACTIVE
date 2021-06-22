package com.infinity.infoway.rkuniversity.faculty.faculty_news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.custom_class.TextViewRegularFont;
import com.infinity.infoway.rkuniversity.utils.CommonUtil;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class FacultyNewsSliderAdapter extends SliderViewAdapter<FacultyNewsSliderAdapter.FacultyNewsSliderViewHolder> {

    private Context context;
    private ArrayList<FacultyNewsSliderImageModel> facultyNewsSliderDataArrayList;
    private LayoutInflater layoutInflater;

    public FacultyNewsSliderAdapter(Context context, ArrayList<FacultyNewsSliderImageModel> facultyNewsSliderDataArrayList) {
        this.context = context;
        this.facultyNewsSliderDataArrayList = facultyNewsSliderDataArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public FacultyNewsSliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.inflater_faculty_news_slider, parent, false);
        return new FacultyNewsSliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FacultyNewsSliderViewHolder viewHolder, int position) {
        FacultyNewsSliderImageModel facultyNewsSliderImageModel = facultyNewsSliderDataArrayList.get(position);
        if (!CommonUtil.checkIsEmptyOrNullCommon(facultyNewsSliderImageModel.getNewsText())) {
            viewHolder.tv_auto_image_slider.setText(facultyNewsSliderImageModel.getNewsText());
        }

        Glide.with(context)
                .load(facultyNewsSliderImageModel.getImgUrl())
                .fitCenter()
                .placeholder(R.drawable.no_news)
                .error(R.drawable.no_news)
                .into(viewHolder.imgNews);

    }

    @Override
    public int getCount() {
        return facultyNewsSliderDataArrayList.size();
    }

    class FacultyNewsSliderViewHolder extends SliderViewAdapter.ViewHolder {

//        ImageView imageViewBackground;
//        ImageView imageGifContainer;
//        TextView textViewDescription;

        AppCompatImageView imgNews;
        TextViewRegularFont tv_auto_image_slider;

        public FacultyNewsSliderViewHolder(View itemView) {
            super(itemView);
            imgNews = itemView.findViewById(R.id.imgNews);
            tv_auto_image_slider = itemView.findViewById(R.id.tv_auto_image_slider);
//            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
        }
    }

}
