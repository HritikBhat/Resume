package com.hritik.resume.list_adapters;

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

import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import com.hritik.resume.MyHelper;
import com.hritik.resume.R;
import com.hritik.resume.insertion.Prg_Desc;

import java.util.ArrayList;

public class ListAdapterPRG extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList<String> descp,names,tech;

    private Activity act;

    public ListAdapterPRG(Activity context, ArrayList<String> names, ArrayList<String> tech, ArrayList<String> descp) {
        super(context, R.layout.my_list_project,names);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.names=names;
        this.tech=tech;
        this.descp=descp;
    }
    public void editOption(int pos){
        Intent ct=new Intent(context, Prg_Desc.class);
        String des= descp.get(pos);
        String nam= names.get(pos);
        String tch= tech.get(pos);
        ct.putExtra("name",nam);
        ct.putExtra("tech",tch);
        ct.putExtra("desc",des);
        context.startActivity(ct);
    }
    public void deleteOption(int pos){
        MyHelper dpHelper = new MyHelper(context);
        SQLiteDatabase db = dpHelper.getReadableDatabase();
        dpHelper.onDeleteOne("prg","prg_desc",descp.get(pos));
        descp.remove(descp.get(pos));
        names.remove(names.get(pos));
        tech.remove(tech.get(pos));
        notifyDataSetChanged();
    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.my_list_project, null,true);
        final View vw=view;
        TextView nameText = rowView.findViewById(R.id.name);
        nameText.setText(names.get(position));
        TextView techText = rowView.findViewById(R.id.tech);
        techText.setText(tech.get(position));
        TextView descText = rowView.findViewById(R.id.desc);
        descText.setText(descp.get(position));
        CardView cardv = rowView.findViewById(R.id.card);
        cardv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View parentRow = (View) view;
                //To get the object of ListView parentRow -> LinearLayout -> LinearLayout -> ListView
                ListView listView = (ListView) parentRow.getParent();
                final int pos = listView.getPositionForView(parentRow);
                editOption(pos);
                Toast.makeText(getContext(),"edit",Toast.LENGTH_LONG).show();
            }
        });
        final AppCompatButton imagebtn = rowView.findViewById(R.id.imgbtn);
        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View parentRow = (View) view.getParent();
                //To get the object of ListView parentRow -> LinearLayout -> LinearLayout -> ListView
                ListView listView = (ListView) parentRow.getParent().getParent();
                final int pos = listView.getPositionForView(parentRow);
                //Toast t=Toast.makeText(context,descp.get(pos),Toast.LENGTH_SHORT).show();
                deleteOption(pos);
                Toast.makeText(getContext(),"delete",Toast.LENGTH_LONG).show();
            }
        });

        return rowView;

    }
}
