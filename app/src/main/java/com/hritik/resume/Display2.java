package com.hritik.resume;

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

public class Display2 extends AppCompatActivity {
    WebView myWebView;
    int edu_count=0,prg_count=0,ach_count=0,sk_count=0,exp_count=0,intr_count=0;
    private String web_Start(){
        String st_start="<html><head><link rel=\"stylesheet\" type=\"text/css\" href=\"sty2.css\"/></head>";
        return st_start;
    }

    private String web_Personal(String name,String addr,String phone,String email,String dob){
        String ps="<body>\n" +
                "    <div class=\"header\">\n" +
                "        <div class=\"hd-left\">\n" +
                "            "+name+"\n" +
                "            <p id=\"addr\">"+addr+"<br>\n" +
                "            DOB-"+dob+"</p>\n" +
                "        </div>\n" +
                "        <div class=\"hd-right\">\n" +
                "            "+email+"<br>Phone: "+phone+"\n" +
                "        </div>\n" +
                "     </div>\n" +
                "     <hr>\n" +
                "     <br>";

        return ps;
    }

    private String web_Objective(String obj){
        String ob="<div class=\"objective\">\n" +
                "        <div class=\"ob-left\" >\n" +
                "            Objective\n" +
                "        </div>\n" +
                "        <div class=\"ob-right\">\n" +
                "            <p id=\"obpara\">\n" + obj +
                "            </p>\n" +
                "        </div>\n" +
                "     </div>\n" +
                "     <br>\n" +
                "     <hr>\n" +
                "        <br>";

        return ob;
    }

    //End this with </ul> <br>
    private String web_Internship(String name,String city,String year,String desc){
        String intr;String hd="";
        if (intr_count==0){
            hd="<div class=\"internship\">\n" +
                    "    <div class=\"ep-left\">\n" +
                    "      Internship\n" +
                    "    </div>"+"<div class=\"ep-right\">\n";
            intr_count+=1;
        }
        intr=hd+
                "            <div class=\"ep-row\">\n" +
                "              <div class=\"epsub-left\">\n" +
                "                <p id=\"company-name\">"+name+","+city+"</p><br>\n" +
                "                <!--<p id=\"post\">Executive Manager</p>-->\n" +
                "                <p id=\"eppara\">\n" + desc+
                "                </p><br><br><br>\n" +
                "              </div>\n" +
                "              <div class=\"epsub-right\">\n" +
                "                <p id=\"epDate\">"+year+"</p>\n" +
                "              </div>\n" +
                "            </div>";
        return intr;

    }

    //End this with </ul> <br>
    private String web_Experience(String name,String city,String post,String year,String desc){
        String ep;String hd="";
        if (exp_count==0){
            hd="<div class=\"experience\">\n" +
                    "    <div class=\"ep-left\">\n" +
                    "      Experience\n" +
                    "    </div>"+"<div class=\"ep-right\">\n" ;
            exp_count+=1;
        }
        ep=hd+
                "      <div class=\"ep-row\">\n" +
                "        <div class=\"epsub-left\">\n" +
                "          <p id=\"company-name\">"+name+","+city+"</p><br>\n" +
                "          <p id=\"post\">"+post+"</p>\n" +
                "          <p id=\"eppara\">\n" + desc+
                "          </p><br><br><br>\n" +
                "        </div>\n" +
                "        <div class=\"epsub-right\">\n" +
                "          <p id=\"epDate\">"+year+"</p>\n" +
                "        </div>\n" +
                "      </div>";
        return ep;
    }

    //End this with </ul> <br>
    private String web_Education(String name,String city,String degree,String year,String type,String grade){
        String ed;String hd="";
        if (edu_count==0){
            hd="<div class=\"education\">\n" +
                    "      <div class=\"ed-left\">\n" +
                    "        Education\n" +
                    "      </div>"+"<div class=\"ed-right\">\n";
            edu_count+=1;
        }
        if (type.equalsIgnoreCase("percentage")){
            ed=hd+
                    "        <div class=\"ed-row\">\n" +
                    "          <div class=\"edsub-left\">\n" +
                    "            <p id=\"name\">"+name+","+city+"</p><br>\n" +
                    "            <p id=\"pt\">"+degree+" - Percentage Scored: "+grade+"</p><br><br><br>\n" +
                    "          </div>\n" +
                    "          <div class=\"edsub-right\">\n" +
                    "            <p id=\"edDate\">"+year+"</p>\n" +
                    "          </div>\n" +
                    "        </div>";
        }
        else{
            ed=hd +
                    "        <div class=\"ed-row\">\n" +
                    "          <div class=\"edsub-left\">\n" +
                    "            <p id=\"name\">"+name+","+city+"</p><br>\n" +
                    "            <p id=\"pt\">"+degree+" - CGPA Scored: "+grade+"</p><br><br><br>\n" +
                    "          </div>\n" +
                    "          <div class=\"edsub-right\">\n" +
                    "            <p id=\"edDate\">"+year+"</p>\n" +
                    "          </div>\n" +
                    "        </div>";
        }
        return ed;
    }

