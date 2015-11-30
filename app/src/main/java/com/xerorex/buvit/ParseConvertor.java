package com.xerorex.buvit;

/**
 * Created by LAViATHoR on 11/29/2015.
 */
import java.util.ArrayList;
import java.util.List;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseConvertor implements DataConvertor <ParseObject, UserProfile> {

    @Override

    //Converts an array of ParseObjects to an ArrayList of UserProfiles. 

    public void arrayToList(ParseObject[] array, List<UserProfile> list) {

        for(ParseObject i :array)
            list.add(convertToOutput(i));

    }

    @Override
    public void listToArray(List<ParseObject> list, UserProfile[] array) {

        for(int i = 0; i < list.size()+1; i++)
            array[i]= convertToOutput(list.get(i));

    }

    @Override
    public UserProfile convertToOutput(ParseObject x) {

        UserProfile temp = new UserProfile();

        temp.setFirst_Name(x.getString("first_name"));
        temp.setLast_Name(x.getString("last_name"));
        temp.setEmail_address(x.getString("email"));
        temp.setObjectID(x.getString("ObjectID"));
        temp.setNumOfPunches(x.getInt("punches"));



        return temp;
    }

    @Override
    public void convertToInput(UserProfile x, ParseObject y) {



    }


}