package com.example.hw9.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hw9.MyApp;
import com.example.hw9.R;
import com.example.hw9.activity.CommitteeDetailActivity;
import com.example.hw9.adapter.CommitteesAdapter;
import com.example.hw9.api.BaseApi;
import com.example.hw9.bean.Committees;
import com.example.hw9.bean.DatabaseEvent;
import com.example.hw9.network.ResponseCallBack;
import com.example.hw9.util.JsonUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommitteesChildsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_FILTER = "filter";
    private static final String ARG_STORE = "isLocal";
    @BindView(R.id.lv)
    ListView mLv;
    private boolean isVisible = false;
    // TODO: Rename and change types of parameters
    private String mFilter;
    private boolean isLocal = false;

    public CommitteesChildsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LegislatorsChildsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CommitteesChildsFragment newInstance(String filter, boolean isLocal) {
        CommitteesChildsFragment fragment = new CommitteesChildsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FILTER, filter);
        args.putBoolean(ARG_STORE, isLocal);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFilter = getArguments().getString(ARG_FILTER);
            isLocal = getArguments().getBoolean(ARG_STORE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_viewpager_childs, container, false);
        ButterKnife.bind(this, view);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Committees.ResultsBean item = (Committees.ResultsBean) ((CommitteesAdapter) mLv.getAdapter()).getItem(i);
                Intent intent = new Intent(getActivity(), CommitteeDetailActivity.class);
                intent.putExtra("detail", item);
                startActivity(intent);
            }
        });
        if (isVisible) {
            getData();
        }
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (getUserVisibleHint()) {
            isVisible = true;
            if (getActivity() != null) {
                getData();
            }

        } else {
            isVisible = false;
        }
    }

    public void getData() {
        if (mLv == null) {
            return;
        }
        mLv.setAdapter(null);
        if (isLocal) {
            List<Committees.ResultsBean> datas = null;
            try {
                datas = MyApp.getmInstance().getDb().findAll(Selector.from(Committees.ResultsBean.class).orderBy("committee_id", false));
            } catch (DbException e) {
                e.printStackTrace();
            }
            mLv.setAdapter(new CommitteesAdapter(getActivity(), datas));
        } else {
            BaseApi.getCommittees(getActivity(), mFilter, new ResponseCallBack<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Committees committees = JsonUtils.fromJson(response.toString(), Committees.class);
                    mLv.setAdapter(new CommitteesAdapter(getActivity(), committees.getResults()));
                }
            });
        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DatabaseEvent event) {

        getData();
    }

}
