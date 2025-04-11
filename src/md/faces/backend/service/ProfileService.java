package md.faces.backend.service;

import md.faces.backend.dao.ProfileDao;
import md.faces.backend.dto.ProfilegetDto;
import md.faces.backend.mapper.ProfileToDtoMapper;
import md.faces.backend.mapper.DtoToProfileMapper;

import java.util.List;
import java.util.Optional;

public class ProfileService {

    private static final ProfileService INSTANCE = new ProfileService();

    private ProfileService() {}

    public static ProfileService getInstance() {
        return INSTANCE;
    }

    private final ProfileToDtoMapper profileGetDtoMapper = ProfileToDtoMapper.getInstance();
    private final ProfileDao profDao = ProfileDao.getInstance();
    private final DtoToProfileMapper dtoToProfileMapper = DtoToProfileMapper.getInstance();


    public Long save(ProfilegetDto profile) {
        return profDao.addProfile(dtoToProfileMapper.from(profile)).getId();
    }

    public Optional<ProfilegetDto> findById(Long id) {
        if (id == null) return Optional.empty();
        return profDao.getProfile(id).map(profileGetDtoMapper::from);
    }

    public List<ProfilegetDto> findAll() {

        return profDao.getAllProfiles().stream().map(profileGetDtoMapper::from).toList();
    }

    public void update(ProfilegetDto profile) {
        profDao.updateProfile(dtoToProfileMapper.from(profile));
    }

    public boolean delete(Long id) {
        if(id == null) return false;
        return profDao.deleteProfile(id);
    }
}
