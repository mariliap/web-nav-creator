package acoesVisitors;

import acoesBasics.Acao;

/**
 * Created by Marilia on 25/02/2018.
 */
public interface VisitableAcao {

    Acao accept(AcoesVisitor visitor);
}
