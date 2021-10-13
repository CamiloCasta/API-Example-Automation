package co.com.prueba.api.tasks;

import co.com.prueba.api.utils.General;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import org.json.simple.JSONObject;
import org.yecht.Data;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.Tasks.instrumented;


public class CreateUser implements Task {

    private JSONObject jsonObject;

    public CreateUser(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public static Performable of(JSONObject jsonObject) {
        return instrumented(CreateUser.class, jsonObject);
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to(General.CREAR.toString()).with(request -> request
                .header("content-type","application/json")
                .header("Authorization", "Bearer " + General.ACCES_TOKEN.toString())
                .body(jsonObject)
                .log().all()
                .relaxedHTTPSValidation()));
        SerenityRest.lastResponse().prettyPrint();

        if (lastResponse().statusCode() == 201) {
            String id = lastResponse().jsonPath().getString("data.id");
            actor.remember("ident",id);
            System.out.println("Id del nuevo cliente"+id);


        } else {
            throw new IllegalStateException("Error at request for insurance issue");
        }
    }
}
