package co.com.prueba.api.tasks;

import co.com.prueba.api.utils.General;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Patch;
import net.serenitybdd.screenplay.rest.interactions.Put;
import org.json.simple.JSONObject;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.Tasks.instrumented;


public class ActualizarUsuario implements Task {

    private JSONObject jsonObject;

    public ActualizarUsuario(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public static Performable of(JSONObject jsonObject) {
        return instrumented(ActualizarUsuario.class, jsonObject);
    }
    @Override
    public <T extends Actor> void performAs(T actor) {

        int num=3048;
        actor.attemptsTo(Patch.to(General.CREAR.toString() + "/" + num).with(request -> request
                .header("content-type","application/json")
                .header("Authorization", "Bearer " + General.ACCES_TOKEN.toString())
                .body(jsonObject)
                .log().all()
                .relaxedHTTPSValidation()));
        SerenityRest.lastResponse().prettyPrint();
        System.out.println("Status code de respuesta : "+lastResponse().statusCode());

        if (lastResponse().statusCode() == 200) {
            String data = lastResponse().jsonPath().getString("data");
            System.out.println(data);

        } else {
            throw new IllegalStateException("Error at request for insurance issue");
        }
    }
}
