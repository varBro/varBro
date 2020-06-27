package com.varbro.varbro.service.production;

import com.varbro.varbro.model.production.Beer;
import com.varbro.varbro.model.production.Request;
import com.varbro.varbro.repository.production.BeerRepository;
import com.varbro.varbro.repository.production.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    public void save(Request request) {
        requestRepository.save(request);
    }

    public void saveAll(List<Request> requests) {
        requestRepository.saveAll(requests);
    }

    public void deleteAll() {
        requestRepository.deleteAll();
    }
}
