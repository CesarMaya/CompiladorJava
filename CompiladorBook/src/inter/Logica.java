
package inter;

import symbols.Type;
import analizadorLexico.*;

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
    public Type comprobar(Type p1, Type p2){
        if(p1 == Type.Bool && p2 == Type.Bool)
            return Type.Bool;
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