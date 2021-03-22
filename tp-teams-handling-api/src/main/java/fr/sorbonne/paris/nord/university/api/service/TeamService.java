package fr.sorbonne.paris.nord.university.api.service;

import fr.sorbonne.paris.nord.university.api.entity.TeamEntity;
import fr.sorbonne.paris.nord.university.api.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService (TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    public List <TeamEntity> findAllTeam() {
        return teamRepository.findAll();
    }

    public TeamEntity findTeamById(Long id) {
        return teamRepository.findById(id).orElse(null) ;
        //pay attention about using an optional because of the exception it throws
        //that's why we using orElse instead
    }

    @Transactional
    public void deleteTeamById(Long id) {
        teamRepository.deleteById(id);
    }

    @Transactional
    public TeamEntity addTeam (TeamEntity teamEntity){
        return teamRepository.save(teamEntity);
    }

    @Transactional
    public TeamEntity updateTeam (TeamEntity teamEntity){
        return teamRepository.save(teamEntity);
    }

}
