/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Analizadores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
//import Analizadores.Nodo_tabla;
//import Analizadores.Nodo_lista_transicion;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Luis
 */
public class Arboles {
    public Nodo_arbol arbol;
    public int contador = 0;
    public int identificador = 1;
    public String anulabilidad = "";
    public int orden = 0;
    
    
    //Datos para tabla
    //public ArrayList<Nodo_tabla> tabla2;
    
    

    public Arboles(Nodo_arbol arbol) {
        Nodo_arbol raiz = new Nodo_arbol(".");
        Nodo_arbol centinela =new Nodo_arbol("#");
        centinela.setHoja(true);
        raiz.setRight(centinela);
        raiz.setLeft(arbol);
        this.arbol = raiz;
        ArrayList<Nodo_tabla> tabla = new ArrayList<Nodo_tabla>();
        ArrayList<Nodo_tabla> tabla2 = new ArrayList<Nodo_tabla>();
        ArrayList<Nodo_lista_transicion> lista_transicion = new ArrayList<Nodo_lista_transicion>();
       
        
        
        
        //Mostrar codigo graphviz para arbol
        //System.out.println(Mostrar_arbol(this.arbol, contador));
        //Crea arbol y datos para la tabla
        Mostrar_arbol(this.arbol, contador);
        
        //Mostrar codigo graphviz para tabla
        String graphviz_tabla = "nodo[style=filled, shape=plaintext, label=<\n"+
                "<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\">\n"
                + "<TR>\n"+
                "   <TD>Simbolo</TD>\n"+
                "   <TD>Nodo</TD>\n"+
                "   <TD>Siguientes</TD>\n"+
                "</TR>\n";
        
        
        
        tabla2 = Mostrar_tabla(this.arbol, tabla);
        graphviz_tabla+=Reporte_tabla(tabla2);
        graphviz_tabla+="</TABLE>>]\n";
        
        Tabla_transicion(this.arbol, tabla2, lista_transicion);
        
        //System.out.println(graphviz_tabla);
        
        
    
    }
    
    //public void asignar_identificador(Nodo_arbol temp){
        
    //    if(temp.isHoja()){
    //        temp.setId(identificador);
    //        identificador++;
    //        return;
    //    }
        
    //    asignar_identificador(temp.getLeft());
    //    asignar_identificador(temp.getRight());
        
    //}
    
