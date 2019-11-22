/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inter;
import simbolos.*;
import analizadorLexico.*;
/**

/**
 *
 * @author LocalFam
 */
public class Rel extends Logica{
    
    public Rel(Token tok, Expr x1, Expr x2) { super(tok, x1, x2); }
    public Tipo comprobar(Tipo p1, Tipo p2) {
      if ( p1 instanceof Arreglo || p2 instanceof Arreglo ) return null;
      else if( p1 == p2 ) return Tipo.Bool;
      else return null;
   }
    public void jumping(int t, int f) {
      Expr a = expr1.reducir();
      Expr b = expr2.reducir();
      String test = a.toString() + " " + op.toString() + " " + b.toString();
      emitirsaltos(test, t, f);
   }
    
    
    
}
