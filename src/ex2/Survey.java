package ex2;

import java.util.HashMap;
import java.util.Vector;

public class Survey {

    private String mQuestion;
    private HashMap<String, Integer> mOptions = new HashMap<>();
    private Vector<String> vec = new Vector<>();

    Survey() {
        this.mQuestion = null;
        this.vec =null;
    }

    /**
     * Constructor for the survey (set the question and the answers)
     * @param question question
     * @param options options
     */
    Survey(String question, Vector<String> options) {
        mQuestion = question;
        vec = options;
        for (int i = 0; i < options.size(); i++) {
            mOptions.put(options.elementAt(i), 0);
        }
    }

    /**
     * Function to update the data in the hash map
     * @param opt index for data
     */
    public void UpdateResult(String opt) {
        mOptions.put(vec.elementAt(Integer.parseInt(opt)-1), mOptions.get(vec.elementAt(Integer.parseInt(opt)-1)) + 1);
    }

    /**
     *
     * @return Question string
     */
    public String getmQuestion() {
        return mQuestion;
    }

    /**
     *
     * @return full data for the survey
     */
    public HashMap<String, Integer> getmOptions() {
        return mOptions;
    }

    /**
     *
     * @return vector of the answers/options
     */
    public Vector<String> getOptiosStr() {
        return vec;
    }

    /**
     * check if the file survey read the data correctly
     * @return Return if the file is valid
     */
    public boolean validFile(){
        return !(mQuestion == null || vec == null || vec.size()<2);
    }
}
