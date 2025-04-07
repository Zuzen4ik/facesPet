package md.faces.backend.service;

import md.faces.backend.dao.ProfileDao;
import md.faces.backend.model.Profile;

import java.util.List;
import java.util.Optional;

public class ProfileService {
    private final ProfileDao profDao;

    public ProfileService(ProfileDao profDao) {
        this.profDao = profDao;
    }

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
