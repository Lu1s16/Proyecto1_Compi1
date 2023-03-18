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
public class Nodo_lista_estados {
    
    //Estado
    //Lista de numeros
    //analizado/no analizado
    
    String estado;
    ArrayList<Integer> numeros = new ArrayList<Integer>();
    

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<Integer> getNumeros() {
        return numeros;
    }

    public void setNumeros(ArrayList<Integer> numeros) {
        this.numeros = numeros;
    }

   
    
    
    
}
