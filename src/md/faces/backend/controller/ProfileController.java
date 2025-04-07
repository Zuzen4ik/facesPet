package md.faces.backend.controller;

import md.faces.backend.model.Profile;
import md.faces.backend.service.ProfileService;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class ProfileController {

    private final  ProfileService profService;

    public ProfileController(ProfileService profService) {
        this.profService = profService;
    }

    public String save(String request) {
        String[] strings = request.split(",");
        if(strings.length !=4) return "Bad request";

        Profile profile = new Profile();
        profile.setFirstName(strings[0]);
        profile.setLastName(strings[1]);
        profile.setEmail(strings[2]);
        profile.setAboutMe(strings[3]);

        return profService.save(profile).toString();
    }

    public Optional<Profile> findById(Long id) {
        return profService.findById(id);
    }

    public List<Profile> findAll() {
        return profService.findAll();
    }

    public String update(String request) {
        String[] strings = request.split(",");
        if(strings.length != 5) return "Bad request";

        long id;
        try{ id = Long.parseLong(strings[0]);
        } catch (NumberFormatException e) {
            return "Bad request. cannot parse id";}

        Profile profile = new Profile();
        profile.setId(id);
        profile.setFirstName(strings[1]);
        profile.setLastName(strings[2]);
        profile.setEmail(strings[3]);
        profile.setAboutMe(strings[4]);

        profService.update(profile);

        return "Profile " + profile.getId() + " updated";
    }


    public String delete(String request) {
        String[] strings = request.split(",");
        if(strings.length != 1) return "Bad request";

        long id;
        try{ id = Long.parseLong(strings[0]);
        } catch (NumberFormatException e) {
            return "Bad request. cannot parse id";
        }

        boolean deleted = profService.delete(id);
        if (!deleted) return "Profile " + id + " not found!";

        return "Profile " + id + " deleted";
    }
}
