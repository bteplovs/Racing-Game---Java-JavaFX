package seng201.team0.models.Game;

import seng201.team0.models.Cars.Car;

/**
 * Represents a racer in the race which are both player and AI opponents.
 * It provides shared methods for tracking race state.
 */
public interface Racer {
        String getName();
        double getDistanceCovered();

        /**
         * Updates the distance that teh racer has traveled so far.
         * @param distance the new distance to set.
         */
        void setDistanceCovered(double distance);
        Car getCar();
        double getFuel();
        int getFinishTime();

        /**
         * Sets the time in ticks at which the racer finished the race.
         * @param finishTime the finish tick to set.
         */
        void setFinishTime(int finishTime);
        int getTimePenalty();

        /**
         * Sets the total penalty the racer has added onto their time.
         * @param timePenalty the penalty time to set.
         */
        void setTimePenalty(int timePenalty);

}
