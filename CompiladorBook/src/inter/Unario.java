
package inter;

import symbols.Type;
import analizadorLexico.*;

public class Unario extends Op{
    public Expr expr;

    public Unario(Token tok, Expr x) {
        super(tok, null);
        expr = x;
        tipo = Type.max(Type.Int, expr.tipo);
        if(tipo == null) error("error de tipo");
    }
    
    public Expr gen(){
        return new Unario(op, expr.reducir());
    }
    
    public String toString(){
        return op.toString() + " " + expr.toString();
    }
}