    //public tip_de_variable de retorno
    public String Mostrar_arbol(Nodo_arbol nodo, int padre){
      //System.out.println(this.arbol); 
      
      String graphviz = "";
      if(nodo != null){
          
        contador+=1;
        int actual = contador;
        
        //lista para los primeros
        ArrayList<Integer> primeros = new ArrayList<Integer>();
        String primeros_string = "";
        
        //Lista para los ultimos
        ArrayList<Integer> ultimos = new ArrayList<Integer>();
        String ultimos_string = "";
 
        //moverse por lado izquierdo o derecho
        graphviz += Mostrar_arbol(nodo.getLeft(), actual);
        graphviz += Mostrar_arbol(nodo.getRight(), actual);
         
        //si se cumple llegamos al final del arbol
        
        
        
        //------nodos hojas-------
        if (nodo.isHoja()){
          //Creacion del nodo
          //node_1 [style=filled, label="hola", shape=box];
          
          /*<
<TABLE BORDER="0" CELLBORDER="1" CELLSPACING="0">
  <TR>
    <TD>Hoja</TD>
  </TR>
  <TR>
    <TD>"_"</TD>
  </TR>
</TABLE>>
          */
          
            System.out.println("Nodo_" + orden + " es: " + nodo.getDato() + " y es hoja");
            primeros.add(identificador);
            ultimos.add(identificador);
            
            
            //ciclo for para extraer los primeros de la lista del nodo
            for(int i = 0;i < primeros.size(); i++) {
                
                if(i == primeros.size()-1){
                    primeros_string+=primeros.get(i);
                    
                } else {
                    primeros_string+=primeros.get(i) + ",";
                    
                }
                      
            }
            
            //ciclo for para extraer los ultimos
            for(int i = 0; i<ultimos.size(); i++) {
                
                if(i == ultimos.size()-1){
                    ultimos_string+=ultimos.get(i);
                    
                } else {
                    ultimos_string+=ultimos.get(i);
                    
                }
                
            }
            
            anulabilidad = "N";
            graphviz += "node_" + actual + "[style=filled, shape=plaintext, label= <<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\">\n"+
                   "<TR>\n"+
                   "  <TD>"+ nodo.getDato() +"</TD>\n"+
                   "</TR>\n"+
                   "<TR>\n"+
                   "  <TD>Anulable: " + anulabilidad  + "</TD>\n"+
                   "</TR>\n"+
                   "<TR>\n"+
                   "  <TD>Primeros:" + primeros_string + "</TD>\n"+
                   "</TR>\n"+
                   "<TR>\n"+
                   "  <TD>Ultimos:"+ ultimos_string +"</TD>\n"+
                   "</TR>\n"+
                   "<TR>\n"+
                   "  <TD>Identificador: "+ identificador + "</TD>\n"+
                   "</TR>\n"+
                   "</TABLE>>];\n";
            
            nodo.setAnulabilidad(anulabilidad);
            nodo.setId(identificador);
            
            nodo.setUltimos(ultimos);
            nodo.setPrimeros(primeros);
            identificador++;
          
           
      
        //--------nodos no hojas---------
        } else {
            
            Nodo_arbol izquierda = nodo.getLeft();
            Nodo_arbol derecha = nodo.getRight();
            ArrayList<Integer> primeros_temp = new ArrayList<Integer>(); //para left
            ArrayList<Integer> primeros_Right_temp = new ArrayList<Integer>(); //para left
            
            ArrayList<Integer> ultimos_left_temp = new ArrayList<Integer>();
            ArrayList<Integer> ultimos_right_temp = new ArrayList<Integer>();
            
          
            switch (nodo.getDato()) {
                case "*":
                    {
                        //-----Anulabilidad------
                        anulabilidad = "A";
                        
                        
                        //------Primeros-------
                        primeros_temp = izquierda.getPrimeros();
                        
                        //For para almacenar los primeros temp en la lista original
                        for(int i = 0;i < primeros_temp.size(); i++) {
                
                            primeros.add(primeros_temp.get(i));
                      
                        }
                        
                        //For para almacenar los primeros en un string
                        for(int i = 0; i < primeros.size(); i++){
                             
                            if(i == primeros.size()-1){
                                
                                primeros_string+=primeros.get(i);
                        
                            } else {
                                primeros_string+=primeros.get(i) + ",";
                    
                            }  
                
                        }
                        
                        //------Ultimos------
                        ultimos_left_temp = izquierda.getUltimos();
                        
                        for(int i = 0; i< ultimos_left_temp.size(); i++) {
                            
                            ultimos.add(ultimos_left_temp.get(i));
                        }
                        
                        for(int i = 0; i < ultimos.size(); i++){
                             
                            if(i == ultimos.size()-1){
                                
                                ultimos_string+=ultimos.get(i);
                        
                            } else {
                                ultimos_string+=ultimos.get(i) + ",";
                    
                            }  
                
                        }
                        
                        
                        
                        
                        
                        break;
                    }
                case "|":
                    {
                       
                        //------Anulabilidad-------
                        if(derecha.getAnulabilidad().equals("N") && izquierda.getAnulabilidad().equals("A")){
                            anulabilidad = "A";
                            
                        } else if(derecha.getAnulabilidad().equals("A") && izquierda.getAnulabilidad().equals("N")){
                            anulabilidad = "A";
                            
                        } else {
                            anulabilidad = "N";
                            
                        } 
                        
                        //-------primeros-------
                        primeros_temp = izquierda.getPrimeros();
                        primeros_Right_temp = derecha.getPrimeros();
                        
                        //For para almacenar los primeros temp en la lista original
                        for(int i = 0;i < primeros_temp.size(); i++) {
                
                            primeros.add(primeros_temp.get(i));
                      
                        }
                        
                        for(int i = 0; i < primeros_Right_temp.size(); i++){
                            primeros.add(primeros_Right_temp.get(i));
                            
                        }
                        
                        //For para almacenar los primeros en un string
                        for(int i = 0; i < primeros.size(); i++){
                             
                            if(i == primeros.size()-1){
                                
                                primeros_string+=primeros.get(i);
                        
                            } else {
                                primeros_string+=primeros.get(i) + ",";
                    
                            }  
                
                        }
                        
                        
                        //-------ultimos-------
                        ultimos_left_temp = izquierda.getUltimos();
                        ultimos_right_temp = derecha.getUltimos();
                        
                        //For para almacenar los primeros temp en la lista original
                        for(int i = 0;i < ultimos_left_temp.size(); i++) {
                
                            ultimos.add(ultimos_left_temp.get(i));
                      
                        }
                        
                        for(int i = 0; i < ultimos_right_temp.size(); i++){
                            ultimos.add(ultimos_right_temp.get(i));
                            
                        }
                        
                        //For para almacenar los primeros en un string
                        for(int i = 0; i < ultimos.size(); i++){
                             
                            if(i == ultimos.size()-1){
                                
                                ultimos_string+=ultimos.get(i);
                        
                            } else {
                                ultimos_string+=ultimos.get(i) + ",";
                    
                            }  
                
                        }
                        

                        break;
                    }
                case "?":
                        //------Anulabilidad------
                        anulabilidad = "A";
                    
                         //------Primeros-------
                        primeros_temp = izquierda.getPrimeros();
                        
                        //For para almacenar los primeros temp en la lista original
                        for(int i = 0;i < primeros_temp.size(); i++) {
                
                            primeros.add(primeros_temp.get(i));
                      
                        }
                        
                        //For para almacenar los primeros en un string
                        for(int i = 0; i < primeros.size(); i++){
                             
                            if(i == primeros.size()-1){
                                
                                primeros_string+=primeros.get(i);
                        
                            } else {
                                primeros_string+=primeros.get(i) + ",";
                    
                            }  
                
                        }
                        
                        //------Ultimos------
                        ultimos_left_temp = izquierda.getUltimos();
                        
                        for(int i = 0; i< ultimos_left_temp.size(); i++) {
                            
                            ultimos.add(ultimos_left_temp.get(i));
                        }
                        
                        for(int i = 0; i < ultimos.size(); i++){
                             
                            if(i == ultimos.size()-1){
                                
                                ultimos_string+=ultimos.get(i);
                        
                            } else {
                                ultimos_string+=ultimos.get(i) + ",";
                    
                            }  
                
                        }
                    

                    break;
                case "+":
                    {
                        //------Anulabilidad----------
                       
                        if(izquierda.getAnulabilidad().equals("A")){
                            anulabilidad = "A";
                            
                        }
                        
                        
                        //------Primeros-------
                      
                        
                        primeros_temp = izquierda.getPrimeros();
                        
                        //For para almacenar los primeros temp en la lista original
                        for(int i = 0;i < primeros_temp.size(); i++) {
                
                            primeros.add(primeros_temp.get(i));
                      
                        }
                        
                        //For para almacenar los primeros en un string
                        for(int i = 0; i < primeros.size(); i++){
                             
                            if(i == primeros.size()-1){
                                
                                primeros_string+=primeros.get(i);
                        
                            } else {
                                primeros_string+=primeros.get(i) + ",";
                    
                            }  
                
                        }
                        
                        //------Ultimos------
                        ultimos_left_temp = izquierda.getUltimos();
                        
                        for(int i = 0; i< ultimos_left_temp.size(); i++) {
                            
                            ultimos.add(ultimos_left_temp.get(i));
                        }
                        
                        for(int i = 0; i < ultimos.size(); i++){
                             
                            if(i == ultimos.size()-1){
                                
                                ultimos_string+=ultimos.get(i);
                        
                            } else {
                                ultimos_string+=ultimos.get(i) + ",";
                    
                            }  
                
                        }
                        
                        
                        
                        break;
                    }
                case ".":
                    {
                        
                        //------anulabilidad------
                        if(derecha.getAnulabilidad().equals("A") && izquierda.getAnulabilidad().equals("A")){
                            anulabilidad = "A";
                            
                        } else {
                            anulabilidad = "N";
                            
                        }       
                        
                        //-------Primeros------
                        if(izquierda.getAnulabilidad().equals("A")){
                            
                            primeros_temp = izquierda.getPrimeros();
                            primeros_Right_temp = derecha.getPrimeros();
                        
                            //For para almacenar los primeros temp en la lista original
                            for(int i = 0;i < primeros_temp.size(); i++) {
                
                                primeros.add(primeros_temp.get(i));
                      
                            }
                        
                            for(int i = 0; i < primeros_Right_temp.size(); i++){
                                primeros.add(primeros_Right_temp.get(i));
                            
                            }
                        
                            //For para almacenar los primeros en un string
                            for(int i = 0; i < primeros.size(); i++){
                             
                                if(i == primeros.size()-1){
                                
                                    primeros_string+=primeros.get(i);
                        
                                } else {
                                    primeros_string+=primeros.get(i) + ",";
                    
                                }  
                
                            }
                           
                            
                        } else {
                            primeros_temp = izquierda.getPrimeros();
                        
                            //For para almacenar los primeros temp en la lista original
                            for(int i = 0;i < primeros_temp.size(); i++) {
                
                                primeros.add(primeros_temp.get(i));
                      
                            }
                        
                            //For para almacenar los primeros en un string
                            for(int i = 0; i < primeros.size(); i++){
                             
                                if(i == primeros.size()-1){
                                
                                    primeros_string+=primeros.get(i);
                        
                                } else {
                                    primeros_string+=primeros.get(i) + ",";
                    
                                }  
                
                            }
                            
 
                        }
                        
                        
                        
                        
                        //-------ultimos------
                        if(derecha.getAnulabilidad().equals("A")){
                            
                            ultimos_left_temp = izquierda.getUltimos();
                            ultimos_right_temp = derecha.getUltimos();
                        
                            //For para almacenar los primeros temp en la lista original
                            for(int i = 0;i < ultimos_left_temp.size(); i++) {
                
                                ultimos.add(ultimos_left_temp.get(i));
                      
                            }
                        
                            for(int i = 0; i < ultimos_right_temp.size(); i++){
                                ultimos.add(ultimos_right_temp.get(i));
                            
                            }
                        
                            //For para almacenar los primeros en un string
                            for(int i = 0; i < ultimos.size(); i++){
                             
                                if(i == ultimos.size()-1){
                                
                                    ultimos_string+=ultimos.get(i);
                        
                                } else {
                                    ultimos_string+=ultimos.get(i) + ",";
                    
                                }  
                
                            }
                           
                            
                        } else {
                            ultimos_right_temp = derecha.getUltimos();
                        
                            //For para almacenar los primeros temp en la lista original
                            for(int i = 0;i < ultimos_right_temp.size(); i++) {
                
                                ultimos.add(ultimos_right_temp.get(i));
                      
                            }
                        
                            //For para almacenar los primeros en un string
                            for(int i = 0; i < ultimos.size(); i++){
                             
                                if(i == ultimos.size()-1){
                                
                                    ultimos_string+=ultimos.get(i);
                        
                                } else {
                                    ultimos_string+=ultimos.get(i) + ",";
                    
                                }  
                
                            }
                            
 
                        }
                        
                        
                        
                        
                        break;
                    }
                default:
                    break;
            }
          
            System.out.println("Nodo_" + orden + " es: " + nodo.getDato());
            //Crear otro tipo de nodo, no tiene espacio para colocar numero de hoja
            graphviz += "node_" + actual + "[style=filled, shape=plaintext, label= <<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\">\n"+
                   "<TR>\n"+
                   "  <TD>" + nodo.getDato() + "</TD>\n"+
                   "</TR>\n"+
                   "<TR>\n"+
                   "  <TD>Anulable:"+ anulabilidad +"</TD>\n"+
                   "</TR>\n"+
                   "<TR>\n"+
                   "  <TD>Primeros:" + primeros_string + " </TD>\n"+
                   "</TR>\n"+
                  "<TR>\n"+
                   "  <TD>Ultimos:"+ ultimos_string +"</TD>\n"+
                   "</TR>\n"+
                   "</TABLE>>];\n";
          
        }
        
        nodo.setAnulabilidad(anulabilidad);
        
        nodo.setUltimos(ultimos);
        nodo.setPrimeros(primeros);
        
        orden++;
      
        if (padre != 0){
            //Enlazar el padre con el nodo actual
            //a += "N_" + padre + " -> N_" + actual + ",\n";
            graphviz += "node_" + padre + " -> node_" + actual + ";\n";
          
          
        }
        
        
        
          
      }
      
      return graphviz;
      
      
      
    }
    
