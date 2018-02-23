

import acoesBasics.*;
import elementos.Elemento;
import paginas.Pagina;
import paginas.PaginaEdicaoParceiro;
import paginas.PaginaGenerica;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marilia on 07/02/2018.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

//        List<AcaoD> listaAcoes = new ArrayList<>();
//        AcaoD acaoD = new AcaoDVazia();
//        listaAcoes.adicionar(acaoD);

//        acaoD = new AcaoPreencherCampoDecorator(acaoD);
//        acaoD.executar();
//
//        acaoD = new ChainResponsability.AcaoEsperaVisibilidadeDecorator(acaoD);
//        acaoD.executar();
//
//        acaoD = new AcaoValidarInformacaoDecorator(acaoD);
//        acaoD.executar();

//        acaoD = acaoD.compor(new AcaoPreencherCampoDecorator());
//
//        acaoD = acaoD.compor(new ChainResponsability.AcaoEsperaVisibilidadeDecorator());
//
//        acaoD = acaoD.compor(new AcaoValidarInformacaoDecorator());
//
//        acaoD.executar();


//        Acao acaoX = new AcaoPreencherCampo();
//        acaoX.adicionar(new AcaoClicarBotao());
//        acaoX.adicionar(new AcaoSelecionarItem());
//        acaoX.adicionar(new AcaoValidarInformacao());
//        acaoX.executar();


        Pagina pagina = new PaginaEdicaoParceiro();
        Elemento elemento1 = new Elemento();
        elemento1.setPagina(pagina);

        PaginaGenerica pagina2 = new PaginaGenerica();
        Elemento elemento2 = new Elemento();
        elemento2.setPagina(pagina2);


        List<Acao> listaAcoes = new ArrayList<>();

        Acao acao1 = new AcaoPreencherCampo();
        acao1.setElemento(elemento1);
        Acao acao1_1 = new AcaoClicarBotao();
        acao1_1.setElemento(elemento1);

        Acao acao2 = new AcaoPreencherCampo();
        acao2.setElemento(elemento1);
        Acao acao2_1 = new AcaoClicarBotao();
        acao2_1.setElemento(elemento2);

        Acao acao3 = new AcaoPreencherCampo();
        acao3.setElemento(elemento1);
//        Acao acao4 = new AcaoClicarBotao();
//        acao4.setElemento(elemento1);
        Acao acao5 = new AcaoSelecionarItem();
        acao5.setElemento(elemento1);
        Acao acao6 = new AcaoValidarInformacao();
        acao6.setElemento(elemento1);
        Acao acao7 = new AcaoValidarInformacao();
        acao7.setElemento(elemento1);
        Acao acao8 = new AcaoValidarInformacao();
        acao8.setElemento(elemento1);
        listaAcoes.add(acao1);
        listaAcoes.add(acao1_1);
        listaAcoes.add(acao2);
        listaAcoes.add(acao2_1);
        listaAcoes.add(acao3);
//        listaAcoes.add(acao4);
        listaAcoes.add(acao5);
        listaAcoes.add(acao6);
        listaAcoes.add(acao7);
        listaAcoes.add(acao8);

        Pagina pagAtual = null;
        Acao teste = new AcaoVazia();
        for (Acao acao : listaAcoes) {
//            if(pagAtual == null){
//                pagAtual = acao.getElemento().getPagina();
//            } else if(!pagAtual.equals(acao.getElemento().getPagina())){
//                acao = new AcaoEsperaVisibilidadeDecorator(acao);
//            }
            teste.adicionar(acao);
        }
        teste.executar();

    }
}
