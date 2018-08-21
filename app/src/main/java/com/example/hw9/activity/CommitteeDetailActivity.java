package com.example.hw9.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hw9.MyApp;
import com.example.hw9.R;
import com.example.hw9.bean.Committees;
import com.example.hw9.bean.DatabaseEvent;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommitteeDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.cb_fav)
    CheckBox mCbFav;
    @BindView(R.id.tv_committee_id)
    TextView mTvCommitteeId;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_chamber)
    TextView mTvChamber;
    @BindView(R.id.tv_parent_committee)
    TextView mTvParentCommittee;
    @BindView(R.id.tv_contact)
    TextView mTvContact;
    @BindView(R.id.tv_office)
    TextView mTvOffice;
    @BindView(R.id.activity_detail)
    LinearLayout mActivityDetail;
    @BindView(R.id.iv)
    ImageView mIv;
    private Committees.ResultsBean mResultsBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Committee Info");
        mResultsBean = (Committees.ResultsBean) getIntent().getSerializableExtra("detail");

        Committees.ResultsBean temp = null;
        try {
            temp = MyApp.getmInstance().getDb().findFirst(Selector.from(Committees.ResultsBean.class)
                    .where("committee_id", "=", mResultsBean.getCommittee_id()).and("parent_committee_id", "=", mResultsBean.getParent_committee_id()));
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (temp != null) {
            mResultsBean.setId(temp.getId());
            mCbFav.setChecked(true);
        }
        mCbFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                try {
                    if (b) {
                        MyApp.getmInstance().getDb().saveBindingId(mResultsBean);
                    } else {
                        MyApp.getmInstance().getDb().delete(mResultsBean);
                    }

                    EventBus.getDefault().post(new DatabaseEvent());

                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });

        if (!TextUtils.isEmpty(mResultsBean.getCommittee_id())) {
            mTvCommitteeId.setText(mResultsBean.getCommittee_id());
        }
        if (!TextUtils.isEmpty(mResultsBean.getName())) {
            mTvName.setText(mResultsBean.getName());
        }
        if (!TextUtils.isEmpty(mResultsBean.getChamber())) {
            mIv.setVisibility(View.VISIBLE);
            mTvChamber.setText(toUpperCaseFirstOne(mResultsBean.getChamber()));
        }
        if (!TextUtils.isEmpty(mResultsBean.getParent_committee_id())) {
            mTvParentCommittee.setText(mResultsBean.getParent_committee_id());
        }
        if (!TextUtils.isEmpty(mResultsBean.getPhone())) {
            mTvContact.setText(mResultsBean.getPhone());
        }
        if (!TextUtils.isEmpty(mResultsBean.getOffice())) {
            mTvOffice.setText(mResultsBean.getOffice());
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
