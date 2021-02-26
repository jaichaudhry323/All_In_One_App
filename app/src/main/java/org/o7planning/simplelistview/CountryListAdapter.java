package org.o7planning.simplelistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CountryListAdapter extends BaseAdapter {

    private List<Country> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CountryListAdapter(Context context, List<Country> listData) {
        this.context = context;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // function called o populate the listview
    // The adapter populates each list item with a View object by calling getView on each row.
    // Adapter uses convertView as a way of recycling old objects that are no longer being used
    // so it can be said that it is optional but still the below syntax is the best to get rid of any accidental error
    // In this way, the ListView can send the Adapter old, "recycled" view objects that are no longer being displayed instead of instantiating an entirely new object each time the Adapter wants to display a new list item
    // So if a list is of 15 items, but window can show only 5 items, then at first convertView would be null, and we need to create new views for these five items, but when you scroll down, you have two options, either create 6-10 views, or re-use old views and load new data into these views. Adapter and convertView enables you to do the later method.
    // ArrayAdapter kind of uses this in its loop from 0->list.size()
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {           // position -> index of next element  | convertView -> the object we are placing in list as rows , can be made from scratch or be reused
        ViewHolder holder;                                                                  // holder === listview that we are populating
        if (convertView == null) {                                                          // if the listview we are populating isnt already inflated then inflate it
            convertView = layoutInflater.inflate(R.layout.item_list_layout, null);     // item_list_layout is the layout of each object i.e each row inside list view
            holder = new ViewHolder();
            holder.flagView = convertView.findViewById(R.id.imageView_flag);
            holder.countryNameView = convertView.findViewById(R.id.textView_countryName);
            holder.populationView = convertView.findViewById(R.id.textView_population);
            convertView.setTag(holder);
        } else {                                                                            // if already inflated the just get it
            holder = (ViewHolder) convertView.getTag();
        }

        // NOW INITIALIZING THE DATA OF ONE LIST ITEM
        Country country = this.listData.get(position);
        holder.countryNameView.setText(country.getCountryName());
        holder.populationView.setText("Population: " + country.getPopulation());

        int imageId = this.getMipmapResIdByName(country.getFlagName());

        holder.flagView.setImageResource(imageId);

        return convertView;                // we return a row , i.e an object //
    }                                      // that has a flag pic ,name and population text with number


    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName) {   // image mapper , idk how this works as of now
        String pkgName = context.getPackageName();
        System.out.println("pkgName=" + pkgName);

        int resID = context.getResources().getIdentifier(resName, "mipmap", pkgName);
        System.out.println("ResID=" + resID);

        Log.i("CustomListView", "Res Name: " + resName + "==> Res ID = " + resID);
        return resID;
    }

    static class ViewHolder {    // One object i.e row of listview | created for simplicity
        ImageView flagView;
        TextView countryNameView;
        TextView populationView;
    }

}