/**
 * Se hara un interfaz grafica de una graficadora con el metodo Swing.
 *
 * @author Cristhian Adal Garcia Hdez.
 * @version 5.9.5
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.io.*;      
public class PuntosEcuacionUI extends JFrame
{
    //Variables a considerar
    File archivo = new File ("coordenadas.csv");
    private PuntosEcuacion pe, abc;
    private Polinomio p;
    private Termino t;
    private JButton polinomio, termino, graficos, limite, botonIncremento, escritura, resetear, color;
    private JLabel caja1, caja2, caja3, caja4, caja5, caja6, caja7,view, mostrarFuncion, aumento;
    private JTextField agregacion, coeficiente, exponente, superior, inferior, incremento;
    private String exponen, coefic, arriba, abajo, uno, incre;
    private boolean bandera=false, flag=false;    
    private int coe, temp, exp, total, two=0, x=0;
    private double a,b,c;
    private Color colores_3;
    
    /**
     * Constructor for objects of class PuntosEcuacionUI
     */
    public PuntosEcuacionUI()
    {
        super("Graficadora de Funciones 9000");
        
        //Tipos de fuente
        Font titulo = new Font ("Dialog", Font.PLAIN, 18);
        Font etiquetas = new Font ("Dialog", Font.BOLD, 14);
        
        
        //Creaciones de las etiquetas
        caja1 = new JLabel ("Esta es una graficadora de funciones");
        caja2 = new JLabel ("Numero de terminos en el polinomio");
        caja3 = new JLabel ("Coeficiente del termino");
        caja4 = new JLabel ("Exponente del termino");
        caja5 = new JLabel ("Limite superior");
        caja6 = new JLabel ("Limite inferior");
        caja7 = new JLabel ("Incremento");
        aumento = new JLabel ("Terminos: 0");
        mostrarFuncion = new JLabel ("Funcion dada");
        view  = new JLabel ("0");
        
        //Creacion de los Botones
        polinomio       = new JButton ("Agregar numero de terminos"); 
        termino         = new JButton ("Agregar termino al polinomio");
        graficos        = new JButton ("Graficar Funcion");         
        limite          = new JButton ("Agregar Limites");
        botonIncremento = new JButton ("Agregar incremento");
        escritura       = new JButton ("Generar archivo Excel");
        resetear        = new JButton ("Reestablecer");
        color           = new JButton ("Elegir color");
        
        //Asignacion de que hace cada boton de la interfaz
        polinomio.addActionListener( new Accionar() );
        termino.addActionListener( new BotonTermino() );
        graficos.addActionListener( new Graficos() );
        limite.addActionListener( new Limites() );
        botonIncremento.addActionListener( new Incrementar() );
        escritura.addActionListener( new Escribir() );
        resetear.addActionListener( new Limpiar() );
        color.addActionListener( new Colores() );
        
        //Detallamiento de lo que ira en cada caja de texto
        agregacion = new JTextField ("0");
        coeficiente = new JTextField ("0");
        coeficiente.setEnabled(false);
        exponente = new JTextField ("0");
        exponente.setEnabled(false);        
        superior = new JTextField("0");
        superior.setEnabled(false);
        inferior = new JTextField("0");
        inferior.setEnabled(false);
        incremento = new JTextField("0");
        incremento.setEnabled(false);
        
        //Caracteristicas de las etiquetas
        caja1.setBounds(10,10,320,25);//(eje x, eje en y, largo, ancho)
        caja1.setFont(titulo);
        caja2.setBounds(10,70,320,20);
        caja2.setFont(etiquetas);
        caja3.setBounds(10,150,320,20);
        caja3.setFont(etiquetas);
        caja4.setBounds(10,170,320,20);
        caja4.setFont(etiquetas);
        caja5.setBounds(10,240, 320,20);
        caja5.setFont(etiquetas);
        caja6.setBounds(10,260,320,20);
        caja6.setFont(etiquetas);
        caja7.setBounds(10,340,320,20);
        caja7.setFont(etiquetas);
        aumento.setBounds(360,160,320,20);
        aumento.setFont(etiquetas);
        view.setBounds(10,475,400,30);
        view.setFont(titulo);
        mostrarFuncion.setBounds(10,440,300,30);
        mostrarFuncion.setFont(etiquetas);
                
        //Caracteristicas de los Text Field
        agregacion.setBounds(290,70,50,20);
        coeficiente.setBounds(290,150,50,20);
        exponente.setBounds(290,170,50,20);        
        superior.setBounds(290,240,50,20);
        inferior.setBounds(290,260,50,20);
        incremento.setBounds(290,340,50,20);
        
        //Caracteristicas de los botones
        polinomio.setBounds(110,100,200,30);
        termino.setBounds(110,200,200,30);
        termino.setEnabled(false);        
        limite.setBounds(110,300,200,30);
        limite.setEnabled(false);
        botonIncremento.setBounds(110,370,200,30);
        botonIncremento.setEnabled(false);
        graficos.setBounds(10,540,200,30);
        graficos.setBackground(java.awt.Color.yellow);
        escritura.setBounds(10,580,200,30);
        escritura.setEnabled(false);
        resetear.setBounds(240,440,200,30);
        color.setBounds(240,540,200,30);
        
        //Aniadicion de todos los botones y cajas de texto al Path principal
        add(caja1);
        add(caja2);
        add(caja3);
        add(caja4);
        add(caja5);
        add(caja6);
        add(caja7);
        add(color);
        add(aumento);
        add(agregacion);
        add(coeficiente);
        add(exponente);
        add(view);
        add(superior);
        add(inferior);
        add(limite);
        add(incremento);
        add(polinomio);
        add(termino);
        add(botonIncremento);
        add(graficos);
        add(mostrarFuncion);
        add(escritura);
        add(resetear);
        graficos.setEnabled(false);
        addWindowListener(new CW());
        setSize(500,550);        
        setLayout(null);
        
    }
    
    private class CW extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            setVisible(false);
            dispose();
        }
    }
    
    private class Limpiar implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(pe != null)
            {
                pe.dispose();    
                pe=null;
                escritura.setEnabled(false);
                graficos.setEnabled(false);
                limite.setEnabled(false);
                incremento.setEnabled(false);
                agregacion.setEnabled(true);
                polinomio.setEnabled(true);
                termino.setEnabled(false);
                coeficiente.setEnabled(false);
                exponente.setEnabled(false);
                superior.setEnabled(false);
                inferior.setEnabled(false);                
                botonIncremento.setEnabled(false);
                agregacion.setText("0");
                coeficiente.setText("0");
                exponente.setText("0");
                superior.setText("0");
                inferior.setText("0");
                incremento.setText("0");
                two=0;
                view.setText("0");
                aumento.setText("Terminos: 0");
            }
            else
            {
                pe=null;
                escritura.setEnabled(false);
                graficos.setEnabled(false);
                limite.setEnabled(false);
                incremento.setEnabled(false);
                agregacion.setEnabled(true);
                polinomio.setEnabled(true);
                termino.setEnabled(false);
                coeficiente.setEnabled(false);
                exponente.setEnabled(false);
                superior.setEnabled(false);
                inferior.setEnabled(false);                
                botonIncremento.setEnabled(false);
                agregacion.setText("0");
                coeficiente.setText("0");
                exponente.setText("0");
                superior.setText("0");
                inferior.setText("0");
                incremento.setText("0");
                two=0;
                view.setText("0");
                aumento.setText("Terminos: 0");
            }
        }
    }
    
    private class Colores implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           if(pe != null)
                pe.dispose();
           colores_3 = JColorChooser.showDialog(null, "Selecciona un color", colores_3);                             
        }
    }
    
    //Esta funcion es responsable de actualizar los graficos
    private class Graficos implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(pe != null)
                pe.dispose();
            if( colores_3 != null)
                pe = new PuntosEcuacion(p, b, a, c, colores_3);                        
            else
            {
                colores_3 = Color.red;
                pe = new PuntosEcuacion(p, b, a, c, colores_3);                        
            }
        }
    }
    
    //Esta funcion es responsable de guardar los limites que se dan
    private class Limites implements ActionListener
    {
        public void actionPerformed ( ActionEvent e )
        {           
            int a1=-1, a2=-1;
            arriba = superior.getText();
            arriba = arriba.trim();
            abajo = inferior.getText();
            abajo = abajo.trim();           
            a1 = soloNumerosNegativos(arriba);
            try{
            if ( ( x == 1 && a1 != 1  ) || x == 0)
            {
                if( a1 == arriba.length() )
                    a2 = soloNumerosNegativos(abajo);
                if ( ( x == 1 && a2 != 1  ) || x == 0 ){
                    if( a2 == abajo.length() )
                {
                  a = Double.parseDouble( arriba );                      
                  b = Double.parseDouble( abajo );        
                  //System.out.println(a);
                  //System.out.println(b);
                }
            
                if( a>b )
                {
                    limite.setEnabled(false);
                    inferior.setEnabled(false);
                    superior.setEnabled(false);
                    botonIncremento.setEnabled(true);
                    incremento.setEnabled(true);
                }
                else
                {
                    getToolkit().beep();
                    JOptionPane.showMessageDialog(new JFrame(), "El limite superior debe ser mayor al inferior", "Error"
                                                       ,JOptionPane.ERROR_MESSAGE); 
                }
            }        
            else 
            {
               x=0;
               getToolkit().beep();
               JOptionPane.showMessageDialog(new JFrame(), "Ingrese numeros", "Error"
                                                       ,JOptionPane.ERROR_MESSAGE);
           }
           }
            else 
           {
               x=0;
               getToolkit().beep();
               JOptionPane.showMessageDialog(new JFrame(), "Ingrese numeros", "Error"
                                                       ,JOptionPane.ERROR_MESSAGE);
           }
           }catch(NumberFormatException n)
                {
                    getToolkit().beep();
                    JOptionPane.showMessageDialog(new JFrame(), "Ingrese bien los numeros", "Error"
                                                       ,JOptionPane.ERROR_MESSAGE);
                }
        }        
    }
    
    //Esta funcion tiene la responsabilidad de obtener el coeficiente y exponente de cada termino
    private class BotonTermino implements ActionListener
    {
        public void actionPerformed ( ActionEvent e )
        {
           int z1=-1, z2=-1;
           coefic = coeficiente.getText();
           coefic = coefic.trim();
           exponen = exponente.getText();
           exponen = exponen.trim();
           z1 = soloNumerosNegativos(coefic);
           if ( ( x == 1 && z1 != 1) || x == 0 )
           {
               if ( z1 == coefic.length() )
               { 
                   z2 = soloNumeros(exponen);                
                   if(z2 != exponen.length() )
                   {
                       x=0;   
                       JOptionPane.showMessageDialog(new JFrame(), "Ingrese solo numeros positivos en el exponente", "Error"
                                                       ,JOptionPane.ERROR_MESSAGE);                      
                   }                                                       
                   else
                   try{
                   {        
                       flag = true;                
                       coe = Integer.parseInt(coefic);
                       //System.out.println(coe);
                       //coefic = coefic + e.getActionCommand();                                
                       exp = Integer.parseInt(exponen);
                       //System.out.println(exp);
                       //exponen = exponen + e.getActionCommand();                
                       t = new Termino(coe, exp);                
                       p.agregaTermino(t);
                       two++;
                       aumento.setText("Terminos: " + two );
                    }
                }catch(NumberFormatException n)
                {
                    getToolkit().beep();
                    JOptionPane.showMessageDialog(new JFrame(), "Ingrese bien los numeros", "Error"
                                                       ,JOptionPane.ERROR_MESSAGE);
                }
                }    
           }
           else 
           {
               x=0;
               getToolkit().beep();
               JOptionPane.showMessageDialog(new JFrame(), "Ingrese numeros", "Error"
                                                       ,JOptionPane.ERROR_MESSAGE);
           }            
           if(two >= total)
           {
               termino.setEnabled(false);
               superior.setEnabled(true);
               inferior.setEnabled(true);
               coeficiente.setEnabled(false);
               exponente.setEnabled(false);
               limite.setEnabled(true);               
               view.setText( p.toString() );
           }           
        }
    }
    
    //Esta funcion acciona la agregacion del numero de terminos que tendra el polinomio
    private class Accionar implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            int vd;
            bandera=true;
            uno = agregacion.getText();
            uno = uno.trim();
            //Validacion contra letras
            vd = soloNumeros(uno);
            if ( vd==1 && uno.equals("0") ){
                getToolkit().beep();
               JOptionPane.showMessageDialog(new JFrame(), "Ingrese numeros", "Error"
                                                       ,JOptionPane.ERROR_MESSAGE);
                                                    }
            else
            {
            if( vd==uno.length() )
            {
                temp = Integer.parseInt(uno);            
                //uno = uno + e.getActionCommand();   
                p = new Polinomio(temp);
                exponente.setEnabled(true);
                coeficiente.setEnabled(true);
                polinomio.setEnabled(false);
                agregacion.setEnabled(false);
                total = temp;
                termino.setEnabled(true);
            }
        }
        }
    }
    
    //Este metodo tiene la resposabilidad de validar contra letra en cualquier campo
    private int soloNumeros( String u )
    {
        char c[];
        c=u.toCharArray(); 
        int f=0;
        while( f < u.length() )
        {
           if(Character.isLetter(c[f]) || !( c[f] >='0'   && c[f] <= '9') )
           {  
             getToolkit().beep();
             JOptionPane.showMessageDialog(new JFrame(), "Ingrese solo numeros", "Se ha encontrado un caracter"
                                                       ,JOptionPane.ERROR_MESSAGE);            
             return f;
           }           
           f++;
        }     
        return f;
    }
    private int soloNumerosNegativos( String u )
    {
        x=0;
        char c[];
        c=u.toCharArray(); 
        int f=0;
        while( f < u.length() )
        {
           if( c[f] ==  '-' )
                x++;
           else
           {
               if( Character.isLetter(c[f]) || !( c[f] >='0'   && c[f] <= '9') )
               {  
                   getToolkit().beep();
                   JOptionPane.showMessageDialog(new JFrame(), "Ingrese solo numeros", "Se ha encontrado un caracter"
                                                       ,JOptionPane.ERROR_MESSAGE);            
                   return f;
               }   
           }             
           f++;
        }           
        return f;
    }
    
    private class Incrementar implements ActionListener
    {
        public void actionPerformed ( ActionEvent e )
        {
            int w=0, punto=0;
            char d[];        
            incre = incremento.getText();
            incre = incre.trim();            
            d=incre.toCharArray(); 
            //w = soloNumeros(incre);
            while( w < incre.length() )
            {
                if( d[w] ==  '.' )
                    punto++;
                else
                {
                    if( Character.isLetter(d[w]) || !( d[w] >='0'   && d[w] <= '9') )
                    {  
                        getToolkit().beep();
                        JOptionPane.showMessageDialog(new JFrame(), "Ingrese numeros correctos", "Se ha encontrado un caracter"
                        ,JOptionPane.ERROR_MESSAGE);         
                        break;
                    }   
                }
                w++;
            }   
            if( w == incre.length() && punto <= 1 && w != 1 || ( w == incre.length() && punto==0 ) )
            {
                c = Double.parseDouble(incre);
                graficos.setEnabled(true);
                incremento.setEnabled(false);
                botonIncremento.setEnabled(false);
                mostrarFuncion.setEnabled(true);
                escritura.setEnabled(true);
            }
            if (punto > 1)
            {
                getToolkit().beep();
                        JOptionPane.showMessageDialog(new JFrame(), "Ingrese numeros correctos", 
                        "Se ha encontrado un caracter de mas"
                        ,JOptionPane.ERROR_MESSAGE);   
            }            
        }
    }
    
    private class Escribir implements ActionListener
    {
        public void actionPerformed ( ActionEvent e ) 
        {
             int maximo;
             double m;
             FileWriter fichero = null;
             PrintWriter pw = null;
             m = (a-b)/c;
             maximo = (int) m;
             abc = new PuntosEcuacion(p, b, a, c, colores_3);                         
             try {
                 fichero = new FileWriter("coordenadas.csv");
                 pw = new PrintWriter(fichero);
                 for(int index = 0; index<maximo; index++)
                 {
                     pw.print( abc.getPunto(index).getX() );
                     pw.print( ',' );                     
                     pw.print( abc.getPunto(index).getY() );
                     pw.print( '\n' );
                 }
                }catch(Exception a){
                      a.printStackTrace();
                    }finally{
                        try{                    
             if( null != fichero ){   
                 fichero.close();     
                }                  
             }catch (Exception a2){ 
                a2.printStackTrace();
             }
             }
             abc.dispose();           
             abc = null;
             getToolkit().beep();
             JOptionPane.showMessageDialog( null, "Creacion de archivo Exitoso!!!\nVerifique la carpeta" );
            }
      }
    
    public static void main (String args[])
    {
        PuntosEcuacionUI geogebra9000 = new PuntosEcuacionUI();
 
        geogebra9000.setSize(500,700);
        geogebra9000.setVisible(true);
    }
}
