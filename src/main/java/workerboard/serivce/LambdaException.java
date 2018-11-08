package workerboard.serivce;

public interface LambdaException<T> {

    void accept(T t) throws Exception;
}
