package elementos;

import acoesBasics.Acao;
import paginas.Pagina;

/**
 * Created by Marilia on 07/02/2018.
 */
public class Elemento {

    private Pagina pagina;
    private String xpath;

    public Pagina getPagina() {
        return pagina;
    }

    public void setPagina(Pagina pagina) {
        this.pagina = pagina;
    }

    public Acao decorarAcao(Acao original){
        //if(original instanceof AcaoClicarBotao && pagina.existePainelProcessamento()) {
        //    return new AcaoEsperaVisibilidadeDecorator(original);
        //} else {
        //    return original;
        //}
        return original;
    }

}
