package workerboard.model.dto;

import java.util.ArrayList;
import java.util.List;

public class PremiumDto {

    private List<Integer> one = new ArrayList<>();
    private List<Integer> two = new ArrayList<>();
    private List<Integer> four = new ArrayList<>();
    private List<Integer> twelve = new ArrayList<>();

    public PremiumDto() {
    }

    public PremiumDto(List<Integer> one, List<Integer> two, List<Integer> four, List<Integer> twelve) {
        this.one = one;
        this.two = two;
        this.four = four;
        this.twelve = twelve;
    }

    public List<Integer> getOne() {
        return one;
    }

    public void setOne(List<Integer> one) {
        this.one = one;
    }

    public List<Integer> getTwo() {
        return two;
    }

    public void setTwo(List<Integer> two) {
        this.two = two;
    }

    public List<Integer> getFour() {
        return four;
    }

    public void setFour(List<Integer> four) {
        this.four = four;
    }

    public List<Integer> getTwelve() {
        return twelve;
    }

    public void setTwelve(List<Integer> twelve) {
        this.twelve = twelve;
    }
}
