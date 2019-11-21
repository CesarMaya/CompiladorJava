/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadorLexico;

/**
 *
 * @author LocalFam
 */
public class Palabra extends Token {
    public String lexema = "";
    public Palabra(String s, int etiqueta){super(etiqueta); lexema=s;}
    public String toString(){return lexema;}
    public static final Palabra
            and = new Palabra("&&",Etiqueta.AND), or = new Palabra( "||", Etiqueta.OR),
            eq = new Palabra("==",Etiqueta.EQ), ne = new Palabra("!=", Etiqueta.NE),
            le = new Palabra("<=",Etiqueta.LE) ,ge = new Palabra(">=", Etiqueta.GE),
            minus = new Palabra("minus", Etiqueta.MINUS),
            True = new Palabra("true", Etiqueta.TRUE),
            False = new Palabra("false", Etiqueta.FALSE),
            temp = new Palabra("t", Etiqueta.TEMP);
}
