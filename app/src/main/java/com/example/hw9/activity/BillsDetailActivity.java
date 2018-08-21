package com.example.hw9.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hw9.MyApp;
import com.example.hw9.R;
import com.example.hw9.bean.Bills;
import com.example.hw9.bean.DatabaseEvent;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillsDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.cb_fav)
    CheckBox mCbFav;
    @BindView(R.id.tv_bill_id)
    TextView mTvBillId;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_bill_type)
    TextView mTvBillType;
    @BindView(R.id.tv_sponsor)
    TextView mTvSponsor;
    @BindView(R.id.tv_chamber)
    TextView mTvChamber;
    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.tv_introduced_on)
    TextView mTvIntroducedOn;
    @BindView(R.id.tv_congress_url)
    TextView mTvCongressUrl;
    @BindView(R.id.tv_version_status)
    TextView mTvVersionStatus;
    @BindView(R.id.tv_bill_url)
    TextView mTvBillUrl;
    @BindView(R.id.activity_detail)
    LinearLayout mActivityDetail;
    private Bills.ResultsBean mResultsBean;
    private SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd,yyyy", Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Bill Info");

        mResultsBean = (Bills.ResultsBean) getIntent().getSerializableExtra("detail");

        Bills.ResultsBean temp = null;
        try {
            temp = MyApp.getmInstance().getDb().findFirst(Selector.from(Bills.ResultsBean.class)
                    .where("bill_id", "=", mResultsBean.getBill_id()).and("sponsor_id", "=", mResultsBean.getSponsor_id()));
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

        if (!TextUtils.isEmpty(mResultsBean.getBill_id())) {
            mTvBillId.setText(mResultsBean.getBill_id().toUpperCase());
        }
        if (!TextUtils.isEmpty(mResultsBean.getShort_title())) {
            mTvTitle.setText(mResultsBean.getShort_title());
        } else if (!TextUtils.isEmpty(mResultsBean.getOfficial_title())) {
            mTvTitle.setText(mResultsBean.getOfficial_title());
        }
        if (!TextUtils.isEmpty(mResultsBean.getBill_type())) {
            mTvBillType.setText(mResultsBean.getBill_type().toUpperCase());
        }
        Bills.ResultsBean.SponsorBean sponsor = mResultsBean.getSponsor();
        if (sponsor != null) {

            mTvSponsor.setText(sponsor.getTitle() + "." + sponsor.getLast_name() + "," + sponsor.getFirst_name());
        }
        String chamber = mResultsBean.getChamber();
        if (!TextUtils.isEmpty(chamber)) {
            mTvChamber.setText(toUpperCaseFirstOne(chamber));
        }
        Bills.ResultsBean.HistoryBean history = mResultsBean.getHistory();
        if (history != null) {
            if (history.isActive()) {
                mTvStatus.setText("Active");
            } else {
                mTvStatus.setText("New");
            }
        }
        if (!TextUtils.isEmpty(mResultsBean.getIntroduced_on())) {
            try {
                Date date = sdf1.parse(mResultsBean.getIntroduced_on());
                mTvIntroducedOn.setText(sdf2.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Bills.ResultsBean.UrlsBean urls = mResultsBean.getUrls();
        if (urls != null && !TextUtils.isEmpty(urls.getCongress())) {
            mTvCongressUrl.setText(urls.getCongress());
        }
        Bills.ResultsBean.LastVersionBean lastVersion = mResultsBean.getLast_version();
        if (lastVersion != null && !TextUtils.isEmpty(lastVersion.getVersion_name())) {
            mTvVersionStatus.setText(lastVersion.getVersion_name());
        }
        if (lastVersion != null) {
            Bills.ResultsBean.LastVersionBean.UrlsBeanX urlsX = lastVersion.getUrls();
            if (urlsX != null && !TextUtils.isEmpty(urlsX.getPdf())) {
                mTvBillUrl.setText(urlsX.getPdf());
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
    private  String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
