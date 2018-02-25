package acoesDecorators;

import acoesBasics.Acao;

/**
 * Created by Marilia on 07/02/2018.
 */
public class AcaoAnteriorDecorator extends AcaoDecorator {

    public AcaoAnteriorDecorator(Acao acaoDASerIncrementada) {
        super(acaoDASerIncrementada);
    }

    @Override
    public void executarOperacaoPrincipal() {

        System.out.print("\nAcaoAnteriorDecorator > ");
        super.executarOperacaoPrincipal();
    }
}
