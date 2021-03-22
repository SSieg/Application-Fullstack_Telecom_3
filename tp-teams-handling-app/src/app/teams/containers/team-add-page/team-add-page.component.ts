import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {TeamService} from '@team-handling/teams/services/team.service';
import {Router} from '@angular/router';
import {Team} from '@team-handling/teams/models/team';

@Component({
  selector: 'app-team-add-page',
  templateUrl: './team-add-page.component.html',
  styleUrls: ['./team-add-page.component.scss']
})
export class TeamAddPageComponent implements OnInit {

  addTeamForm!: FormGroup; /**/

  constructor(private fb: FormBuilder,
              private teamService: TeamService,
              private router: Router) {

  }

  createTeam(){
    if (this.addTeamForm.invalid) {
      return;
    }

    const team: Team = {
      name: this.addTeamForm.value.name,
      slogan: this.addTeamForm.value.slogan
    };

    this.teamService
      .createTeam(team)
      .subscribe(_ => this.router.navigate(['teams']));
  }

  ngOnInit(): void {

    this.createForm();
  }
  private createForm(): void {
  this.addTeamForm = this.fb.group({
    name: ['', Validators.required],
    slogan: ['', Validators.required]
  });
}

}
