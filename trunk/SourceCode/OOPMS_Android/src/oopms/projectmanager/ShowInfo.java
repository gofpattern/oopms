package oopms.projectmanager;

import java.util.ArrayList;

import oopms.projectmanager.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowInfo extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);
        App app=App.getInstance();
        Intent intent=getIntent();
        int Id=intent.getExtras().getInt("Id");
        ArrayList<Project> list=app.getListproject();
        Project project=list.get(Id);
        TextView projectName=(TextView)findViewById(R.id.projectName);
        
        TextView projectManager=(TextView)findViewById(R.id.projectManager);
        TextView projectCustomer=(TextView)findViewById(R.id.projectCustomer);
        
        TextView projectBudget=(TextView)findViewById(R.id.projectBudget);
        TextView projectStartDay=(TextView)findViewById(R.id.projectStartDay);
        TextView projectEndDay=(TextView)findViewById(R.id.projectEndDay);
        
        TextView projectCost=(TextView)findViewById(R.id.projectCost);
        TextView projectSchedule=(TextView)findViewById(R.id.projectSchedule);
        TextView projectResource=(TextView)findViewById(R.id.projectResource);

        TextView projectRisks=(TextView)findViewById(R.id.projectRisks);
        TextView projectIssues=(TextView)findViewById(R.id.projectIssues);
        TextView projectChanges=(TextView)findViewById(R.id.projectChanges);
        
        projectName.setText(project.getName());
        projectCustomer.setText(project.getCustomer());
        projectManager.setText(project.getManager());
        
        projectBudget.setText(project.getBudget());
        projectStartDay.setText(project.getStartDate());
        projectEndDay.setText(project.getEndDate());
        
        projectCost.setText(project.getCost());
        projectSchedule.setText(project.getSchedule());
        projectResource.setText(project.getResources());
        
        projectRisks.setText(project.getRisks());
        projectIssues.setText(project.getIssues());
        projectChanges.setText(project.getChanges());
        
    }
}
