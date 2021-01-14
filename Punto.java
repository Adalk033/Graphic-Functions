/**
 * Esta es una clase generica donde acepta cualquier dato
 *
 * @author Cristhian Adal Garcia Hdez.
 * @version 1.0
 */
public class Punto <E>
{
    private E x;
    private E y;
    
    public Punto(E x, E y){
        this.x = x;
        this.y = y;
    }

    public E getX(){
        return x;
    }
    
    public E getY(){
        return y;
    }
    
    public void setX(E x){
        this.x = x;
    }
    
    public void setY(E y){ 
        this.y = y;
    }
    
}