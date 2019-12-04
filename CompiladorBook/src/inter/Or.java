
package inter;

import lexer.Token;

public class Or extends Logica{
    public Or(Token tok, Expr xl, Expr x2){
        super(tok, xl, x2);
    }
    
    public void salto(int t, int f){
    int etiqueta = t !=0 ? t: nuevaEtiqueta();
    exprl.salto(etiqueta, 0);
    expr2.salto(t, f);
    if(t == 0) emitirEtiqueta(etiqueta);
    }
}