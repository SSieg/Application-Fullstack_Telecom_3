package fr.sorbonne.paris.nord.university.api;

import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.service.TeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Test
    public void givenPSGTeamId_whenFindTeamById_thenPSGTeamInResponse() {

        // Given.
        Long psgTeamId = 1L;

        // When.
        TeamEntity findTeamById = teamService.findTeamById(psgTeamId);

        // Then.
        String psgTeamName = "PSG";
        assertThat(findTeamById).isNotNull();
        assertThat(findTeamById.getName())
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(psgTeamName);
    }

    @Test
    public void shouldReturnEveryTeams_whenFindAllTeams() {

        List<TeamEntity> teams = teamService.findAllTeam();

        assertThat(teams).isNotNull().isNotEmpty();
    }


    @Test
    public void givenBayernTeamId_whenDeleteTeamById_thenBayernTeamNotInResponse() {

        // Given.
        Long bayernTeamId = 4L;
        // When.
        teamService.deleteTeamById(bayernTeamId);
        // Then.
        //assertThat(bayernTeamId).isNotNull();
        assertThat(teamService.findTeamById(bayernTeamId))
                .isNull();
    }

    @Test
    public void givenLiverpoolTeam_whenAddTeam_thenLiverpoolTeamInResponse() {

        // Given.
        TeamEntity liverpool = new TeamEntity(6L, "Liverpool", "You'll never walk alone ");
        // When.
        teamService.addTeam(liverpool);
        // Then.
        assertThat(liverpool).isNotNull();
    }

    @Test
    public void givenLiverpoolTeam_whenUpdateTeamLiverpool_thenLiverpoolTeamInResponseWithLFCAsName() {

        // Given.
        TeamEntity liverpool = new TeamEntity(6L, "Liverpool", "You'll never walk alone ");
        // When.
        liverpool.setName("LFC");
        teamService.updateTeam(liverpool);
        // Then.
        assertThat(liverpool.getName())
                .isNotNull()
                .isNotEmpty()
                .isEqualTo("LFC");
    }

}
