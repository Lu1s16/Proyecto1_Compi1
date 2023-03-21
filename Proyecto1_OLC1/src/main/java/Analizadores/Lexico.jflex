/* 1. Package e importaciones */
package Analizadores;
import java_cup.runtime.Symbol;
import java.util.ArrayList;


%%
/* 2. Configuraciones para el analisis (Opciones y Declaaciones) */
%{
    //Codigo de usuario en sintaxis java
    //Agregar clases, variables, arreglos, objetos etc...
    public ArrayList<Nodo_error> lista_error = new ArrayList<Nodo_error>();
%}
//Directivas
%class Lexico
%public 
%cup
%char
%column
%full
%line
%unicode

//Inicializar el contador de columna y fila con 1
%init{ 
    yyline = 1; 
    yychar = 1; 
%init}


//Palabras reservadas
reservada = CONJ


//Espacios
BLANCOS=[ \t\r]+

//Simbolos
flecha = "-" {BLANCOS}* ">"


//comentarios 
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
comentariosimple = "//" {InputCharacter}* {LineTerminator}?

entrada = [^\!] | ("!"[^\>])
comentarioMulti = "<!" {entrada}+ "!>"

//caracteres especiales
ascii = [!-/] | [:-@] | [\[-`] | [{-}]


digitos = [0-9]+
minusculas = [a-z]
mayusculas = [A-Z]
identificador = [a-zA-Z_][a-zA-Z0-9_]+



comilla = \"

//cadena y caracter
escapados = "\\n" | "\\\"" | "\\\'"
no_escapados = [^\'\"]
caracter = (\" {no_escapados} \") | {escapados}
cadena = \" ([^\"] | "\\\"")+ \"



%%
/* 3. Reglas Semanticas */

//Comentarios
{comentarioMulti} {}
{comentariosimple} {}

//espacios
{BLANCOS} {} 

\n (yychar=1;)

//Palabras reservadas
{reservada} {  return new Symbol(sym.PR_CONJ,yyline,yychar,yytext());} 

//simbolos
":" {  return new Symbol(sym.DP,yyline,yychar,yytext());}

{flecha} {  return new Symbol(sym.FLECHA,yyline,yychar,yytext());}

"," {  return new Symbol(sym.COMA,yyline,yychar,yytext());}

"~" {  return new Symbol(sym.SEP,yyline,yychar,yytext());}

"{" {  return new Symbol(sym.LLA,yyline,yychar,yytext());}

"}" {  return new Symbol(sym.LLC,yyline,yychar,yytext());}

"%" {  return new Symbol(sym.POR,yyline,yychar,yytext());}

";" {  return new Symbol(sym.PC,yyline,yychar,yytext());}

{comilla} { } 


"?" {  return new Symbol(sym.QUESTION,yyline,yychar,yytext());}

"+" {  return new Symbol(sym.SUMA,yyline,yychar,yytext());}

"*" {  return new Symbol(sym.AST,yyline,yychar,yytext());}

"." {  return new Symbol(sym.PT,yyline,yychar,yytext());}

"|" {  return new Symbol(sym.BARRA,yyline,yychar,yytext());}

//identificadores
{identificador} { return new Symbol(sym.ID,yyline,yychar,yytext());}




{digitos} {  return new Symbol(sym.NUMBERS,yyline,yychar,yytext());}

{minusculas} { return new Symbol(sym.MIN,yyline,yychar,yytext());}

{caracter} {  return new Symbol(sym.CARACTER,yyline,yychar,yytext());}

{cadena} {  return new Symbol(sym.CADENA,yyline,yychar,yytext());}



{mayusculas} { return new Symbol(sym.MAY,yyline,yychar,yytext());}



//caracteres especiales
{ascii} {  System.out.println("Reconocio ascii: "+yytext()); 
          return new Symbol(sym.ASCII,yyline,yychar,yytext());} 






. {
    //Aqui se debe guardar los valores (yytext(), yyline, yychar ) para posteriormente generar el reporte de errores Léxicos.
    System.out.println("Este es un error lexico: "+yytext()+ ", en la linea: "+yyline+", en la columna: "+yychar);

    Nodo_error nuevo_error = new Nodo_error();

    nuevo_error.setSimbolo_error(yytext());
    nuevo_error.setLinea(yyline);
    nuevo_error.setColumna(yychar);

    lista_error.add(nuevo_error);



}

