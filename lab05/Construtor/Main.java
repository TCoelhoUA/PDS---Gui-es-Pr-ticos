package Construtor;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String title = "Vitória";
        int year = 2025;

        Person director =  new Person("Andrucha Waddington", 12345678);
        Person writer = new Person("Paula Fiuza", 87654321);
        
        List<Person> cast = new ArrayList<>();
        cast.add(new Person("Fernanda Montenegro", 23456789));
        cast.add(new Person("Linn da Quebrada", 98765432));
        cast.add(new Person("Sacha Bali", 34567891));
        cast.add(new Person("Alan Rocha", 19876543));
        cast.add(new Person("Silvio GUindane", 45678901));
        cast.add(new Person("Laila Garin", 10987654));
        cast.add(new Person("Thelmo Fernandes", 56789012));
        cast.add(new Person("Jennifer Dias", 21098765));
        cast.add(new Person("Thawan Lucas", 67890123));
        
        List<Place> locations = new ArrayList<>();
        locations.add(new Place("Rio de Janeiro (Brasil)"));

        List<String> languages = new ArrayList<>();
        languages.add("PT-BR");

        List<String> genres = new ArrayList<>();
        genres.add("Drama");

        Movie movie = new Movie.Builder(title, year)
                                .director(director)
                                .writer(writer)
                                .cast(cast)
                                .locations(locations)
                                .languages(languages)
                                .genres(genres)
                                .isTelevision(false)
                                .isNetflix(false)
                                .isIndependent(false)
                                .build();

        System.out.println(movie);
    }
}
