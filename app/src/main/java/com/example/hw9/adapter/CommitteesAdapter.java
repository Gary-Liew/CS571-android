package com.example.hw9.adapter;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hw9.R;
import com.example.hw9.bean.Committees;

import java.util.List;

public class CommitteesAdapter extends BaseAdapter {

    private List<Committees.ResultsBean> datas;
    private Context mContext;

    public CommitteesAdapter(Context context, List<Committees.ResultsBean> datas) {
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

        Committees.ResultsBean item = datas.get(i);
        holder.item_tv_title.setText(item.getCommittee_id());
        holder.item_tv_desc.setText(item.getName());
        String chamber = item.getChamber();
        if (!TextUtils.isEmpty(chamber)) {
            holder.item_tv_date.setText(toUpperCaseFirstOne(chamber));
        }


        return view;
    }

    class ViewHolder {
        TextView item_tv_title;
        TextView item_tv_desc;
        TextView item_tv_date;
    }

    private String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
