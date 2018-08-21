package com.example.hw9.adapter;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.example.hw9.R;
import com.example.hw9.bean.Legislators;
import com.squareup.picasso.Picasso;

import java.util.List;


public class LegislatorsAdapter extends BaseAdapter implements SectionIndexer {

    private List<Legislators.ResultsBean> datas;
    private Context mContext;
    private String mFilter;

    public LegislatorsAdapter(Context context, List<Legislators.ResultsBean> datas, String filter) {
        this.mContext = context;
        this.datas = datas;
        this.mFilter = filter;
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

            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_legislators, null);
            holder = new ViewHolder();
            holder.item_iv_avatar = (ImageView) view.findViewById(R.id.item_iv_avatar);
            holder.item_tv_name = (TextView) view.findViewById(R.id.item_tv_name);
            holder.item_tv_aka = (TextView) view.findViewById(R.id.item_tv_aka);
            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();
        }

        Legislators.ResultsBean item = datas.get(i);
        Picasso.with(mContext).load("https://theunitedstates.io/images/congress/original/" + item.getBioguide_id() + ".jpg").resizeDimen(R.dimen.pic_small_width, R.dimen.pic_small_height).into(holder.item_iv_avatar);
        holder.item_tv_name.setText(item.getLast_name() + ", " + item.getFirst_name());
        holder.item_tv_aka.setText("(" + item.getParty() + ")" + item.getState_name() + " - " + "District " + item.getDistrict());

        return view;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String name;
            if (TextUtils.isEmpty(mFilter)) {
                name = datas.get(i).getState_name();
            } else {
                name = datas.get(i).getLast_name();
            }
            String sortStr = getSortLetters(name);
            char firstChar = sortStr.charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        String name;
        if (TextUtils.isEmpty(mFilter)) {
            name = datas.get(position).getState_name();
        } else {
            name = datas.get(position).getLast_name();
        }
        return getSortLetters(name).charAt(0);
    }

    private String getSortLetters(String str) {
        if (TextUtils.isEmpty(str)) {
            return "#";
        }
        String sortStr = str.trim().substring(0, 1).toUpperCase();
        if (sortStr.matches("[A-Z]")) {
            return sortStr;
        } else {
            return "#";
        }
    }

    class ViewHolder {
        ImageView item_iv_avatar;
        TextView item_tv_name;
        TextView item_tv_aka;
    }
}
