package co.com.prueba.api.questions;

import io.restassured.path.json.exception.JsonPathException;
import jline.internal.Log;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class Response implements Question<String> {

    private String field;


    public Response(String field) {
        this.field = field;
    }

    public static Response receivedIn(String field) {
        return new Response(field);
    }

    @Override
    public String answeredBy(Actor actor) {
        try {
            return lastResponse().jsonPath().getString(field).replace("[", "").replace("]", "");
        } catch (JsonPathException e) {
            Log.info(e);
            return "";
        }
    }
}
