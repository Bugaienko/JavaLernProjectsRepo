package lesson04;

/**
 * @author Sergii Bugaienko
 */

public class Perebor {
    public static void main(String[] args) {
        int[] weights = {3, 4, 5, 8, 9};
        int[] prices = {1, 6, 4, 7, 6};

        int maxWeight = 13;
        long count = 1 << weights.length;

        long maxPrice = 0;
        long maxState = 0;

        for (long state = 0; state < count; state++) {
            long price = statePrice(state, prices);
            long weight = stateWeight(state, weights);

            if (weight <= maxWeight) {
                maxPrice = price;
                maxState = state;
            }
        }

        System.out.println("Оптичальное содержимое");
        long powerOfTwo = 0;
        for (int i = 0; i < weights.length; i++) {
            if ((powerOfTwo & maxWeight) != 0) {
                System.out.println();
            }

        }

    }

    private static long statePrice(long state, int[] prises) {
        long powerOfTwo = 1;
        int preice = 0;

        for (int i = 0; i < prises.length; i++) {
            if ((powerOfTwo & state) != 0) {
                preice += prises[i];
            }
            powerOfTwo <<= 1;
        }
        return preice;
    }


    private static long stateWeight(long state, int[] weights) {
        long powerOfTwo = 1;
        int weight = 0;

        for (int i = 0; i < weights.length; i++) {
            if ((powerOfTwo & state) != 0) {
                weight += weights[i];
            }
            powerOfTwo <<= 1;
        }
        return weight;
    }
}
