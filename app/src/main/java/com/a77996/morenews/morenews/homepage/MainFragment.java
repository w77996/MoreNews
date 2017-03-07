package com.a77996.morenews.morenews.homepage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a77996.morenews.morenews.R;
import com.a77996.morenews.morenews.adapter.MainPagerApdapter;

/**
 * Created by Administrator on 2017/3/7.
 */
public class MainFragment extends Fragment{

    private Context context;
    private MainPagerApdapter mainPagerApdapter;

    private TabLayout tabLayout;

    private ZhihuDailyFragment zhihuDailyFragment;
    private DoubanMomentFragment doubanMomentFragment;
    public MainFragment(){};
    public static MainFragment newInstance(){
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getActivity();
        zhihuDailyFragment = ZhihuDailyFragment.newInstance();
        doubanMomentFragment = DoubanMomentFragment.newInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        tabLayout = (TabLayout)view.findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(2);
       // Log.d("dd","main inti");
        mainPagerApdapter = new MainPagerApdapter(getChildFragmentManager(),context,zhihuDailyFragment,doubanMomentFragment);

        viewPager.setAdapter(mainPagerApdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
