package com.martintecno.inmobiliarialab3.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Propietario implements Serializable {

    private int idPropietario;
    private String dni;
    private String nombre;
    private String apellido;
    private String mail;
    private String password;
    private String telefono;
    private String avatar;

    public Propietario(){}
    public Propietario(int idPropietario, String dni, String nombre, String apellido, String mail, String password, String telefono, String avatar) {
        this.idPropietario = idPropietario;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.password = password;
        this.telefono = telefono;
        this.avatar=avatar;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Propietario that = (Propietario) o;
        return idPropietario == that.idPropietario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPropietario);
    }

    @Override
    public String toString() {
        return "Propietario{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}