    //End this with </ul> <br>
    private String web_Project(String name,String tech,String desc){
        String prg;String hd="";
        if (prg_count==0){
            hd="<div class=\"project\">\n" +
                    "          <div class=\"ep-left\">\n" +
                    "            Project\n" +
                    "          </div>"+"<div class=\"ep-right\">\n" ;
            prg_count+=1;
        }

        prg=hd +
                "            <div class=\"ep-row\">\n" +
                "              <div class=\"epsub-left\">\n" +
                "                <p id=\"company-name\">"+name+"</p><br>\n" +
                "                <p id=\"post\">Technologies Used: "+tech+"</p>\n" +
                "                <p id=\"eppara\">\n" + desc+
                "                </p><br><br><br>\n" +
                "              </div>\n" +
                "              <div class=\"epsub-right\">\n" +
                "                <!--<p id=\"epDate\"> January 2015 - January 2016</p>-->\n" +
                "              </div>\n" +
                "            </div>";
        return prg;
    }

    //End this with </ul> <br>
    private String web_Achievement(String desc){
        String ah;String hd="";
        if (ach_count==0){
            hd="<div class=\"achieve\">\n" +
                    "        <div class=\"sk-left\">\n" +
                    "            Achievements\n" +
                    "        </div>\n" +
                    "        <div class=\"sk-right\">\n" +
                    "            <ul id=\"sk-list\">";
            ach_count+=1;
        }
        ah= hd + "<li>\n" +
                "                    <p id=\"achpara\">" +desc+
                "                </li>";
        return ah;
    }

    //End this with </ul> <br>
    private String web_Skill(String desc){
        String sk;String hd="";
        if (sk_count==0){
            hd="<div class=\"skills\">\n" +
                    "        <div class=\"sk-left\">\n" +
                    "            Skills\n" +
                    "        </div>\n" +
                    "        <div class=\"sk-right\">\n" +
                    "            <ul id=\"sk-list\">";
            sk_count+=1;
        }
        sk = hd + "<li>\n" +
                "                    <p id=\"skpara\">" +desc+
                "                </li>";
        return sk;
    }

    private String web_Declaration(String desc){
        String dc="<div class=\"declaration\">\n" +
                "          <div class=\"ob-left\" >\n" +
                "              Declaration\n" +
                "          </div>\n" +
                "          <div class=\"ob-right\">\n" +
                "              <p id=\"obpara\">\n" +
                desc +
                "              </p>";
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
        cursor.moveToFirst();
        desc=cursor.getString(cursor.getColumnIndex("obj_desc"));
        cursor.close();
        return web_Objective(desc);
    }
    private String declaration_Task(){
        String desc;
        Cursor cursor=getTabledata("dec");
        cursor.moveToFirst();
        desc=cursor.getString(cursor.getColumnIndex("dec_desc"));
        cursor.close();
        return web_Declaration(desc);
    }
    private String achievement_Task(){
        String desc;String st="";
        Cursor cursor=getTabledata("ach");
        //cursor.moveToFirst();
        while (cursor.moveToNext()) {
            //System.out.println(cursor.getString(cursor.getColumnIndex("name")));
            desc=cursor.getString(cursor.getColumnIndex("ach_desc"));
            st+=web_Achievement(desc);
        }
        cursor.close();
        st+="            </ul>\n" +
                "        </div>\n" +
                "     </div>\n" +
                "     <hr>\n" +
                "     <br>";
        return st;
    }
    private String skill_Task(){
        String desc;String st="";
        Cursor cursor=getTabledata("sk");
        //cursor.moveToFirst();
        while (cursor.moveToNext()) {
            //System.out.println(cursor.getString(cursor.getColumnIndex("name")));
            desc=cursor.getString(cursor.getColumnIndex("sk_desc"));
            st+=web_Skill(desc);
        }
        cursor.close();
        st+="            </ul>\n" +
                "        </div>\n" +
                "     </div>\n" +
                "     <hr>\n" +
                "     <br>";
        return st;
    }

