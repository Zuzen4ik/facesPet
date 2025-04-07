package md.faces.backend.dao;

import md.faces.backend.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProfileDao {

    private final ConcurrentHashMap<Long, Profile> profStorage;

    private final AtomicLong idStorage;

    public ProfileDao() {
        this.profStorage = new ConcurrentHashMap<>();
        Profile profile = new Profile();
        profile.setId(1L);
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setEmail("john.doe@example.com");
        profile.setAboutMe("John like Java");
        profStorage.put(profile.getId(), profile);
        this.idStorage = new AtomicLong(1L);
    }

    public Profile addProfile(Profile profile) {
        long id = idStorage.incrementAndGet();
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
        profStorage.put(profile.getId(), profile);
    }

    public boolean deleteProfile(long id) {
        return profStorage.remove(id) != null;

    }
}
