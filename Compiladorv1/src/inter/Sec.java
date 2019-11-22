/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inter;

/**
 *
 * @author LocalFam
 */
public class Sec extends Instr {
   
   Instr instr1; Instr instr2;

   public Sec(Instr s1, Instr s2) { instr1 = s1; instr2 = s2; }

   public void gen(int b, int a) {
      if ( instr1 == Instr.Null ) instr2.gen(b, a);
      else if ( instr2 == Instr.Null ) instr1.gen(b, a);
      else {
         int etiqueta = nuevaEtiqueta();
         instr1.gen(b,etiqueta);
         emitirEtiqueta(etiqueta);
         instr2.gen(etiqueta,a);
      }
   }
    
    
}
