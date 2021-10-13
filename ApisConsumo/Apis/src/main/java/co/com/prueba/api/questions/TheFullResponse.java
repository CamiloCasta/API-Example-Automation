package co.com.prueba.api.questions;

import io.restassured.path.json.exception.JsonPathException;
import jline.internal.Log;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class TheFullResponse implements Question<String> {

    public static TheFullResponse is() {
        return new TheFullResponse();
    }

    @Override
    public String answeredBy(Actor actor) {
        try {
            return lastResponse().asString();
        } catch (JsonPathException e) {
            Log.info(e);
            return "";
        }
    }
}
