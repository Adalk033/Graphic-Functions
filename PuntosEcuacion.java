/**
 * Esta clase tiene el trabajo de crear la base de las graficas con su plano cartesiano.
 *
 * @author Cristhian Adal Garcia Hdez.
 * @version 2.9.5.6
 */
import java.util.Vector;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.JOptionPane;

public class PuntosEcuacion extends Frame implements Runnable
{
    private Vector<Punto> puntos;
    private Polinomio polinomio;
    private  Vector<Termino> termin;
    private double li;
    private Thread thread;
    private int cuenta=0;
    private Color coloritos;
    public PuntosEcuacion(Polinomio polinomio,double limiteInferior,double lsup,double inc, Color color_2)
    {
        this.polinomio = polinomio;
        puntos = new Vector<Punto>();
        li = limiteInferior;       
        for(int index=0; index <= ( ( lsup-limiteInferior ) / inc ); index++ )
        {
            puntos.addElement( new Punto( li, this.polinomio.evalua( li ) ) );
            li = li + inc;
        }
        setSize(900,900);        
        setVisible(true);
        thread = new Thread(this);
        thread.start();
        coloritos = color_2;
    }
    
    public PuntosEcuacion()
    {
        super("Graficacion de Funciones");   
    }
    
    public Vector getPuntosEcuacion()
    {
        return puntos;
    }
    
    public void run()
    {
        
    }
    
    public boolean handleEvent(Event e)
    {
        if(e.id == Event.WINDOW_DESTROY)//Ayuda a saber si el clic es un cierre de ventana 
           {
               hide(); 
               dispose(); //libera los recursos de la ventana
               return true;
            }
        return super.handleEvent(e);        
    }
    
    public Punto getPunto(int numero)
    {
        return puntos.elementAt(numero);
    }
    
    public void paint(Graphics g)
    {
        Graphics2D G2D = (Graphics2D) g;
        
        //Hago el centro de todo el plano 
        G2D.translate(450,450);
        final float dash[]={ 15.0f };
        
        //Dibujo los ejes X y Y
        G2D.setStroke(new BasicStroke(3.0f));   
        G2D.draw( new Line2D.Float(0,420,0,-420));
        G2D.draw( new Line2D.Float(420,0,-420,0));
        
        //Se dibujan todas las lineas tanto en X como en Y para cuadricular
        for (int index = -420; index<420; index=index+22)
        {            
            G2D.setStroke(new BasicStroke(0.5f));
            G2D.draw( new Line2D.Float(420, index, -420, index) );
        }
        for (int index = -420; index<420; index=index+22)
        {
            G2D.setStroke(new BasicStroke(0.5f));
            G2D.draw( new Line2D.Float(index, 420, index, -420) );
        }
        Font etiqueta = new Font ("Cambria", Font.BOLD,30);
        G2D.setFont(etiqueta);
        G2D.setColor(Color.BLACK);
        G2D.drawString("Y", 10,-390);
        G2D.drawString("X", 390,-15);
        //Dibujo la funcion
                 
        //Hacemos la generacion de las lineas
        GeneralPath lineas = new GeneralPath(GeneralPath.WIND_EVEN_ODD,puntos.size());                                        
                       
        lineas.moveTo(50*((double)puntos.elementAt(0).getX()), 50*(-1*((double)puntos.elementAt(0).getY())));
        //Va trazando cada parte de las lineas
        for(int index = 1; index < puntos.size(); index++)
        {   
            G2D.setColor(coloritos);  
            G2D.setStroke(new BasicStroke(3.5f));            
            lineas.lineTo(50*((double)puntos.elementAt(index).getX()), 50*(-1*((double)puntos.elementAt(index).getY())));       
            G2D.draw(lineas);
            try
            {
                thread.sleep(100);
            }catch( InterruptedException e )
            {
                e.printStackTrace();
            }
            G2D.setColor(coloritos);
            setVisible(true);
        }        
        if (cuenta < 1 )
        {
            getToolkit().beep();
            JOptionPane.showMessageDialog( null, "Graficacion Exitosa!!" );
            cuenta++;
        }
    }
    public static void main()
    {
       /*Polinomio a = new Polinomio(1);
       Termino b = new Termino(1,2);
       a.agregaTermino(b);
       PuntosEcuacion puntos_Ecuacion = new PuntosEcuacion(a,-100,100, .005);  
       puntos_Ecuacion.setSize(900,900);
       puntos_Ecuacion.show();*/
    }
}