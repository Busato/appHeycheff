package com.example.home.testeheycheff;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText textFind  = (EditText) findViewById(R.id.textWritten);
        Button searchButton = (Button) findViewById(R.id.searchButton);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getBaseContext());


        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Pegar texto escrito
                String searchText = textFind.getText().toString();
                Bundle bundle = new Bundle();

                if(!searchText.isEmpty()){
                    Intent it = new Intent(MainActivity.this, SearchActivity.class);
                    bundle.putString("text", searchText);
                    it.putExtras(bundle);
                    startActivity(it);
                }
                else{
                    alertDialogBuilder.setTitle("Careful");
                    alertDialogBuilder.setMessage("You need do type something!");
                    alertDialogBuilder.setPositiveButton(" Ok ", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                }
            }

            });
    }
}
