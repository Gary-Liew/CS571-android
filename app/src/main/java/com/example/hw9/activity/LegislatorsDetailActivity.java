package com.example.hw9.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hw9.MyApp;
import com.example.hw9.R;
import com.example.hw9.bean.DatabaseEvent;
import com.example.hw9.bean.Legislators;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LegislatorsDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.cb_fav)
    CheckBox mCbFav;
    @BindView(R.id.btn_facebook)
    ImageButton mBtnFacebook;
    @BindView(R.id.btn_twitter)
    ImageButton mBtnTwitter;
    @BindView(R.id.btn_gov)
    ImageButton mBtnGov;
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.iv_party)
    ImageView mIvParty;
    @BindView(R.id.tv_party)
    TextView mTvParty;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_email)
    TextView mTvEmail;
    @BindView(R.id.tv_chamber)
    TextView mTvChamber;
    @BindView(R.id.tv_contact)
    TextView mTvContact;
    @BindView(R.id.tv_start_term)
    TextView mTvStartTerm;
    @BindView(R.id.tv_end_term)
    TextView mTvEndTerm;
    @BindView(R.id.pb_term)
    ProgressBar mPbTerm;
    @BindView(R.id.tv_office)
    TextView mTvOffice;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.tv_fax)
    TextView mTvFax;
    @BindView(R.id.tv_birthday)
    TextView mTvBirthday;
    @BindView(R.id.activity_detail)
    LinearLayout mActivityDetail;
    @BindView(R.id.tv_percentage)
    TextView mTvPercentage;
    private Legislators.ResultsBean mResultsBean;
    private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd,yyyy", Locale.ENGLISH);
    private Date startDate, endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislators_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Legislator Info");

        mResultsBean = (Legislators.ResultsBean) getIntent().getSerializableExtra("detail");

        Legislators.ResultsBean temp = null;
        try {
            temp = MyApp.getmInstance().getDb().findFirst(Selector.from(Legislators.ResultsBean.class)
                    .where("bioguide_id", "=", mResultsBean.getBioguide_id()).and("crp_id", "=", mResultsBean.getCrp_id()));
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

        Picasso.with(this).load("https://theunitedstates.io/images/congress/original/" + mResultsBean.getBioguide_id() + ".jpg").resizeDimen(R.dimen.pic_big_width, R.dimen.pic_big_height).into(mIvAvatar);

        String party = mResultsBean.getParty();
        if ("D".equals(party)) {
            mTvParty.setText("Democrats");
            mIvParty.setImageResource(R.drawable.d);
        } else if ("R".equals(party)) {
            mTvParty.setText("Republican");
            mIvParty.setImageResource(R.drawable.r);
        }

        mTvName.setText(mResultsBean.getTitle() + "." + mResultsBean.getLast_name() + "," + mResultsBean.getFirst_name());
        if (!TextUtils.isEmpty(mResultsBean.getOc_email())) {
            mTvEmail.setText(mResultsBean.getOc_email());
        }
        if (!TextUtils.isEmpty(mResultsBean.getPhone())) {
            mTvContact.setText(mResultsBean.getPhone());
        }
        if (!TextUtils.isEmpty(mResultsBean.getChamber())) {
            mTvChamber.setText(mResultsBean.getChamber());
        }
        if (!TextUtils.isEmpty(mResultsBean.getChamber())) {
            mTvChamber.setText(mResultsBean.getChamber());
        }

        if (!TextUtils.isEmpty(mResultsBean.getTerm_start())) {
            try {
                startDate = sdf1.parse(mResultsBean.getTerm_start());
                mTvStartTerm.setText(sdf2.format(startDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (!TextUtils.isEmpty(mResultsBean.getTerm_end())) {
            try {
                endDate = sdf1.parse(mResultsBean.getTerm_end());
                mTvEndTerm.setText(sdf2.format(endDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (startDate != null && endDate != null) {
            int percentage = (int) ((System.currentTimeMillis() - startDate.getTime()) * 1.0f / (endDate.getTime() - startDate.getTime()) * 100);
            mPbTerm.setMax(100);
            mPbTerm.setProgress(percentage);
            mPbTerm.setVisibility(View.VISIBLE);
            mTvPercentage.setText(percentage + "%");
        }
        if (!TextUtils.isEmpty(mResultsBean.getOffice())) {
            mTvOffice.setText(mResultsBean.getOffice());
        }
        if (!TextUtils.isEmpty(mResultsBean.getState())) {
            mTvState.setText(mResultsBean.getState());
        }
        if (!TextUtils.isEmpty(mResultsBean.getFax())) {
            mTvFax.setText(mResultsBean.getFax());
        }
        if (!TextUtils.isEmpty(mResultsBean.getBirthday())) {
            try {
                Date date = sdf1.parse(mResultsBean.getBirthday());
                mTvBirthday.setText(sdf2.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
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

    @OnClick({R.id.btn_facebook, R.id.btn_twitter, R.id.btn_gov})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_facebook:
                if (!TextUtils.isEmpty(mResultsBean.getFacebook_id())) {
                    Uri uri = Uri.parse("http://www.facebook.com/" + mResultsBean.getFacebook_id());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "No Facebook page", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_twitter:
                if (!TextUtils.isEmpty(mResultsBean.getTwitter_id())) {
                    Uri uri = Uri.parse("http://twitter.com/" + mResultsBean.getTwitter_id());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "No Twitter page", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_gov:
                if (!TextUtils.isEmpty(mResultsBean.getWebsite())) {
                    Uri uri = Uri.parse(mResultsBean.getWebsite());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "No Website page", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
