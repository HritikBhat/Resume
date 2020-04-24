package com.hritik.resume.displays;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hritik.resume.MyHelper;
import com.hritik.resume.R;

public class Display extends AppCompatActivity {
    WebView myWebView;
    int edu_count=0,prg_count=0,ach_count=0,sk_count=0,exp_count=0,intr_count=0;
    private String web_Start(){
        String st_start="<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"sty.css\"/></head>";
        return st_start;
    }

    private String web_Personal(String name,String addr,String phone,String email,String dob){
        String ps="<body>\n" +
                "<h1 id=\"name\">"+name+"</h1>\n" +
                "<p id=\"addr\">"+addr+",</p>\n" +
                "<p id=\"phn\">"+phone+","+email+"</p>\n" +
                "<p id=\"dob\">DOB-"+dob+"</p>\n" +
                "<br>";

        return ps;
    }

    private String web_Objective(String obj){
        String ob="<p id=\"sub-header\">OBJECTIVE</p>\n" +
                "<p id=\"obj-txt\">"+obj+"</p>\n" +
                "<br>";
        return ob;
    }

    //End this with </ul> <br>
    private String web_Internship(String name,String city,String year,String desc){
        String intr;String hd="";
        if (intr_count==0){
            hd="<p id=\"sub-header\">INTERNSHIP</p>\n" +
                    "<ul id=\"point-header\">";
            intr_count+=1;
        }
        intr=hd+"<li><b>"+name+"</b>,"+city+",("+year+")<br><br>\n" +
                "\t<ul id=\"point-desc\">\n" +
                "  \t\t<li id=\"point-single\">"+desc+"<br>\n" +
                "  \t\t</li>\n" +
                "\t</ul>\n" +
                "  </li><br>";
        return intr;

    }

    //End this with </ul> <br>
    private String web_Experience(String name,String city,String post,String year,String desc){
        String ep;String hd="";
        if (exp_count==0){
            hd="<p id=\"sub-header\">PROFESSIONAL EXPERIENCE</p>\n" +
                    "<ul id=\"point-header\">";
            exp_count+=1;
        }
        ep=hd + "  <li><b>"+name+"</b>, "+city+"<br>\n" +
                "\t <b>"+post+" ("+year+")</b><br><br>\n" +
                "\t<ul id=\"point-desc\">\n" +
                "  \t\t<li id=\"point-single\">"+desc+"<br>\n" +
                "  \t\t</li>\n" +
                "\t</ul>\n" +
                "  </li>\n" +
                "  <br>";
        return ep;
    }

    //End this with </ul> <br>
    private String web_Education(String name,String city,String degree,String year,String type,String grade){
        String ed;String hd="";
        if (edu_count==0){
            hd="<p id=\"sub-header\">EDUCATION</p>\n" +
                    "<ul id=\"point-header\">";
            edu_count+=1;
        }
        if (type.equalsIgnoreCase("percentage")){
            ed=hd+"<li><b>"+name+"</b><br>\n" +
                    "\t<p id=\"obj-txt\" style=\"margin-left:0px;line-height:0.45;\">"+city+" - "+degree+". Date of Completion: "+year+"</p>\n" +
                    "\t<p id=\"obj-txt\" style=\"margin-left:0px;line-height:0.45;\">Percentage Secured: "+grade+"</p>\n" +
                    "  </li>";
        }
        else{
            ed=hd+"<li><b>"+name+"</b><br>\n" +
                    "\t<p id=\"obj-txt\" style=\"margin-left:0px;line-height:0.45;\">"+city+" - "+degree+". Date of Completion: "+year+"</p>\n" +
                    "\t<p id=\"obj-txt\" style=\"margin-left:0px;line-height:0.45;\">CGPA Secured: "+grade+"</p>\n" +
                    "  </li>";
        }
        return ed;
    }

    //End this with </ul> <br>
    private String web_Project(String name,String tech,String desc){
        String prg;String hd="";
        if (prg_count==0){
            hd="<p id=\"sub-header\">PROJECTS</p>\n" +
                    "<ul id=\"point-header\">\n";
            prg_count+=1;
        }

        prg=hd + "  <li><b>"+name+"</b><br>\n" +
                "\t<p id=\"obj-txt\" style=\"margin-left:0px;line-height:0.45;\">Technolgies Used: "+tech+"</p>\n" +
                "\t<p id=\"obj-txt\" style=\"margin-left:0px;line-height:0.45;\">"+desc+"</p>\n" +
                "  </li>";
        return prg;
    }

