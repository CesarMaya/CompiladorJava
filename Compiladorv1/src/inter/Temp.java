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
public class Temp extends Expr{
    static int conteo = 0;
    int numero = 0;
    public Temp(Tipo p){super(Palabra.temp, p); numero = ++conteo;}
    public String toString(){return "t"+numero;}
    
}
