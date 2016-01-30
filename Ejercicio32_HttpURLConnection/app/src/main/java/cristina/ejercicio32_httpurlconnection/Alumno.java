package cristina.ejercicio32_httpurlconnection;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cristina on 20/01/2016.
 */
public class Alumno implements Parcelable {
    private String nombre, telefono, direccion, curso, foto;
    private int edad;
    private boolean repetidor;

    public Alumno(String nombre, String telefono, String direccion, String curso, String foto, int edad, boolean repetidor){
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.curso = curso;
        this.foto = foto;
        this.edad = edad;
        this.repetidor = repetidor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isRepetidor() {
        return repetidor;
    }

    public void setRepetidor(boolean repetidor) {
        this.repetidor = repetidor;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.telefono);
        dest.writeString(this.direccion);
        dest.writeString(this.curso);
        dest.writeString(this.foto);
        dest.writeInt(this.edad);
        dest.writeByte(repetidor ? (byte) 1 : (byte) 0);
    }

    protected Alumno(Parcel in) {
        this.nombre = in.readString();
        this.telefono = in.readString();
        this.direccion = in.readString();
        this.curso = in.readString();
        this.foto = in.readString();
        this.edad = in.readInt();
        this.repetidor = in.readByte() != 0;
    }

    public static final Creator<Alumno> CREATOR = new Creator<Alumno>() {
        public Alumno createFromParcel(Parcel source) {
            return new Alumno(source);
        }

        public Alumno[] newArray(int size) {
            return new Alumno[size];
        }
    };
}
