package acoesDecorators;

import acoesBasics.Acao;
import acoesVisitors.AcoesVisitor;

/**
 * Created by Marilia on 07/02/2018.
 */
public class AcaoDecorator extends Acao {

    private Acao acaoASerIncrementada = null;


    public AcaoDecorator(Acao acaoDASerIncrementada) {
        this.acaoASerIncrementada = acaoDASerIncrementada;
    }

    @Override
    public void executarOperacaoPrincipal() {
        this.acaoASerIncrementada.executarOperacaoPrincipal();
    }

    @Override
    public Acao accept(AcoesVisitor av) {
        return this.acaoASerIncrementada.accept(av);
    }
}
