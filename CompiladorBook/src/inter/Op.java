
package inter;

import symbols.Type;
import analizadorLexico.*;

public class Op extends Expr{
    
    public Op(Token tok, Type p) {
        super(tok, p);
    }
    
    public Expr reducir() {
        Expr x = gen();
        Temp t = new Temp(tipo);
        emitir(t.toString() + " = " + x.toString());
        return t;
    }
}