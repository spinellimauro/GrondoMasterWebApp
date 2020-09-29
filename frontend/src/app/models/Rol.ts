export interface Rol {
    id: string
    name: string;
    list: string[];

    CanEnter(url: string): String;

}

export class RolAdmin implements Rol {

    id: string;
    name: string;
    list: string[];

    CanEnter(url: string) {
        return url;
    }

}

export class RolUser implements Rol {

    id: string;
    name: string;
    list: string[] = ['/cambio-contrasena', '/'];

    CanEnter(url: string) {
        return this.list.find(u => url.search(u) == 0);
    }

}