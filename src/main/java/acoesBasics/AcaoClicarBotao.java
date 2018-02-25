package acoesBasics;

import acoesVisitors.AcoesVisitor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Marilia on 07/02/2018.
 */
@Entity
@DiscriminatorValue("AcaoClicarBotao")
public class AcaoClicarBotao extends Acao {

    @Override
    public void executarOperacaoPrincipal() {
        System.out.print("\nAcaoClicarBotao Elemento: " + getElemento().getNome() +" > ");
        //super.executar();
    }

    @Override
    public Acao accept(AcoesVisitor av) {
        return av.visit(this);
    }
}
