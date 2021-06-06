package App.Multicenter.Space;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomNameGenerator {
    private final RandomInRanges RiR;

    public RandomNameGenerator() {
        RiR = new RandomInRanges(48, 57);
        RiR.addRange(97, 122);

    }

    public String generate(File folder, String extension) {

        StringBuilder id = new StringBuilder();

        if (folder.exists() && folder.isDirectory()) {

            List<String> filesinfolder = Arrays.asList(folder.list());

            do {
                id = new StringBuilder();

                for (int i = 0; i < 8; i++) {
                    id.append((char) RiR.getRandom());
                }
                id.append(extension);

            } while (filesinfolder.contains(id.toString()));

        }

        return id.toString();

    }

    public String generate(File folder) {
        return generate(folder, "");
    }

    private class RandomInRanges {
        private final List<Integer> range = new ArrayList<>();

        public RandomInRanges(int min, int max) {
            this.addRange(min, max);
        }

        public final void addRange(int min, int max) {
            for (int i = min; i <= max; i++) {
                range.add(i);
            }
        }

        public int getRandom() {
            return this.range.get(new Random().nextInt(this.range.size()));
        }

    }
}
