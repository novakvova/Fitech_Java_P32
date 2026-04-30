package org.example.data;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import org.example.entities.Genre;
import org.example.repositories.IGenreRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class AppSeedData {
    //final - теж саме, що readonly у С#
    private final IGenreRepository genreRepository;
    private final Faker faker = new Faker(new Locale("uk"));
    @PostConstruct
    public void seed() {
        System.out.println("---------Run seed data-----------");
        if(genreRepository.count() == 0)
        {
            List<String> genres = new ArrayList<>();
            int n = 10, i=0;
            while(i<n) {
                String genreName = faker.music().genre();
                if(!genres.contains(genreName)) {
                    genres.add(genreName);
                    i++;
                }
            }
            for (String genreName : genres) {
                Genre genre = new Genre();
                genre.setName(genreName);
                genreRepository.save(genre);
            }
        }
    }
}
