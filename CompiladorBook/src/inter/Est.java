
package inter;


import symbols.Type;

public class Est extends Instr{
    public Id id;
    public Expr expr;
    public Est(Id i, Expr x){
        id = i;
        expr = x;
        if(comprobar(id.tipo, expr.tipo) == null)
            error("error de tipo");
    }
    
    public Type comprobar(Type p1, Type p2){
        if(Type.numerico(p1) && Type.numerico(p2)) return p2;
        else if(p1 == Type.Bool && p2 == Type.Bool)return p2;
        else return null;
    }
    
    public void gen(int b, int a){
        emitir(id.toString() + " = " + expr.gen().toString());
    }
}