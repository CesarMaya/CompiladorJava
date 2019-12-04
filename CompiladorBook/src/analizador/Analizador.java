
package analizador;


import lexer.Etiqueta;
import lexer.AnalizadorLexico;
import lexer.Num;
import lexer.Palabra;
import lexer.Token;
import symbols.Type;
import symbols.Env;
import symbols.Array;
import java.io.*;
import inter.*;

public class Analizador {
    private AnalizadorLexico lex;
    private Token busca;
    Env sup = null;
    int usado = 0;
    
    public Analizador(AnalizadorLexico l) throws IOException{
        lex = l;
        mover();
    }
    
    void mover() throws IOException{
        busca = lex.explorar(); // .escanear();
    }
    
    void error(String s){
        throw new Error("cerca de linea " + lex.linea + ": " + s);
    }
    
    void coincidir(int t) throws IOException{
        if(busca.etiqueta == t) mover();
        else error("error de sintaxis");
    }
    
    public void programa() throws IOException{
        Instr s = bloque();
        int inicio = s.nuevaEtiqueta();
        int despues = s.nuevaEtiqueta();
        s.emitirEtiqueta(inicio);
        s.gen(inicio, despues);
        s.emitirEtiqueta(despues);
    }

    Instr bloque() throws IOException{//bloque-}{decísinstrs}
        coincidir('{');
        Env entGuardado = sup;
        sup = new Env(sup);
        decls();
        Instr s = instrs();
        coincidir('}');
        sup = entGuardado;
        return s;
    }
    
    void decls() throws IOException{
        while(busca.etiqueta == Etiqueta.BASIC){//D-}tipoID;
            Type p = tipo();
            Token tok = busca;
            coincidir(Etiqueta.ID);
            coincidir(';');
            Id id = new Id( (Palabra) tok, p, usado);
            sup.put(tok, id);
            usado = usado + p.anchura;
        }
    }
    
    Type tipo() throws IOException{
        Type p = (Type)busca;
        coincidir(Etiqueta.BASIC);
        if(busca.etiqueta != '[') return p;
        else return dims(p);
    }
    
    Type dims(Type p) throws IOException{
        coincidir('[');
        Token tok = busca;
        coincidir(Etiqueta.NUM);
        coincidir(']');
        if(busca.etiqueta == '[')
            p = dims(p);
        return new Array(((Num) tok).valor, p);
    }

    Instr instrs() throws IOException{
        if(busca.etiqueta == '}') return Instr.Null;
        else return new Sec(instr(), instrs());
    }

    Instr instr() throws IOException{
        Expr x;
        Instr s, s1, s2;
        Instr instrGuardada;
        switch(busca.etiqueta){
            case ';':
                mover();
                return Instr.Null;
            case Etiqueta.IF:
                coincidir(Etiqueta.IF);
                coincidir('(');
                x = bool();
                coincidir(')');
                s1 = instr();
                if(busca.etiqueta != Etiqueta.ELSE) return new If(x, s1);
                coincidir(Etiqueta.ELSE);
                s2 = instr();
                return new Else(x, s1, s2);
            case Etiqueta.WHILE:
                While nodowhile = new While();
                instrGuardada = Instr .Circundante;
                Instr .Circundante = nodowhile;
                coincidir(Etiqueta.WHILE);
                coincidir('(');
                x = bool();
                coincidir(')');
                s1 = instr();
                nodowhile.inic(x, s1);
                Instr.Circundante = instrGuardada;//restableceInstr.Circundante
                return nodowhile;
            case Etiqueta.DO:
                Do nododo = new Do();
                instrGuardada = Instr.Circundante;
                Instr.Circundante = nododo;
                coincidir(Etiqueta.DO);
                s1 = instr();
                coincidir(Etiqueta.WHILE);
                coincidir('(');
                x = bool();
                coincidir(')');
                coincidir(';');
                nododo.inic(s1, x);
                Instr.Circundante = instrGuardada; //restableceInstr.Circundante
                return nododo;
            case Etiqueta.BREAK:
                coincidir(Etiqueta.BREAK);
                coincidir(';');
                return new Break();
            case '{':
                return bloque();
            default:
                return asignar();
        }
    }

