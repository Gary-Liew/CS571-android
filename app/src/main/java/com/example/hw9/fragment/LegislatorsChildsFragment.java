package com.example.hw9.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hw9.MyApp;
import com.example.hw9.R;
import com.example.hw9.activity.LegislatorsDetailActivity;
import com.example.hw9.adapter.LegislatorsAdapter;
import com.example.hw9.api.BaseApi;
import com.example.hw9.bean.DatabaseEvent;
import com.example.hw9.bean.Legislators;
import com.example.hw9.network.ResponseCallBack;
import com.example.hw9.util.JsonUtils;
import com.example.hw9.view.SideView;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.hw9.R.id.dialog;

public class LegislatorsChildsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_FILTER = "filter";
    private static final String ARG_STORE = "isLocal";
    @BindView(R.id.lv)
    ListView mLv;
    @BindView(dialog)
    TextView mDialog;
    @BindView(R.id.sidrbar)
    SideView mSidrbar;

    private boolean isVisible = false;
    private boolean isLocal = false;

    // TODO: Rename and change types of parameters
    private String mFilter;
    private Map<String, String> lettersMap = new TreeMap<>();


    public LegislatorsChildsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LegislatorsChildsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LegislatorsChildsFragment newInstance(String filter, boolean isLocal) {
        LegislatorsChildsFragment fragment = new LegislatorsChildsFragment();
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
                Legislators.ResultsBean item = (Legislators.ResultsBean) ((LegislatorsAdapter) mLv.getAdapter()).getItem(i);
                Intent intent = new Intent(getActivity(), LegislatorsDetailActivity.class);
                intent.putExtra("detail", item);
                startActivity(intent);
            }
        });

        mSidrbar.setTextView(mDialog);

        mSidrbar.setOnTouchingLetterChangedListener(new SideView.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                LegislatorsAdapter adapter = ((LegislatorsAdapter) mLv.getAdapter());
                if (adapter != null) {
                    int position = adapter.getPositionForSection(s.charAt(0));
                    if (position != -1) {
                        mLv.setSelection(position);
                    }
                }
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
            Logger.d("getData");
            return;
        }
        mLv.setAdapter(null);
        if (isLocal) {

            List<Legislators.ResultsBean> datas = null;
            try {
                datas = MyApp.getmInstance().getDb().findAll(Selector.from(Legislators.ResultsBean.class).orderBy("last_name", false));
            } catch (DbException e) {
                e.printStackTrace();
            }
            mLv.setAdapter(new LegislatorsAdapter(getActivity(), datas, mFilter));

        } else {
            BaseApi.getLegislators(getActivity(), mFilter, new ResponseCallBack<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Legislators legislators = JsonUtils.fromJson(response.toString(), Legislators.class);
                    if (TextUtils.isEmpty(mFilter)) {
                        Collections.sort(legislators.getResults(), new CompartorState());
                        if (!isLocal) {
                            lettersMap.clear();
                            for (Legislators.ResultsBean item : legislators.getResults()) {
                                lettersMap.put(toUpperCaseFirstOne(item.getState_name()), "");
                            }
                        }

                    } else {
                        Collections.sort(legislators.getResults(), new CompartorName());
                        if (!isLocal) {
                            lettersMap.clear();
                            for (Legislators.ResultsBean item : legislators.getResults()) {
                                lettersMap.put(toUpperCaseFirstOne(item.getLast_name()), "");
                            }
                        }

                    }

                    if (!isLocal) {
                        Iterator iter = lettersMap.entrySet().iterator();
                        List<String> keys = new ArrayList<>();
                        while (iter.hasNext()) {
                            Map.Entry entry = (Map.Entry) iter.next();
                            keys.add(entry.getKey().toString());
                        }
                        int size = keys.size();
                        String[] lettsers = (String[]) keys.toArray(new String[size]);//
                        mSidrbar.setLetters(lettsers);
                        mSidrbar.setVisibility(View.VISIBLE);
                    }
                    mLv.setAdapter(new LegislatorsAdapter(getActivity(), legislators.getResults(), mFilter));

                }
            });
        }


    }

    class CompartorState implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {

            Legislators.ResultsBean sdto1 = (Legislators.ResultsBean) o1;

            Legislators.ResultsBean sdto2 = (Legislators.ResultsBean) o2;

            int sort1 = sdto1.getState_name().compareTo(sdto2.getState_name());
            if (sort1 == 0) {
                return sdto1.getLast_name().compareTo(sdto2.getLast_name());
            } else {

                return sort1;
            }
        }
    }

    class CompartorName implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {

            Legislators.ResultsBean sdto1 = (Legislators.ResultsBean) o1;

            Legislators.ResultsBean sdto2 = (Legislators.ResultsBean) o2;

            return sdto1.getLast_name().compareTo(sdto2.getLast_name());

        }
    }

    private String toUpperCaseFirstOne(String s) {
        return new StringBuilder().append(Character.toUpperCase(s.charAt(0))).toString();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DatabaseEvent event) {

        getData();
    }

}
