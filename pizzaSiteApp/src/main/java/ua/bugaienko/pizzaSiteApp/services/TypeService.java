package ua.bugaienko.pizzaSiteApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.bugaienko.pizzaSiteApp.models.TypeIngredient;
import ua.bugaienko.pizzaSiteApp.repositiries.TypesRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Sergii Bugaienko
 */

@Service
@Transactional(readOnly = true)
public class TypeService {
    private final TypesRepository typesRepository;

    @Autowired
    public TypeService(TypesRepository typesRepository) {
        this.typesRepository = typesRepository;
    }

    public List<TypeIngredient> findAllSorted(){
        return typesRepository.findAll(Sort.by("id").ascending());
    }

    @Transactional
    public void create(TypeIngredient type) {
        TypeIngredient newType = new TypeIngredient(type.getName());
//        System.out.println(newType);
        typesRepository.save(newType);
    }

    public Optional<TypeIngredient> findByName(String name) {
        return typesRepository.findByName(name);
    }
}
