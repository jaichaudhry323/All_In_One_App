package org.o7planning.simplelistview.Umbeo;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.o7planning.simplelistview.R;

import java.util.List;

public class AdapterSlidingImage extends RecyclerView.Adapter<AdapterSlidingImage.ItemViewHolder> {

    Context mContext;
    List mImagePaths;


    public AdapterSlidingImage(Context context,List<String>images)
    {
        mContext = context;
        mImagePaths = images;

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        ImageView mImageView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.sliding_image_view);
        }
    }

    @NonNull
    @Override
    public AdapterSlidingImage.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sliding_image,parent,false);

        ItemViewHolder v = new ItemViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

//        String uri = "@drawable/myresource";  // where myresource (without the extension) is the file
//        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
//        Drawable res = getResources().getDrawable(imageResource);
//        imageView.setImageDrawable(res);

//        String uri = (String) mImagePaths.get(position);
//        int imageResource = mContext.getResources().getIdentifier(uri, null, mContext.getPackageName());
//        Drawable res = mContext.getResources().getDrawable(imageResource);
        holder.mImageView.setImageResource(R.drawable.image_1);
    }

    @Override
    public int getItemCount() {
        return mImagePaths.size();
    }




}
