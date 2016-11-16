/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author IanChien
 */
public class GA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Map store chromosome correct probability
        HashMap<Object, Double> hashMap = new HashMap<Object, Double>();
       
        int[] crossover = new int[90];
        int[] mutation = new int[90];
        Initial[] chromosome = new Initial[30];
//Initial
        for (int i = 0; i < chromosome.length; i++) {
            if (i < 10) {
                chromosome[i] = new Initial(1);
            } else {
                chromosome[i] = new Initial();
            }
        }
             
       
        
        
        
        GeneticProcess GP = new GeneticProcess();
        double[] weight;
        double input[][] = {{1, 0, 1}, {0, 1, 1}, {1, 1, 0}, {0, 0, 0}};
        double output;
        int expect=0;
        double correctcnt=0;
        double correctProbability=0;
        int runtimes=0;
      while(runtimes<1000){
//Crossover
        int index = 10;
        for (int i = 0; i < 10; i++) {

            crossover = GP.CrossOver(chromosome);
            chromosome[index].Genetic = crossover.clone();
            index++;
        }
                 
        
//Mutation
        for (int i = 0; i < 10; i++) {
            mutation = GP.mutation(chromosome, i);
            chromosome[index++].Genetic = mutation.clone();
        }
        
         
//MLP
        
        
        for (int i = 0; i < chromosome.length; i++) {
            weight = chromosome[i].Chromosome2Num();

            for(int j=0;j<input.length;j++){
            output = GP.fitness(weight, input[j][0], input[j][1]);
           
            if(output>0.5)expect=1;
            else expect=0;
            
            if(expect==input[j][2])correctcnt++;
            
           
            }
            correctProbability=100*(correctcnt/4);          
            hashMap.put(chromosome[i],correctProbability);
            
            correctcnt=0;
 
        }
        
        
        sortByValues(hashMap,chromosome);
       runtimes++;
        
      }
      //print
      for (int i = 0; i < 10; i++) {
           double[] finalweight = chromosome[i].Chromosome2Num();

      System.out.println(finalweight[0]+","+finalweight[0]+","+finalweight[1]+","+finalweight[2]+","+finalweight[3]+","+finalweight[4]
              +","+finalweight[5]+","+finalweight[6]+","+finalweight[7]+","+finalweight[8]+", \t    correctProbability :   "+hashMap.get(chromosome[i])+"%");  
      }      
      
      
      
    }
 public static<K, V extends Comparable<V>> void sortByValues(Map<K, V> map,Initial []chromosome) {
     //sorted
     Initial clone[]=new Initial[10];
     
     
    Comparator<K> valueComparator =  new Comparator<K>() {
        public int compare(K k1, K k2) {
            int compare = map.get(k2).compareTo(map.get(k1));
            if (compare == 0) return 1;
            else return compare;
        }
    };
            
             Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
             sortedByValues.putAll(map);
             Map<K,V> sorted =sortedByValues;
             int index=0;
      //choose top 10        
              for (Object key : sorted.keySet()) {
                   
                   //System.out.println("Index:\t" + key + "\t\tCorrect Probability: " + map.get(key));
                    
                    clone[index++]=(Initial)key;
                    
                   
                    if(index==10)break;
                                
                      }
            
      //
              
              for(int i=0;i<chromosome.length;i++){
                  chromosome[i]=new Initial();
                  if(i<10){
                    
                     chromosome[i]=clone[i];
                     
                 }
                  
                  //chromosome[i].printGenetic();
                }
     
     
             
    }
}
