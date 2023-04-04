package ua.bugaienko.pizzaSiteApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.bugaienko.pizzaSiteApp.models.Cafe;
import ua.bugaienko.pizzaSiteApp.repositiries.CafeRepository;

import java.util.List;

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

    public List<Cafe> findAll(){
        return cafeRepository.findAll();
    }


    @Transactional
    public Cafe create(Cafe cafe) {
        return cafeRepository.save(cafe);
    }

    public List<Cafe> findAllSorted() {
        return cafeRepository.findAll(Sort.by("city").and(Sort.by("title")));
    }
}
