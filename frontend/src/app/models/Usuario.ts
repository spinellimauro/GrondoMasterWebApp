import { Rol } from './Rol';

export class Usuario {
    id: string;
    nombre: string;
    apellido: string;
    email: string;
    // password: string;
    // emailConfirmed: boolean;
    habilitacion: boolean;
    userId: string
    Roles: Rol[]
}