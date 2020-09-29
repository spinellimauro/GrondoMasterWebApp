import { NgModule } from '@angular/core';
import {
  MatIconModule, MatButtonModule, MatToolbarModule, MatSidenavModule,
  MatListModule, MatCardModule, MatInputModule, MatDialogModule,
  MatTooltipModule, MatStepperModule, MatSelectModule, MatCheckboxModule,
  MatTabsModule, MatDatepickerModule, MatNativeDateModule, MatExpansionModule, MatRadioModule, MatMenuModule,
} from '@angular/material';

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
