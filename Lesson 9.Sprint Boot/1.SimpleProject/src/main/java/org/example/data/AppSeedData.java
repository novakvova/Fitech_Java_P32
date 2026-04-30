package org.example.data;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppSeedData {


    //Цей метод буде Seed даних у БД
    //Цей метод в java Spring буде зпускати автоматично
    @PostConstruct
    public void seed() {
        System.out.println("---------Run seed data-----------");
    }
}
