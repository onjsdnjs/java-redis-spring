package ps.zhao.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ps.zhao.demo.beans.Person;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class PersonService {

    /**
     * 对象的List存储在redis中
     */
    @Cacheable(value="dev")
    public List<Person> getPerson() {

        log.debug("没有cache，走到方法体内部");

        List<Person> personList = IntStream.range(1, 4).mapToObj(Integer::new).map(i -> {
            return new Person("name_1" + i.intValue(), i.intValue());
        }).collect(Collectors.toList());

        return personList;
    }
}
