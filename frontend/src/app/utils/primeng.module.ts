import { NgModule } from '@angular/core';
import {
    TreeTableModule, SharedModule, FileUploadModule, DataGridModule,
    BreadcrumbModule, AutoCompleteModule, InputTextModule, ButtonModule,
    MessageModule, MessagesModule, ToolbarModule, TooltipModule
} from 'primeng/primeng';

@NgModule({
    declarations: [],
    imports: [
        TreeTableModule, SharedModule, FileUploadModule, DataGridModule,
        BreadcrumbModule, AutoCompleteModule, InputTextModule, ButtonModule,
        MessageModule, MessagesModule, ToolbarModule, TooltipModule
    ],
    exports: [
        TreeTableModule, SharedModule, FileUploadModule, DataGridModule,
        BreadcrumbModule, AutoCompleteModule, InputTextModule, ButtonModule,
        MessageModule, MessagesModule, ToolbarModule, TooltipModule
    ],
})

export class PrimeNGModule { }
