package first.controller;

import first.controller.vo.PersonVO;
import first.domain.Person;
import first.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.controller.SimpleRestApi;

import java.util.List;

/**
 * @author Sergii Bugaienko
 */

@RestController
@RequestMapping("/api")
public class RestPersonController implements SimpleRestApi {

    private final PersonService personService;

    @Autowired
    public RestPersonController(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public ResponseEntity<PersonVO> add(PersonVO body) {
        PersonVO result = personService.add(body);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<PersonVO> get(Integer id) {
        Person person = personService.getById(id);
        PersonVO result = PersonVO.valueOf(person);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<List<PersonVO>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<PersonVO> update(Integer id, PersonVO body) {
        return null;
    }
}
