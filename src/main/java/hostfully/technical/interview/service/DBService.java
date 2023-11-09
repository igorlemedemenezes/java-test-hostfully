package hostfully.technical.interview.service;

import hostfully.technical.interview.model.UserAccount;
import hostfully.technical.interview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private UserRepository userRepository;

    public void instantiateTestDataBase(){

        UserAccount admin = new UserAccount(1L, "Admin", true, null);
        UserAccount user = new UserAccount(2L, "User", false, null);

        userRepository.saveAll(Arrays.asList(admin, user));
    }


}
