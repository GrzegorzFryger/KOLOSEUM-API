package workerboard.serivce;

@FunctionalInterface
public interface EXCEPTION<T> {


        void accept(T t) throws Exception;
    }
