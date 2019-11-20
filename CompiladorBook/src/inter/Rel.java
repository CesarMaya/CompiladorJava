
package inter;

import analizadorLexico.*;
import simbolos.*;

public class Rel extends Logica{
    public Rel(Token tok, Expr xl, Expr x2){
        super(tok, xl, x2);
    }
    
    public Tipo comprobar(Tipo p1, Tipo p2){
        if(p1 instanceof Arreglo || p2 instanceof Arreglo)
            return null;
        else if(p1 == p2) return Tipo.Bool;
        else return null;
    }
    
    public void salto(int t, int f){
        Expr a = exprl.reducir();
        Expr b = expr2.reducir();
        String prueba = a.toString() + " " + op.toString() + " " + b.toString();
        emitirsaltos(prueba, t, f);
    }
}