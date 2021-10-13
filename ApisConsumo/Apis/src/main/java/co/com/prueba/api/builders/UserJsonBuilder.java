package co.com.prueba.api.builders;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yecht.Data;


import static co.com.prueba.api.utils.ReadFileAsString.readFileAsString;

public class UserJsonBuilder implements Builder<JSONObject> {

    private static final String FILE = "./src/test/resources/datadriven/crear.json";
    private static final Logger LOGGER = LoggerFactory.getLogger(UserJsonBuilder.class);


    private String json;

    @Override
    public JSONObject build() {
        try {
            json = readFileAsString(FILE);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        JSONParser jsonParser = new JSONParser();
        JSONObject userJson = null;
        try {
            userJson = (JSONObject) jsonParser.parse(json);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
        }


        String num=String.valueOf(Math.round(Math.random()*1000));
        userJson.put("email", "prueba"+num+"@gmail.com");
/*
        JSONObject userInformationobj = (JSONObject)((JSONObject)((JSONObject)((JSONObject) userJson
                .get("name"))
                .get("gender"))
                .get("status"))
                .get("email");
        userInformationobj.put("name",name);
        userInformationobj.put("gender",gender);
        userInformationobj.put("email",email);
        userInformationobj.put("status",status);*/

        return userJson;
    }


}
