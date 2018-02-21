package main.java;

/**
 * Created by Marilia on 07/02/2018.
 */
public abstract class Pagina {

    public abstract Acao getAcaoEspera();

    public abstract Acao decorarAcao(AcaoClicarBotao original);

    public abstract Acao decorarAcao(AcaoPreencherCampo original);

    public abstract Acao decorarAcao(AcaoValidarInformacao original);

    public abstract Acao decorarAcao(AcaoSelecionarItem original);
}
