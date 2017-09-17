package iammert.com.androidarchitecture.ui.main;

import android.os.Bundle;

import iammert.com.androidarchitecture.R;
import iammert.com.androidarchitecture.databinding.ActivityMainBinding;
import iammert.com.androidarchitecture.ui.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding.viewPager.setAdapter(new MoviesPagerAdapter(getSupportFragmentManager()));
        dataBinding.tabs.setupWithViewPager(dataBinding.viewPager);
        dataBinding.viewPager.setOffscreenPageLimit(3);
    }
}
