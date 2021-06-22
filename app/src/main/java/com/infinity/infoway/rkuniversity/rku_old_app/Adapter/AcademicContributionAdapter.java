package com.infinity.infoway.rkuniversity.rku_old_app.Adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.infinity.infoway.rkuniversity.R;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.BooksChapter;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ConferencePubActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.Consultancy;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.CpdApplication;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.FDPAttended;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.GrantReceived;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.JournalPub;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.PGScholars;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.PatentAwarded;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.PhDDScholars;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.PublicationInConferencesListActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqBookOrChapterListActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqCPDApplicationListActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqConsultancyRequestListActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqFDPAttendedListActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqGrantReceivedRequestListActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqPatentAwardedListActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqPgScholarGuidedListActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqPhdScholarGuidedListActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqPublicationInJournalsListActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.ReqSeedMoneyListActivity;
import com.infinity.infoway.rkuniversity.rku_old_app.Activity.SeedMoney;
import com.infinity.infoway.rkuniversity.rku_old_app.CommonCls.CustomTextView;
import com.infinity.infoway.rkuniversity.rku_old_app.models.AcademicContributionModel;

import java.util.ArrayList;

/*
* created by remish varsani on 16-03-2020
* 
*/

public class AcademicContributionAdapter extends RecyclerView.Adapter<AcademicContributionAdapter.MyViewHolder>{

    Context context;
    ArrayList<AcademicContributionModel> academicContributionArrayList;
    LayoutInflater inflater;

    public AcademicContributionAdapter(Context context,ArrayList<AcademicContributionModel> academicContributionArrayList){
        this.context = context;
        this.academicContributionArrayList = academicContributionArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.inflater_academic_contribution,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        if (academicContributionArrayList != null && academicContributionArrayList.size() > 0){
            myViewHolder.imgAcademicContributionIcone.setImageResource(academicContributionArrayList.get(position).getIcone());
            myViewHolder.tvTitleAcademicContributionIcone.setText(academicContributionArrayList.get(position).getAcademicContributionTitle());


            myViewHolder.llAcademicContributionMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("Publication in Conferences")){
                        Intent intent = new Intent(context, PublicationInConferencesListActivity.class);
//                        intent.putExtra(IntentConstants.IS_EDITABLE,true); commant by remish 05-04-2020
                        context.startActivity(intent);
                    } else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("Publication in Conferences Approval")){
                        //publication in conference approve
                        Intent intent = new Intent(context, ConferencePubActivity.class);
                        context.startActivity(intent);
                    }
                    else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("Publication in Journals")){
                        Intent intent = new Intent(context, ReqPublicationInJournalsListActivity.class);
                        context.startActivity(intent);
                    }
                    else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("Publication in Journals Approval")){
                        //publication in journal approve
                        Intent intent = new Intent(context, JournalPub.class);
                        context.startActivity(intent);
                    }
                    else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("Book/Chapter Publication")){
                        Intent intent = new Intent(context, ReqBookOrChapterListActivity.class);
                        context.startActivity(intent);
                    }
                    else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("Book/Chapter Publication Approval")){
                        //Book/Chapter publication approve
                        Intent intent = new Intent(context, BooksChapter.class);
                        context.startActivity(intent);
                    }
                    else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("Patent Awarded Request")){
                        Intent intent = new Intent(context, ReqPatentAwardedListActivity.class);
                        context.startActivity(intent);
                    }
                    else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("Patent Awarded Approval")){
                        //ptent awarded approve
                        Intent intent = new Intent(context, PatentAwarded.class);
                        context.startActivity(intent);
                    }
                    else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("PG Scholars Guided Request")){
                        Intent intent = new Intent(context, ReqPgScholarGuidedListActivity.class);
                        context.startActivity(intent);
                    }else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("PG Scholars Guided Approval")){
                        //PG Scholar Guided Approvel
                        Intent intent = new Intent(context, PGScholars.class);
                        context.startActivity(intent);
                    }
                    else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("PhD Scholars Guided Request")){
                        Intent intent = new Intent(context, ReqPhdScholarGuidedListActivity.class);
                        context.startActivity(intent);
                    }else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("PhD Scholars Guided Approval")){
                        //PHD Scholar Guided Approvel
                        Intent intent = new Intent(context, PhDDScholars.class);
                        context.startActivity(intent);
                    }
                    else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("Consultancy Request")){
                        Intent intent = new Intent(context, ReqConsultancyRequestListActivity.class);
                        context.startActivity(intent);
                    }
                    else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("Consultancy Approval")){
                        //Consultancy Approve
                        Intent intent = new Intent(context, Consultancy.class);
                        context.startActivity(intent);
                    }
                    else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("Grant Received Request")){
                        Intent intent = new Intent(context, ReqGrantReceivedRequestListActivity.class);
//                        intent.putExtra(IntentConstants.IS_EDITABLE,true); commant by remish varsani 05-04-2020
                        context.startActivity(intent);
                    }else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("Grant Received Approval")){
                        //Grant Received Approve
                        Intent intent = new Intent(context, GrantReceived.class);
                        context.startActivity(intent);
                    } else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("Seed Money Received From University Request")){
                        Intent intent = new Intent(context, ReqSeedMoneyListActivity.class);
                        context.startActivity(intent);
                    }else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("Seed Money Received From University Approval")){
                       //Seed money approval
                        Intent intent = new Intent(context, SeedMoney.class);
                        context.startActivity(intent);
                    } else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("FDP Attended Request")){
                        Intent intent = new Intent(context, ReqFDPAttendedListActivity.class);
                        context.startActivity(intent);
                    }else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("FDP Attended Approval")){
                        //FDP Attended Approval
                        Intent intent = new Intent(context, FDPAttended.class);
                        context.startActivity(intent);
                    }
                    else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("CPD Application Request")){
                        Intent intent = new Intent(context, ReqCPDApplicationListActivity.class);
                        context.startActivity(intent);
                    }else if (academicContributionArrayList.get(position).getAcademicContributionTitle().equalsIgnoreCase("CPD Application Approval")){
                        //CPD Application Approval
                        Intent intent = new Intent(context, CpdApplication.class);
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return academicContributionArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        LinearLayout llAcademicContributionMain;
        ImageView imgAcademicContributionIcone;
        CustomTextView tvTitleAcademicContributionIcone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgAcademicContributionIcone = itemView.findViewById(R.id.ivAcademicContributionInflater);
            tvTitleAcademicContributionIcone = itemView.findViewById(R.id.tvAcademicContributionInflater);
            llAcademicContributionMain = itemView.findViewById(R.id.llAcademicContributionMain);
        }
    }

}
