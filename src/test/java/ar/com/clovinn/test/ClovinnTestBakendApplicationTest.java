package ar.com.clovinn.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ClovinnTestBakendApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ClovinnTestBakendApplicationTest {

    @Test
    public void contextLoads() {
    }
}