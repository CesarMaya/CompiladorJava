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
public class Acceso extends Op {
    public Id arreglo;
    public Expr indice;
    public Acceso(Id a, Expr i, Tipo p) {    
      super(new Palabra("[]", Etiqueta.INDEX), p);  
      arreglo = a; indice = i;
   }
    
    public Expr gen() { return new Acceso(arreglo, indice.reducir(), tipo); }

   public void salto(int t,int f) { emitirsaltos(reducir().toString(),t,f); }

   public String toString() {
      return arreglo.toString() + " [ " + indice.toString() + " ]";
   }

    
}
