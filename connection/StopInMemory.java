package connection;

import java.util.Optional;
import java.util.Vector;

//tato trieda je asi trocha zbytocna aj ked mozno je len vela hodin :D
//ale zda sa mi ze trieda Stop by mohla plnit tuto ulohu a stopDatabase od nej len dedit
public class StopInMemory extends Stop{
    private StopName name;
    private Optional<Time> reachableAt;
    private Optional<LineName> reachableVia;
    private Vector<LineName> lines;

    public StopInMemory(StopName stopName) {
        super(stopName);
    }

    @Override
    public void search(){
    }

}
