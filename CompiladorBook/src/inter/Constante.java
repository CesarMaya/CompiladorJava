
package inter;

import lexer.Num;
import lexer.Palabra;
import lexer.Token;
import symbols.Type;

public class Constante extends Expr{
    
    public Constante(Token tok, Type p) {
        super(tok, p);
    }
    
    public Constante(int i){
        super(new Num(i), Type.Int);
    }

    public static final Constante
    True = new Constante(Palabra.True, Type.Bool), 
    False = new Constante(Palabra.False, Type.Bool);
    
    public void salto(int t, int f){
        if(this == True && t != 0)
            emitir("gotoL" + t);
        else if(this == False && f!= 0)
            emitir("gotoL" + f);
    }
}