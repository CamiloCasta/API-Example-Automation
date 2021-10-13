package co.com.prueba.api.tasks;

import co.com.prueba.api.builders.UserJsonBuilder;
import co.com.prueba.api.utils.General;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static net.serenitybdd.rest.SerenityRest.lastResponse;



public class EliminarUsuario implements Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(EliminarUsuario.class);
    @Override
    public <T extends Actor> void performAs(T actor) {
        int num=3048;
        actor.attemptsTo(Delete.from(General.CREAR.toString()+"/"+num).with(request -> request
                .header("content-type","application/json")
                .header("Authorization", "Bearer " + General.ACCES_TOKEN.toString())
                .log().all()
                .relaxedHTTPSValidation()));
        SerenityRest.lastResponse().prettyPrint();
        System.out.println("Status code de respuesta : "+lastResponse().statusCode());
        if (lastResponse().statusCode() == 204) {
            LOGGER.info("Eliminado Exitosamente");

        } else {
            throw new IllegalStateException("Error at request for insurance issue");
        }
    }

    public static EliminarUsuario conId(){
        return Tasks.instrumented(EliminarUsuario.class);
    }
}
