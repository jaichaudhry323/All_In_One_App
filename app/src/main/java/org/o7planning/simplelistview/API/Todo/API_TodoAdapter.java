package org.o7planning.simplelistview.API.Todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.o7planning.simplelistview.API.Todo.API_Todo;
import org.o7planning.simplelistview.R;

import java.util.ArrayList;

public class API_TodoAdapter extends ArrayAdapter<API_Todo> {

    Context mContext;
    ArrayList<API_Todo> mTodoList;

    public API_TodoAdapter(Context context, ArrayList<API_Todo> todolist) {
        super(context, R.layout.todo_item_layout, todolist);
        mContext = context;
        mTodoList = todolist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.todo_item_layout, null, false);
        }

        TextView TitleTextView = convertView.findViewById(R.id.todo_title);
        TextView UseridTextView = convertView.findViewById(R.id.todo_userid);
        TextView IdTextView = convertView.findViewById(R.id.todo_id);
        TextView CompletedTextView = convertView.findViewById(R.id.todo_completed);
        TextView UserNumberTextView = convertView.findViewById(R.id.api_user_number);

        API_Todo obj = mTodoList.get(position);

        TitleTextView.setText("title : " + obj.getmTitle());
        UseridTextView.setText("UserId : " + obj.getmUserId());
        IdTextView.setText("Id : " + obj.getmId());
        CompletedTextView.setText("completed : " + obj.getmCompleted());
        UserNumberTextView.setText("User N.o : " + String.valueOf(position));

        return convertView;
    }

}
