package org.o7planning.simplelistview.DB.SQLite;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.o7planning.simplelistview.R;
import org.o7planning.simplelistview.utils.ToastUtil;

public class ActivitySqLite extends AppCompatActivity {

    DatabaseHelper myDb;
    TextView mEditNameText;
    TextView mEditSurnameText;
    TextView mEditMarksText;
    TextView mEditIdText;

    Button mAddDataButton;
    Button mViewAllButton;
    Button mDeleteButton;
    Button mUpdateButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqllite_database);

        myDb=new DatabaseHelper(this);

        mEditMarksText=findViewById(R.id.editText_Marks);
        mEditNameText=findViewById(R.id.editText_name);
        mEditSurnameText=findViewById(R.id.editText_surname);
        mEditIdText=findViewById(R.id.editText_id);

        mAddDataButton=findViewById(R.id.button_add);
        mDeleteButton=findViewById(R.id.button_delete);
        mViewAllButton=findViewById(R.id.button_viewAll);
        mUpdateButton=findViewById(R.id.button_update);

        addData();
        viewAll();
        updateData();
        deleteData();
    }

    public void addData() {
        mAddDataButton.setOnClickListener(v->{
            String name=mEditNameText.getText().toString();
            String surname=mEditSurnameText.getText().toString();
            String marks=mEditMarksText.getText().toString();
            boolean isInserted=myDb.insertData(name,surname,marks);

            if(isInserted)
            {
                ToastUtil.makeShortToast(this,"Data Inserted");
            }
            else{
                ToastUtil.makeShortToast(this,"Data not Inserted");
            }
        });

    }

    // getting data
    public void viewAll()
    {
        mViewAllButton.setOnClickListener(v->{

            Cursor res= myDb.getAllData();

            if(res.getCount()==0)
            {
                // show message
                ToastUtil.makeShortToast(this,"NO DATA FOUND");
                return;
            }

            // there is some data
            StringBuilder stringBuffer=new StringBuilder();   // StringBuffer too can be used , search the diff between the two
            while(res.moveToNext())
            {
                stringBuffer.append("Id :"+res.getString(0));
                stringBuffer.append("Name :"+res.getString(1));
                stringBuffer.append("Surname :"+res.getString(2));
                stringBuffer.append("Marks :"+res.getString(3));
            }

            // show all data
            showMessage("Data",stringBuffer.toString());
        });
    }

    // showing data
    public void showMessage(String title, String Message)
    {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setCancelable(true);
        alert.setTitle(title);
        alert.setMessage(Message);
        alert.show();
    }

    public void updateData()
    {
        mUpdateButton.setOnClickListener(v->{
            String name=mEditNameText.getText().toString();
            String surname=mEditSurnameText.getText().toString();
            String marks=mEditMarksText.getText().toString();
            String id=mEditIdText.getText().toString();

            boolean isUpdate= myDb.updateData(id,name,surname,marks);

            if(isUpdate)
            {
                ToastUtil.makeShortToast(this,"Updated");
            }
            else{
                ToastUtil.makeShortToast(this,"Not Updated");
            }
        });
    }

    public void deleteData(){

        mDeleteButton.setOnClickListener(v->{

            String id=mEditIdText.getText().toString();
            Integer status = myDb.deleteData(id);
            if(status>0)
            {
                ToastUtil.makeShortToast(this,"Data was deleted");
            }
            else{
                ToastUtil.makeShortToast(this,"Data not present by default");
            }
        });
    }

}
