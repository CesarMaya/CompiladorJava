
package inter;

import symbols.Type;
import analizadorLexico.*;

public class Id extends Expr{
    public int desplazamiento;
    
    public Id(Palabra id, Type p, int b) {
        super(id, p);
        desplazamiento = b;
    }
    
}
