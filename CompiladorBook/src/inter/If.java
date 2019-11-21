
package inter;

import symbols.Type;

public class If extends Instr{
    Expr expr;
    Instr instr;
    
    public If(Expr x, Instr s){
        expr = x;
        instr = s;
        if(expr.tipo != Type.Bool)
            expr.error("se requiere booleano en if");
    }
    
    public void gen(int b, int a){
        int etiqueta = nuevaEtiqueta(); // etiquetaparaelcodigodeinstr
        expr.salto(0, a); // pasaporaltoentrue, vahaciaaenfalse
        emitirEtiqueta(etiqueta);
        instr.gen(etiqueta, a);
    }
}