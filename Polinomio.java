/**
 * Esta clase esta encargada de crear el polinomio adjuntado terminos
 *
 * @author Cristhian Adal Garcia Hdez.
 * @version 1.5.5
 */
public class Polinomio
{
    // instance variables - replace the example below with your own
    private Termino termino[];
    private int dos=0;
    private String cadena = "\0";
    public Polinomio(int grado)
    {
        termino = new Termino[grado+1];
    }
    
    public void agregaTermino(Termino term)
    {
        termino[dos]=term;
        dos++;
    }
    
    public double evalua(double x)
    {
        double evaluar = 0;
        for (int index = 0; index < termino.length; index++)
        {
            if( termino[index] != null )
                evaluar += termino[index].evalua(x);
        }
        return evaluar;
    }
    
    public String toString()
    {
        for(int gis = 0; gis<dos; gis++ )
        {
            if ( termino[gis].getCoeficiente() < 0 )
                cadena = ( cadena + termino[gis].getCoeficiente() + "x^ " + termino[gis].getExponente() );
            else
                cadena = (cadena + "+" + termino[gis].getCoeficiente() + "x^ " + termino[gis].getExponente() );
        }     
        return cadena;
    }
}
