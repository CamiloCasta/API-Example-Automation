package co.com.prueba.api.tasks;

import co.com.prueba.api.utils.General;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import org.json.simple.JSONObject;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.Tasks.instrumented;


public class ConsultarUsiarios implements Task {



    public static Performable of(JSONObject jsonObject) {
        return instrumented(ConsultarUsiarios.class, jsonObject);
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource(General.CREAR.toString()).with(request -> request
                .header("content-type","application/json")
                .log().all()
                .relaxedHTTPSValidation()));
        SerenityRest.lastResponse().prettyPrint();

        if (lastResponse().statusCode() == 200) {
            String total = lastResponse().jsonPath().getString("meta.pagination.total");
            System.out.println(total);

        } else {
            throw new IllegalStateException("Error at request for insurance issue");
        }
    }
}
