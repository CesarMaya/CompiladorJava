/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inter;
import simbolos.*;
import analizadorLexico.*;
/**
 *
 * @author LocalFam
 */
public class Constante extends Expr{
    public Constante(Token tok, Tipo p) { super(tok, p ); }
    public Constante(int i) { super(new Num(i), Tipo.Int); }
    public static final Constante
            True = new Constante(Palabra.True, Tipo.Bool),
            False = new Constante(Palabra.False, Tipo.Bool);
    public void salto (int t , int f) {
        if ( this == True && t != 0 ) emitir("goto L" + t ); 
        else if ( this == False && f != 0) emitir("goto L" + f);
        
    }       
            
    
}
