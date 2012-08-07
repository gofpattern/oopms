package oopms.projectmanager;

import java.util.ArrayList;

import oopms.projectmanager.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private ArrayList<Project> projects;
    public HomeAdapter(Context context,ArrayList<Project> projects) {
        this.mInflater = LayoutInflater.from(context);
		this.projects=projects;
	}

	@Override
	public int getCount() {

	    int count = (this.projects == null) ? 0 : this.projects.size();
        return count;
	}

	@Override
	public Object getItem(int position) {

		return projects.get(position);
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder holder;

		if (convertView == null) {
			convertView = this.mInflater.inflate(R.layout.row, null);
			holder = new ViewHolder();
			holder.projectName=(TextView)convertView.findViewById(R.id.projectName);
			holder.projectTime = (ImageView) convertView.findViewById(R.id.projectTime);
			holder.projectCost=(ImageView)convertView.findViewById(R.id.projectCost);
			holder.projectResource=(ImageView)convertView.findViewById(R.id.projectResource);
			holder.projectProgress=(ImageView)convertView.findViewById(R.id.projectProgress);
			holder.projectEfficiency=(ImageView)convertView.findViewById(R.id.projectEfficiency);			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Project curProject=projects.get(position);
		holder.projectName.setText(curProject.getName());
		holder.projectTime.setImageResource(getID(curProject.getTimeStatus()));
		holder.projectCost.setImageResource(getID(curProject.getCostStatus()));
		holder.projectResource.setImageResource(getID(curProject.getResourcesStatus()));
		holder.projectProgress.setImageResource(getID(curProject.getProgressStatus()));
		holder.projectEfficiency.setImageResource(getID(curProject.getEfficiencyStatus()));
		
		return convertView;
	}
	public int getID(String id){
	    if (id.equals("R")){
	        return R.drawable.no;
	    }else if (id.equals("G")){
	        return R.drawable.yes;
	    }else if (id.equals("Y")){
	        return R.drawable.warning;
	    }
	    return R.drawable.yes;
	}

	public class ViewHolder {
		ImageView projectTime;
		ImageView projectCost;	
		ImageView projectResource;
		ImageView projectProgress;
		ImageView projectEfficiency;			
		TextView projectName;		
	}
}
