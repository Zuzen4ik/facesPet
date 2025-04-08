package md.faces.backend.service;

import md.faces.backend.dao.ProfileDao;
import md.faces.backend.model.Profile;

import java.util.List;
import java.util.Optional;

public class ProfileService {

    private static final ProfileService INSTANCE = new ProfileService();

    private ProfileService() {}

    public static ProfileService getInstance() {
        return INSTANCE;
    }

    private final ProfileDao profDao = ProfileDao.getInstance();


    public Profile save(Profile profile) {
        return profDao.addProfile(profile);
    }

    public Optional<Profile> findById(Long id) {
        if (id == null) return Optional.empty();
        return profDao.getProfile(id);
    }

    public List<Profile> findAll() {

        return profDao.getAllProfiles();
    }

    public void update(Profile profile) {
        profDao.updateProfile(profile);
    }

    public boolean delete(Long id) {
        if(id == null) return false;
        return profDao.deleteProfile(id);
    }
}
