package iammert.com.androidarchitecture.ui;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



/**
 * Created by mertsimsek on 15/09/2017.
 * Updated by johnjeremih on 26/11/2019
 */

public abstract class BaseActivity<DB extends ViewDataBinding> extends AppCompatActivity {

    public DB dataBinding;

    @LayoutRes
    public abstract int getLayoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, getLayoutRes());
    }


}
