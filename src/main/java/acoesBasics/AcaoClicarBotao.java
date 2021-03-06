package acoesBasics;

import acoesVisitors.AcoesVisitor;
import org.openqa.selenium.By;

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

        if(getDriver()!=null) {
            getDriver().findElement(By.xpath(getElemento().getXpath())).click();
        }
        //super.executar();
    }

    @Override
    public Acao accept(AcoesVisitor av) {
        return av.visit(this);
    }
}
