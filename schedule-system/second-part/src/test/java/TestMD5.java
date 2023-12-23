import org.junit.Test;
import util.MD5Util;

public class TestMD5 {
    @Test
    public void test(){
        String encrypt = MD5Util.encrypt("123456");
        System.out.println(encrypt);
    }
}
