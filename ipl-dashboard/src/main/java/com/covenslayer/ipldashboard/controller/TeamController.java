package com.covenslayer.ipldashboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.covenslayer.ipldashboard.model.Team;
import com.covenslayer.ipldashboard.repository.MatchRepository;
import com.covenslayer.ipldashboard.repository.TeamRepository;

@RestController
public class TeamController {
	
	private TeamRepository teamRepository;
	private MatchRepository matchRepository;
	
	

	public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
		super();
		this.teamRepository = teamRepository;
		this.matchRepository = matchRepository;
	}



	@GetMapping("team/{teamName}")
	public Team getTeam(@PathVariable String teamName) {
		Team team = this.teamRepository.findByTeamName(teamName);
		team.setMatches(matchRepository.findLatestMatchesbyTeam(teamName, 4));
		
		return team;
	}
}
