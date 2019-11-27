package iammert.com.androidarchitecture.databinding;

import androidx.databinding.BindingAdapter;
import androidx.core.content.ContextCompat;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import iammert.com.androidarchitecture.R;
import iammert.com.androidarchitecture.data.remote.ApiConstants;

/**
 * Created by mertsimsek on 20/05/2017.
 */

public final class ImageBindingAdapter {

    @BindingAdapter(value = "url")
    public static void loadImageUrl(ImageView view, String url) {
        if (url != null && !url.equals(""))
            Picasso.get()
                    .load(ApiConstants.IMAGE_ENDPOINT_PREFIX + url)
                    .placeholder(R.drawable.placeholder)
                    .into(view);
    }

}
