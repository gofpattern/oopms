package oopms.projectmanager;


import java.util.ArrayList;

import oopms.projectmanager.R;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView=(ListView)findViewById(R.id.list);
        ArrayList<Project> projects=new ArrayList<Project>();
        projects=getProject();
        listView.setAdapter(new HomeAdapter(MainActivity.this, projects));
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                Intent intent=new Intent(MainActivity.this, ShowInfo.class);
                intent.putExtra("Id", arg2);
                startActivity(intent);
            }
        });
    }
    public ArrayList<Project> getProject(){

              
        ArrayList<Project> list=new ArrayList<Project>();
        
        //----------------------------------------------------------
        Project project=new Project();
        project.setName("Dubai Transportation System");
        project.setCustomer("Nikholas");
        project.setManager("Ritesh");
        project.setBudget("$40000");
        project.setStartDate("20 August 2012");
        project.setEndDate("20 August 2013");
        project.setCost("10% over budget");
        project.setSchedule("20% behind schedule");
        project.setResources("40% under allocated");
        project.setIssues("3");
        project.setRisks("4");
        project.setChanges("3");
        
        project.setTimeStatus("G");
        project.setCostStatus("Y");
        project.setResourcesStatus("R");
        project.setProgressStatus("Y");
        project.setEfficiencyStatus("Y");
        
        list.add(project);
        //---------------------------------------------------------------
        Project project2=new Project();
        project2.setName("iMedical Dictionary");
        project2.setCustomer("Brian");
        project2.setManager("Duy");
        project2.setBudget("$50000");
        project2.setStartDate("20 August 2012");
        project2.setEndDate("20 August 2013");
        project2.setCost("10% over budget");
        project2.setSchedule("20% behind schedule");
        project2.setResources("80% under allocated");
        project2.setIssues("5");
        project2.setRisks("6");
        project2.setChanges("9");
        
        project2.setTimeStatus("G");
        project2.setCostStatus("Y");
        project2.setResourcesStatus("Y");
        project2.setProgressStatus("G");
        project2.setEfficiencyStatus("Y");
                      
        list.add(project2);
      //---------------------------------------------------------------
        Project project3 =new Project();
        project3.setName("Continuous Integration System");
        project3.setCustomer("Catherine");
        project3.setManager("Sunil");
        project3.setBudget("$60000");
        project3.setStartDate("20 August 2012");
        project3.setEndDate("20 August 2013");
        project3.setCost("20% over budget");
        project3.setSchedule("10% behind schedule");
        project3.setResources("90% under allocated");
        project3.setIssues("3");
        project3.setRisks("4");
        project3.setChanges("3");
        
        project3.setTimeStatus("Y");
        project3.setCostStatus("G");
        project3.setResourcesStatus("G");
        project3.setProgressStatus("G");
        project3.setEfficiencyStatus("G");
                      
        list.add(project3);
      //---------------------------------------------------------------
        Project project4 =new Project();
        project4.setName("Jasons Project");
        project4.setCustomer("Jason Wood");
        project4.setManager("Vincent");
        project4.setBudget("$70000");
        project4.setStartDate("20 August 2012");
        project4.setEndDate("20 August 2013");
        project4.setCost("25% over budget");
        project4.setSchedule("60% behind schedule");
        project4.setResources("35% under allocated");
        project4.setIssues("1");
        project4.setRisks("7");
        project4.setChanges("4");
        
        project4.setTimeStatus("Y");
        project4.setCostStatus("R");
        project4.setResourcesStatus("R");
        project4.setProgressStatus("Y");
        project4.setEfficiencyStatus("R");
                      
        list.add(project4);
      //---------------------------------------------------------------
        Project project5 =new Project();
        project5.setName("Football Manager System");
        project5.setCustomer("DuyND");
        project5.setManager("Francis");
        project5.setBudget("$180000");
        project5.setStartDate("20 August 2012");
        project5.setEndDate("20 August 2013");
        project5.setCost("50% over budget");
        project5.setSchedule("20% behind schedule");
        project5.setResources("70% under allocated");
        project5.setIssues("2");
        project5.setRisks("6");
        project5.setChanges("5");
        
        project5.setTimeStatus("R");
        project5.setCostStatus("Y");
        project5.setResourcesStatus("Y");
        project5.setProgressStatus("Y");
        project5.setEfficiencyStatus("Y");
                      
        list.add(project5);
      //---------------------------------------------------------------
        Project project6 =new Project();
        project6.setName("iCreative Ideas Project");
        project6.setCustomer("Ujjwal");
        project6.setManager("Praveen");
        project6.setBudget("$90000");
        project6.setStartDate("20 August 2012");
        project6.setEndDate("20 August 2013");
        project6.setCost("45% over budget");
        project6.setSchedule("13% behind schedule");
        project6.setResources("97% under allocated");
        project6.setIssues("8");
        project6.setRisks("3");
        project6.setChanges("7");
        
        project6.setTimeStatus("R");
        project6.setCostStatus("G");
        project6.setResourcesStatus("G");
        project6.setProgressStatus("G");
        project6.setEfficiencyStatus("Y");
                      
        list.add(project6);
        
        
        App app=App.getInstance();
        app.setListproject(list);
        return list;
    }
}
