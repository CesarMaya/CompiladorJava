
package inter;

import analizadorLexico.*;
import simbolos.*;

public class And extends Logica{
    public And(Token tok, Expr xl, Expr x2){
        super(tok, xl, x2);
    }
    public void salto(int t, int f){
        int etiqueta = f != 0 ? f:nuevaEtiqueta();
        exprl.salto(etiqueta, 0);
        expr2.salto(t, f);
        if(f == 0) emitirEtiqueta(etiqueta);
    }
}