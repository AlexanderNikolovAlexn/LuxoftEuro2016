package com.samodeika.json;

import com.samodeika.entity.Player;
import com.samodeika.utils.NumberUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Alexander Nikolov on 13.6.2016 ?..
 */
public class JsonProcessorImpl implements JsonProcessor {

    @Override
    public List<Player> processFile(String fileContent) {
        List<Player> players = new ArrayList<>();
        if(fileContent == null || fileContent.isEmpty()) {
            // return empty List
            return players;
        }

        JSONObject jsonObject = new JSONObject(fileContent);
        Iterator<?> keys = jsonObject.keys();
        while(keys.hasNext()) {
            JSONObject json = jsonObject.getJSONObject((String) keys.next());
            JSONArray array = json.getJSONArray("Players");
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String name = obj.getString("name");
                String bio = obj.getString("bio");
                boolean isPhoto = (obj.getString("photo done?").equalsIgnoreCase("yes"));
                String specialPlayer = obj.getString("special player? (eg. key player, promising talent, etc)");
                String position = obj.getString("position");
                Integer number = NumberUtils.parseInt(obj.getString("number"));
                int caps = obj.getInt("caps");
                int goals = obj.getInt("goals for country");
                String club = obj.getString("club");
                String league = obj.getString("league");
                Date birthDate = null;
                try {
                    birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(obj.getString("date of birth"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Double rating1 = NumberUtils.parseDouble(obj.getString("rating_match1"));
                Double rating2 = NumberUtils.parseDouble(obj.getString("rating_match2"));
                Double rating3 = NumberUtils.parseDouble(obj.getString("rating_match3"));
                Player p = new Player(name, bio, isPhoto, specialPlayer, position, number, caps, goals, club, league,
                        birthDate, rating1, rating2, rating3);
//                System.out.println(p);
                players.add(p);
            }
        }
        return players;
    }

}
