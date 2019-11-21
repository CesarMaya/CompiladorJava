
package inter;

import symbols.Type;
import symbols.Array;
import analizadorLexico.*;

public class Rel extends Logica{
    public Rel(Token tok, Expr xl, Expr x2){
        super(tok, xl, x2);
    }
    
    public Type comprobar(Type p1, Type p2){
        if(p1 instanceof Array || p2 instanceof Array)
            return null;
        else if(p1 == p2) return Type.Bool;
        else return null;
    }
    
    public void salto(int t, int f){
        Expr a = exprl.reducir();
        Expr b = expr2.reducir();
        String prueba = a.toString() + " " + op.toString() + " " + b.toString();
        emitirsaltos(prueba, t, f);
    }
}