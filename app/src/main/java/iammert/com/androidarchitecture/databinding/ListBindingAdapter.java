package iammert.com.androidarchitecture.databinding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import iammert.com.androidarchitecture.data.Resource;
import iammert.com.androidarchitecture.ui.BaseAdapter;

/**
 * Created by mertsimsek on 20/05/2017.
 */

public final class ListBindingAdapter{
    @BindingAdapter(value = "resource")
    public static void setResource(RecyclerView recyclerView, Resource resource){
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if(adapter == null)
            return;

        if(resource == null || resource.data == null)
            return;

    }
}
