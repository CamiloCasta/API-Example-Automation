package stepdefinitions;


import co.com.prueba.api.builders.ActualizarUsuarioJsonBuilder;
import co.com.prueba.api.builders.UserJsonBuilder;
import co.com.prueba.api.exceptions.ResponseCodeIncorrect;
import co.com.prueba.api.questions.TheFullResponse;
import co.com.prueba.api.tasks.ActualizarUsuario;
import co.com.prueba.api.tasks.ConsultarUsiarios;
import co.com.prueba.api.tasks.CreateUser;
import co.com.prueba.api.tasks.EliminarUsuario;
import co.com.prueba.api.utils.General;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;
import org.json.simple.JSONObject;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static org.hamcrest.Matchers.containsString;

public class ConsumoApiStepDefinition {

    private String theRestApiBaseUrl;
    private EnvironmentVariables environmentVariables;
    public static final String MENSAJE_ERROR_EN_RESPONSE = "The Response Code is not correct ";

    @Before
    public void prepareStage() {
       OnStage.setTheStage(new OnlineCast());
    }

    @Before
    public void configureBaseUrl() {
        theRestApiBaseUrl = environmentVariables.optionalProperty("restapi.baseurl")
                .orElse(General.URL_BASE.toString());
    }

    @Given("Api para pruebas")
    public void apiParaPruebas() {
        theActorCalled("Camilo");
        theActorInTheSpotlight().whoCan(CallAnApi.at(theRestApiBaseUrl));

    }
    @When("solicito la creacion de un nuevo usuario")
    public void solicitoLaCreacionDeUnNuevoUsuario() {
        JSONObject crearusuario = new UserJsonBuilder().build();
        theActorInTheSpotlight().attemptsTo(CreateUser.of(crearusuario));

    }
    @Then("puedo ver que el usuario se creo exitosamente")
    public void puedoVerQueElUsuarioSeCreoExitosamente() {
       // theActorInTheSpotlight().should(GivenWhenThen.seeThat(Response.receivedIn("status"), Matchers.is("success")).orComplainWith(ResponseStatusIncorrectException.class, "The Response status is not created"));
        theActorInTheSpotlight().should(seeThat(TheFullResponse.is(), containsString("data")).orComplainWith(ResponseCodeIncorrect.class, MENSAJE_ERROR_EN_RESPONSE));

    }

    @When("consultar usuario")
    public void consultarUsuario() {
        JSONObject usuario = new UserJsonBuilder().build();
        theActorInTheSpotlight().attemptsTo(ConsultarUsiarios.of(usuario));
    }
    @Then("puedo ver el usuario exitosamente")
    public void puedoVerElUsuarioExitosamente() {
        theActorInTheSpotlight().should(seeThat(TheFullResponse.is(), containsString("data")).orComplainWith(ResponseCodeIncorrect.class, MENSAJE_ERROR_EN_RESPONSE));

    }

    @When("comentar registro de usuario")
    public void comentarRegistroDeUsuario() {
        JSONObject actualizarusuario = new ActualizarUsuarioJsonBuilder().build();
        theActorInTheSpotlight().attemptsTo(ActualizarUsuario.of(actualizarusuario));

    }
    @Then("el comentario fue aplicado exitosamente")
    public void elComentarioFueAplicadoExitosamente() {
        theActorInTheSpotlight().should(seeThat(TheFullResponse.is(), containsString("data")).orComplainWith(ResponseCodeIncorrect.class, MENSAJE_ERROR_EN_RESPONSE));
    }

    @When("elimino el usuario creado")
    public void eliminoElUsuarioCreado() {
        JSONObject usuario = new UserJsonBuilder().build();
        theActorInTheSpotlight().attemptsTo(EliminarUsuario.conId());

    }
    @Then("se elimina el usuario exitosamente")
    public void seEliminaElUsuarioExitosamente() {
        theActorInTheSpotlight().should(String.valueOf(SerenityRest.lastResponse().statusCode()==200));
    }

}
