package acoesBasics;

import acoesVisitors.AcoesVisitor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Marilia on 25/01/2018.
 */
@Entity
@DiscriminatorValue("AcaoVazia")
public class AcaoVazia extends Acao {

    @Override
    public void executarOperacaoPrincipal() {
        System.out.print("AcaoVazia > ");
        //super.executar();
    }

    @Override
    public Acao accept(AcoesVisitor av) {
        return av.visit(this);
    }
}
