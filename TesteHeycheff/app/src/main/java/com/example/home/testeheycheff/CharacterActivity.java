package com.example.home.testeheycheff;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CharacterActivity extends Activity {

    Character character = new Character();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        // Retrieve data from mainActivity
        Intent intent = getIntent();
        character = (Character) intent.getSerializableExtra("char");


        // Image
        String url = character.getImage();
        new RetrieveFeedTask().execute(url);

        // Name
        TextView name = (TextView) findViewById(R.id.name_character);
        name.setText("Name : " + character.getName());

        // Species
        TextView species = (TextView) findViewById(R.id.species_character);
        species.setText("Species : " + character.getSpecies());

        // Gender
        TextView gender = (TextView) findViewById(R.id.gender_character);
        gender.setText("Gender : " + character.getGender());

        // House
        TextView house = (TextView) findViewById(R.id.house_character);
        if(!character.getHouse().isEmpty()) {
            house.setText("House : " + character.getHouse());
        }else{
            house.setText("House : Unknown");
        }

        // DateOfBirth
        TextView dateOfBirth = (TextView) findViewById(R.id.dateOfBirth_character);
        if(!character.getDateOfBirth().isEmpty()) {
            dateOfBirth.setText("Date of Birth : " + character.getDateOfBirth());
        }else{
            dateOfBirth.setText("Date of Birth : Unknown");
        }

        // YearOfBirth
        TextView yearOfBirth = (TextView) findViewById(R.id.yearOfBirth_character);
        if(!character.getYearOfBirth().isEmpty()) {
            yearOfBirth.setText("Year of Birth : " + character.getYearOfBirth());
        }else{
            yearOfBirth.setText("Year of Birth  : Unknown");
        }

        // Ancestry
        TextView ancestry = (TextView) findViewById(R.id.ancestry_character);
        if(!character.getAncestry().isEmpty()) {
            ancestry.setText("Ancestry : " + character.getAncestry());
        }else{
            ancestry.setText("Ancestry : Unknown");
        }


        // EyeColour
        TextView eyeColour = (TextView) findViewById(R.id.eyeColour_character);
        if(!character.getEyeColour().isEmpty()) {
            eyeColour.setText("Eye Colour : " + character.getEyeColour());
        }else{
            eyeColour.setText("Eye Colour  : Unknown");
        }


        // HairColour
        TextView hairColour = (TextView) findViewById(R.id.hairColour_character);
        if(!character.getHairColour().isEmpty()) {
            hairColour.setText("Hair Colour : " + character.getHairColour());
        }else{
            hairColour.setText("Hair Colour : Unknown");
        }


        // Wood
        TextView wood = (TextView) findViewById(R.id.wood_character);
        if(!character.getWand().getWood().isEmpty()) {
            wood.setText("Wand Wood : " + character.getWand().getWood());
        }else{
            wood.setText("Wand Wood : Unknown");
        }


        // Core
        TextView core = (TextView) findViewById(R.id.core_character);
        if(!character.getWand().getCore().isEmpty()) {
            core.setText("Wand Core : " + character.getWand().getCore());
        }else{
            core.setText("Wand Core : Unknown");
        }


        // Length
        TextView length = (TextView) findViewById(R.id.length_character);
        if(!character.getWand().getLength().isEmpty()) {
            length.setText("Wand Length : " + character.getWand().getLength());

        }else{
            length.setText("Wand Length : Unknown");
        }


        // Patronus
        TextView patronus = (TextView) findViewById(R.id.patronus_character);
        if(!character.getPatronus().isEmpty()) {
            patronus.setText("Patronus : " + character.getPatronus());

        }else{
            patronus.setText("Patronus : Unknown");
        }

        // HogwartsStudent
        TextView hogwartsStudent = (TextView) findViewById(R.id.hogwartsStudent_character);
        if(!character.getHogwartsStudent().isEmpty()) {
            hogwartsStudent.setText("Its a Hogwarts student : " + character.getHogwartsStudent());

        }else{
            hogwartsStudent.setText("Its a Hogwarts student : Unknown");
        }


        // HogwartsStaff
        TextView hogwartsStaff = (TextView) findViewById(R.id.hogwartsStaff_character);
        if(!character.getHogwartsStaff().isEmpty()) {
            hogwartsStaff.setText("Its a Hogwarts staff member : " + character.getHogwartsStaff());

        }else{
            hogwartsStaff.setText("Its a Hogwarts staff member : Unknown");
        }


        // Actor
        TextView actor = (TextView) findViewById(R.id.actor_character);
        if(!character.getActor().isEmpty()) {
            actor.setText("Actor : " + character.getActor());

        }else{
            actor.setText("Actor : Unknown");
        }


        // Alive
        TextView alive = (TextView) findViewById(R.id.alive_character);
        if(!character.getAlive().isEmpty()) {
            alive.setText("Its Alive : " + character.getAlive());

        }else{
            alive.setText("Its Alive : Unknown");
        }

    }

    class RetrieveFeedTask extends AsyncTask<String, Void, Boolean> {

        private ProgressDialog dialog = new ProgressDialog(CharacterActivity.this);



        @Override
        protected Boolean doInBackground(String... strings) {
            final ImageView i = (ImageView) findViewById(R.id.image_character);
            Bitmap   bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(character.getImage()).getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }

            final Bitmap finalBitmap = bitmap;
            runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        i.setImageBitmap(finalBitmap);
                    }
                });
                   return null;}
            }
    }


