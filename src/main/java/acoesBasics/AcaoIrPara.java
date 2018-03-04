package acoesBasics;

import acoesVisitors.AcoesVisitor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Marilia on 07/02/2018.
 */
@Entity
@DiscriminatorValue("AcaoIrPara")
public class AcaoIrPara extends Acao {

    @Override
    public void executarOperacaoPrincipal() {
        System.out.print("\nAcaoIrPara: " + getElemento().getNome() +" > ");

        if(getDriver()!=null) {
            getDriver().get(getElemento().getXpath());
        }
        //super.executar();
    }

    @Override
    public Acao accept(AcoesVisitor av) {
        return av.visit(this);
    }
}
