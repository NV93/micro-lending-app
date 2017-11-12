package lt.nevytas;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

/**
 * Created by nevyt
 */
public class MyUtils {

    public static JSONObject parseStringToJson(String string) {

        JSONParser parser = new JSONParser(0);
        JSONObject parsedJson = null;
        try {
            parsedJson = (JSONObject) parser.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedJson;
    }

    public static String getUserJSONString(String personsId, String name, String surname, String address, String phone, String email) {
        String userString =
                  "{\"personsId\" : \" " + personsId
                + "\", \"name\" : \"" + name
                + "\", \"surname\" : \"" + surname
                + "\", \"address\" : \"" + address
                + "\", \"phoneNumber\" : \" " + phone
                + "\", \"email\" : \" " + email
                + "\"}";
        return userString;
    }

    public static String getLoanJSONString(double ammount, int term){
        String loanString =
                "{\"ammount\" : \" " + ammount
                + "\", \"term\" : \" " + term
                + "}";
        return loanString;
    }
}
