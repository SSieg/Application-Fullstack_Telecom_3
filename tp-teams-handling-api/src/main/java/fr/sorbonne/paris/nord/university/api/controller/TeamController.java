package fr.sorbonne.paris.nord.university.api.controller;

import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.mapper.TeamMapper;
import fr.sorbonne.paris.nord.university.api.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TeamController {

    private final TeamService teamService;
    private final TeamMapper teamMapper;

    @Autowired
    public TeamController(TeamService teamService, TeamMapper teamMapper) {
        this.teamService= teamService;
        this.teamMapper = teamMapper;
    }

    @GetMapping("/teams")
    public List<TeamDto> getTeams() {
        return teamService.findAllTeam()
                            .stream()
                            .map(teamMapper::fromEntityToDto)
                            .collect(Collectors.toList());
    }

    @RequestMapping("/teams/{id}")
    public TeamDto getTeamById(@PathVariable(value = "id") Long id) {
        return teamMapper
                .fromEntityToDto(teamService.findTeamById(id));
    }

    @PostMapping("/createTeam")
    public TeamDto createTeam(@RequestBody TeamDto teamDto){
        TeamEntity teamEntity = teamMapper.fromDtoToEntity(teamDto);
        return teamMapper
                .fromEntityToDto(teamService.addTeam(teamEntity));
    }

    @PutMapping("/modifyTeamById/{id}")
    public TeamDto modifyTeamById(@RequestBody TeamDto teamDto, @PathVariable(value = "id") Long id) {
        return teamMapper
                .fromEntityToDto(teamService.updateTeam(teamMapper.fromDtoToEntity(teamDto)));
    }

    @DeleteMapping("/deleteTeamById/{id}")
    public void deleteTeamById(@PathVariable(value = "id") Long id) {
        teamService.deleteTeamById(id);
    }


}
