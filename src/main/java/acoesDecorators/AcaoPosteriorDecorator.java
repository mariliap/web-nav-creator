package acoesDecorators;

import acoesBasics.Acao;

/**
 * Created by Marilia on 07/02/2018.
 */
public class AcaoPosteriorDecorator extends AcaoDecorator {

    public AcaoPosteriorDecorator(Acao acaoDASerIncrementada) {
        super(acaoDASerIncrementada);
    }

    @Override
    public void executarOperacaoPrincipal() {

        super.executarOperacaoPrincipal();
        System.out.print("\nAcaoPosteriorDecorator > ");
    }
}
