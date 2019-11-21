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
public class Instr extends Nodo{
   public Instr() { }

   public static Instr Null = new Instr();

   public void gen(int b, int a) {} 

   int despues = 0;                 
   public static Instr Circundante = Instr.Null;  
    
    
}
