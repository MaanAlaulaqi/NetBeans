/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lines;

/**
 *
 * @author lab
 */
public class Lines {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*DDALine(2,2,12,6);
        System.out.println("-------------");
        DDALineOpt(2,2,12,6);*/
        lineBres(20,10,30,18);
    }
    // For positive slope 
    
    public static void lineBres(int x0,int y0,int x1,int y1)
    {
       
       int dx=x1-x0;
       int dy=y1-y0;
       double m=dy/(double)dx;
       
       int w=2*dy-2*dx;
       int twody=2*dy;
       
       int p=twody=dx;
       int x=x0;
       int y=y0;
       int step=0;
       System.out.println("step   p  (x, y)");
       System.out.println("--------------------");
       if(m<1)
       {
           for(int i=x0+1;i<=x1;i++)
           {
               step++;
               if(p<0)
               {
                   p+=twody;
               }
               else
               {
                   y++;
                   p+=w;
               }
               System.out.println(step+"\t "+p+"\t "+"("+i+","+y+")");
               
           }
       }
       
    }
    public static void DDALine(int x0,int y0,int x1,int y1)
    {
        //(x0,y0) --> (x1,y1)
        double m=((double)(y1-y0))/(x1-x0);
        if(m<0)
        {
            
        }
        else
        {
            if(m<=1)
            {
                System.out.println("("+x0+","+y0+")");
                double y=y0;
                for(int i=x0+1;i<=x1;i++)
                {
                    y=y+m;
                    System.out.println("("+i+","+Math.round(y)+")");
                }
            }
            else
            {
                System.out.println("("+x0+","+y0+")");
                double x=x0;
                for(int i=y0+1;i<=y1;i++)
                {
                   x=x+(1/m);
                   System.out.println("("+Math.round(x)+","+i+")");
                }
            }
        }
    }
    
    public static void DDALineOpt(int x0,int y0,int x1,int y1)
    {
        int dx=x1-x0;
        int dy=y1=y0;
        double x,y;
        int steps;
        
        double incX,incY;
        
        if(Math.abs(dx)>Math.abs(dy))// how many points are there?
            steps=Math.abs(dx);
        else
            steps=Math.abs(dy);
        
        incX=((double)dx)/steps;
        incY=((double)dy)/steps;
        System.out.println("("+x0+","+y0+")");
        
        x=x0;
        y=y0;
        for(int i=0; i<steps; i++)
        {
            x+=incX;
            y+=incY;
            
            System.out.println("("+Math.round(x)+","+Math.round(y)+")");
        }
        
    }
    
}
