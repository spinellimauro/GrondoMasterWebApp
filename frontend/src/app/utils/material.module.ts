import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatStepperModule } from '@angular/material/stepper';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';

@NgModule({
  imports: [
    MatIconModule, MatButtonModule, MatToolbarModule, MatSidenavModule,
    MatListModule, MatCardModule, MatInputModule, MatDialogModule,
    MatTooltipModule, MatStepperModule, MatSelectModule, MatCheckboxModule,
    MatTabsModule, MatDatepickerModule, MatNativeDateModule, MatExpansionModule, MatRadioModule,
    MatMenuModule
  ],
  exports: [
    MatIconModule, MatButtonModule, MatToolbarModule, MatSidenavModule,
    MatListModule, MatCardModule, MatInputModule, MatDialogModule,
    MatTooltipModule, MatStepperModule, MatSelectModule, MatCheckboxModule,
    MatTabsModule, MatDatepickerModule, MatNativeDateModule, MatExpansionModule, MatRadioModule, MatMenuModule
  ]
})

export class MaterialModule { }
