
package inter;

import analizadorLexico.*;
import simbolos.*;

public class Arit extends Op{
    public Expr exprl, expr2;
    
    public Arit(Token tok, Expr xl, Expr x2) {
        super(tok, null);
        exprl = xl;
        expr2 = x2;
        tipo = Tipo.max(exprl.tipo, expr2.tipo);
        if (tipo == null ) error("error de tipo");
    }
    
    public Expr gen() {
        return new Arit(op, exprl.reducir(), expr2.reducir());
    }
    
    public String toString(){
        return exprl.toString() + " " + op.toString() + " " + expr2.toString();
    }
}