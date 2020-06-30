import com.shen.consumer.BootStrap;
import com.shen.rpc.ExampleService;
import com.shen.rpc.consumer.Reference;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootStrap.class)
public class Tests {


    @Reference
    ExampleService exampleService;

    @Test
    public void test(){
        exampleService.info();

    }
}
