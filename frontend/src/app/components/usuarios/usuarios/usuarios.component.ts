import { Component, OnInit } from "@angular/core";
import { Usuario } from "src/app/models/Usuario";
import { UsuariosService } from "../usuarioService";
import { ConfigService } from "src/app/utils/config.service";
import { MatDialog } from "@angular/material/dialog";
import { ToastrService } from "ngx-toastr";
import { UsuarioDialog } from "src/app/dialogs/usuario/usuario.dialog";

@Component({
  selector: "usuarios-root",
  templateUrl: "./usuarios.component.html",
  styleUrls: ["./usuarios.component.scss"],
})
export class UsuariosComponent implements OnInit {
  usuarios: Usuario[] = [];

  cols: any[] = [];

  constructor(
    private usuarioService: UsuariosService,
    private toastyService: ToastrService,
    private configService: ConfigService,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    // this.usuarioService.getUsuarios().subscribe((usuarios: Usuario[]) => this.usuarios = usuarios);
    // this.cols = [
    //   { field: 'nombre', header: 'Nombre' },
    //   { field: 'apellido', header: 'Apellido' },
    //   { field: 'email', header: 'Email' },
    //   { field: 'habilitacion', header: 'Habilitación' }
    // ];
  }

  // AgregarUsuario() {
  //   this.dialog
  //     .open(UsuarioDialog, { width: '500px', height: 'auto', data: {} })
  //     .afterClosed()
  //     .subscribe(data => {

  //       if (data != null) {
  //         this.usuarios.push(data);
  //         this.usuarios = [...this.usuarios];

  //         this.toastyService.success(
  //           this.configService.mensajes.TituloSuccess,
  //           this.configService.mensajes.AUsuario
  //         );
  //       }

  //     });
  // }

  // editar(userId: string) {
  //   this.usuarioService
  //     .getUsuario(userId)
  //     .subscribe(usuario => {
  //       this.dialog
  //         .open(UsuarioDialog, { width: '500px', height: 'auto', data: { usuario } })
  //         .afterClosed()
  //         .subscribe(data => {

  //           if (data != null) {
  //             const index = this.usuarios.findIndex(u => u.id == userId);
  //             this.usuarios.splice(index, 1);

  //             this.usuarios.push(data);
  //             this.usuarios = [...this.usuarios];

  //             this.toastyService.success(
  //               this.configService.mensajes.MUsuario,
  //               this.configService.mensajes.TituloSuccess
  //             );
  //           }

  //         });
  //     });
  // }

  // borrar(userId: string) {
  //   this.usuarioService
  //     .deleteUsuario(userId)
  //     .subscribe(() => {

  //       const index = this.usuarios.findIndex(u => u.id === userId);
  //       this.usuarios.splice(index, 1);

  //       this.usuarios = [...this.usuarios];

  //       this.toastyService.success(
  //         this.configService.mensajes.BUsuario,
  //         this.configService.mensajes.TituloSuccess
  //       );

  //     })

  // }
}
