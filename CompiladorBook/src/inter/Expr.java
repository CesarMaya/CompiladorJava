
package inter;

import symbols.Type;
import analizadorLexico.*;

public class Expr extends Node{
    public  Token op;
    public Type  tipo;
    Expr (Token tok, Type p){
        op = tok;
        tipo = p;
    }
    
    public  Expr gen(){ return this; }
    
    public  Expr reducir(){ return this; }
    
    public  void salto(int t, int f){
        emitirsaltos(toString(), t, f);
    }
    
    public  void emitirsaltos(String prueba, int t, int f){
        if(t != 0 && f != 0){
            emitir("if " + prueba + " gotoL" + t);
            emitir("goto L" + f);
        }
        else if(t != 0)
            emitir("if " + prueba + " goto L" + t);
        else if(f != 0)
            emitir("iffalse " + prueba + " goto L" + f);
        else ; // nada, ya que tanto t como f pasan directo
    }
    
    public  String toString(){ return op.toString(); }
}