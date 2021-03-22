import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TeamListPageComponent} from './containers/team-list-page/team-list-page.component';
import {MatCardModule} from '@angular/material/card';
import {TeamDetailPageComponent} from './containers/team-detail-page/team-detail-page.component';
import {MatButtonModule} from '@angular/material/button';
import {TeamAddPageComponent} from './containers/team-add-page/team-add-page.component';
import {ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';


@NgModule({
  declarations: [
    TeamListPageComponent,
    TeamDetailPageComponent,
    TeamAddPageComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,

    MatFormFieldModule,
    MatCardModule,
    MatButtonModule,
    MatInputModule,
  ]
})
export class TeamsModule {
}
