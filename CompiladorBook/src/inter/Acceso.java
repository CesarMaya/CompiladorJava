
package inter;

import lexer.Etiqueta;
import lexer.Palabra;
import symbols.Type;

public class Acceso extends Op{
    public Id arreglo;
    public Expr indice;
    public Acceso(Id a, Expr i, Type p){
        super(new Palabra("□", Etiqueta.INDEX), p); // aplanarelarreglo
        arreglo = a;
        indice = i;
    }
    
    public Expr gen(){
        return new Acceso(arreglo, indice.reducir(), tipo);
    }
    
    /*public void salto(int t, int f){
        emitirSaltos(reducir().toString(), t, f);
    }*/
    
    public String toString(){
        return arreglo.toString() + " [ " + indice.toString() + " ]";
    }
}