package com.example.home.testeheycheff;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.home.testeheycheff.JSONParser.classtag;


 public class SearchActivity  extends ListActivity {

    private static String url = "http://hp-api.herokuapp.com/api/characters";

    private ListView lv;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> characterList = new ArrayList<>();

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
        setContentView(R.layout.activity_search);


        Bundle extras = getIntent().getExtras();
        String value;

        if (extras != null) {
            value = extras.getString("key");
        }

        new ProgressTask(SearchActivity.this).execute();
    }

    private class ProgressTask extends AsyncTask<String, Void, Boolean> {
        private ProgressDialog dialog;
        private ListActivity activity;

        public ProgressTask(ListActivity activity) {
            this.activity = activity;
            context = activity;
            dialog = new ProgressDialog(context);
        }

        private Context context;


        protected void onPreExecute() {
            this.dialog.setMessage("Progress start");
            this.dialog.show();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            ListAdapter adapter = new SimpleAdapter(context, characterList,
                    R.layout.list_item, new String[]{TAG_NAME, TAG_IMAGE}, new int[]{R.id.name, R.id.image});
            setListAdapter(adapter);
            lv = getListView();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            JSONParser parser = new JSONParser(); // object of JSONParser

            String jString = parser.makeHTTPCall(url);

            Log.e(classtag, "Response from URL: " + jString);

            if (jString != null) {
                try {
                    JSONObject jObj = new JSONObject(jString); //our json data starts with the object
                    JSONArray characters = jObj.getJSONArray("characterList");


                    // Looping through all data
                    for (int ind = 0; ind < characters.length(); ind++) {
                        JSONObject character = characters.getJSONObject(ind);

                        String name = character.getString(TAG_NAME);
                        String species = character.getString(TAG_SPECIES);
                        String gender = character.getString(TAG_GENDER);
                        String house = character.getString(TAG_HOUSE);
                        String dateOfBirth = character.getString(TAG_DATE_OF_BIRTH);
                        String yearOfBirth = character.getString(TAG_YEAR_OF_BIRTH);
                        String ancestry = character.getString(TAG_ANCESTRY);
                        String eyeColour = character.getString(TAG_EYE_COLOR);
                        String hairColour = character.getString(TAG_HAIR_COLOR);

                        JSONObject wand= character.getJSONObject(TAG_WAND);
                        String wood = character.getString(TAG_WAND_WOOD);
                        String core = character.getString(TAG_WAND_CORE);
                        String length = character.getString(TAG_WAND_LENGTH);

                        String patronus = character.getString(TAG_PATRONUS);
                        String hogwartsStudent = character.getString(TAG_HOGWARTS_STUDENT);
                        String hogwartsStaff = character.getString(TAG_HOGWARTS_STAFF);
                        String actor = character.getString(TAG_ACTOR);
                        String alive = character.getString(TAG_ALIVE);
                        String image = character.getString(TAG_IMAGE);

                        // Temporary HashMap for single data
                        HashMap<String, String> temp = new HashMap<>();

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
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from url");
            }
            return null;
        }
    }
}