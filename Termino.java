/**
 * Esta clase crea cada termino con coeficiente y exponente
 * 
 * @author Cristhian Adal Garcia Hernadez
 * @version 2.0
 */
public class Termino
{
    private double coeficiente;
    private int exponente;
    
    public Termino(double coeficiente, int exponente)
    {
        this.coeficiente = coeficiente;
        this.exponente = exponente;
    }
    
    public double evalua(double x)
    {
        return coeficiente * ( Math.pow ( x,exponente ) );
    }

    public int getExponente()
    {
        return this.exponente;
    }
    
    public double getCoeficiente()
    {
        return this.coeficiente;
    }
}
