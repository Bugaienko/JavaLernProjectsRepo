package ua.bugaienko.pizzaSiteApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.bugaienko.pizzaSiteApp.models.Cafe;
import ua.bugaienko.pizzaSiteApp.repositiries.CafeRepository;

/**
 * @author Sergii Bugaienko
 */

@Service
@Transactional(readOnly = true)
public class CafeService {
    private final CafeRepository cafeRepository;

    @Autowired
    public CafeService(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    public Cafe findById(int id){
        return cafeRepository.findById(id).get();
    }


}