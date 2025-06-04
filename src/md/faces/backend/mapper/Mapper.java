package md.faces.backend.mapper;

import md.faces.backend.model.Profile;

public interface Mapper<From, To> {

    To mapFrom(From obj);
    To mapFrom(From obj, To to);
}
