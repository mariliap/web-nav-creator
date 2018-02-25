package elementos;

import acoesBasics.Acao;
import acoesVisitors.PaginaAcoesVisitor;
import commons.GenericEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Marilia on 07/02/2018.
 */
@Entity
public class Elemento extends GenericEntity {

    //private PaginaOld pagina;
    @Column
    private String xpath;

    @Column
    private String nome;

    @ManyToOne //@MapsId
    private PaginaAcoesVisitor visitor;// = new PaginaGenenericaAcoesVisitor();

    public PaginaAcoesVisitor getVisitor() {
        return visitor;
    }

    public void setVisitor(PaginaAcoesVisitor visitor) {
        this.visitor = visitor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Acao decorar(Acao original){
        return original.accept(visitor);
        //return original;
    }

}
