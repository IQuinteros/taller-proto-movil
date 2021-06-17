package com.iquinteros.taller_prototipos.modelos;

import com.iquinteros.taller_prototipos.api.Parametro;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MiPerfilModelo {
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String descripcion;
    private Date horaCreacion;
    private boolean esGenio;
    private Date fechaEspecial;
    private double precio;

    public MiPerfilModelo() {
    }

    public MiPerfilModelo(String nombre, String apellido, int edad, String descripcion, Date horaCreacion, boolean esGenio, Date fechaEspecial, double precio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.descripcion = descripcion;
        this.horaCreacion = horaCreacion;
        this.esGenio = esGenio;
        this.fechaEspecial = fechaEspecial;
        this.precio = precio;
    }

    public MiPerfilModelo(JSONObject fromJSON) throws JSONException {
        this.id = fromJSON.getInt("id");
        this.nombre = fromJSON.getString("nombre");
        this.apellido = fromJSON.getString("apellido");

        try {
            this.edad = fromJSON.getInt("edad");
        } catch (Exception e){}

        try {
            this.descripcion = fromJSON.getString("descripcion");
        } catch (Exception e){}

        try {
            this.horaCreacion = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromJSON.getString("hora_creacion"));
        } catch (Exception e){}

        try {
            this.esGenio = fromJSON.getInt("es_genio") != 0;
        } catch (Exception e){}

        try {
            this.fechaEspecial = new SimpleDateFormat("yyyy-MM-dd").parse(fromJSON.getString("fecha_especial"));
        } catch (Exception e){}

        try {
            this.precio = fromJSON.getDouble("precio");
        } catch (Exception e){}
    }

    public List<Parametro> parametros(){
        List<Parametro> retornar = new ArrayList<>();
        try {
            retornar.add(new Parametro("id", getId()));
        } catch (Exception e){}
        retornar.add(new Parametro("nombre", getNombre()));
        retornar.add(new Parametro("apellido", getApellido()));

        try {
            retornar.add(new Parametro("edad", getEdad()));
        } catch (Exception e){}

        try {
            retornar.add(new Parametro("descripcion", getDescripcion()));
        } catch (Exception e){}

        try {
            retornar.add(new Parametro("hora_creacion", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(getHoraCreacion())));
        } catch (Exception e){}

        try {
            retornar.add(new Parametro("es_genio", isEsGenio()? 1 : 0 ));
        } catch (Exception e){}

        try {
            retornar.add(new Parametro("fecha_especial", new SimpleDateFormat("yyyy-MM-dd").format(getFechaEspecial())));
        } catch (Exception e){}

        try {
            retornar.add(new Parametro("precio", getPrecio()));
        } catch (Exception e){}

        return retornar;
    }

    @Override
    public String toString() {
        return id + ") " + nombre + " " + apellido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getHoraCreacion() {
        return horaCreacion;
    }

    public void setHoraCreacion(Date horaCreacion) {
        this.horaCreacion = horaCreacion;
    }

    public boolean isEsGenio() {
        return esGenio;
    }

    public void setEsGenio(boolean esGenio) {
        this.esGenio = esGenio;
    }

    public Date getFechaEspecial() {
        return fechaEspecial;
    }

    public void setFechaEspecial(Date fechaEspecial) {
        this.fechaEspecial = fechaEspecial;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
