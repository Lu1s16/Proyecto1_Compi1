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
public class Nodo_arbol {
    
    public String dato;
    public Nodo_arbol left; //izquierda
    public Nodo_arbol right; //derecha
    public boolean hoja = false;
    public int id;
    public String anulabilidad;
    public ArrayList<Integer> primeros;
    public ArrayList<Integer> ultimos;
    
    //get y set para arrayList de ultimos

    public ArrayList<Integer> getUltimos() {
        return ultimos;
    }

    public void setUltimos(ArrayList<Integer> ultimos) {
        this.ultimos = ultimos;
    }
    
    
    //get y set para arraylist de primeros

    public ArrayList<Integer> getPrimeros() {
        return primeros;
    }

    public void setPrimeros(ArrayList<Integer> primeros) {
        this.primeros = primeros;
    }
    
    
    
    
    
    //get y set para anulabilidad
    public String getAnulabilidad() {
        return anulabilidad;
    }

    public void setAnulabilidad(String anulabilidad) {
        this.anulabilidad = anulabilidad;
    }
    
    
    
    //get y set para ID
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    //Para ver si es Hoja
    public boolean isHoja() {
        return hoja;
    }

    public void setHoja(boolean hoja) {
        this.hoja = hoja;
    }

    
    //Constructor del dato y agregar valor al nodo
    public Nodo_arbol(String dato) {
        this.dato = dato;
    }
    
    public String getDato() {
        return dato;
    }
    

    //get y set para los nodos izq. y der.
    public Nodo_arbol getLeft() {
        return left;
    }

    public void setLeft(Nodo_arbol left) {
        this.left = left;
    }

    public Nodo_arbol getRight() {
        return right;
    }

    public void setRight(Nodo_arbol right) {
        this.right = right;
    }

    

    
    

    
}
