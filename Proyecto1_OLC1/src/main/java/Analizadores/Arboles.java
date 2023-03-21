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

import Analizadores.Lexico;

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
        //tabla siguientes para graficar
        ArrayList<Nodo_tabla> tabla2 = new ArrayList<Nodo_tabla>();
        
        
        ArrayList<Nodo_lista_transicion> lista_transicion = new ArrayList<Nodo_lista_transicion>();
        //lista transiciones para graficar
        ArrayList<Nodo_lista_transicion> lista_transicion2 = new ArrayList<Nodo_lista_transicion>();
        
        
        
        //Mostrar codigo graphviz para arbol
        //System.out.println("-----------------Arbol--------------");
        String reporte_arbol = "digraph G {\n";
        reporte_arbol+=Mostrar_arbol(this.arbol, contador);
        reporte_arbol+="\n}";
        //System.out.println(reporte_arbol);
        
        
        
        //Mostrar codigo graphviz para tabla siguientes
        //System.out.println("----------------siguientes-----------");
        String reporte_tabla_siguientes = "digraph G{\n";
        reporte_tabla_siguientes+= "nodo[style=filled, shape=plaintext, label=<\n"+
                "<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\">\n"
                + "<TR>\n"+
                "   <TD>Simbolo</TD>\n"+
                "   <TD>Nodo</TD>\n"+
                "   <TD>Siguientes</TD>\n"+
                "</TR>\n";
        
        tabla2 = Mostrar_tabla(this.arbol, tabla);
        reporte_tabla_siguientes+=Reporte_tabla(tabla2);
        reporte_tabla_siguientes+="</TABLE>>]\n\n}";
        //System.out.println(reporte_tabla_siguientes);
        
        
        //lista de transiciones para el reporte de tabla transiciones
        //y para el afd
        lista_transicion2 = Tabla_transicion(this.arbol, tabla2, lista_transicion);
        
        //Graficar tabla transiciones
        //System.out.println("------------------Transiciones-------------------");
        String reporte_tabla_transiciones = "digraph G {\n";
        reporte_tabla_transiciones+=Graficar_tabla_transiciones(lista_transicion2, tabla2);
        reporte_tabla_transiciones+="\n}";
        //System.out.println(reporte_tabla_transiciones);
        
        //Graficar AFD
        //System.out.println("-------------------AFD-----------------------");
        String reporte_afd = "digraph G {\n";
        reporte_afd+=Graficar_AFD(lista_transicion2);
        reporte_afd+="\n}";
        //System.out.println(reporte_afd);
        
        Reportes nuevo_reporte = new Reportes(reporte_arbol, reporte_tabla_siguientes, reporte_tabla_transiciones, reporte_afd, "");
        
        nuevo_reporte.Crear_reportes();
        
        
    
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
    //Reporte de arbol
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
          
            //System.out.println("Nodo_" + orden + " es: " + nodo.getDato() + " y es hoja");
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
    
    //Reporte de tabla siguientes en grapviz
    public String Reporte_tabla(ArrayList<Nodo_tabla> tabla){
        
        String graphviz = "";
        
        for(int i = 0; i<tabla.size(); i++){
            
            //System.out.println("--------------------------size para graficar");
            //System.out.println(tabla.size());
            
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
    
    
    public ArrayList<Nodo_lista_transicion> Tabla_transicion(Nodo_arbol nodo, ArrayList<Nodo_tabla> tabla, ArrayList<Nodo_lista_transicion> lista_transicion){
        
        
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
            //System.out.println("------Veces que entra a la lista con los numeros del estado actual-----");
            //System.out.println(lista_estados.get(i).getNumeros());
            
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
                               //System.out.println("Entra al if cuando no esta repetido------------------------");
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
                               nuevo_nodo_transicion2.setEstado_final1(estado_final);
                               
                               lista_transicion.add(nuevo_nodo_transicion2);
                               break;
                               
                               
                           }
                           
                       }
                       
                       
                       
                   }
                    
                }
                
                
            }
            
            contador2++;
            
            
            
        }
        
        //System.out.println("--------------Tabla tansiciones------------------");
        //for(int h = 0; h<lista_transicion.size(); h++){
        //    System.out.println("-----Datos-----");
        //    System.out.println(lista_transicion.get(h).getEstado_inicial());
        //    System.out.println(lista_transicion.get(h).getEstado_final());
        //    System.out.println(lista_transicion.get(h).getSimobolo());
        //}
        
     
        return lista_transicion;
        
        
        
    }
    
    //Reporte de grafica de tabla de transiciones
    public String Graficar_tabla_transiciones(ArrayList<Nodo_lista_transicion> lista_transicion, ArrayList<Nodo_tabla> tabla){
        
        String graphviz_tabla = "nodo[style=filled, shape=plaintext, label=<\n"+
                "<TABLE BORDER=\"0\" CELLBORDER=\"1\" CELLSPACING=\"0\">\n"
                + "<TR>\n"+
                "<TD>Estado</TD>\n";
        
        //lista de simbolos repetidos
        ArrayList<String> simbolos_repetidos = new ArrayList<String>();
        for(int c = 0; c<tabla.size(); c++){
            simbolos_repetidos.add(tabla.get(c).getDato());
            
        }
        
        Set<String> simbolos_sin_repetir = new HashSet<>(simbolos_repetidos);
        
        //lista de simbolos sin repetirse incluyendo "#"
        ArrayList<String> lista_simbolos = new ArrayList<>(simbolos_sin_repetir);
        
        //variable para posicion en tabla
        int posicion = 0;
        
        //Lista de diccionarios con su posicion y simbolo
        ArrayList<Map<Integer, String>> lista_posiciones = new ArrayList<Map<Integer, String>>();
        
        //contiene para "#" aunque no se grafique
        for(int c = 0; c<lista_simbolos.size(); c++){
            //Creo el diccionario para la posicion en celda
            //contiene la posicion y simbolo
            
            if(lista_simbolos.get(c).equals("#")){
                
            } else {
                Map<Integer, String> posicion_en_celda = new HashMap<>();
            
                posicion_en_celda.put(posicion, lista_simbolos.get(c));
                lista_posiciones.add(posicion_en_celda);
                posicion++;
                
            }
            
            
            
            
        }
        
        //Grafico simbolos en la tabla
        for(int c = 0; c<lista_simbolos.size(); c++){
            
            //No coloco # en la tabla
            if(lista_simbolos.get(c).equals("#")){
                
            } else {
                graphviz_tabla+="<TD>"+ lista_simbolos.get(c) + "</TD>\n";
            }
            
        }
        
        graphviz_tabla+="<TD>Aceptacion</TD>\n</TR>\n";
        
        //lista de celdas, para agregar un estado si existe
        //en esa celda.
        ArrayList<String> lista_celdas = new ArrayList<String>();
        //La ultima celda es de # que equivale al de aceptacion
        for(int c = 0; c<lista_simbolos.size(); c++){
            
            if(c == lista_simbolos.size()-1){
                String celda = "<TD>false</TD>\n";
                lista_celdas.add(celda);
                
            } else {
                String celda = "<TD></TD>\n";
                lista_celdas.add(celda);
            }
            
            
            
        }
        
        
        //Grafico el resto de estados
        int contador_estados = 0;
        
        boolean bandera = false;
        
        //Estado actual
        int contador_str1 = 0;
        String str = "S"+contador_str1;
        
        //Recorro la lista de transiciones
        for(int c = 0; c<lista_transicion.size(); c++){
            
            //Verifico si el estaodo  no a cambiado
            if(str.equals(lista_transicion.get(c).getEstado_inicio())){
                
                
                //Recorro la lista de posiciones
                for(int e = 0; e<lista_posiciones.size(); e++){
                    
                    //Comparo que el simbolo de la posicion es igual al de la transicion
                    if(lista_posiciones.get(e).get(e).equals(lista_transicion.get(c).getSimobolo())){
                        
                        String celda = "<TD>" + lista_transicion.get(c).getEstado_final1()+"</TD>";
                        lista_celdas.set(e, celda);
                        
                    }
                    
                }
                //Colocar la celda como true
                //if(lista_transicion.get(c).getEstado_final().get(lista_transicion.get(c).getEstado_inicio() != null)){
                if(lista_transicion.get(c).getEstado_inicio().equals(lista_transicion.get(c).getEstado_final1()) && lista_transicion.get(c).getEstado_final().get(lista_transicion.get(c).getEstado_final1())){
                    for(int n = 0; n<lista_celdas.size(); n++){
                        
                        if(n == lista_celdas.size()-1){
                            String celda = "<TD>True</TD>";
                            lista_celdas.set(n, celda);
                        }
                    }
                }
                    
                    
                    
                //}
                
                
                
                
            } else {
                String celdas = "<TR>\n<TD>"+ str +"</TD>\n";
                
                for(int k = 0; k<lista_celdas.size(); k++){
                    celdas+=lista_celdas.get(k);
                           
                }
                
                celdas+="</TR>\n\n";
                
                //aumento el estado
                contador_str1++;
                str = "S"+contador_str1;
                
                graphviz_tabla+=celdas;
                
                //reseteo las celdas
                for(int f = 0; f < lista_celdas.size(); f++){
                    
                    if(f == lista_celdas.size()-1){
                        lista_celdas.set(f, "<TD>false</TD>");
                        
                    } else {
                        lista_celdas.set(f, "<TD></TD>");
                        
                    }
                    
                    
                    
                }
                
                bandera = true;
                
                
                
            }
            
            if(bandera){
                
                for(int m = 0; m<lista_posiciones.size(); m++){
                    
                    if(lista_posiciones.get(m).get(m).equals(lista_transicion.get(c).getSimobolo())){
                        
                        String celda = "<TD>" + lista_transicion.get(c).getEstado_final1()+"</TD>";
                        lista_celdas.set(m, celda);
                    }
                    
                }
                
                //Colocar la celda como true
                if(lista_transicion.get(c).getEstado_inicio().equals(lista_transicion.get(c).getEstado_final1()) && lista_transicion.get(c).getEstado_final().get(lista_transicion.get(c).getEstado_final1())){
                    for(int n = 0; n<lista_celdas.size(); n++){
                        
                        if(n == lista_celdas.size()-1){
                            String celda = "<TD>True</TD>";
                            lista_celdas.set(n, celda);
                        }
                    }
                }
                
                bandera = false;
                
                
            }
            
            //ultima transicion
            if(c == lista_transicion.size()-1){
                
                String celdas = "<TR>\n<TD>"+ str +"</TD>\n";
                
                for(int k = 0; k<lista_celdas.size(); k++){
                    celdas+=lista_celdas.get(k);
                    
                }
                
                celdas+="</TR>\n\n";
                
                contador_str1++;
                str = "S"+contador_str1;
                
                graphviz_tabla+=celdas;
                
                
            }
            
            
            
        }
        
        graphviz_tabla+="</TABLE>>]";
        //System.out.println("------------Tabla de transiciones-----------");
        //System.out.println(graphviz_tabla);
        return graphviz_tabla;
        
    }
    
    //Reporte de AFD
    public String Graficar_AFD(ArrayList<Nodo_lista_transicion> lista_transicion){
        
        
        String graphviz = "rankdir=LR;\n";
        String conexiones = "";
        String aceptacion = "";
        
        for(int c = 0; c<lista_transicion.size(); c++){
            
            if(lista_transicion.get(c).getSimobolo().equals("#")){
                
            } else {
                if(lista_transicion.get(c).getEstado_final().get(lista_transicion.get(c).getEstado_final1())){
                
                    //S2 [color="#ff0000"]
                    aceptacion+=lista_transicion.get(c).getEstado_final1()+"[color=\"#ff0000\"]\n";
                
                }
                
                //Verifica si el simbolo tiene "_"
                if(lista_transicion.get(c).getSimobolo().contains("\"")){
                    conexiones+=lista_transicion.get(c).getEstado_inicio()+"->"+lista_transicion.get(c).getEstado_final1()+"[label="+ lista_transicion.get(c).getSimobolo() +"]\n";
                    
                    
                } else if(lista_transicion.get(c).getSimobolo().contains("\\n")){
                    conexiones+=lista_transicion.get(c).getEstado_inicio()+"->"+lista_transicion.get(c).getEstado_final1()+"[label=\"\\\" \\\"\"]\n";
                } else {
                    conexiones+=lista_transicion.get(c).getEstado_inicio()+"->"+lista_transicion.get(c).getEstado_final1()+"[label=\""+ lista_transicion.get(c).getSimobolo() +"\"]\n";
                    
                }

                
            }
            
            
            
            
        }
        
        graphviz+=aceptacion+conexiones;
        //System.out.println("---------------Grafo AFD--------------");
        //System.out.println(graphviz);
        return graphviz;
        
    }
    
    
}