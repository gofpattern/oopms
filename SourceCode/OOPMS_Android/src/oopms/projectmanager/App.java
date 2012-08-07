package oopms.projectmanager;

import java.util.ArrayList;

public class App {
    private static App _instance;
    private ArrayList<Project> listproject=new ArrayList<Project>();
    private App() {
    };
    public synchronized static App getInstance()
    {
        if(_instance==null)
        {
            _instance = new App();           
        }
        
        return _instance; 
    }
    public ArrayList<Project> getListproject() {
        return listproject;
    }
    public void setListproject(ArrayList<Project> listproject) {
        this.listproject = listproject;
    }
    
}
