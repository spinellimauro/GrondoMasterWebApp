import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/usuarios/Login/login.component';
import { ConfirmarEmailComponent } from './components/usuarios/confirmacion-contrasena/confirmar-email.component';
import { UsuariosComponent } from './components/usuarios/usuarios/usuarios.component';
import { LoggedGuard } from './utils/logged.guard';
import { CambiarContrasenaComponent } from './components/usuarios/cambiarcontrasena/cambiarcontrasena.component';


const routes: Routes = [
  { path: 'home', redirectTo: '' },
  { path: '', component: UsuariosComponent, canActivate: [LoggedGuard], data: { label: 'Nuevo Pedido' } },
  { path: 'usuario/confirmar-email/:id/:code', component: ConfirmarEmailComponent, data: { label: 'Confirmar Email' } },
  { path: 'login', component: LoginComponent,  data: { label: 'Iniciar Sesión' } },
  { path: 'cambio-contrasena', component: CambiarContrasenaComponent, canActivate: [LoggedGuard],  data: { label: 'Cambiar Constraseña' } },
  // { path: 'usuarios', component: UsuariosComponent, canActivate: [LoggedGuard], data: { label: 'Consulta Usuarios' } },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true, onSameUrlNavigation: 'reload' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