    public ArrayList<Nodo_tabla> Mostrar_tabla(Nodo_arbol nodo, ArrayList<Nodo_tabla> tabla){
       
         
        /* nodo[style=filled, shape=plaintext, label=<
<TABLE BORDER="0" CELLBORDER="1" CELLSPACING="0">
  <TR>
    <TD>Simbolo</TD>
    <TD>Nodo</TD>
    <TD>Siguientes</TD>
  </TR>
  <TR>
    <TD>"_"</TD>
    <TD></TD>
    <TD></TD>
  </TR>
</TABLE>>]*/
        String graphviz = "";
        //ArrayList<Nodo_tabla> tabla = new ArrayList<Nodo_tabla>();
        
        if( nodo != null ) {
            
            
            
            
            
            
            Mostrar_tabla(nodo.getLeft(), tabla);
            Mostrar_tabla(nodo.getRight(), tabla);
            
            Nodo_tabla nuevo_nodo_tabla = new Nodo_tabla();
            String Simbolo = "";
            int numero_nodo_tabla = 1;
            
            if(nodo.isHoja()){
                
                //Recolecto el simbolo y numero de hoja
                Simbolo = nodo.getDato();
                numero_nodo_tabla = nodo.getId();
                
                nuevo_nodo_tabla.setDato(Simbolo);
                nuevo_nodo_tabla.setNodo(numero_nodo_tabla);
                
                
                
                
                tabla.add(nuevo_nodo_tabla);
                //System.out.println("--------size al añadir hoja");
                //System.out.println(tabla.size());
                 
 
                
            } else {
                Nodo_arbol left = nodo.getLeft();
                Nodo_arbol right = nodo.getRight();
                
                
                switch (nodo.getDato()) {
                    
                    case ".":
                        // código a ejecutar si variable/expresión es igual a valor1
                        //Recorro cada elemento de los ultimos del hijo izquierdo
                        
                        for(int i = 0; i<left.getUltimos().size(); i++){
                            
                            //System.out.println("-------------------ultimo del hizo izquierdo para .-----------------------");
                            //System.out.println(left.getUltimos().get(i));
                            
                            int ultimo = left.getUltimos().get(i);
                            
                            for(int j = 0; j<tabla.size(); j++){
                                
                                if(ultimo == tabla.get(j).nodo){
                                    
                                    for(int k = 0; k<right.getPrimeros().size(); k++){
                                        tabla.get(j).siguientes.add(right.getPrimeros().get(k));
                                    }
                                    
                                    
                                    //Elimina los repetidos y los guarda en un HashSet siguientes_sin_repetir
                                    HashSet<Integer> siguientes_sin_repetir = new HashSet<Integer>(tabla.get(j).siguientes);
                                    
                                    //Vacio la lista de siguientes
                                    tabla.get(j).siguientes.clear();
                                    
                                    //Agrego el hashSet al Arraylist anterior
                                    tabla.get(j).siguientes.addAll(siguientes_sin_repetir);
                                    
                                    
                                    //Por ultimo ordenarlo
                                    Collections.sort(tabla.get(j).siguientes);
                                    //System.out.println(tabla.get(j).siguientes);
                                    
                                    
                                    
                                    
                                }
                            }
                            
                        }
                        
                        
                        
                        
                        break;
                    case "+":
                        //Recorro cada elemento del ultimo del hijo izquierdo
                        for(int i = 0; i<left.getUltimos().size(); i++){
                            
                            int ultimo = 0;
                            ultimo = left.getUltimos().get(i);
                            
                            //Recorro cada elemento de la tabla para buscar
                            //el elemento del ultimo del hijo izquierdo
                            for(int j = 0; j<tabla.size(); j++){
                                
                                //Verifico que coincida el ultimo
                                //Con el nodo de la tabla
                                if(ultimo == tabla.get(j).nodo){
                                    
                                    //Recorro los primeros para almacenarlos a la lista
                                    //siguientes
                                    for(int k = 0; k<left.getPrimeros().size(); k++){
                                        tabla.get(j).siguientes.add(left.getPrimeros().get(k));
                                        
                                    }
                                    
                                    
                                    
                                    //Elimina los repetidos y los guarda en un HashSet siguientes_sin_repetir
                                    HashSet<Integer> siguientes_sin_repetir = new HashSet<Integer>(tabla.get(j).siguientes);
                                    
                                    //Vacio la lista de siguientes
                                    tabla.get(j).siguientes.clear();
                                    
                                    //Agrego el hashSet al Arraylist anterior
                                    tabla.get(j).siguientes.addAll(siguientes_sin_repetir);
                                    
                                    
                                    //Por ultimo ordenarlo
                                    Collections.sort(tabla.get(j).siguientes);
                                    //System.out.println(tabla.get(j).siguientes);
                                    
                                }
                                
                                
                            }
                            
                        }
                        
                        
                       
                        break;
                    case "*":
                        
                        
                        //Recorro cada elemento del ultimo del hijo izquierdo
                        for(int i = 0; i<left.getUltimos().size(); i++){
                            
                            
                            int ultimo = 0;
                            ultimo = left.getUltimos().get(i);
                            
                            //Recorro cada elemento de la tabla para buscar
                            //el elemento del ultimo del hijo izquierdo
                            
                            
                            for(int j = 0; j<tabla.size(); j++){
                                
                                //Verifico que coincida el ultimo
                                //Con el nodo de la tabla
                                if(ultimo == tabla.get(j).nodo){
                                    
                                    //Recorro los primeros para almacenarlos a la lista
                                    //siguientes
                                    for(int k = 0; k<left.getPrimeros().size(); k++){
                                        tabla.get(j).siguientes.add(left.getPrimeros().get(k));
                                        
                                    }
                                    
                                    
                                    
                                    //Elimina los repetidos y los guarda en un HashSet siguientes_sin_repetir
                                    HashSet<Integer> siguientes_sin_repetir = new HashSet<Integer>(tabla.get(j).siguientes);
                                    
                                    //Vacio la lista de siguientes
                                    tabla.get(j).siguientes.clear();
                                    
                                    //Agrego el hashSet al Arraylist anterior
                                    tabla.get(j).siguientes.addAll(siguientes_sin_repetir);
                                    
                                    
                                    //Por ultimo ordenarlo
                                    Collections.sort(tabla.get(j).siguientes);
                                    
                                    
                                }
                                
                                
                            }
                            
                        }
                        
 
                        // código a ejecutar si variable/expresión es igual a valor3
                        break;
                    // más casos
                    default:
                        break;
        
                }
                
 
                
            }
 
        }
        //Fin del if
        
        //System.out.println(tabla.get(i).siguientes);

            /*  <TR>
    <TD>"_"</TD>
    <TD></TD>
    <TD></TD>
  </TR>*/
 
        
        return tabla;
        
        
        
    }
    
