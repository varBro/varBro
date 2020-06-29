package com.varbro.varbro.service.production;

import com.varbro.varbro.model.production.Vat;
import com.varbro.varbro.repository.production.VatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VatService {

    @Autowired
    VatRepository vatRepository;

    public void saveVat(Vat vat) {
        vatRepository.save(vat);
    }

    public void saveVats(List<Vat> vat) {
        vatRepository.saveAll(vat);
    }

    public void deleteAll() {
        vatRepository.deleteAll();
    }
}
