package com.hritik.resume;

import android.app.Activity;
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

public class ListAdapterINT extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList<String> name,city,year,descp;

    public ListAdapterINT(Activity context, ArrayList<String> name, ArrayList<String> city, ArrayList<String> year,ArrayList<String> descp) {
        super(context, R.layout.my_list_internship,name);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.name=name;
        this.city=city;
        this.year=year;
        this.descp=descp;
    }
    public void deleteOption(int pos){
        MyHelper dpHelper = new MyHelper(context);
        SQLiteDatabase db = dpHelper.getReadableDatabase();
        dpHelper.onDeleteOne("intr","intr_desc",descp.get(pos));
        city.remove(city.get(pos));
        name.remove(name.get(pos));
        year.remove(year.get(pos));
        descp.remove(descp.get(pos));
        notifyDataSetChanged();
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.my_list_internship, null,true);
        final View vw=view;
        TextView nameText = (TextView) rowView.findViewById(R.id.name);
        nameText.setText(name.get(position)+", "+city.get(position));
        TextView dtText = (TextView) rowView.findViewById(R.id.dt2);
        dtText.setText(year.get(position));

        final ImageButton imagebtn = (ImageButton) rowView.findViewById(R.id.imgbtn);
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
                        return true;
                    }
                });
                popup.show();
            }
        });

        return rowView;

    }
}
