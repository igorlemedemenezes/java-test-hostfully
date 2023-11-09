package hostfully.technical.interview.service;

import hostfully.technical.interview.model.UserAccount;
import hostfully.technical.interview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    protected UserAccount getUserById(Long id){
        return repo.getReferenceById(id);
    }

    protected Boolean isUserAdmin(Long id){
        UserAccount user = repo.getReferenceById(id);
        return user.getIsAdmin();
    }
}
