package Stack;

import java.util.*;

public class CarFleet {

    private static class Car {
        private int pos;
        private int speed;

        public Car(int pos, int speed) {
            this.pos = pos;
            this.speed = speed;
        }
    }

    public int carFleet(int target, int[] position, int[] speed) {
        int N = speed.length;

        // Intuition: Compare two cars from the right, see if
        // the behind car can reach the destination before or at
        // the same as the ahead car, if it can, they form a fleet.
        // i.e. simply compute the time taken by each to reach the target.

        // 1. Firt off, we need to sort the cars by pos in desc order to start processing the cars who's nearest to the target.
        // 1.1 Prep a list of car as pairs -- pos, speed to sort them
        // collectively idx wise.
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            cars.add(new Car(position[i], speed[i]));
        }
        cars.sort((a, b) -> b.pos - a.pos);

        // 2. Process the cars and store their time of
        // arrival at the target in the stack.
        // toa = (target - pos) / speed
        Stack<Double> st = new Stack<>();
        for (int i = 0; i < N; i++) {
            // Compute toa for each car
            Car car = cars.get(i);
            double toa = (double) (target - car.pos) / car.speed;

            // It'll belong to stack if it's not the
            // part of the fleet in stack.
            // Meaning if toa > toa of fleet in stack, implies
            // current car doesnt intersect w any other car.
            if (st.isEmpty()) st.push(toa);
            else {
                double toaFleet = st.peek();
                if (toa > toaFleet) st.push(toa);
            }
        }

        return st.size();

        // Time complexity: O(N log N)
        // Space complexity O(N)
    }
}