    public String Reporte_tabla(ArrayList<Nodo_tabla> tabla){
        
        String graphviz = "";
        
        for(int i = 0; i<tabla.size(); i++){
            
            System.out.println("--------------------------size para graficar");
            System.out.println(tabla.size());
            
            String Simbolo_string = "";
            String siguientes_string = "";
            int nodo_string = 0;
            
            Simbolo_string = tabla.get(i).dato;
            nodo_string = tabla.get(i).nodo;
            
            if(Simbolo_string.equals("#")){
                String simbolo_centinela = "\"";
                simbolo_centinela+=Simbolo_string+"\"";
                
                graphviz+="<TR>\n"+
                    "<TD>" + simbolo_centinela + "</TD>\n"+
                    "<TD>" + nodo_string + "</TD>\n"+
                    "<TD></TD>\n"+
                    "</TR>\n";
                        
            } else{
                
                
                    for(int j = 0; j < tabla.get(i).siguientes.size(); j++){
                        
                                //ultimo elemento de la lista
                                if(j == tabla.get(i).siguientes.size()-1){
                                
                                    siguientes_string+=tabla.get(i).siguientes.get(j);
                        
                                } else {
                                    siguientes_string+=tabla.get(i).siguientes.get(j) + ",";
                    
                                }  
                
                    }
                    
                
                
                
                
                 graphviz+="<TR>\n"+
                    "<TD>" + Simbolo_string + "</TD>\n"+
                    "<TD>" + nodo_string + "</TD>\n"+
                    "<TD>"+ siguientes_string +"</TD>\n"+
                    "</TR>\n";
                
            }
            
            
           
            
            
        }
        
        return graphviz;
        
        
        
    }
    
