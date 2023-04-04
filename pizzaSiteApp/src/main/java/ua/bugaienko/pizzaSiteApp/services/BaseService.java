package ua.bugaienko.pizzaSiteApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.bugaienko.pizzaSiteApp.models.Base;
import ua.bugaienko.pizzaSiteApp.repositiries.BaseRepository;

import java.util.Optional;

/**
 * @author Sergii Bugaienko
 */

@Service
@Transactional(readOnly = true)
public class BaseService {

    private final BaseRepository baseRepository;

    @Autowired
    public BaseService(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    public Optional<Base> findByName(String name) {
        return baseRepository.findByName(name);
    }

    @Transactional
    public void create(Base base) {
        Base newBase = new Base(base.getSize(), base.getName(), base.getPrice());
        //DONE save tp BD
        baseRepository.save(newBase);
    }
}
