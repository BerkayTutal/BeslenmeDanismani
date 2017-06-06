package tr.com.berkaytutal.beslenmedanismani.Utils;

import java.util.ArrayList;

/**
 * Created by berka on 6.06.2017.
 */

public class CircleMakerHelper {


    public ArrayList<CircleTekrarAbsPOJO> makeCircleWithThis(ArrayList<ExercisePOJO> exercisePOJOs) {

        ArrayList<ExercisePOJO> tempExercisez = new ArrayList<>(exercisePOJOs);

        return handleTekrars(tempExercisez);


    }


    private ArrayList<CircleTekrarAbsPOJO> handleTekrars(ArrayList<ExercisePOJO> exercisePOJOs) {

        ArrayList<CircleTekrarAbsPOJO> arrayListTOTAL = new ArrayList<>();


        for (int i = 0; i < exercisePOJOs.size(); i++) {
            if (i + 1 < exercisePOJOs.size()) {
                if (exercisePOJOs.get(i).getExercises_ID() == exercisePOJOs.get(i + 1).getExercises_ID()) {
                    ArrayList<ExercisePOJO> arrayList2 = new ArrayList<>();
                    int j = i;
                    while (exercisePOJOs.get(i).getExercises_ID() == exercisePOJOs.get(j).getExercises_ID()) {
                        arrayList2.add(exercisePOJOs.get(j));
                        j++;
                    }

                    arrayListTOTAL.add(new TekrarPOJO(arrayList2));

                    i = j-1;
                } else {
                    arrayListTOTAL.add(exercisePOJOs.get(i));

                }
            } else {
                arrayListTOTAL.add(exercisePOJOs.get(i));
            }

        }

        return arrayListTOTAL;

    }
}