    public void Tabla_transicion(Nodo_arbol nodo, ArrayList<Nodo_tabla> tabla, ArrayList<Nodo_lista_transicion> lista_transicion){
        
        
        //Obtener datos para el estado S0 y almacenarlo
        //en la lista de estados
        
        ArrayList<Nodo_lista_estados> lista_estados = new ArrayList<Nodo_lista_estados>();
        ArrayList<Integer> lista_temp_numeros_estados = new ArrayList<Integer>();
        
        
        //Diccionario
        
        
        
        //Contador de estados para lista estados
        int contador = 0;
        String estado = "S"+contador;
        
        //contador de estados para estados de lista transicion
        int contador2 = 0;
        String estado2 = "";
        
        Nodo_lista_estados nuevo_nodo_estado = new Nodo_lista_estados();
        
        Nodo_lista_transicion nuevo_nodo_transicion = new Nodo_lista_transicion();
        
        nuevo_nodo_estado.setEstado(estado);
        lista_temp_numeros_estados = nodo.getPrimeros();
        nuevo_nodo_estado.setNumeros(lista_temp_numeros_estados);
        
        
        //Agrego el estado cero a la lista
        lista_estados.add(nuevo_nodo_estado);
        
        //Recorrer la lista de estados para analizarlos
        for(int i = 0; i<lista_estados.size(); i++){
            
            

            
            //LIsta temporal con los numeros del estado actual
            lista_temp_numeros_estados = lista_estados.get(i).getNumeros();
            System.out.println("------Veces que entra a la lista con los numeros del estado actual-----");
            System.out.println(lista_estados.get(i).getNumeros());
            
            //Recorrer cada elemento de la tabla
            for(int j = 0; j<tabla.size(); j++){
                boolean coincide = false;
                ArrayList<Integer> lista_temp_para_estados = new ArrayList<Integer>();
                
                //Recorrer cada elemento de la lista temporal
                for(int k = 0; k<lista_temp_numeros_estados.size(); k++){
                    
                   //verifico si el dato de la tabla coincide con el de la lista temporal
                   if(lista_temp_numeros_estados.get(k) == tabla.get(j).getNodo()){
                       
                       //Agrego los siguientes a la lista temporal para estados
                       //Se agrega del estado anterior
                       lista_temp_para_estados.addAll(tabla.get(j).getSiguientes());
                       coincide = true;
                   }
                   
                   //Si es true es porque recorrio toda la lista de numeros
                   //si coincide es false no ejecutara nada de lo demas
                   //no almacenara siguientes ni nada de eso
                   if(k == lista_temp_numeros_estados.size()-1 && coincide){
                       
                       boolean repetido = false;
                       

                       
                       for(int m = 0; m<lista_estados.size(); m++){
                           
                           //Verifico si ya existe los siguientes
                           boolean repetidos = lista_temp_para_estados.equals(lista_estados.get(m).getNumeros());
                           if(repetidos){
                               repetido = true;
                           }
                           
                           if(repetido == false && m == lista_estados.size()-1){
                               Nodo_lista_estados nuevo_nodo_estado2 = new Nodo_lista_estados();
                               System.out.println("Entra al if cuando no esta repetido------------------------");
                               //creo datos para agregar a lista de estados
                               
                               contador++;
                               estado = "S"+contador;
                               
                               nuevo_nodo_estado2.setEstado(estado);
                               //aqui se cambia el unico estado por el que deberia agregarse
                               nuevo_nodo_estado2.setNumeros(lista_temp_para_estados);
                               
                               //Agrego el nuevo estado a la lista estados
                               lista_estados.add(nuevo_nodo_estado2);
                               
                               
                               
                           }
                       }
                       
                       Nodo_lista_transicion nuevo_nodo_transicion2 = new Nodo_lista_transicion();

                       //diccionario para el estado inicial
                       Map<String, ArrayList<Integer>> nuevo_diccionario_inicial = new HashMap<>();
                       
                       //Obtengo los datos para la lista transicion
                       //estado 2 = estado inicial
                       estado2 = "S"+contador2;
                       
                       if(estado2.equals("S0")){
                           nuevo_diccionario_inicial.put(estado2, nodo.getPrimeros());
                           nuevo_nodo_transicion2.setEstado_inicial(nuevo_diccionario_inicial);
                           
                       } else {
                           nuevo_diccionario_inicial.put(estado2, lista_temp_para_estados);
                           nuevo_nodo_transicion2.setEstado_inicial(nuevo_diccionario_inicial);
                           
                       }
                       
                       
                       
                       //guardo estado inicial
                       nuevo_nodo_transicion2.setEstado_inicio(estado2);
                       //guardo simbolo
                       nuevo_nodo_transicion2.setSimobolo(tabla.get(j).getDato());
                       
                       //Busco esta esta lista en la lista de estados
                       //y al encontrarlo obtengo su estado.
                       //lista_temp_para_estados
                       
                       for(int n=0; n<lista_estados.size(); n++){
                           
                           
                           boolean encontrado = lista_estados.get(n).getNumeros().equals(lista_temp_para_estados);
                           
                           if(encontrado){
                               
                               boolean aceptacion = false;
                               
                               String estado_final = lista_estados.get(n).getEstado();
                               
                               //creo diccionario
                               if(lista_temp_para_estados.contains(tabla.get(tabla.size()-1).getNodo())){
                                   aceptacion = true;
                               }
                               
                               //Aqui se resetea la lista de numeros del nuevo estado
                               //lista_temp_para_estados.clear();
                               
                               Map<String, Boolean> nuevo_diccionario = new HashMap<>();
                               
                               nuevo_diccionario.put(estado_final, aceptacion);
                               //guardo estado final
                               nuevo_nodo_transicion2.setEstado_final(nuevo_diccionario);
                               
                               
                               lista_transicion.add(nuevo_nodo_transicion2);
                               break;
                               
                               
                           }
                           
                       }
                       
                       
                       
                   }
                    
                }
                
                
            }
            
            contador2++;
            
            
            
        }
        
        System.out.println("--------------Tabla tansiciones------------------");
        //for(int h = 0; h<lista_transicion.size(); h++){
        //    System.out.println("-----Datos-----");
        //    System.out.println(lista_transicion.get(h).getEstado_inicial());
        //    System.out.println(lista_transicion.get(h).getEstado_final());
        //    System.out.println(lista_transicion.get(h).getSimobolo());
        //}
        
        
        //Graficar
        
        
        String graphviz_tabla_t = "nodo[style=filled, shape=plaintext, label=<\n"+
                "<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\">\n"
                + "<TR>\n"+
                "<TD>Estado</TD>\n";
                //las demas celdas las creo con los terminales
                
        
        ArrayList<String> simbolos_repetidos = new ArrayList<String>();

        for(int c = 0; c<tabla.size(); c++){
            
            simbolos_repetidos.add(tabla.get(c).getDato());
        }
        
                
                
        Set<String> simbolos_sin_repetir = new HashSet<>(simbolos_repetidos);        
        ArrayList<String> listaSinRepetidos = new ArrayList<>(simbolos_sin_repetir);        
        
        
        Map<String, Integer> posiciones_en_celdas = new HashMap<>();
        ArrayList<Map<String, Integer>> lista_posiciones = new ArrayList<Map<String, Integer>>();
        
        int contador_posiciones = 1;
        for(int c = 0; c<listaSinRepetidos.size(); c++){
            
            if(listaSinRepetidos.get(c).equals("#")){
                     
            } else {
                posiciones_en_celdas.put(listaSinRepetidos.get(c), contador_posiciones);
                lista_posiciones.add(posiciones_en_celdas);
                graphviz_tabla_t+="<TD>"+ listaSinRepetidos.get(c) +"</TD>\n";
                
                contador++;
            }
     
        }
        
        graphviz_tabla_t+="<TD>Aceptacion</TD>\n</TR>\n"; 
        
        
        //demas estados
        int contador_str1 = 0;
        int contador_str2 = 1;
        
        String str1 = "S"+contador_str1;
        String str2 = "S"+contador_str2;
        
        for(int c = 0; c<lista_transicion.size(); c++){
            graphviz_tabla_t+="<TR>\n";
            
            if(str1.equals(str2)){
                contador_str1++;
                contador_str2++;
                str1 = "S"+contador_str1;
                str2 = "S"+contador_str2;
            } else {
                
                //Creo celdas para el html
            }
            
          
            
        }
        
        
        
        graphviz_tabla_t+="</TABLE>>]";
        System.out.println(graphviz_tabla_t);
        
        
        
    }
    
    
}