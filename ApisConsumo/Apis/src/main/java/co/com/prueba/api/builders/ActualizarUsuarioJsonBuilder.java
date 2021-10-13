package co.com.prueba.api.builders;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static co.com.prueba.api.utils.ReadFileAsString.readFileAsString;

public class ActualizarUsuarioJsonBuilder implements Builder<JSONObject> {

    private static final String FILE = "./src/test/resources/datadriven/actualizar.json";
    private static final Logger LOGGER = LoggerFactory.getLogger(ActualizarUsuarioJsonBuilder.class);


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
        userJson.put("email", "pruebaActualizar"+num+"@gmail.com");

        return userJson;
    }


}
