package com.example.hw9.adapter;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hw9.R;
import com.example.hw9.bean.Bills;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BillsAdapter extends BaseAdapter {

    private List<Bills.ResultsBean> datas;
    private Context mContext;
    private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd,yyyy",Locale.ENGLISH);

    public BillsAdapter(Context context, List<Bills.ResultsBean> datas) {
        this.mContext = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {

            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_biils, null);
            holder = new ViewHolder();
            holder.item_tv_title = (TextView) view.findViewById(R.id.item_tv_title);
            holder.item_tv_desc = (TextView) view.findViewById(R.id.item_tv_desc);
            holder.item_tv_date = (TextView) view.findViewById(R.id.item_tv_date);
            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();
        }

        Bills.ResultsBean item = datas.get(i);
        holder.item_tv_title.setText(item.getBill_id().toUpperCase(Locale.ENGLISH));
        if(TextUtils.isEmpty(item.getShort_title())){
            holder.item_tv_desc.setText(item.getOfficial_title());
        }else{
            holder.item_tv_desc.setText(item.getShort_title());
        }

        try {
            Date date = sdf1.parse(item.getIntroduced_on());
            holder.item_tv_date.setText(sdf2.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return view;
    }

    class ViewHolder {
        TextView item_tv_title;
        TextView item_tv_desc;
        TextView item_tv_date;
    }
}
