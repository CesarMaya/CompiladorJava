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
public class Unario extends Op{
    
    public Expr expr;
    
    public Unario(Token tok, Expr x)
    {
        super(tok, null); expr = x;
        tipo = Tipo.max(Tipo.Int, expr.tipo);
        if(tipo == null) error("error de tipo");
    }
    public Expr gen(){return new Unario(op, expr.reducir());}
    public String toString(){return op.toString()+" "+expr.toString();}
    
}
