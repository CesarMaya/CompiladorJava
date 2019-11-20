
package inter;

public class Sec extends Instr{
    Instr instr1, instr2;

    public Sec(Instr instr1, Instr instr2) {
        this.instr1 = instr1;
        this.instr2 = instr2;
    }
    
    public void gen(int b, int a){
        if(instr1 == Instr.Null)
            instr2.gen(b, a);
        else if(instr2 == Instr.Null)
            instr1.gen(b, a);
        else{
            int etiqueta = nuevaEtiqueta();
            instr1.gen(b, etiqueta);
            emitirEtiqueta(etiqueta);
            instr2.gen(etiqueta, a);
        }
    }
}