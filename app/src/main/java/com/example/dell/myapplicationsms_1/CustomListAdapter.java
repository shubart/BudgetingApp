package com.example.dell.myapplicationsms_1;

import java.util.HashMap;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import static com.example.dell.myapplicationsms_1.Constants.FIRST_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.NINE_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.SECOND_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.SEVEN_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.THIRD_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.FOURTH_COLUMN;
import static com.example.dell.myapplicationsms_1.Constants.FIRTH_COLUMN;


/**
 * Created by Dell on 2/9/2017.
 */
public class CustomListAdapter extends ArrayAdapter<HashMap<String, String>> {
    private final Activity context;

    private ArrayList<HashMap<String, String>> BudgetSaved;

    public CustomListAdapter(Activity context, ArrayList<HashMap<String, String>> budgetlist) {
        super(context, R.layout.budget_list, budgetlist);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.BudgetSaved =budgetlist;



    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.budget_list, null, true);

        TextView itemName = (TextView) rowView.findViewById(R.id.itemNam);
        TextView buyfrom = (TextView) rowView.findViewById(R.id.buyFrm);
        TextView unitpricet = (TextView) rowView.findViewById(R.id.unitPrc);
        TextView quantity = (TextView) rowView.findViewById(R.id.quantity);
        TextView itemTotalprice = (TextView) rowView.findViewById(R.id.itemTotalP);
        TextView count = (TextView) rowView.findViewById(R.id. notesView);



        HashMap<String, String> budgetList = BudgetSaved.get(position);

        itemName.setText(budgetList.get(FIRST_COLUMN));
        buyfrom.setText(budgetList.get(SEVEN_COLUMN));
        unitpricet.setText(budgetList.get(SECOND_COLUMN));
        quantity.setText(budgetList.get(THIRD_COLUMN));
        itemTotalprice.setText(budgetList.get(FOURTH_COLUMN));
        count.setText(budgetList.get(NINE_COLUMN));


        return rowView;

    };

}