    Instr asignar() throws IOException{
        Instr instr;
        Token t = busca;
        coincidir(Etiqueta.ID);
        Id id = sup.get(t);
        if(id == null) error(t.toString () + " no declarado");
        if(busca.etiqueta == '='){//S-}id = E;
            mover();
            instr = new Est(id, bool());
        }
        else {//S-}L = E;
            Acceso x = desplazamiento(id);
            coincidir('=');
            instr = new EstElem(x, bool());
        }
        coincidir(';');
        return instr;
    }
    
    Expr bool() throws IOException{
        Expr x = unir();
        while(busca.etiqueta == Etiqueta.OR){
            Token tok = busca;
            mover();
            x = new Or(tok, x, unir());
        }
        return x;
    }
    
    Expr unir() throws IOException{
        Expr x = igualdad();
        while(busca.etiqueta ==  Etiqueta.AND){
            Token tok = busca;
            mover();
            x = new And(tok, x, igualdad());
        }
        return x;
    }

    Expr igualdad() throws IOException{
        Expr x = rel();
        while(busca.etiqueta == Etiqueta.EQ || busca.etiqueta == Etiqueta.NE){
            Token tok = busca;
            mover();
            x = new Rel(tok, x, rel());
        }
        return x;
    }
    
    Expr rel() throws IOException{
        Expr x = expr();
        switch(busca.etiqueta){
            case '<':
            case Etiqueta.LE:
            case Etiqueta.GE:
            case '>':
                Token tok = busca;
                mover();
                return new Rel(tok, x, expr());
            default:
                return x;
        }
    }

    Expr expr() throws IOException{
        Expr x = term();
        while(busca.etiqueta == '+' || busca.etiqueta == '-'){
            Token tok = busca;
            mover();
            x = new Arith(tok, x, term());
        }
        return x;
    }
    
    Expr term() throws IOException{
        Expr x = unario();
        while(busca.etiqueta == '*' || busca.etiqueta == '/'){
            Token tok = busca;
            mover();
            x = new Arith(tok, x, unario());
        }
        return x;
    }
    
    Expr unario() throws IOException{
        if(busca.etiqueta == '-'){
            mover();
            return new Unary(Palabra.minus, unario());
        }
        else if(busca.etiqueta ==  '!'){
            Token tok = busca;
            mover();
            return new Not(tok, unario());
        }
        else return factor();
    }

    Expr factor() throws IOException{
        Expr x = null;
        switch(busca.etiqueta){
            case '(':
                mover();
                x = bool();
                coincidir(')');
                return x;
            case Etiqueta.NUM:
                x = new Constante(busca, Type.Int);
                mover();
                return x;
            case Etiqueta.REAL:
                x = new Constante(busca, Type.Float);
                mover();
                return x;
            case Etiqueta.TRUE:
                x = Constante.True;
                mover();
                return x;
            case Etiqueta.FALSE:
                x = Constante.False;
                mover();
                return x;
            default:
                error("error de s1ntaxis");
                return x;
            case Etiqueta.ID:
                String s = busca.toString ();
                Id id = sup.get(busca);
                if(id == null) error(busca.toString() + "no declarado");
                mover();
                if(busca.etiqueta != '[') return id;
                else return desplazamiento(id);
        }
    }

    Acceso desplazamiento(Id a) throws IOException{//I = }[E]|[E]I
        Expr i;
        Expr w;
        Expr t1, t2;
        Expr ubic;
        Type tipo = a.tipo;
        coincidir('[');
        i = bool();
        coincidir(']');//primerÍndice, I = }[E]
        tipo = ((Array) tipo).de;
        w = new Constante(tipo.anchura);
        t1 = new Arith(new Token('*'), i, w);
        ubic = t1;
        while(busca.etiqueta ==  '['){//multi = dimens1onalI = }[E]I
            coincidir('[');
            i = bool();
            coincidir('[');
            tipo = ((Array) tipo).de;
            w = new Constante(tipo.anchura);
            t1 = new Arith(new Token('*'), i, w);
            t2 = new Arith(new Token('+'), ubic, t1);
            ubic = t2;
        }
        return new Acceso(a, ubic, tipo);
    }
}