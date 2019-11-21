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
public class Break extends Instr {
   Instr instr;

   public Break() {
      if( Instr.Circundante == Null ) error("break no cerrada");
      instr = Instr.Circundante;
   }

   public void gen(int b, int a) {
      emitir( "goto L" + instr.despues);
   }
    
}
