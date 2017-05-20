package iammert.com.androidarchitecture.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import iammert.com.androidarchitecture.R;

/**
 * Created by mertsimsek on 19/05/2017.
 */

public class MovieDetailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
    }
}