    //End this with </ul> <br>
    private String web_Achievement(String desc){
        String ah;String hd="";
        if (ach_count==0){
            hd="<p id=\"sub-header\">ACHIEVEMENTS</p>\n" +
                    "<ul id=\"point-header\" style=\"font-size:14px;\">\n";
            ach_count+=1;
        }
        ah= hd + "  <li id=\"point-single\">"+desc+"</li>";
        return ah;
    }

    //End this with </ul> <br>
    private String web_Skill(String desc){
        String sk;String hd="";
        if (sk_count==0){
            hd="<p id=\"sub-header\">SKILLS</p>\n" +
                    "<ul id=\"point-header\" style=\"font-size:14px;\">\n";
            sk_count+=1;
        }
        sk = hd + "  <li id=\"point-single\">"+desc+"</li>";
        return sk;
    }

    private String web_Declaration(String desc){
        String dc="<p id=\"sub-header\">DECLARATION</p>\n" +
                "<p id=\"obj-txt\">"+desc+"</p>\n" +
                "<br></body>\n" +
                "</html>";
        return dc;
    }

    private Cursor getTabledata(String table){
        MyHelper dpHelper = new MyHelper(getApplicationContext());
        SQLiteDatabase db = dpHelper.getReadableDatabase();
        Cursor cur = dpHelper.getAllData(table);
        return cur;
    }

    private String personal_Task(){
        String nm,ad,ph,em,db;
        Cursor cursor=getTabledata("psl");
        if (cursor.getCount() == 0) {
            return "";
        }
        cursor.moveToFirst();
        nm=cursor.getString(cursor.getColumnIndex("name"));
        ad=cursor.getString(cursor.getColumnIndex("addr"));
        ph=cursor.getString(cursor.getColumnIndex("phone"));
        em=cursor.getString(cursor.getColumnIndex("email"));
        db=cursor.getString(cursor.getColumnIndex("dob"));
        cursor.close();
        return web_Personal(nm,ad,ph,em,db);
    }
    private String objective_Task(){
        String desc;
        Cursor cursor=getTabledata("obj");
        if (cursor.getCount() == 0) {
            return "";
        }
        cursor.moveToFirst();
        desc=cursor.getString(cursor.getColumnIndex("obj_desc"));
        cursor.close();
        return web_Objective(desc);
    }
    private String declaration_Task(){
        String desc;
        Cursor cursor=getTabledata("dec");
        if (cursor.getCount() == 0) {
            return "";
        }
        cursor.moveToFirst();
        desc=cursor.getString(cursor.getColumnIndex("dec_desc"));
        cursor.close();
        return web_Declaration(desc);
    }
    private String achievement_Task() {
        String desc;
        String st = "";
        Cursor cursor = getTabledata("ach");
        if (cursor.getCount() == 0) {
            return "";
        }
        //cursor.moveToFirst();
        while (cursor.moveToNext()) {
            //System.out.println(cursor.getString(cursor.getColumnIndex("name")));
            desc = cursor.getString(cursor.getColumnIndex("ach_desc"));
            st += web_Achievement(desc);
        }
        cursor.close();
        st += "</ul> <br>";
        return st;
    }

    private String skill_Task(){
        String desc;String st="";
        Cursor cursor=getTabledata("sk");
        if (cursor.getCount() == 0) {
            return "";
        }
        //cursor.moveToFirst();
        while (cursor.moveToNext()) {
            //System.out.println(cursor.getString(cursor.getColumnIndex("name")));
            desc=cursor.getString(cursor.getColumnIndex("sk_desc"));
            st+=web_Skill(desc);
        }
        cursor.close();
        st+="</ul> <br>";
        return st;
    }

    private String project_Task(){
        String name,tech,desc;String st="";
        Cursor cursor=getTabledata("prg");
        if (cursor.getCount() == 0) {
            return "";
        }
        //cursor.moveToFirst();
        while (cursor.moveToNext()) {
            //System.out.println(cursor.getString(cursor.getColumnIndex("name")));
            name=cursor.getString(cursor.getColumnIndex("name"));
            tech=cursor.getString(cursor.getColumnIndex("tech"));
            desc=cursor.getString(cursor.getColumnIndex("prg_desc"));
            st+=web_Project(name,tech,desc);
        }
        cursor.close();
        st+="</ul> <br>";
        return st;
    }

