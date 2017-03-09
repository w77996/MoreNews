package com.a77996.morenews.morenews.homepage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
    private GuokrFragment guokrMomentFragment;
    private DoubanMomentFragment doubanMomentFragment;

    private ZhihuDailyPresenter zhihuDailyPresenter;
    private GuokrPresenter guokrPresenter;
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
        guokrMomentFragment = GuokrFragment.newInstance();
        zhihuDailyPresenter = new ZhihuDailyPresenter(context,zhihuDailyFragment);
        guokrPresenter = new GuokrPresenter(context,guokrMomentFragment);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main,container,false);
        initViews(view);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
                if (tab.getPosition() == 1) {
                    fab.hide();
                } else {
                    fab.show();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
        return view;
    }

    private void initViews(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(3);
        // Log.d("dd","main inti");
        mainPagerApdapter = new MainPagerApdapter(getChildFragmentManager(), context, zhihuDailyFragment, guokrMomentFragment, doubanMomentFragment);

        viewPager.setAdapter(mainPagerApdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
