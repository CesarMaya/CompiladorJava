/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inter;
import simbolos.*;
import analizadorLexico.*;
/**
 *
 * @author LocalFam
 */
public class And extends Logica{
    public And(Token tok, Expr x1, Expr x2) { super(tok, x1, x2); }
    public void salto(int t, int f) {
      int etiqueta = t != 0 ? t : nuevaEtiqueta();
      expr1.salto(etiqueta, 0);
      expr2.salto(t,f);
      if( t == 0 ) emitirEtiqueta(etiqueta);
    }
    
}
