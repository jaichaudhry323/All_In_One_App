package org.o7planning.simplelistview.API.Heros;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.o7planning.simplelistview.API.Heros.Hero;
import org.o7planning.simplelistview.R;

import java.util.ArrayList;

public class HeroListAdapter extends ArrayAdapter<Hero> {

    ArrayList<Hero> mHeroList;
    Context mContext;

    public HeroListAdapter(Context context, ArrayList<Hero> HeroList) {
        super(context, R.layout.hero_layout_listview, HeroList);
        mHeroList = HeroList;
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);      // This is very very important

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.hero_layout_listview, null, false);
        }

        TextView NameTextView = convertView.findViewById(R.id.api_name);
        TextView ImageUrlTextView = convertView.findViewById(R.id.api_imageurl);
        ImageView mImageView = convertView.findViewById(R.id.api_imageview);
        Hero currentHero = mHeroList.get(position);

        // USING PICASSO
        // https://www.tutorialspoint.com/how-to-add-picasso-library-in-android-studio
        //

        Picasso.get().load(currentHero.getmImageUrl()).into(mImageView);

        NameTextView.setText(currentHero.getName());
        ImageUrlTextView.setText(currentHero.getmImageUrl());
        return convertView;
    }

}
