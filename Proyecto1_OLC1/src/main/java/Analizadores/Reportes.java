/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Analizadores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author Luis
 */
public class Reportes {
    
    
    
    String Arbol;
    String Siguientes;
    String Transiciones;
    String AFD;
    String AFND;
    

    public Reportes(String Arbol, String Siguientes, String Transiciones, String AFD, String AFND) {
        this.Arbol = Arbol;
        this.Siguientes = Siguientes;
        this.Transiciones = Transiciones;
        this.AFD = AFD;
        this.AFND = AFND;
    }
    
    public void Crear_reportes(){
        
        //Obtengo la ruta actual
        String directoryName = System.getProperty("user.dir");
        
        //Creo las carpetas
        File archivo_arbol = new File(directoryName+"/ARBOLES_202010814");
        File carpeta_afd = new File(directoryName+"/AFD_202010814");
        File carpeta_afnd = new File(directoryName+"/AFND_202010814");
        File carpeta_siguientes = new File(directoryName+"/SIGUIENTES_202010814");
        File carpeta_transiciones = new File(directoryName+"/TRANSICIONES_202010814");
        File carpeta_errores = new File(directoryName+"/ERRORES_202010814");
        
        //Carpeta para arboles
        if(!archivo_arbol.exists()){
            if(archivo_arbol.mkdirs()){
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        
        //Carpeta para afd
        if(!carpeta_afd.exists()){
            if(carpeta_afd.mkdirs()){
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        
        //carpeta afnd
        if(!carpeta_afnd.exists()){
            if(carpeta_afnd.mkdirs()){
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        
        //carpeta siguientes
        if(!carpeta_siguientes.exists()){
            if(carpeta_siguientes.mkdirs()){
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        
        //carpeta transiciones
        if(!carpeta_transiciones.exists()){
            if(carpeta_transiciones.mkdirs()){
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        
        //carpeta errores
        if(!carpeta_errores.exists()){
            if(carpeta_errores.mkdirs()){
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        
        //Creo archivos en las carpetas correspondientes
        
        DateFormat dateFormat = new SimpleDateFormat("HH,mm,ss");
        String fecha_string = dateFormat.format(new Date());
        
        
        String extension = ".dot";
        String archivo_dot_arbol = fecha_string+extension;
        
        
        
        
        //lo convierte a png
        try {
            File f = new File(directoryName+"/ARBOLES_202010814",archivo_dot_arbol); 
            if(!f.exists()){
                f.createNewFile();
            }
            
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.Arbol);
            bw.close();
            
            
            try {
            
                String arg1 = f.getAbsolutePath();
                String arg2 = f.getPath();
              
                String[] c = {"dot", "-Tpng", arg2, "-O"};
                Process p = Runtime.getRuntime().exec(c); 
                int err = p.waitFor();
            
           
            
            } catch(IOException e1) {
                System.out.println(e1);
            } catch(InterruptedException e2) {
                System.out.println(e2);
            }
            
            

        } catch(IOException e1) {
            System.out.println(e1);
        }
        
        
        
        String archivo_dot_siguientes = fecha_string+extension;
        
        try {
            File f = new File(directoryName+"/SIGUIENTES_202010814",archivo_dot_siguientes); 
            if(!f.exists()){
                f.createNewFile();
            }
            
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.Siguientes);
            bw.close();
            
            
            try {
            
                String arg1 = f.getAbsolutePath();
                String arg2 = f.getPath();
              
                String[] c = {"dot", "-Tpng", arg2, "-O"};
                Process p = Runtime.getRuntime().exec(c); 
                int err = p.waitFor();
            
           
            
            } catch(IOException e1) {
                System.out.println(e1);
            } catch(InterruptedException e2) {
                System.out.println(e2);
            }
            
            

        } catch(IOException e1) {
            System.out.println(e1);
        }
        
        
        String archivo_dot_transiciones = fecha_string+extension;
        
        try {
            File f = new File(directoryName+"/TRANSICIONES_202010814",archivo_dot_transiciones); 
            if(!f.exists()){
                f.createNewFile();
            }
            
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.Transiciones);
            bw.close();
            
            
            try {
            
                String arg1 = f.getAbsolutePath();
                String arg2 = f.getPath();
              
                String[] c = {"dot", "-Tpng", arg2, "-O"};
                Process p = Runtime.getRuntime().exec(c); 
                int err = p.waitFor();
            
           
            
            } catch(IOException e1) {
                System.out.println(e1);
            } catch(InterruptedException e2) {
                System.out.println(e2);
            }
            
            

        } catch(IOException e1) {
            System.out.println(e1);
        }
        
        
        
        String archivo_dot_afd = fecha_string+extension;
        
        try {
            File f = new File(directoryName+"/AFD_202010814",archivo_dot_afd); 
            if(!f.exists()){
                f.createNewFile();
            }
            
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.AFD);
            bw.close();
            
            
            try {
            
                String arg1 = f.getAbsolutePath();
                String arg2 = f.getPath();
              
                String[] c = {"dot", "-Tpng", arg2, "-O"};
                Process p = Runtime.getRuntime().exec(c); 
                int err = p.waitFor();
            
           
            
            } catch(IOException e1) {
                System.out.println(e1);
            } catch(InterruptedException e2) {
                System.out.println(e2);
            }
            
            

        } catch(IOException e1) {
            System.out.println(e1);
        }
        
        
        
        
        
        
        /*<h2 align="center"> Tabla errores</h2>

    <table border="1">
        <tr>
            <td>#</td>
            <td>Tipo error</td>
            <td>Descripcion</td>
            <td>Linea</td>
            <td>Columna</td>
        </tr>
        <tr>
            <td>Pera</td>
        </tr>
        <tr>
            <td>Banano</td>
        </tr>

    </table>*/
        
        
        
        
        
    }
    
    
    
    
    
}
