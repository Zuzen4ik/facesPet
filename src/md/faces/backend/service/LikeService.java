package md.faces.backend.service;

public class LikeService {


    // Singleton
    private static final LikeService INSTANCE = new LikeService();

    private LikeService() {}

    public static LikeService getInstance() {
        return INSTANCE;
    }



    public long getLikesById(Long id) {
        return 19 + id;
    }
}
