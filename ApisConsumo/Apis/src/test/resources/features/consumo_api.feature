Feature: Consumo apis de prueba

  #https://gorest.co.in/

  @UserStory=Xxx
Scenario: Registro nuevo usuario
  Given Api para pruebas
  When solicito la creacion de un nuevo usuario
  Then puedo ver que el usuario se creo exitosamente

  @UserStory=Xxx
  Scenario: Consultar registro
    Given Api para pruebas
    When consultar usuario
    Then puedo ver el usuario exitosamente

  @UserStory=Xxx
  Scenario: Comentar registro
    Given Api para pruebas
    When comentar registro de usuario
    Then el comentario fue aplicado exitosamente

  @UserStory=Xxx
  Scenario: Eliminar usuario
    Given Api para pruebas
    When elimino el usuario creado
    Then se elimina el usuario exitosamente