    private String project_Task(){
        String name,tech,desc;String st="";
        Cursor cursor=getTabledata("prg");
        //cursor.moveToFirst();
        int count=cursor.getCount();
        for (int i=1;i<=count;i++){
            cursor.moveToNext();
            name=cursor.getString(cursor.getColumnIndex("name"));
            tech=cursor.getString(cursor.getColumnIndex("tech"));
            desc=cursor.getString(cursor.getColumnIndex("prg_desc"));
            st+=web_Project(name,tech,desc);
            if (i!=count){
                st+="<hr id=\"small-hr\">\n" +
                        "      <br>";
            }
        }
        cursor.close();
        st+="</div>\n" +
                "  </div>\n" +
                "  <br>\n" +
                "  <hr>\n" +
                "     <br>";
        return st;
    }

    private String education_Task(){
        String name,city,deg,year,type1,ptcg;String st="";
        Cursor cursor=getTabledata("edu");
        int count=cursor.getCount();
        for (int i=1;i<=count;i++){
            cursor.moveToNext();
            //System.out.println(cursor.getString(cursor.getColumnIndex("name")));
            name=cursor.getString(cursor.getColumnIndex("name"));
            city=cursor.getString(cursor.getColumnIndex("city"));
            deg=cursor.getString(cursor.getColumnIndex("degree"));
            year=cursor.getString(cursor.getColumnIndex("tfrom"))+" - "+cursor.getString(cursor.getColumnIndex("tto"));
            type1=cursor.getString(cursor.getColumnIndex("type"));
            ptcg=cursor.getString(cursor.getColumnIndex("grade"));
            st+=web_Education(name,city,deg,year,type1,ptcg);
            if (i!=count){
                st+="<hr id=\"small-hr\">\n" +
                        "      <br>";
            }
        }
        cursor.close();
        st+="</div>\n" +
                "  </div>\n" +
                "  <br>\n" +
                "  <hr>\n" +
                "     <br>";
        return st;
    }

    private String internship_Task(){
        String name,city,year,desc;String st="";
        Cursor cursor=getTabledata("intr");
        //cursor.moveToFirst();
        int count=cursor.getCount();
        for (int i=1;i<=count;i++){
            cursor.moveToNext();
            //System.out.println(cursor.getString(cursor.getColumnIndex("name")));
            name=cursor.getString(cursor.getColumnIndex("name"));
            city=cursor.getString(cursor.getColumnIndex("city"));
            year=cursor.getString(cursor.getColumnIndex("tfrom"))+" - "+cursor.getString(cursor.getColumnIndex("tto"));
            desc=cursor.getString(cursor.getColumnIndex("intr_desc"));
            st+=web_Internship(name,city,year,desc);
            if (i!=count){
                st+="<hr id=\"small-hr\">\n" +
                        "      <br>";
            }
        }
        cursor.close();
        st+="</div>\n" +
                "  </div>\n" +
                "<br>\n" +
                "<hr>\n" +
                "  <br>";
        return st;
    }

    private String experience_Task(){
        String name,city,post,year,desc;String st="";
        Cursor cursor=getTabledata("exp");
        //if (cursor.getCount()<1){return "";}
        //cursor.moveToFirst();
        int count=cursor.getCount();
        for (int i=1;i<=count;i++){
            cursor.moveToNext();
            //System.out.println(cursor.getString(cursor.getColumnIndex("name")));
            name=cursor.getString(cursor.getColumnIndex("name"));
            city=cursor.getString(cursor.getColumnIndex("city"));
            post=cursor.getString(cursor.getColumnIndex("post"));
            year=cursor.getString(cursor.getColumnIndex("tfrom"))+" - "+cursor.getString(cursor.getColumnIndex("tto"));
            desc=cursor.getString(cursor.getColumnIndex("exp_desc"));
            st+=web_Experience(name,city,post,year,desc);
            if (i!=count){
                st+="<hr id=\"small-hr\">\n" +
                        "      <br>";
            }
        }
        cursor.close();
        st+="</div>\n" +
                "  </div>\n" +
                "<br>\n" +
                "<hr>\n" +
                "  <br>";
        return st;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display2);
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
        String htmldata=(pert+objt+expt+intt+prgt+acht+skt+edut+dect);
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
