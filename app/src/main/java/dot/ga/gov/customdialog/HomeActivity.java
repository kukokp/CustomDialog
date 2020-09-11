package dot.ga.gov.customdialog;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements DataAdapter.RecyclerViewItemClickListener {
    Button clickButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        clickButton = (Button) findViewById(R.id.button);
    }

    CustomListViewDialog customDialog;

    public void clickHere(View view) {

        List<Fruits> mStrings = new ArrayList<Fruits>();
        for (int i = 1; i < 301; i++) {
            Fruits fruits = new Fruits();
            fruits.setId(i);
            if (i % 5 == 0)
                fruits.setFruitName(String.valueOf(i) + " Name : ok i % 5 ");
            else if (i % 7 == 0 )
                fruits.setFruitName(String.valueOf(i) + " Name : YY OK i % 7 ");
            else if (i % 11 == 0 )
                fruits.setFruitName(String.valueOf(i) + " Name : ZZ YY OK i % 11 ");
            else if (i % 13 == 0 )
                fruits.setFruitName(String.valueOf(i) + " Name : YY O A  i % 13 ");
            else if (i % 17 == 0 )
                fruits.setFruitName(String.valueOf(i) + " Name : YY O B  i % 17 ");
            else if (i % 19== 0 )
                fruits.setFruitName(String.valueOf(i) + " Name : YY O CC  i % 19 ");
            else if (i % 23== 0 )
                fruits.setFruitName(String.valueOf(i) + " Name : YY O DD  i % 23 ");
            else if (i % 29== 0 )
                fruits.setFruitName(String.valueOf(i) + " Name : YY O KK  i % 29 ");
            else if (i % 31== 0 )
                fruits.setFruitName(String.valueOf(i) + " Name : YY O LL  i % 31 ");
            else if (i % 37== 0 )
                fruits.setFruitName(String.valueOf(i) + " Name : YY O NN  i % 37 ");
            else
                fruits.setFruitName(String.valueOf(i) + " Name : ZZ KK");

            mStrings.add(fruits);
        }

        DataAdapter dataAdapter = new DataAdapter(mStrings, this);
        customDialog = new CustomListViewDialog(HomeActivity.this, dataAdapter);

        customDialog.show();
        customDialog.setCanceledOnTouchOutside(false);


    }

    @Override
    public void clickOnItem(String data) {
        clickButton.setText(data);

        if (customDialog != null) {
            customDialog.dismiss();
        }
    }
}
