package com.example.home.testeheycheff;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<CharacterName> characterList = null;
    private List<CharacterName> characterListPerm = null;

    private List<CharacterName>  characterNames;


    public ListViewAdapter(Context context, List<CharacterName> characterList) {
        mContext = context;
        this.characterList = characterList;
        inflater = LayoutInflater.from(mContext);
        this.characterNames = new ArrayList<>();
        this.characterListPerm = new ArrayList<>();
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {

        return characterList.size();
    }

    @Override
    public CharacterName getItem(int position) {

        return characterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);
            // Locate the TextViews in list_view_items.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(characterList.get(position).getCharacterName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase();
        if(characterListPerm.isEmpty()){
            for(int i =0; characterList.size()>i; i++){
                characterListPerm.add(characterList.get(i));

            }
        }
        characterNames.clear();
        characterNames.addAll(characterListPerm);
        characterList.clear();
        if (charText.length() == 0) {
            characterList.addAll(characterNames);
        } else {
            for (CharacterName wp : characterNames) {
                if (wp.getCharacterName().toLowerCase().contains(charText.toLowerCase())) {
                    characterList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}