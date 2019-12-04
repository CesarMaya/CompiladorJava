
package compiladorbook;
import lexer.AnalizadorLexico;
import java.io.*;
import analizador.* ;
/**
 *
 * @author 130182
 */

public class CompiladorBook {
    public static void main(String[] args) throws IOException {
        AnalizadorLexico lex = new AnalizadorLexico() ;
        Analizador analizar = new Analizador(lex);
        analizar.programa();
        System.out.write('\n');
    }
}