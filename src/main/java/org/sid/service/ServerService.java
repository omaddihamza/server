package org.sid.service;

import org.sid.entities.Server;
import java.io.IOException;
import java.util.List;

public interface ServerService {
    Server create(Server server);
    Server ping(String ipAddress) throws IOException;
    List<Server> list();
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);
}