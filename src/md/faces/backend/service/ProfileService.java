package md.faces.backend.service;

import md.faces.backend.dao.ProfileDao;
import md.faces.backend.dto.ProfileGetDto;
import md.faces.backend.dto.ProfileUpdateDto;
import md.faces.backend.dto.RegistrationDto;
import md.faces.backend.mapper.ProfileToDtoMapper;
import md.faces.backend.mapper.ProfileUpdateDtoToProfileMapper;
import md.faces.backend.mapper.RegistrationDtoToProfileMapper;
import md.faces.backend.model.exceptions.DuplicateEmailException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class ProfileService {

    private static final ProfileService INSTANCE = new ProfileService();

    private ProfileService() {}

    public static ProfileService getInstance() {
        return INSTANCE;
    }

    private final ProfileToDtoMapper profileGetDtoMapper = ProfileToDtoMapper.getInstance();
    private final RegistrationDtoToProfileMapper registrationDtoToProfileMapper = RegistrationDtoToProfileMapper.getInstance();
    private final ProfileUpdateDtoToProfileMapper profileUpdateDtoToProfileMapper = ProfileUpdateDtoToProfileMapper.getInstance();
    private final ProfileDao profDao = ProfileDao.getInstance();


    public Long save(RegistrationDto profile) {
        return profDao.addProfile(registrationDtoToProfileMapper.mapFrom(profile)).getId();
    }

    public Optional<ProfileGetDto> findById(Long id) {
        if (id == null) return Optional.empty();
        return profDao.getProfile(id).map(profileGetDtoMapper::mapFrom);
    }

    public List<ProfileGetDto> findAll() {

        return profDao.getAllProfiles().stream().map(profileGetDtoMapper::mapFrom).toList();
    }

    public void update(ProfileUpdateDto dto) {

        profDao.getProfile(dto.getId())
                .ifPresent(profile -> {
                            checkEmail(profile.getEmail(), dto.getEmail());
                    profDao.updateProfile(profileUpdateDtoToProfileMapper.mapFrom(dto, profile));
                        }
                );
    }

    private void checkEmail(String email, String newEmail) {
        if (newEmail == null) return;
        Set<String> emails = profDao.getAllEmails();
        if (!Objects.equals(email, newEmail) && emails.contains(newEmail)) {
            throw new DuplicateEmailException();
        }
    }

    public boolean delete(Long id) {
        if(id == null) return false;
        return profDao.deleteProfile(id);
    }
}
