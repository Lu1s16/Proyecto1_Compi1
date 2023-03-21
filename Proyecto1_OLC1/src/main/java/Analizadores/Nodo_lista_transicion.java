/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Analizadores;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class Nodo_lista_transicion {
    
    String estado_inicio;
    Map<String, ArrayList<Integer>> estado_inicial = new HashMap<>();
    String simobolo;
    Map<String, Boolean> estado_final = new HashMap<>();
    String estado_final1;

    public String getEstado_final1() {
        return estado_final1;
    }

    public void setEstado_final1(String estado_final1) {
        this.estado_final1 = estado_final1;
    }
    
    

    public Map<String, ArrayList<Integer>> getEstado_inicial() {
        return estado_inicial;
    }

    public void setEstado_inicial(Map<String, ArrayList<Integer>> estado_inicial) {
        this.estado_inicial = estado_inicial;
    }

    
    
    
    public String getEstado_inicio() {
        return estado_inicio;
    }

    public void setEstado_inicio(String estado_inicio) {
        this.estado_inicio = estado_inicio;
    }

    public String getSimobolo() {
        return simobolo;
    }

    public void setSimobolo(String simobolo) {
        this.simobolo = simobolo;
    }

    public Map<String, Boolean> getEstado_final() {
        return estado_final;
    }

    public void setEstado_final(Map<String, Boolean> estado_final) {
        this.estado_final = estado_final;
    }

    
    
    
    
}
