package com.example.home.testeheycheff;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.home.testeheycheff.JSONParser.classtag;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static String url = "http://hp-api.herokuapp.com/api/characters";

    HashMap<String, Character> characterList = new HashMap<>();

    ArrayList<CharacterName> characterNames = new ArrayList<>();

    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;

    private static final String TAG_NAME = "name";
    private static final String TAG_SPECIES = "species";
    private static final String TAG_GENDER = "gender";
    private static final String TAG_HOUSE = "house";
    private static final String TAG_DATE_OF_BIRTH = "dateOfBirth";
    private static final String TAG_YEAR_OF_BIRTH = "yearOfBirth";
    private static final String TAG_ANCESTRY = "ancestry";
    private static final String TAG_EYE_COLOR = "eyeColour";
    private static final String TAG_HAIR_COLOR = "hairColour";

    private static final String TAG_WAND = "wand";
    private static final String TAG_WAND_WOOD = "wood";
    private static final String TAG_WAND_CORE = "core";
    private static final String TAG_WAND_LENGTH = "length";
    private static final String TAG_PATRONUS = "patronus";
    private static final String TAG_HOGWARTS_STUDENT = "hogwartsStudent";
    private static final String TAG_HOGWARTS_STAFF = "hogwartsStaff";
    private static final String TAG_ACTOR = "actor";
    private static final String TAG_ALIVE = "alive";
    private static final String TAG_IMAGE = "image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ProgressTask().execute(url);

        // Locate the ListView in list_view_main.xml
        list = (ListView) findViewById(R.id.listview);

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, characterNames);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);


        // Locate the EditText in list_view_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener(){


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                // The characters Name
                String searchText = ((TextView)view.findViewById(R.id.name)).getText().toString();
                Bundle bundle = new Bundle();
                Intent it = new Intent(MainActivity.this, CharacterActivity.class);
                bundle.putString("text", searchText);

                // New hashmap with only the character that we want
                Character newCharacter = characterList.get(searchText);

                // Send characters.name and object character
                it.putExtra("char", newCharacter);
                it.putExtras(bundle);
                startActivity(it);
            }

        };

        list.setOnItemClickListener (listener);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;

    }

    class ProgressTask extends AsyncTask<String, Void, Boolean> {

        private ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        JSONArray characters = null;
        String jString = null;

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }

        @Override
        protected Boolean doInBackground(String... strings) {

            JSONParser parser = new JSONParser(); // object of JSONParser

            jString = parser.makeHTTPCall(url);


            try {
                if (jString != null) {
                    characters = new JSONArray(jString);
                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }




                Log.e(classtag, "Response from URL: " + jString);
                try {
                    // Looping through all data
                    if (jString != null) {
                        if (characters != null) {
                            for (int i = 0; i < characters.length(); i++) {
                                JSONObject characterJSON = characters.getJSONObject(i);

                                Character character = new Character();
                                String name = characterJSON.getString(TAG_NAME);
                                character.setName(name);
                                character.setSpecies(characterJSON.getString(TAG_SPECIES));
                                character.setGender(characterJSON.getString(TAG_GENDER));
                                character.setHouse(characterJSON.getString(TAG_HOUSE));
                                character.setDateOfBirth(characterJSON.getString(TAG_DATE_OF_BIRTH));
                                character.setYearOfBirth(characterJSON.getString(TAG_YEAR_OF_BIRTH));
                                character.setAncestry(characterJSON.getString(TAG_ANCESTRY));
                                character.setEyeColour(characterJSON.getString(TAG_EYE_COLOR));
                                character.setHairColour(characterJSON.getString(TAG_HAIR_COLOR));

                                JSONObject wand = characterJSON.getJSONObject(TAG_WAND);
                                character.setWand(new Wand());
                                character.getWand().setWood(wand.getString(TAG_WAND_WOOD));
                                character.getWand().setCore(wand.getString(TAG_WAND_CORE));
                                character.getWand().setLength(wand.getString(TAG_WAND_LENGTH));

                                character.setPatronus(characterJSON.getString(TAG_PATRONUS));
                                character.setHogwartsStudent(characterJSON.getString(TAG_HOGWARTS_STUDENT));
                                character.setHogwartsStaff(characterJSON.getString(TAG_HOGWARTS_STAFF));
                                character.setActor(characterJSON.getString(TAG_ACTOR));
                                character.setAlive(characterJSON.getString(TAG_ALIVE));
                                character.setImage(characterJSON.getString(TAG_IMAGE));


                                // Add names to ArrayList
                                characterNames.add(new CharacterName(name));

                                // Adding each child node to Hashmap key -> value
                                characterList.put(name, character);

                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            return null;}

        }

    }









