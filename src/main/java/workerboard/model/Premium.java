package workerboard.model;

import java.util.ArrayList;
import java.util.List;

public class Premium {

    private List<Integer> one = new ArrayList<>();
    private List<Integer> two = new ArrayList<>();
    private List<Integer> four = new ArrayList<>();
    private List<Integer> twelve = new ArrayList<>();

    public Premium() {
    }

    public Premium(List<Integer> one, List<Integer> two, List<Integer> four, List<Integer> twelve) {
        this.one = one;
        this.two = two;
        this.four = four;
        this.twelve = twelve;
    }

    public List<Integer> getOne() {
        return one;
    }

    public List<Integer> getTwo() {
        return two;
    }

    public List<Integer> getFour() {
        return four;
    }

    public List<Integer> getTwelve() {
        return twelve;
    }
}
