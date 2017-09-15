package iammert.com.androidarchitecture.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import iammert.com.androidarchitecture.R;
import iammert.com.androidarchitecture.databinding.ActivityMainBinding;
import iammert.com.androidarchitecture.ui.BaseActivity;

public class MainActivity extends BaseActivity{

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.viewPager.setAdapter(new MoviesPagerAdapter(getSupportFragmentManager()));
        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.viewPager.setOffscreenPageLimit(3);
    }
}