    private String education_Task(){
        String name,city,deg,year,type1,ptcg;String st="";
        Cursor cursor=getTabledata("edu");
        if (cursor!=null) {
            //cursor.moveToFirst();
            if (cursor.getCount() == 0) {
                return "";
            }
                //System.out.println(cursor.getString(cursor.getColumnIndex("name")));
                name = cursor.getString(cursor.getColumnIndex("name"));
                city = cursor.getString(cursor.getColumnIndex("city"));
                deg = cursor.getString(cursor.getColumnIndex("degree"));
                year = cursor.getString(cursor.getColumnIndex("tfrom")) + " - " + cursor.getString(cursor.getColumnIndex("tto"));
                type1 = cursor.getString(cursor.getColumnIndex("type"));
                ptcg = cursor.getString(cursor.getColumnIndex("grade"));
                st += web_Education(name, city, deg, year, type1, ptcg);
            }
            cursor.close();
            st += "</ul> <br>";
            return st;
    }

    private String internship_Task(){
        String name,city,year,desc;String st="";
        Cursor cursor=getTabledata("intr");
        if (cursor.getCount() == 0) {
            return "";
        }
        //cursor.moveToFirst();
        while (cursor.moveToNext()) {
            //System.out.println(cursor.getString(cursor.getColumnIndex("name")));
            name=cursor.getString(cursor.getColumnIndex("name"));
            city=cursor.getString(cursor.getColumnIndex("city"));
            year=cursor.getString(cursor.getColumnIndex("tfrom"))+" - "+cursor.getString(cursor.getColumnIndex("tto"));
            desc=cursor.getString(cursor.getColumnIndex("intr_desc"));
            st+=web_Internship(name,city,year,desc);
        }
        cursor.close();
        st+="</ul>";
        return st;
    }

    private String experience_Task(){
        String name,city,post,year,desc;String st="";
        Cursor cursor=getTabledata("exp");
        if (cursor.getCount() == 0) {
            return "";
        }
            //if (cursor.getCount()<1){return "";}
            //cursor.moveToFirst();
            while (cursor.moveToNext()) {
                //System.out.println(cursor.getString(cursor.getColumnIndex("name")));
                name = cursor.getString(cursor.getColumnIndex("name"));
                city = cursor.getString(cursor.getColumnIndex("city"));
                post = cursor.getString(cursor.getColumnIndex("post"));
                year = cursor.getString(cursor.getColumnIndex("tfrom")) + " - " + cursor.getString(cursor.getColumnIndex("tto"));
                desc = cursor.getString(cursor.getColumnIndex("exp_desc"));
                st += web_Experience(name, city, post, year, desc);
            }
            cursor.close();
            st += "</ul>";
            return st;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setSupportZoom(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setDisplayZoomControls(false);
        myWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        myWebView.setScrollbarFadingEnabled(false);
        String st,pert,objt,expt,intt,edut,prgt,acht,skt,dect;
        st=web_Start();
        pert=personal_Task();
        objt=objective_Task();
        expt=experience_Task();
        intt=internship_Task();
        edut=education_Task();
        prgt=project_Task();
        acht=achievement_Task();
        skt=skill_Task();
        dect=declaration_Task();
        String htmldata=(pert+objt+expt+intt+prgt+skt+acht+edut+dect);
        htmldata=st+htmldata;
        myWebView.loadDataWithBaseURL("file:///android_asset/", htmldata, "text/html", "UTF-8", null);

    }
    //create a function to create the print job
    private void createWebPrintJob(WebView webView) {

        //create object of print manager in your device
        PrintManager printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
        //create object of print adapter
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();

        //provide name to your newly generated pdf file
        String jobName = getString(R.string.app_name) + " Print Test";
        //open print dialog
        printManager.print(jobName, printAdapter, new PrintAttributes.Builder().build());
    }

    //perform click pdf creation operation on click of print button click
    public void printPDF(View view) {
        createWebPrintJob(myWebView);
    }
}
