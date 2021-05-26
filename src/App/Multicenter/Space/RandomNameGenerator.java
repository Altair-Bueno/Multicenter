package App.Multicenter.Space;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomNameGenerator {
    private RandomInRanges RiR;

    private class RandomInRanges{
        private List<Integer> range = new ArrayList<>();

        public RandomInRanges(int min, int max){
            this.addRange(min, max);
        }

        public final void addRange(int min, int max){
            for(int i = min; i <= max; i++){
                range.add(i);
            }
        }

        public int getRandom(){
            return this.range.get(new Random().nextInt(this.range.size()));
        }

    }

    public RandomNameGenerator(){
        RiR = new RandomInRanges(48, 57);
        RiR.addRange(97, 122);

    }

    public String generate(File folder){

        String id = "";

        if(folder.exists() && folder.isDirectory()){

            List<String> filesinfolder = Arrays.asList(folder.list());

            for(int i = 0; i < 8; i++){
                id += (char) RiR.getRandom();
            }

            while(filesinfolder.contains(id)){
                id = "";

                for(int i = 0; i < 8; i++){
                    id += (char) RiR.getRandom();
                }

            }

        }

        return id;

    }
}
