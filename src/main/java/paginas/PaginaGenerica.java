package paginas;

import acoesBasics.*;
import elementos.Elemento;

import java.util.List;

/**
 * Created by Marilia on 07/02/2018.
 */
public class PaginaGenerica extends PaginaOld {

    private List<Elemento> elementos;

    public Acao getAcaoEspera(){
        return null;
    }

    public Acao decorarAcao(AcaoClicarBotao original){
        return original;
    }

    public Acao decorarAcao(AcaoPreencherCampo original){
        return original;
    }

    public Acao decorarAcao(AcaoValidarInformacao original){
        return original;
    }

    public Acao decorarAcao(AcaoSelecionarItem original){
        return original;
    }
}
