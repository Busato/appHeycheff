package com.example.home.testeheycheff;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.home.testeheycheff.JSONParser.classtag;


public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static String url = "http://hp-api.herokuapp.com/api/characters";

    ArrayList<HashMap<String, String>> characterList = new ArrayList<>();

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

        new GetJSONTask().execute(url);

        // Locate the ListView in list_view_main.xml
        list = (ListView) findViewById(android.R.id.list);

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, characterNames);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in list_view_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

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

    class GetJSONTask extends AsyncTask<String, Void, JSONObject> {

        private ProgressDialog dialog;
        JSONArray characters = null;
        String jString = null;

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Progress start");
            this.dialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... strings) {

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
                                JSONObject character = characters.getJSONObject(i);

                                String name = character.getString(TAG_NAME);
                                String species = character.getString(TAG_SPECIES);
                                String gender = character.getString(TAG_GENDER);
                                String house = character.getString(TAG_HOUSE);
                                String dateOfBirth = character.getString(TAG_DATE_OF_BIRTH);
                                String yearOfBirth = character.getString(TAG_YEAR_OF_BIRTH);
                                String ancestry = character.getString(TAG_ANCESTRY);
                                String eyeColour = character.getString(TAG_EYE_COLOR);
                                String hairColour = character.getString(TAG_HAIR_COLOR);

                                JSONObject wand = character.getJSONObject(TAG_WAND);
                                String wood = wand.getString(TAG_WAND_WOOD);
                                String core = wand.getString(TAG_WAND_CORE);
                                String length = wand.getString(TAG_WAND_LENGTH);

                                String patronus = character.getString(TAG_PATRONUS);
                                String hogwartsStudent = character.getString(TAG_HOGWARTS_STUDENT);
                                String hogwartsStaff = character.getString(TAG_HOGWARTS_STAFF);
                                String actor = character.getString(TAG_ACTOR);
                                String alive = character.getString(TAG_ALIVE);
                                String image = character.getString(TAG_IMAGE);

                                // Temporary HashMap for single data
                                HashMap<String, String> temp = new HashMap<>();


                                // Add names to ArrayList
                                characterNames.add(new CharacterName(name));

                                // Adding each child node to Hashmap key -> value
                                temp.put(TAG_NAME, name);
                                temp.put(TAG_SPECIES, species);
                                temp.put(TAG_GENDER, gender);
                                temp.put(TAG_HOUSE, house);
                                temp.put(TAG_DATE_OF_BIRTH, dateOfBirth);
                                temp.put(TAG_YEAR_OF_BIRTH, yearOfBirth);
                                temp.put(TAG_ANCESTRY, ancestry);
                                temp.put(TAG_EYE_COLOR, eyeColour);
                                temp.put(TAG_HAIR_COLOR, hairColour);
                                temp.put(TAG_WAND_WOOD, wood);
                                temp.put(TAG_WAND_CORE, core);
                                temp.put(TAG_WAND_LENGTH, length);
                                temp.put(TAG_PATRONUS, patronus);
                                temp.put(TAG_HOGWARTS_STUDENT, hogwartsStudent);
                                temp.put(TAG_HOGWARTS_STAFF, hogwartsStaff);
                                temp.put(TAG_ACTOR, actor);
                                temp.put(TAG_ALIVE, alive);
                                temp.put(TAG_IMAGE, image);

                                //Adding user to userList
                                characterList.add(temp);
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            return null;}

        }

    }









