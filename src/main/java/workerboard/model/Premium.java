package workerboard.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Premium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private List<Integer> one = new ArrayList<>();
    @ElementCollection
    private List<Integer> two = new ArrayList<>();
    @ElementCollection
    private List<Integer> four = new ArrayList<>();
    @ElementCollection
    private List<Integer> twelve = new ArrayList<>();

    public Premium() {
    }

    public Premium(List<Integer> one, List<Integer> two, List<Integer> four, List<Integer> twelve) {
        this.one = one;
        this.two = two;
        this.four = four;
        this.twelve = twelve;
    }

    public Long getId() {
        return id;
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

    public void setOne(List<Integer> one) {
        this.one = one;
    }

    public void setTwo(List<Integer> two) {
        this.two = two;
    }

    public void setFour(List<Integer> four) {
        this.four = four;
    }

    public void setTwelve(List<Integer> twelve) {
        this.twelve = twelve;
    }
}
