package backend;

import java.util.List;

/**
 * Created by sebastiankotarski on 11.06.2017.
 */
public interface CompletionHandler {
    public void completed(List<? extends DietEntity> entities);
    public void stopped();
}

