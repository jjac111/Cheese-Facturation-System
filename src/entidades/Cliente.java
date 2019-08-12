package entidades;

public class Cliente {

    private String nombre;

    private String direccion;

    private int telefono;

    public Cliente() {
        nombre = "";
        direccion = "";
        telefono = 0;
    }

    public Cliente(String nom, String dir, int tel) throws myException {
        if (tel == 0) {
            throw new myException(0);
        }
        if (tel < 0) {
            throw new myException(1);
        }
        nombre = nom;
        direccion = dir;
        telefono = tel;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(int telefono) throws myException {
        if (telefono == 0) {
            throw new myException(0);
        }
        if (telefono < 0) {
            throw new myException(1);
        }
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getTelefono() {
        return telefono;
    }

}
