package md.faces.backend.dao;

import lombok.Getter;
import md.faces.backend.model.Gender;
import md.faces.backend.model.Profile;
import md.faces.backend.service.ProfileService;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class ProfileDao {


    private static final ProfileDao INSTANCE = new ProfileDao();

    private final ConcurrentHashMap<Long, Profile> profStorage;
    private final AtomicLong idStorage;

    public ProfileDao() {
        this.profStorage = new ConcurrentHashMap<>();
        Profile profile = new Profile();
        profile.setId(1L);
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setEmail("john.doe@example.com");
        profile.setPassword("password");
        profile.setAboutMe("John like Java");
        profile.setBirthDate(LocalDate.parse("2001-01-01"));
        profile.setGender(Gender.MALE);
        profStorage.put(profile.getId(), profile);


        Profile profile1 = new Profile();
        profile1.setId(2L);
        profile1.setFirstName("Maria");
        profile1.setLastName("Desson");
        profile1.setEmail("maria.desson@example.com");
        profile.setPassword("password");
        profile1.setAboutMe("Maria like Java too");
        profile1.setBirthDate(LocalDate.parse("2003-03-03"));
        profile1.setGender(Gender.FEMALE);
        profStorage.put(profile1.getId(), profile1);
        this.idStorage = new AtomicLong(2L);
    }

    public static ProfileDao getInstance() {

        return INSTANCE;
    }

    public Profile addProfile(Profile profile) {
        Long id = idStorage.incrementAndGet();
        profile.setId(id);
        profStorage.put(id, profile);
        return profile;
    }

    public Optional<Profile> getProfile(Long id) {

        return Optional.ofNullable(profStorage.get(id));
    }

    public List<Profile> getAllProfiles() {

        return new ArrayList<>(profStorage.values());
    }

    public void updateProfile(Profile profile) {
        Long id = profile.getId();
        if(id == null ) return;
        profStorage.put(id, profile);
    }

    public boolean deleteProfile(Long id) {
        if(id == null) return false;
        return profStorage.remove(id) != null;

    }

    public Set<String> getAllEmails() {
        return profStorage.values().stream().map(Profile::getEmail).collect(Collectors.toSet());
    }
}
