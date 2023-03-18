/* 1. Package e importaciones */
package Analizadores;
import java_cup.runtime.Symbol;

%%
/* 2. Configuraciones para el analisis (Opciones y Declaaciones) */

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
BLANCOS=[ \t\r\n]+

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
{comentarioMulti} {  System.out.println("Reconocio Comentario multilinea: "+yytext()); }
{comentariosimple} {  System.out.println("Reconocio comentario simple: "+yytext()); }

//espacios
{BLANCOS} {} 

//Palabras reservadas
{reservada} {  System.out.println("Reconocio PR: "+yytext()); 
          return new Symbol(sym.PR_CONJ,yyline,yychar,yytext());} 

//simbolos
":" {  System.out.println("Reconocio DP: "+yytext()); 
          return new Symbol(sym.DP,yyline,yychar,yytext());}

{flecha} {  System.out.println("Reconocio flecha: "+yytext()); 
          return new Symbol(sym.FLECHA,yyline,yychar,yytext());}

"," {  System.out.println("Reconocio COMA: "+yytext()); 
          return new Symbol(sym.COMA,yyline,yychar,yytext());}

"~" {  System.out.println("Reconocio SEP: "+yytext()); 
          return new Symbol(sym.SEP,yyline,yychar,yytext());}

"{" {  System.out.println("Reconocio LLA: "+yytext()); 
          return new Symbol(sym.LLA,yyline,yychar,yytext());}

"}" {  System.out.println("Reconocio LLC: "+yytext()); 
          return new Symbol(sym.LLC,yyline,yychar,yytext());}

"%" {  System.out.println("Reconocio POR: "+yytext()); 
          return new Symbol(sym.POR,yyline,yychar,yytext());}

";" {  System.out.println("Reconocio PC: "+yytext()); 
          return new Symbol(sym.PC,yyline,yychar,yytext());}

{comilla} { } 


"?" {  System.out.println("Reconocio question: "+yytext()); 
          return new Symbol(sym.QUESTION,yyline,yychar,yytext());}

"+" {  System.out.println("Reconocio suma: "+yytext()); 
          return new Symbol(sym.SUMA,yyline,yychar,yytext());}

"*" {  System.out.println("Reconocio asterisco: "+yytext()); 
          return new Symbol(sym.AST,yyline,yychar,yytext());}

"." {  System.out.println("Reconocio punto: "+yytext()); 
          return new Symbol(sym.PT,yyline,yychar,yytext());}

"|" {  System.out.println("Reconocio barra: "+yytext()); 
          return new Symbol(sym.BARRA,yyline,yychar,yytext());}

//identificadores
{identificador} {  System.out.println("Reconocio identificador: "+yytext()); 
          return new Symbol(sym.ID,yyline,yychar,yytext());}




{digitos} {  System.out.println("Reconocio digitos: "+yytext()); 
          return new Symbol(sym.NUMBERS,yyline,yychar,yytext());}

{minusculas} {  System.out.println("Reconocio minuscula: "+yytext()); 
          return new Symbol(sym.MIN,yyline,yychar,yytext());}

{caracter} {  System.out.println("Reconocio caracter: "+yytext()); 
          return new Symbol(sym.CARACTER,yyline,yychar,yytext());}

{cadena} {  System.out.println("Reconocio cadena: "+yytext()); 
          return new Symbol(sym.CADENA,yyline,yychar,yytext());}



{mayusculas} {  System.out.println("Reconocio mayuscula: "+yytext()); 
          return new Symbol(sym.MAY,yyline,yychar,yytext());}



//caracteres especiales
{ascii} {  System.out.println("Reconocio ascii: "+yytext()); 
          return new Symbol(sym.ASCII,yyline,yychar,yytext());} 






. {
    //Aqui se debe guardar los valores (yytext(), yyline, yychar ) para posteriormente generar el reporte de errores Léxicos.
    System.out.println("Este es un error lexico: "+yytext()+ ", en la linea: "+yyline+", en la columna: "+yychar);
}

