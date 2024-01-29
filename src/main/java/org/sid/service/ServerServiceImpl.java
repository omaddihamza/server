package org.sid.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.sid.entities.Server;
import org.sid.enumeration.Stratus;
import org.sid.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.Random;

import static java.lang.Boolean.TRUE;

@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService{
    @Autowired
    private ServerRepository serverRepository;

    @Override
    public Server create(Server server) {
        log.info("saving new Server:",server.getName());
        server.setImageUrl(setServerImage());
        return serverRepository.save(server);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP!",ipAddress);
        Server server = serverRepository.findIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStratus(address.isReachable(10000)? Stratus.SERVER_UP :Stratus.SERVER_DOWN);
        serverRepository.save(server);
        return server;
    }

    @Override
    public List<Server> list() {
        log.info("Fetching all servers");
        return serverRepository.findAll();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching all servers",id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("update new Server:",server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("delete new Server:",id);
        serverRepository.deleteById(id);
        return TRUE;
    }

    private String setServerImage() {
        String[] imageNames = {"server2.png","server2.png","server3.png","server4.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/"+ imageNames[new Random().nextInt(4)]).toUriString();
    }
}