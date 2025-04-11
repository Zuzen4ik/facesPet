package md.faces.backend.mapper;

import md.faces.backend.model.Profile;

public interface Mapper<From, To> {

    To from(From obj);
    To from(From obj, To to);
}
