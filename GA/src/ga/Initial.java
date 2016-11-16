/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

/**
 *
 * @author IanChien
 */
import static java.lang.Math.pow;
import java.util.*;

public class Initial {

    Random r;

    int Genetic[];
    int amount;
    int position;
    Initial(){Genetic = new int[90];}
    Initial(int chk) {

        r = new Random();
        Genetic = new int[90];

        amount = r.nextInt(90)+1;

        for (int i = amount; i > 0; i--) {

            while (true) {

                position = r.nextInt(90);

                if (Genetic[position] == 0) {
                    Genetic[position] = 1;
                    break;
                }

            }
        }

//        for (int j = 0; j < Genetic.length; j++) {
//            System.out.print(Genetic[j]);
//        }
//        System.out.print("\n");

    }

    int[] getChromosome() {
        return Genetic;
    }

    double[] Chromosome2Num() {

        double[] weight = new double[9];
        double weightsum;
        int weightindex=0;
       // Genetic index 0~89
      for(int index=0;index<Genetic.length;index+=10){
          weightsum=0;
          //value 
          for(int geneticnum=index+1;geneticnum<index+10;geneticnum++){
              
             if(Genetic[geneticnum]==1)
              weightsum+=pow(2,(geneticnum%10)-1);
              
          }   
             if(Genetic[index]==1)
              weightsum=(weightsum*-1)-1;   
             else
              weightsum+=1;
             
             weight[weightindex]=weightsum/256;
            // System.out.println(weight[weightindex]);
             weightindex++;
      }
        
       
        
        return weight;
    }

    void printGenetic(){
        
       
        for(int i=0;i<Genetic.length;i++){
            System.out.print(Genetic[i]);}
        
            System.out.println("");
    }
    
}
