package iammert.com.androidarchitecture.ui.main;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import iammert.com.androidarchitecture.R;
import iammert.com.androidarchitecture.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector{

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentAndroidInjector;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.viewPager.setAdapter(new MoviesPagerAdapter(getSupportFragmentManager()));
        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.viewPager.setOffscreenPageLimit(3);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentAndroidInjector;
    }
}
