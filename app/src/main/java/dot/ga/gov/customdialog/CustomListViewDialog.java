package dot.ga.gov.customdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomListViewDialog extends Dialog implements View.OnClickListener{


    public CustomListViewDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public CustomListViewDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    public Activity activity;
    public Dialog dialog;
    public Button yes, no;
    TextView title;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    DataAdapter  adapter;
    public EditText edtSearch;
    public ImageView imgSearchCancel;



    public CustomListViewDialog(Activity a, DataAdapter adapter) {
        super(a);
        this.activity = a;
        this.adapter = adapter;
        setupLayout();
    }

    private void setupLayout() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_layout);

        yes =  findViewById(R.id.yes);
        no =  findViewById(R.id.no);
        title = findViewById(R.id.title);
        edtSearch = findViewById(R.id.edtSearch);
        imgSearchCancel = findViewById(R.id.imgSearchCancel);
        recyclerView = findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(adapter);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        imgSearchCancel.setOnClickListener(this);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (adapter != null) {
                    adapter.getFilter().filter(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yes:
                //Do Something
                break;
            case R.id.no:
                dismiss();
                break;
            case R.id.imgSearchCancel:
                edtSearch.setText("");
                break;
            default:
                break;
        }
    }
}