
package inter;

import analizadorLexico.*;
import simbolos.*;

public class Logica extends Expr{
    public Expr exprl, expr2;
    Logica(Token tok, Expr xl, Expr x2){
        super(tok, null);
        exprl = xl;
        expr2 = x2;
        tipo = comprobar(exprl.tipo, expr2.tipo);
        if(tipo == null)
            error("errordetipo");
    }
    public Tipo comprobar(Tipo p1, Tipo p2){
        if(p1 == Tipo.Bool && p2 == Tipo.Bool)
            return Tipo.Bool;
        else return null;
    }
    
    public Expr gen(){
        int f = nuevaEtiqueta();
        int a = nuevaEtiqueta();
        Temp temp = new Temp(tipo);
        this.salto(0, f);
        emitir(temp.toString () + " = true");
        emitir("goto L" + a);
        emitirEtiqueta(f);
        emitir(temp.toString() + " = false");
        emitirEtiqueta(a);
        return temp;
    }
    
    public String toString(){
        return exprl.toString() + " " + op.toString() + " " + expr2.toString();
    }
}