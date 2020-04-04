package com.hritik.resume;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListAdapterEDU extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList<String> name,city,deg,year,ptcg,type1;

    private Activity act;

    public ListAdapterEDU(Activity context, ArrayList<String> name, ArrayList<String> city, ArrayList<String> deg, ArrayList<String> year, ArrayList<String> ptcg,ArrayList<String> type1) {
        super(context, R.layout.my_list_education,name);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.name=name;
        this.city=city;
        this.deg=deg;
        this.year=year;
        this.type1=type1;
        this.ptcg=ptcg;
    }
    public void editOption(int pos){
        Intent ct=new Intent(context,Edu_Desc.class);
        String nam= name.get(pos);
        String cit= city.get(pos);
        String deg2= deg.get(pos);
        String yr= year.get(pos);
        String tp= type1.get(pos);
        String gd= ptcg.get(pos);
        ct.putExtra("name",nam);
        ct.putExtra("city",cit);
        ct.putExtra("deg",deg2);
        ct.putExtra("year",yr);
        ct.putExtra("tp",tp);
        ct.putExtra("gd",gd);
        context.startActivity(ct);
    }
    public void deleteOption(int pos){
        MyHelper dpHelper = new MyHelper(context);
        SQLiteDatabase db = dpHelper.getReadableDatabase();
        dpHelper.onDeleteOne("edu","name",name.get(pos));
        city.remove(city.get(pos));
        name.remove(name.get(pos));
        year.remove(year.get(pos));
        ptcg.remove(ptcg.get(pos));
        notifyDataSetChanged();
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.my_list_education, null,true);
        final View vw=view;
        TextView nameText = rowView.findViewById(R.id.name);
        nameText.setText(name.get(position));
        TextView ctdegText = rowView.findViewById(R.id.ctdeg);
        ctdegText.setText(city.get(position)+" - "+deg.get(position));
        TextView dtText = rowView.findViewById(R.id.dt);
        dtText.setText(year.get(position));
        TextView ptcgText = rowView.findViewById(R.id.ptcg);
        ptcgText.setText(ptcg.get(position));
        final ImageButton imagebtn = rowView.findViewById(R.id.imgbtn);
        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View parentRow = (View) view.getParent();
                //To get the object of ListView parentRow -> LinearLayout -> LinearLayout -> ListView
                ListView listView = (ListView) parentRow.getParent().getParent();
                final int pos = listView.getPositionForView(parentRow);
                //Toast t=Toast.makeText(context,descp.get(pos),Toast.LENGTH_SHORT).show();
                PopupMenu popup = new PopupMenu(getContext(), imagebtn);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.popmenu_item, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().toString().equalsIgnoreCase("delete"))
                        {
                            deleteOption(pos);
                            Toast.makeText(getContext(),"Delete",Toast.LENGTH_LONG).show();
                        }
                        if(item.getTitle().toString().equalsIgnoreCase("edit"))
                        {
                            editOption(pos);
                            Toast.makeText(getContext(),"Edit",Toast.LENGTH_LONG).show();
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });

        return rowView;

    }
}
