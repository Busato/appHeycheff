package com.example.home.testeheycheff;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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


        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Pegar texto escrito
                String searchText = textFind.getText().toString();
                Bundle bundle = new Bundle();

                if(searchText.trim().length()>0){
                    Intent it = new Intent(MainActivity.this, SearchActivity.class);
                    bundle.putString("key", searchText);
                    it.putExtras(bundle);
                    startActivity(it);
                }

                }
            });
    }
}
