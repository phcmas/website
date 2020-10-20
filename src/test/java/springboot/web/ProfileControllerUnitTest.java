package springboot.web;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileControllerUnitTest {

    @Test
    public void loadRealProfile() {
        String expectedProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real_-db");

        ProfileController controller = new ProfileController(env);
        String profile = controller.profile();
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void loadFirstIfRealProfileNonExists() {
        String expectedProfile = "oauth";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        String profile = controller.profile();
        assertThat(profile).isEqualTo(expectedProfile);
    }

    @Test
    public void loadDefaultIfActiveProfileNonExists() {
        String expectedProfile = "default";
        MockEnvironment env = new MockEnvironment();
        ProfileController controller = new ProfileController(env);
        String profile = controller.profile();
        assertThat(profile).isEqualTo(expectedProfile);
    }

}
