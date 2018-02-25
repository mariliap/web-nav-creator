package acoesVisitors;

import commons.GenericEntity;
import elementos.Elemento;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marilia on 07/02/2018.
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PaginaAcoesVisitor_Type")
public abstract class PaginaAcoesVisitor extends GenericEntity implements AcoesVisitor{

//    @OneToMany(mappedBy = "visitor")
//    private List<Elemento> elementos = new ArrayList<Elemento>();
//
//    public List<Elemento> getElementos() {
//        return elementos;
//    }
//
//    public void setElementos(List<Elemento> elementos) {
//        this.elementos = elementos;
//    }
}
