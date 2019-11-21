/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inter;
import simbolos.*;
/**
 *
 * @author LocalFam
 */
public class If extends Instr{
    
    Expr expr; Instr instr;

    public If(Expr x, Instr s) {
      expr = x;  instr = s;
      if( expr.tipo != Tipo.Bool ) expr.error("Se Requiere booleano en if");
   }

   public void gen(int b, int a) {
      int etiqueta = nuevaEtiqueta(); // label for the code for stmt
      expr.salto(0, a);     // fall through on true, goto a on false
      emitirEtiqueta(etiqueta); instr.gen(etiqueta, a);
   }
    
}
