/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Analizadores;

/**
 *
 * @author Luis
 */
public class Nodo_error {
    
    String Simbolo_error;
    int linea;
    int columna;

    public String getSimbolo_error() {
        return Simbolo_error;
    }

    public void setSimbolo_error(String Simbolo_error) {
        this.Simbolo_error = Simbolo_error;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    
    
}
