
package inter;

import lexer.*;
import symbols.*;

public class Arith extends Op{
    public Expr exprl, expr2;
    
    public Arith(Token tok, Expr xl, Expr x2) {
        super(tok, null);
        exprl = xl;
        expr2 = x2;
        type = Type.max(exprl.type, expr2.type);
        if (type == null ) error("error de tipo");
    }
    
    public Expr gen() {
        return new Arith(op, exprl.reduce(), expr2.reduce());
    }
    
    public String toString(){
        return exprl.toString() + " " + op.toString() + " " + expr2.toString();
    }
}