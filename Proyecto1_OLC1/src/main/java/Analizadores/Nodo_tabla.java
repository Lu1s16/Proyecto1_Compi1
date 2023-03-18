/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Analizadores;

import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class Nodo_tabla {
    
    public String dato;
    public int nodo;
    ArrayList<Integer> siguientes = new ArrayList<Integer>();

    //Datos
    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }
    
    //Nodo
    public int getNodo() {
        return nodo;
    }

    public void setNodo(int nodo) {
        this.nodo = nodo;
    }

    //lista siguientes
    public ArrayList<Integer> getSiguientes() {
        return siguientes;
    }

    public void setSiguientes(ArrayList<Integer> siguientes) {
        this.siguientes = siguientes;
    }

    
    
    

    
    
    
    
    
    
    
}
