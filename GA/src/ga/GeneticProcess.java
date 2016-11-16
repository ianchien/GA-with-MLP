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
import static java.lang.Math.exp;
import java.util.Random;

public class GeneticProcess {

    GeneticProcess() {
    }

    int[] CrossOver(Initial[] chromosome) {

        Random r = new Random();
        //empty crossover genetic
        int[] crossover = new int[90];
        int[] isrun = new int[90];
        //generate 10 new chromosome

        int pickchromosome1 = r.nextInt(10);
        int pickchromosome2 = r.nextInt(10);
        //check random number 
        while (true) {
            if (pickchromosome2 != pickchromosome1) {
                break;
            }
            pickchromosome2 = r.nextInt(10);
        }

        int choosegenetic_amount = 45;
        int tmp;
        int geneticIndex;
        
            crossover = chromosome[pickchromosome1].Genetic.clone();

        for (int i = 0; i < choosegenetic_amount; i++) {
            geneticIndex = r.nextInt(90);
            while (true) {
                if (isrun[geneticIndex] == 0) {
                    isrun[geneticIndex] = 1;
                    break;
                }
                geneticIndex = r.nextInt(90);
            }

            tmp = chromosome[pickchromosome2].Genetic[geneticIndex];

            crossover[geneticIndex] = tmp;

        }
        return crossover;

    }

    int[] mutation(Initial[] chromosome, int index) {
        Random r = new Random();
        int[] mutation = new int[90];
        //突變數量至少10最多30
        int amount = r.nextInt(21) + 10;
        int chooseindex;
        int[] isrun = new int[90];
        mutation = chromosome[index].Genetic.clone();

        for (int i = 0; i <= amount; i++) {

            chooseindex = r.nextInt(90);
            while (true) {
                if (isrun[chooseindex] == 0) {
                    isrun[chooseindex] = 1;
                    break;
                }
                chooseindex = r.nextInt(90);
            }
            if (mutation[chooseindex] == 0) {
                mutation[chooseindex] = 1;
            } else {
                mutation[chooseindex] = 0;
            }
        }

        return mutation;
    }

    double fitness(double[] Chromosome2Num, double input1, double input2) {
        double tier1node1output;
        double tier1node2output;
        double tier2node1output;
        //MLP
        double weight1[] = {Chromosome2Num[0], Chromosome2Num[1], Chromosome2Num[2]};
        double weight2[] = {Chromosome2Num[3], Chromosome2Num[4], Chromosome2Num[5]};
        double weight3[] = {Chromosome2Num[6], Chromosome2Num[7], Chromosome2Num[8]};
        tier1node1output = Tier(-1, input1, input2, weight1);
        tier1node2output = Tier(-1, input1, input2, weight2);
        tier2node1output = Tier(-1, tier1node1output, tier1node2output, weight3);

        return tier2node1output;
    }

    private double Tier(double bias, double input1, double input2, double weight[]) {
        double output;
        output = 1 / (1 + (exp(-1 * ((weight[0] * bias) + (weight[1] * input1) + (weight[2] * input2)))));
        return output;
    }
}
