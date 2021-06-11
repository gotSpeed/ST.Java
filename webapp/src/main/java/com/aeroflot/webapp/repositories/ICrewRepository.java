package com.aeroflot.webapp.repositories;


import com.aeroflot.webapp.models.personrelated.Crew;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;



public interface ICrewRepository extends CrudRepository<Crew, Long> {

    default Iterable<Crew> findAllByOrderByName() {

        Spliterator<Crew> spliterator = findAll().spliterator();
        Stream<Crew>      stream      = StreamSupport.stream(spliterator, false);

        return stream.sorted(
          Comparator.comparing(mate -> mate.getPerson()
                                           .getName())
        ).collect(Collectors.toList());
    }

}
