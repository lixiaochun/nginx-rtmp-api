package live.livelab.infrastructure.exception;

/**
 * Created by kevin on 16/1/25.
 */
public class RepositoryException extends Exception {

    /**
     * {@link RepositoryException}'s default serial identifier.
     */
    private static final long serialVersionUID = -7920779797756759564L;

    /**
     * Constructs a new <code>RepositoryException</code>.It only calls the corresponding parent constructor.
     */
    public RepositoryException() {
        super();
    }

    /**
     * Constructs a new <code>RepositoryException</code> with the specified detail message. It only calls the
     * corresponding parent constructor.
     *
     * @param msg
     *            message
     */
    public RepositoryException(String msg) {
        super(msg);
    }

    /**
     * Constructs a new <code>RepositoryException</code> with the specified detail message and encapsulated exception. It
     * only calls the corresponding parent constructor.
     *
     * @param msg
     *            message
     * @param e
     *            throwable
     */
    public RepositoryException(String msg, Throwable e) {
        super(msg, e);
    }

    /**
     * Constructs a new <code>RepositoryException</code> with the encapsulated exception. It only calls the corresponding
     * parent constructor.
     *
     * @param e
     *            throwable
     */
    public RepositoryException(Throwable e) {
        super(e);
    }
}
