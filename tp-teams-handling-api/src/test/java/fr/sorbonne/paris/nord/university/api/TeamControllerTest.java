package fr.sorbonne.paris.nord.university.api;

import fr.sorbonne.paris.nord.university.api.controller.TeamController;
import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.mapper.TeamMapper;
import fr.sorbonne.paris.nord.university.api.service.TeamService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TeamControllerTest {

    @Mock
    private  TeamService teamService;
    @Autowired
    private TeamMapper teamMapper;

    @BeforeEach
    public void initialiseRestAssuredMockMvcStandalone() {
        RestAssuredMockMvc.standaloneSetup(new TeamController(teamService,teamMapper));
    }

    private final TeamEntity e1 = new TeamEntity(1L,"Barca", "Vamos");
    private final TeamEntity e2 = new TeamEntity(2L,"Real", "Vamonos");
    private final TeamEntity e3 = new TeamEntity(3L,"Seville", "Vamonosos");

    @Test
    public void shouldReturnStatus200_WhenGetTeamsTestWorks() {

        when(teamService.findAllTeam()).thenReturn(List.of(e1,e2,e3));
        given()
                .when()
                .get("teams")
                .then()
                .assertThat()
                .statusCode(200);

    }

    @Test
    public void shouldReturnStatus200_WhenGetTeamByIdTestWorks() {

        when(teamService.findTeamById(1L)).thenReturn(e1);
        given()
                .when()
                .get("teams/{id}",1L )
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldReturnStatus200_WhenCreateTeamTestWorks() {

        when(teamService.addTeam(Mockito.any()))
                .thenReturn(e1);
        given()
                .contentType("application/json")
                .body(teamMapper.fromEntityToDto(e1))
                .when()
                .post("createTeam")
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldReturnStatus200_WhenModifyTeamByIdTestWorks() {

        when(teamService.updateTeam(Mockito.any()))
                .thenReturn(e1);
        given()
                .contentType("application/json")
                .body(teamMapper.fromEntityToDto(e1))
                .when()
                .put("modifyTeamById/{id}",1L)
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldReturnStatus200_WhenDeleteTestWorks() {

        //when(teamService.deleteTeamById(1L));
        given()
                .contentType("application/json")
                .when()
                .delete("deleteTeamById/{id}", 1L)
                .then()
                .statusCode(200);
    }
}
