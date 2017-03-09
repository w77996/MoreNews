package com.a77996.morenews.morenews.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.a77996.morenews.morenews.R;
import com.a77996.morenews.morenews.bean.BeanType;

/**
 * Created by Administrator on 2017/3/8.
 */
public class DetailActivity extends AppCompatActivity {

    private DetailFragment detailFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);

            detailFragment = DetailFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, detailFragment)
                    .commit();

        DetailPresenter presenter = new DetailPresenter(DetailActivity.this, detailFragment);

        Intent intent =getIntent();

        presenter.setType((BeanType) intent.getSerializableExtra("type"));
        presenter.setId(intent.getIntExtra("id", 0));
        presenter.setTitle(intent.getStringExtra("title"));
        presenter.setCoverUrl(intent.getStringExtra("coverUrl"));
    }
}
