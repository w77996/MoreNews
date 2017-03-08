package com.a77996.morenews.morenews.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.a77996.morenews.morenews.R;

/**
 * Created by Administrator on 2017/3/8.
 */
public class DetailActivity extends AppCompatActivity {

    private DetailFragment detailFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);
        detailFragment = (DetailFragment) getSupportFragmentManager().getFragment(savedInstanceState,"detailFragment");
        DetailPresenter presenter = new DetailPresenter(DetailActivity.this, detailFragment);
    }
}
