
package inter;

import symbols.Type;
import symbols.Array;
import analizadorLexico.*;

public class EstElem extends Instr{
    public Id arreglo;
    public Expr indice;
    public Expr expr;
    
    public EstElem(Acceso x, Expr y){
        arreglo = x.arreglo;
        indice = x.indice;
        expr = y;
        if(comprobar(x.tipo, expr.tipo) == null)
            error("errordetipo");
    }
    
    public Type comprobar(Type p1, Type p2){
        if(p1 instanceof Array || p2 instanceof Array) return null;
        else if(p1 == p2) return p2;
        else if(Type.numerico(p1) && Type.numerico(p2)) return p2;
        else return null;
    }
    public void gen(int b, int a){
        String s1 = indice.reducir().toString();
        String s2 = expr.reducir().toString();
        emitir(arreglo.toString() + " [ " + s1 + " ] = " + s2);
    }
}