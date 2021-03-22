import {Component, OnInit} from '@angular/core';
import {Team} from '@team-handling/teams/models/team';
import {TeamService} from '@team-handling/teams/services/team.service';
import {ActivatedRoute, Router} from '@angular/router';
import {mergeMap} from 'rxjs/operators';

@Component({
  selector: 'app-team-detail-page',
  templateUrl: './team-detail-page.component.html',
  styleUrls: ['./team-detail-page.component.scss']
})
export class TeamDetailPageComponent implements OnInit {

  team: Team | null;

  constructor(private teamService: TeamService,
              private route: ActivatedRoute,
              private router: Router
  ) {
    this.team = null;
  }

  ngOnInit(): void {
    this.teamService.pullTeam().subscribe(team => this.team = team);
    //  this.route.params.pipe(
    //  mergeMap(params => this.teamService.getTeamById(params['id'])))
    //  .subscribe(team => this.team = team);
  }

  redirect(): void {
    this.router.navigate([`teams`]);
  }

}
