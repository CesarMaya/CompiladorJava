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
public class Not extends Logica{
    public Not(Token tok, Expr x2) { super(tok, x2, x2); }
    public void salto(int t, int f) { expr2.salto (f, t) ;}
    public String toString() { return op.toString()+" "+expr2.toString();
    }
    
}
