package pizzaRest.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pizzaRest.models.Base;
import pizzaRest.repositiries.BaseRepository;
import pizzaRest.util.NotFoundException;


import java.util.List;
import java.util.Optional;

/**
 * @author Sergii Bugaienko
 */

@Service
@Transactional(readOnly = true)
public class BaseService {

    private final BaseRepository baseRepository;

    private final Logger logger = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    public BaseService(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    public Optional<Base> findByName(String name) {
        return baseRepository.findByName(name);
    }

    @Transactional
    public Base create(Base base) {
//        Base newBase = new Base(base.getSize(), base.getName(), base.getPrice());
        //DONE save tp BD
        logger.info("Add new base {}", base.getName());
        return baseRepository.save(base);
    }

    public List<Base> findAllSorted() {
        return baseRepository.findAll(Sort.by("size").and(Sort.by("name")));
    }

    public Base findById(int baseId) {
        return baseRepository.findById(baseId).orElseThrow(NotFoundException::new);
    }


    @Transactional
    public Base update(Base base) {
        logger.info("Update base {}", base.getId() + "/" + base.getName());
        return baseRepository.save(base);
    }
    @Transactional
    public Base update(Base baseUpdate, Base baseBody) {
        logger.info("Update base {}", baseUpdate.getId() + "/" + baseBody.getName());
        baseUpdate.setName(baseBody.getName());
        baseUpdate.setSize(baseBody.getSize());
        baseUpdate.setPrice(baseBody.getPrice());
        return baseRepository.save(baseUpdate);
    }
}
