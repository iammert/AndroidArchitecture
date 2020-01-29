package iammert.com.androidarchitecture.ui;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by mertsimsek on 21/05/2017.
 */

public abstract class BaseAdapter<Type extends RecyclerView.ViewHolder, Data> extends RecyclerView.Adapter<Type>{

    public abstract void setData(List<Data> data);
}
